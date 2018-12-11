package sourcedg.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graphs;

import sourcedg.graph.CFG;
import sourcedg.graph.Edge;
import sourcedg.graph.EdgeType;
import sourcedg.graph.Vertex;
import sourcedg.graph.VertexType;

public class CFGBuilder {

	// Adds unreachable components to the CFG
	private static final boolean WITH_UNREACHABLE_COMPONENTS = true;

	// Mapping from methods to CFGs
	private final HashMap<Vertex, CFG> m;

	// CFG under construction
	private CFG cfg;

	// Exit vertex used to break flow (return, break, continue)
	public static final Vertex EXIT = new Vertex("EXIT");

	// Used to add continue edges.
	private final List<Runnable> deferred;

	public CFGBuilder() {
		m = new HashMap<>();
		cfg = new CFG();
		deferred = new ArrayList<>();
	}

	public ControlFlow methodDeclaration(final Vertex v, final List<ControlFlow> params, final ControlFlow bodyFlow) {
		final ControlFlow paramFlow = seq(params);
		final ControlFlow result = connect(connect(v, paramFlow), bodyFlow);
		return new ControlFlow(v, result.getOut());
	}

	public ControlFlow whileStmt(final Vertex v, final ControlFlow bodyFlow) {
		final ControlFlow conn1 = connect(v, bodyFlow);
		final ControlFlow conn2 = connect(conn1, v);
		final ControlFlow result = new ControlFlow(v, v);
		for (final Vertex bv : conn2.getBreaks())
			result.getOut().add(bv);
		return result;
	}

	public ControlFlow doStmt(final Vertex v, final ControlFlow bodyFlow) {
		if (bodyFlow != null && bodyFlow.getOut().size() == 1 && bodyFlow.getOut().contains(EXIT)) {
			final ControlFlow result = new ControlFlow(bodyFlow.getIn(), new HashSet<>());
			for (final Vertex bv : bodyFlow.getBreaks())
				result.getOut().add(bv);
			if (WITH_UNREACHABLE_COMPONENTS)
				addVertex(v);
			return result;
		} else {
			final ControlFlow conn1 = connect(bodyFlow, v);
			final ControlFlow conn2 = connect(v, conn1);
			final ControlFlow result = new ControlFlow(conn1.getIn(), conn2.getOut());
			for (final Vertex bv : conn2.getBreaks())
				result.getOut().add(bv);
			return result;
		}
	}

	public ControlFlow forStmt(final Vertex v, final List<ControlFlow> init, final List<ControlFlow> update,
			final ControlFlow bodyFlow) {
		final ControlFlow initFlow = seq(init);
		final ControlFlow updateFlow = seq(update);
		final ControlFlow conn1 = connect(initFlow, v);
		final ControlFlow conn2 = connect(conn1, bodyFlow);
		final ControlFlow conn3 = connect(conn2, updateFlow);
		final ControlFlow result = connect(conn3, v);
		for (final Vertex bv : result.getBreaks())
			result.getOut().add(bv);
		return result;
	}

	public ControlFlow foreachStmt(final Vertex v, final ControlFlow bodyFlow) {
		final ControlFlow conn1 = connect(v, bodyFlow);
		final ControlFlow result = connect(conn1, v);
		for (final Vertex bv : result.getBreaks())
			result.getOut().add(bv);
		return result;
	}

	public ControlFlow ifStmt(final Vertex v, final ControlFlow thenFlow, final ControlFlow elseFlow) {
		final Set<Vertex> out = new HashSet<>();
		final ControlFlow conn1 = connect(v, thenFlow);
		final ControlFlow conn2 = connect(v, elseFlow);
		out.addAll(conn1.getOut());
		out.addAll(conn2.getOut());
		final ControlFlow result = new ControlFlow(v, out);
		result.getBreaks().addAll(conn1.getBreaks());
		result.getBreaks().addAll(conn2.getBreaks());
		return result;
	}

	public ControlFlow continueStmt(final Vertex v, final Vertex loop) {
		deferred.add(() -> {
			addEdge(v, loop);
		});
		addVertex(v);
		return new ControlFlow(v, CFGBuilder.EXIT);
	}

	public ControlFlow tryStmt(final Vertex v, final ControlFlow tryBlkFlow, final ControlFlow catchFlow,
			final ControlFlow outFlow) {
		final ControlFlow conn1 = connect(v, tryBlkFlow);
		final ControlFlow conn2 = connect(conn1, catchFlow);
		final ControlFlow conn3 = connect(conn2, outFlow);
		return new ControlFlow(conn1.getIn(), conn3.getOut());
	}

	public ControlFlow catchClause(final Vertex v, final ControlFlow bodyFlow) {
		return connect(v, bodyFlow);
	}

	public ControlFlow finallyBlock(final Vertex v, final ControlFlow finallyFlow) {
		return connect(v, finallyFlow);
	}

	public ControlFlow seq(final ControlFlow... seq) {
		return seq(Arrays.asList(seq));
	}

	public ControlFlow seq(final List<ControlFlow> seq) {
		ControlFlow result = null;
		for (int i = 0; i < seq.size(); i++) {
			if (i == 0)
				result = seq.get(0);
			else {
				final ControlFlow next = seq.get(i);
				// If we are leaving the sequence (return, break, continue), the subsequent
				// nodes will be unreachable (unless it is the final exit node in the CFG).
				if (result != null && result.getOut().size() == 1 && result.getOut().contains(EXIT)) {
					// CFG's exit node.
					if (VertexType.EXIT.equals(next.getIn().getType()))
						result = connect(result, next);
					else {
						handleFlowBreak(seq, next, i);
						break;
					}
				} else
					result = connect(result, next);
			}
		}
		return result;
	}

	private void handleFlowBreak(final List<ControlFlow> seq, final ControlFlow next, final int i) {
		if (WITH_UNREACHABLE_COMPONENTS) {
			PDGBuilder.LOGGER.warning("Unreachable code detected.");
			// Add unreachable components to the graph.
			ControlFlow current = next;
			if (i + 1 < seq.size()) {
				for (int j = i + 1; j < seq.size(); j++)
					current = connect(current, seq.get(j), true);
			} else
				Graphs.addAllVertices(cfg, current.getOut());
		}
	}

	private ControlFlow connect(final ControlFlow f, final Vertex v) {
		final ControlFlow fv = new ControlFlow(v, v);
		return connect(f, fv);
	}

	private ControlFlow connect(final ControlFlow f1, final ControlFlow f2) {
		return connect(f1, f2, false);
	}

	private ControlFlow connect(final Vertex v, final ControlFlow f) {
		return connect(new ControlFlow(v, v), f);
	}

	private ControlFlow connect(final ControlFlow f1, final ControlFlow f2, final boolean withUnreachableComponents) {
		if (f1 == null)
			return f2;
		if (f2 == null)
			return f1;
		for (final Vertex o : f1.getOut()) {
			if (o == EXIT) {
				// Add unreachable components
				if (withUnreachableComponents)
					addVertex(f2.getIn());
				continue;
			}
			addVertex(o);
			// Remove out breaks whose edge is created. In breaks have to be propagated, so
			// they are not
			// removed.
			if (VertexType.BREAK.equals(o.getType()))
				f1.getBreaks().remove(o);
			final Vertex i = f2.getIn();
			addVertex(i);
			addEdge(o, i);
		}
		final ControlFlow result = new ControlFlow(f1.getIn(), f2.getOut());
		result.getBreaks().addAll(f1.getBreaks());
		result.getBreaks().addAll(f2.getBreaks());
		return result;
	}

	private void deferred() {
		for (final Runnable r : deferred)
			r.run();
		deferred.clear();
	}

	public void addVertex(final Vertex v) {
		cfg.addVertex(v);
	}

	public void addEdge(final Vertex source, final Vertex target) {
		cfg.addEdge(source, target, new Edge(source, target, EdgeType.CTRL_TRUE));
	}

	public void put(final Vertex k) {
		deferred();
		m.put(k, cfg);
		cfg = new CFG();
	}

	public List<CFG> getCfgs() {
		return new ArrayList<>(m.values());
	}

}
