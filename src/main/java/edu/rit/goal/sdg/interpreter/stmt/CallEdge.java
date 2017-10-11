package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.Vertex;

public class CallEdge extends Stmt {

  public Vertex v;
  public String x;

  public CallEdge(final Vertex v, final String x) {
    super();
    this.v = v;
    this.x = x;
  }

  @Override
  public String toString() {
    return "calledge " + v + " " + x;
  }

}
