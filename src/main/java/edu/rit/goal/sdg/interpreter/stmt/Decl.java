package edu.rit.goal.sdg.interpreter.stmt;

public class Decl extends BaseStmt {

  public String t;
  public String x;

  public Decl(final String t, final String x) {
    this.t = t;
    this.x = x;
  }

  @Override
  public String toString() {
    return t + " " + x;
  }

}
