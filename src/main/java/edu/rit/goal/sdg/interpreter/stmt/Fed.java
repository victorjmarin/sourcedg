package edu.rit.goal.sdg.interpreter.stmt;

public class Fed extends BaseStmt {

  public String x;

  public Fed(final String x) {
    this.x = x;
  }

  @Override
  public String toString() {
    return "fed " + x;
  }

}
