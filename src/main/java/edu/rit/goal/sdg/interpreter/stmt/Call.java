package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.params.Param;

public class Call extends Stmt implements Expr {

  public String x;
  public Param p;

  public Call(final String x, final Param p) {
    super();
    this.x = x;
    this.p = p;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    final String methodName = Translator.removeClassName(x);
    sb.append(methodName);
    sb.append("(");
    sb.append(p);
    sb.append(")");
    return sb.toString();
  }

}
