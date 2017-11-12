package edu.rit.goal.sdg.interpreter.stmt;

public class Pkg extends BaseStmt {

  public String x;

  public Pkg(final String qualifiedName) {
    super();
    this.x = qualifiedName;
  }

  @Override
  public String toString() {
    return "pkg " + x;
  }


}
