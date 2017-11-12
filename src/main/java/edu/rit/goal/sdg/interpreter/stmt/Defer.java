package edu.rit.goal.sdg.interpreter.stmt;

public class Defer extends BaseStmt {

  public BaseStmt s;

  public Defer(final BaseStmt s) {
    super();
    this.s = s;
  }

  @Override
  public String toString() {
    return "defer " + s;
  }

}
