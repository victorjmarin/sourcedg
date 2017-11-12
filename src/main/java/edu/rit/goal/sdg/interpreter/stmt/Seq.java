package edu.rit.goal.sdg.interpreter.stmt;

public class Seq extends BaseStmt {

  public Stmt s1;
  public Stmt s2;

  public Seq() {}

  public Seq(final Stmt s1, final Stmt s2) {
    super();
    this.s1 = s1;
    this.s2 = s2;
  }

  public void add(final Stmt s) {
    if (s1 == null)
      s1 = s;
    else if (s2 == null)
      s2 = s;
    else if (s2 instanceof Seq) {
      ((Seq) s2).add(s);
    } else {
      s2 = new Seq(s2, s);
    }
  }

  @Override
  public String toString(final int indentationLevel) {
    return s1.toString(indentationLevel) + ";\n" + s2.toString(indentationLevel);
  }

  @Override
  public String toString() {
    return s1 + ";" + s2;
  }

}
