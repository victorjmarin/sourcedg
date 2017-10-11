package edu.rit.goal.sdg.interpreter.stmt;

import org.antlr.v4.runtime.tree.ParseTree;

public class Str extends Stmt implements Expr, edu.rit.goal.sdg.interpreter.params.Param {

  public String value;
  public ParseTree pt;

  public Str(final String value) {
    super();
    this.value = value;
  }

  public Str(final ParseTree pt) {
    super();
    value = pt.getText();
    this.pt = pt;
  }

  @Override
  public String toString() {
    return value;
  }

}
