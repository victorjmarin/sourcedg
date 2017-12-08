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
    String toParse = toString();
    try {
      try {
        ast = JavaParser.parseExpression(toParse);
      } catch (final Exception ex) {
        toParse += ";";
        ast = JavaParser.parseStatement(toParse);
      }
    } catch (final Exception ex2) {
      logger.warning("[WARN] Could not build ast for assignment \n\t" + toParse);
    }
  }

  @Override
  public String toString() {
    return x + op + e;
  }

}
