package edu.rit.goal.sdg.interpreter.stmt;

public class CUnit extends BaseStmt {

  public String x;
  public Stmt s;

  public CUnit(final Stmt typeDeclarations) {
    this.s = typeDeclarations;
  }

  @Override
  public String toString() {
    return "cunit " + x + " {\n" + s.toString(1) + "\n}";
  }

}
