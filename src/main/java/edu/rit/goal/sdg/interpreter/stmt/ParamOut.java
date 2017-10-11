package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.Vertex;

public class ParamOut extends Stmt {

  public String x;
  public Vertex v;

  public ParamOut(final String x, final Vertex v) {
    super();
    this.x = x;
    this.v = v;
  }

  @Override
  public String toString() {
    return "paramout " + x + " " + v;
  }

}
