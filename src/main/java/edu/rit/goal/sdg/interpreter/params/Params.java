package edu.rit.goal.sdg.interpreter.params;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class Params extends Stmt implements Param {

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

}
