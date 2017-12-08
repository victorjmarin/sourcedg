package edu.rit.goal.sdg.interpreter.stmt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cls extends BaseStmt {

  public List<String> mods;
  public String x;
  public Stmt s;

  public Cls(final String x, final Stmt body) {
    this.x = x;
    s = body;
    mods = new ArrayList<>();
  }

  @Override
  public String toString() {
    return mods.stream().collect(Collectors.joining(" ")) + " class " + x + " {\n"
        + s.toString(indentationLevel + 1) + "\n" + indentation + "}";
  }

}
