package edu.rit.goal.sdg.interpreter.stmt;

public class For extends BaseStmt {

  public Stmt si, sc, su;
  public Stmt s;

  public For(final Stmt si, final Stmt sc, final Stmt su, final Stmt s) {
    super();
    this.si = si;
    this.sc = sc;
    this.su = su;
    this.s = s;
  }

  @Override
  public String toString() {
    return "for (" + si + ";" + sc + ";" + su + ") {\n" + s.toString(indentationLevel + 1) + "\n"
        + indentation + "}";
  }

}
