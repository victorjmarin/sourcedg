package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Set;

public abstract class BaseStmt implements Stmt {

  protected int indentationLevel;
  protected String indentation;

  private String def;
  private Set<String> uses;

  public String getDef() {
    return def;
  }

  public Set<String> getUses() {
    return uses;
  }

  public void setDef(final String def) {
    this.def = def;
  }

  public void setUses(final Set<String> uses) {
    this.uses = uses;
  }

  @Override
  public String toString(final int indentationLevel) {
    this.indentationLevel = indentationLevel;
    indentation = "";
    for (int i = 0; i < indentationLevel; i++) {
      indentation += "    ";
    }
    return indentation + toString();
  }



}
