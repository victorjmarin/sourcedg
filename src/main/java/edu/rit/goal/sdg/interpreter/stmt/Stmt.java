package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Set;

public interface Stmt {

  String getDef();

  Set<String> getUses();

  void setDef(final String def);

  void setUses(final Set<String> uses);

  String toString(final int indentationLevel);

}
