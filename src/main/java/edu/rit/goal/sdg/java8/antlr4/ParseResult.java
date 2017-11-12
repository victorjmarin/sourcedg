package edu.rit.goal.sdg.java8.antlr4;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class ParseResult {

  private final String value;
  private final Stmt stmt;

  public ParseResult(final String value, final Stmt stmt) {
    this.value = value;
    this.stmt = stmt;
  }

  public ParseResult(final String value) {
    this.value = value;
    stmt = null;
  }

  public ParseResult(final Stmt stmt) {
    value = null;
    this.stmt = stmt;
  }

  public String getValue() {
    return value;
  }

  public Stmt getStmt() {
    return stmt;
  }

  @Override
  public String toString() {
    if (value != null)
      return value;
    return stmt.toString();
  }

}
