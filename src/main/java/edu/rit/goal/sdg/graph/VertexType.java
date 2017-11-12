package edu.rit.goal.sdg.graph;

public enum VertexType {

  CUNIT, PKG, IMPORT, CLASS, ENTRY, FORMAL_IN, FORMAL_OUT, ACTUAL_IN, ACTUAL_OUT, CALL, ASSIGN, RETURN, INITIAL_STATE, BREAK, CONTINUE, CTRL;

  public boolean isFormalParam() {
    return FORMAL_IN.equals(this) || FORMAL_OUT.equals(this);
  }

  public boolean isActualParam() {
    return ACTUAL_IN.equals(this) || ACTUAL_OUT.equals(this);
  }

  public boolean isParam() {
    return isFormalParam() || isActualParam();
  }

  @Override
  public String toString() {
    String result = super.toString().toLowerCase();
    result = result.substring(0, 1).toUpperCase() + result.substring(1);
    result = result.replace("_", "-");
    return result;
  }

}
