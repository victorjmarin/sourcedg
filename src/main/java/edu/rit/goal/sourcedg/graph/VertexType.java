package edu.rit.goal.sourcedg.graph;

public enum VertexType {

  CUNIT, PKG, IMPORT, CLASS, ENTRY, FORMAL_IN, FORMAL_OUT, ACTUAL_IN, ACTUAL_OUT, CALL, DECL, ASSIGN, RETURN, INITIAL_STATE, BREAK, CONTINUE, CTRL, THROW;

  public boolean isAssign() {
    return ASSIGN.equals(this);
  }

  @Override
  public String toString() {
    String result = super.toString().toLowerCase();
    result = result.substring(0, 1).toUpperCase() + result.substring(1);
    result = result.replace("_", "-");
    return result;
  }

}
