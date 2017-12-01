package edu.rit.goal.sdg.interpreter.params;

import edu.rit.goal.sdg.interpreter.stmt.BaseStmt;

public class Params extends BaseStmt implements Param {

  public String x;
  public Param p;

  public Params(final String x, final Param p) {
    super();
    this.x = x;
    this.p = p;
  }

  @Override
  public String toString() {
    return x + "," + p;
  }

  @Override
  public int size() {
    return 1 + p.size();
  }

}
