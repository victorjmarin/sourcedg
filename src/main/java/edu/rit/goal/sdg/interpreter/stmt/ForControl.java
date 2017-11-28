package edu.rit.goal.sdg.interpreter.stmt;

public class ForControl extends BaseStmt {

  public Stmt si, sc, su;

  public ForControl(final Stmt si, final Stmt sc, final Stmt su) {
    super();
    this.si = si;
    this.sc = sc;
    this.su = su;
  }

}
