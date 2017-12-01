package edu.rit.goal.sdg.interpreter.stmt;

import com.github.javaparser.JavaParser;

public class Assign extends BaseStmt {

  public String x;
  public String op;
  public Expr e;

  public Assign(final String x, final Expr e) {
    this(x, "=", e);
  }

  public Assign(final String x, final String op, final Expr e) {
    super();
    this.x = x;
    this.op = op;
    this.e = e;
    ast = JavaParser.parseExpression(toString());
  }

  @Override
  public String toString() {
    return x + op + e;
  }

}
