package edu.rit.goal.sdg.interpreter.stmt;

public class Import extends BaseStmt {
  
  public String mod;
  public String x;

  public Import(final String qualifiedName) {
    this.x = qualifiedName;
  }

  public boolean isStatic() {
    return "static".equals(mod);
  }

  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder("import ");
    if (mod != null) {
      result.append(mod);
      result.append(" ");
    }
    result.append(x);
    return result.toString();
  }

}
