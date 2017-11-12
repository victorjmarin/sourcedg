package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.Vertex;

public class Ve extends BaseStmt {

  public Vertex v;

  public Ve(final Vertex v) {
    super();
    this.v = v;
  }

  @Override
  public String toString() {
    return "ve " + v;
  }

}
