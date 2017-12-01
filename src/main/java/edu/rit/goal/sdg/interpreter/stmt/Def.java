package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;

public class Def extends BaseStmt {

  // Flag indicating if the function returns other than void
  public boolean b;
  public String x;
  public Param p;
  public Stmt s;
  // Name of the method without the class
  public String methodName;
  public Integer startLine;
  public Integer endLine;

  public Def(final boolean b, final String x, final Stmt s) {
    super();
    this.b = b;
    this.x = x;
    p = new EmptyParam();
    this.s = s;
    methodName = Translator.removeClassName(x);
  }

  public Def(final boolean b, final String x, final Param p, final Stmt s) {
    super();
    this.b = b;
    this.x = x;
    this.p = p;
    this.s = s;
    methodName = Translator.removeClassName(x);
  }

  @Override
  public String toString() {
    return "def " + b + " " + x + " (" + p + "): \n" + s.toString(indentationLevel + 1);
  }

}
