package edu.rit.goal.sdg.interpreter.stmt;

public class Return extends Stmt {

  public String e;

  public Return() {
    e = null;
  }

  public Return(final String e) {
    this.e = e;
  }

  @Override
  public String toString() {
    String result = "return";
    if (e != null)
      result += " " + e;
    return result;
  }

}
