package sourcedg.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DirectedPseudograph;

import com.github.javaparser.ast.expr.MethodCallExpr;

import sourcedg.analysis.PDGSlicer;

public class PDG extends DirectedPseudograph<Vertex, Edge> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4208713030783810103L;

	private static Set<VertexType> dontCareTypes;

	static {
		dontCareTypes = new HashSet<>();
		dontCareTypes.add(VertexType.ACTUAL_IN);
		dontCareTypes.add(VertexType.ACTUAL_OUT);
		dontCareTypes.add(VertexType.ARRAY_IDX);
		dontCareTypes.add(VertexType.CLASS);
		dontCareTypes.add(VertexType.FORMAL_IN);
		dontCareTypes.add(VertexType.FORMAL_OUT);
		dontCareTypes.add(VertexType.ENTRY);
		dontCareTypes.add(VertexType.INIT);
		dontCareTypes.add(VertexType.TRY);
		dontCareTypes.add(VertexType.CATCH);
		dontCareTypes.add(VertexType.FINALLY);
	}

	private String pathToProgram;

	public PDG() {
		super(Edge.class);
	}

	// Merges "overlapping" DATA and OUTPUT edges.
	public DefaultDirectedGraph<Vertex, Edge> asDefaultDirectedGraph() {
		DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
		Graphs.addAllVertices(result, vertexSet());
		Graphs.addAllEdges(result, this, edgeSet());
		return result;
	}

	public DefaultDirectedGraph<Vertex, Edge> getCDDG() {
		DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
		Set<Edge> E = edgeSet().stream().filter(e -> e.isControl() || EdgeType.DATA.equals(e.getType()))
				.collect(Collectors.toSet());
		Graphs.addAllVertices(result, vertexSet());
		Graphs.addAllEdges(result, this, E);
		return result;
	}

	public DefaultDirectedGraph<Vertex, Edge> getCDG() {
		DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
		Set<Edge> E = edgeSet().stream().filter(e -> e.isControl()).collect(Collectors.toSet());
		Graphs.addAllVertices(result, vertexSet());
		Graphs.addAllEdges(result, this, E);
		return result;
	}

	public DefaultDirectedGraph<Vertex, Edge> getDDG() {
		DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
		Set<Edge> E = edgeSet().stream().filter(e -> EdgeType.DATA.equals(e.getType())).collect(Collectors.toSet());
		Graphs.addAllVertices(result, vertexSet());
		Graphs.addAllEdges(result, this, E);
		return result;
	}

	public DefaultDirectedGraph<Vertex, Edge> getODG() {
		DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
		Set<Edge> E = edgeSet().stream().filter(e -> EdgeType.OUTPUT.equals(e.getType())).collect(Collectors.toSet());
		Graphs.addAllVertices(result, vertexSet());
		Graphs.addAllEdges(result, this, E);
		return result;
	}

	public DefaultDirectedGraph<Vertex, Edge> getDDGWithOutputEdges() {
		DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
		Set<Edge> E = edgeSet().stream()
				.filter(e -> EdgeType.DATA.equals(e.getType()) || EdgeType.OUTPUT.equals(e.getType()))
				.collect(Collectors.toSet());
		Graphs.addAllVertices(result, vertexSet());
		Graphs.addAllEdges(result, this, E);
		return result;
	}

	public int nestingLevel(Vertex entry, Vertex sink, boolean includeTry) {
		DijkstraShortestPath<Vertex, Edge> sp = new DijkstraShortestPath<>(getCDG());
		GraphPath<Vertex, Edge> path = sp.getPath(entry, sink);
		List<Vertex> vtxLst = path.getVertexList();
		long result = vtxLst.stream().filter(v -> v.getType().equals(VertexType.CTRL)).count();
		if (!includeTry) {
			int tryNodes = (int) path.getVertexList().stream().filter(v -> VertexType.TRY.equals(v.getType())).count();
			result -= tryNodes;
			result = Math.max(0, result);
		}
		return (int) result;
	}

	public Set<Vertex> removeUnusedDefinitions() {
		Set<Vertex> unused = new HashSet<>();
		for (Vertex v : vertexSet()) {
			if (!VertexType.ASSIGN.equals(v.getType()))
				continue;
			int deps = (int) outgoingEdgesOf(v).stream().filter(e -> EdgeType.DATA.equals(e.getType())).count();
			if (deps == 0) {
				// Only remove nodes that are not making a call. Declaring variables that are
				// not used, like String stmp = scan.nextLine();, is rather common yet the stmt
				// is of interest.
				Optional<MethodCallExpr> call = v.getAst().findFirst(MethodCallExpr.class);
				if (!call.isPresent())
					unused.add(v);
			}
		}
		removeAllVertices(unused);
		return unused;
	}

	public void collapseNodes(VertexType type) {
		if (!(type.equals(VertexType.ACTUAL_IN) || type.equals(VertexType.ACTUAL_OUT)
				|| type.equals(VertexType.ARRAY_IDX)))
			throw new IllegalArgumentException();

		Set<Edge> removeEdges = new HashSet<>();
		Set<Vertex> removeNodes = new HashSet<>();
		for (Vertex v : this.vertexSet()) {
			if (v.getType().equals(type)) {
				Edge edge = this.incomingEdgesOf(v).stream().filter(e -> e.getType().equals(EdgeType.CTRL_TRUE))
						.findFirst().get();
				Vertex parent = edge.getSource();

				Set<Edge> in = new HashSet<>(this.incomingEdgesOf(v));
				Set<Edge> out = this.outgoingEdgesOf(v);

				removeNodes.add(v);
				removeEdges.addAll(in);
				removeEdges.addAll(out);

				in.remove(edge);
				for (Edge e : in) {
					Edge newEdge = new Edge(e.getSource(), parent, e.getType());
					this.addEdge(e.getSource(), parent, newEdge);
				}

				for (Edge e : out) {
					Edge newEdge = new Edge(parent, e.getTarget(), e.getType());
					this.addEdge(parent, e.getTarget(), newEdge);
				}
				parent.getSubtypes().addAll(v.getSubtypes());

			}
		}
		this.removeAllEdges(removeEdges);
		this.removeAllVertices(removeNodes);

		// Update IDs of nodes.
		List<Vertex> sortedNodes = new ArrayList<>(this.vertexSet());
		Collections.sort(sortedNodes, Comparator.comparing(v -> v.getId()));
		int id = 0;
		for (Vertex v : sortedNodes) {
			v.setId(id++);
		}
	}

	public Vertex actualOut(final Vertex v) {
		final Set<Edge> edges = outgoingEdgesOf(v);
		Vertex successor = null;
		for (final Edge e : edges) {
			successor = e.getTarget();
			if (VertexType.ACTUAL_OUT.equals(successor.getType())) {
				return successor;
			}
		}
		return null;
	}

	public Vertex getVertexWithId(final int id) {
		final Optional<Vertex> v = vertexSet().stream().filter(u -> u.getId() == id).findFirst();
		if (v.isPresent())
			return v.get();
		return null;
	}

	public static boolean isNodeOfInterest(Vertex v) {
		return !dontCareTypes.contains(v.getType());
	}

	public Set<Vertex> nodesOfInterest() {
		return vertexSet().stream().filter(v -> !dontCareTypes.contains(v.getType())).collect(Collectors.toSet());
	}

	public Set<Edge> edgesOfInterestOf(Vertex v) {
		Set<Edge> result = new HashSet<>();
		Set<Edge> E = edgesOf(v);
		for (Edge e : E) {
			Vertex src = getEdgeSource(e);
			Vertex tar = getEdgeTarget(e);
			if (!dontCareTypes.contains(src.getType()) && !dontCareTypes.contains(tar.getType()))
				result.add(e);
		}
		return result;
	}

	public Vertex getEntry() {
		return vertexSet().stream().filter(n -> VertexType.ENTRY.equals(n.getType())).findFirst().get();
	}

	public Set<Vertex> backwardSlice(final Set<Vertex> S) {
		return PDGSlicer.backward(this, S);
	}

	public Set<Vertex> forwardSlice(final Set<Vertex> S) {
		return PDGSlicer.forward(this, S);
	}

	public String getPathToProgram() {
		return pathToProgram;
	}

	public void setPathToProgram(String pathToProgram) {
		this.pathToProgram = pathToProgram;
	}

}
