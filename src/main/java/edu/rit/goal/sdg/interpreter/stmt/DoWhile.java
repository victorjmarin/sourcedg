package edu.rit.goal.sdg.interpreter.stmt;

public class DoWhile extends Stmt {

  public Stmt s;
  public Expr e;

  public DoWhile(final Stmt s, final Expr e) {
    super();
    this.s = s;
    this.e = e;
  }

  @Override
  public String toString() {
    return "do {" + s + "} while (" + e + ")";
  }

}
