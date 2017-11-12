package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.VertexType;

public class Param extends BaseStmt {

  public String x;
  public VertexType t;
  public edu.rit.goal.sdg.interpreter.params.Param p;

  public Param(final VertexType t, final edu.rit.goal.sdg.interpreter.params.Param p) {
    this(null, t, p);
  }

  public Param(final String x, final VertexType t,
      final edu.rit.goal.sdg.interpreter.params.Param p) {
    super();
    final boolean suitableVertexType =
        t.equals(VertexType.FORMAL_IN) || t.equals(VertexType.ACTUAL_IN)
            || t.equals(VertexType.FORMAL_OUT) || t.equals(VertexType.ACTUAL_OUT);
    if (!suitableVertexType)
      throw new IllegalArgumentException("VertexType " + t + " is not valid.");
    this.x = x;
    this.t = t;
    this.p = p;
  }

  @Override
  public String toString() {
    return "param " + x + " " + t + " " + p;
  }

}
