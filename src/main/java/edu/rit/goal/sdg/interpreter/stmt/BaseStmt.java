package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Set;
import com.github.javaparser.ast.Node;

public abstract class BaseStmt implements Stmt {

  protected int indentationLevel;
  protected String indentation;
  public Node ast;

  private String def;
  private Set<String> uses;

  @Override
  public String getDef() {
    return def;
  }

  @Override
  public Set<String> getUses() {
    return uses;
  }

  @Override
  public void setDef(final String def) {
    this.def = def;
  }

  @Override
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
