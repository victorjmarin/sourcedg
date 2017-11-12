package edu.rit.goal.sdg.interpreter.stmt;

public abstract class IncrDecrOp extends BaseStmt {

  public String x;
  public String op;

  public IncrDecrOp(final String x, final String op) {
    super();
    if (!("++".equals(op) || "--".equals(op))) {
      throw new IllegalArgumentException("The operator should be either '++' or '--'");
    }
    this.x = x;
    this.op = op;
  }

}
