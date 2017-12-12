package edu.rit.goal.sourcedg.builder;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.EdgeType;
import edu.rit.goal.sourcedg.graph.Vertex;

public class CFGBuilder {

  // Mapping from methods to CFGs
  private final HashMap<Vertex, CFG> m;

  // CFG under construction
  private CFG cfg;

  // Exit vertex used to break flow (return, break)
  public static final Vertex EXIT = new Vertex("EXIT");

  public CFGBuilder() {
    m = new HashMap<>();
    cfg = new CFG();
  }

  public ControlFlow methodDeclaration(final Vertex v, final List<ControlFlow> params,
      final ControlFlow bodyFlow) {
    final ControlFlow paramFlow = seq(params);
    connect(connect(v, paramFlow), bodyFlow);
    return new ControlFlow(v, bodyFlow.getOut());
  }

  public ControlFlow whileStmt(final Vertex v, final ControlFlow bodyFlow) {
    connect(v, bodyFlow);
    connect(bodyFlow, v);
    return new ControlFlow(v, v);
  }

  public ControlFlow doStmt(final Vertex v, final ControlFlow bodyFlow) {
    connect(bodyFlow, v);
    connect(v, bodyFlow);
    return new ControlFlow(bodyFlow.getIn(), v);
  }

  public ControlFlow forStmt(final Vertex v, final List<ControlFlow> init,
      final List<ControlFlow> update, final ControlFlow bodyFlow) {
    final ControlFlow initFlow = seq(init);
    final ControlFlow updateFlow = seq(update);
    connect(initFlow, v);
    connect(v, bodyFlow);
    if (updateFlow != null) {
      connect(bodyFlow, updateFlow);
      connect(updateFlow, v);
    } else
      connect(bodyFlow, v);
    return new ControlFlow(initFlow.getIn(), v);
  }

  public ControlFlow ifStmt(final Vertex v, final ControlFlow thenFlow,
      final ControlFlow elseFlow) {
    final Set<Vertex> out = new HashSet<>();
    connect(v, thenFlow);
    out.addAll(thenFlow.getOut());
    if (elseFlow == null)
      out.add(v);
    else {
      connect(v, elseFlow);
      out.addAll(elseFlow.getOut());
    }
    return new ControlFlow(v, out);
  }

  public ControlFlow seq(final List<ControlFlow> seq) {
    ControlFlow result = null;
    for (int i = 0; i < seq.size(); i++) {
      if (i == 0)
        result = seq.get(0);
      else
        result = connect(result, seq.get(i));
    }
    return result;
  }

  private ControlFlow connect(final ControlFlow f, final Vertex v) {
    final ControlFlow fv = new ControlFlow(v, v);
    return connect(f, fv);
  }

  private ControlFlow connect(final ControlFlow f1, final ControlFlow f2) {
    for (final Vertex o : f1.getOut()) {
      if (o == EXIT)
        continue;
      cfg.addVertex(o);
      for (final Vertex i : f2.getIn()) {
        cfg.addVertex(i);
        cfg.addEdge(o, i, new Edge(o, i, EdgeType.CTRL_TRUE));
      }
    }
    return new ControlFlow(f1.getIn(), f2.getOut());
  }

  private ControlFlow connect(final Vertex v, final ControlFlow f) {
    if (v != EXIT) {
      cfg.addVertex(v);
      for (final Vertex i : f.getIn()) {
        cfg.addVertex(i);
        cfg.addEdge(v, i, new Edge(v, i, EdgeType.CTRL_TRUE));
      }
    }
    return new ControlFlow(v, f.getOut());
  }

  public void put(final Vertex k) {
    m.put(k, cfg);
    cfg = new CFG();
  }

  public Collection<CFG> getCfgs() {
    return m.values();
  }

}
