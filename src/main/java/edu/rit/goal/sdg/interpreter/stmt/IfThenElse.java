package edu.rit.goal.sdg.interpreter.stmt;

public class IfThenElse extends BaseStmt {

  public Expr e;
  public Stmt s1;
  public Stmt s2;

  public IfThenElse(final Expr e, final Stmt s1, final Stmt s2) {
    super();
    this.e = e;
    this.s1 = s1;
    this.s2 = s2;
  }

  @Override
  public String toString() {
    return "if (" + e + "):\n" + s1.toString(indentationLevel + 1) + "\n" + indentation + "else\n"
        + s2.toString(indentationLevel + 1);
  }

}
