package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.EdgeType;
import edu.rit.goal.sdg.graph.Vertex;

public class EdgeStmt extends BaseStmt {

  public Vertex v;
  public EdgeType t;
  public Stmt s;

  public EdgeStmt(final EdgeType t, final Vertex v, final Stmt s) {
    super();
    this.v = v;
    this.t = t;
    this.s = s;
  }

  @Override
  public String toString() {
    return "edge " + t + " " + v + " " + s;
  }

}
