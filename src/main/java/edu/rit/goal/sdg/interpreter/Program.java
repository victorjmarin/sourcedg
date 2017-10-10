package edu.rit.goal.sdg.interpreter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class Program {

  SysDepGraph sdg;
  Set<Vertex> Vc;
  Map<String, LinkedHashSet<Vertex>> P;
  // Temporal CFG
  DefaultDirectedGraph<Vertex, Edge> cfg;
  // Mapping from method name to CFG
  Map<String, DirectedGraph<Vertex, Edge>> F;
  List<Stmt> sd;
  Stmt s;

  public Program(final Stmt s) {
    sdg = new SysDepGraph();
    Vc = new HashSet<>();
    P = new HashMap<>();
    cfg = new DefaultDirectedGraph<>(Edge.class);
    F = new HashMap<>();
    sd = new LinkedList<>();
    this.s = s;
  }

  public Program(final Program p) {
    sdg = p.sdg;
    Vc = p.Vc;
    P = p.P;
    cfg = p.cfg;
    F = p.F;
    sd = p.sd;
    s = p.s;
  }

  public Program cloneWithStmt(final Stmt s) {
    final Program result = new Program(this);
    result.s = s;
    return result;
  }

  public DefaultDirectedGraph<Vertex, Edge> clonedCfg() {
    final DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
    for (final Vertex v : cfg.vertexSet()) {
      result.addVertex(v);
    }
    for (final Edge e : cfg.edgeSet()) {
      result.addEdge(e.getSource(), e.getTarget(),
          new Edge(e.getSource(), e.getTarget(), e.getType()));
    }
    return result;
  }

  public SysDepGraph getSdg() {
    return sdg;
  }

}
