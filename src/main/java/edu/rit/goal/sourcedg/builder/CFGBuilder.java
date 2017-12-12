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

  public CFGBuilder() {
    m = new HashMap<>();
    cfg = new CFG();
  }

  public ControlFlow methodDeclaration(final Vertex v, final List<ControlFlow> params, final ControlFlow bodyFlow) {
    cfg.addVertex(v);
    final ControlFlow paramFlow = seq(params);
    connect(connect(v, paramFlow), bodyFlow);
    return new ControlFlow(v, bodyFlow.getOut());
  }

  public ControlFlow ifStmt(final Vertex v, final ControlFlow thenFlow, final ControlFlow elseFlow) {
    final Set<Vertex> out = new HashSet<>();
    cfg.addVertex(v);
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

  private ControlFlow connect(final ControlFlow f1, final ControlFlow f2) {
    for (final Vertex o : f1.getOut()) {
      for (final Vertex i : f2.getIn()) {
        cfg.addVertex(o);
        cfg.addVertex(i);
        cfg.addEdge(o, i, new Edge(o, i, EdgeType.CTRL_TRUE));
      }
    }
    return new ControlFlow(f1.getIn(), f2.getOut());
  }

  private ControlFlow connect(final Vertex v, final ControlFlow f) {
    for (final Vertex i : f.getIn()) {
      cfg.addVertex(i);
      cfg.addEdge(v, i, new Edge(v, i, EdgeType.CTRL_TRUE));
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
