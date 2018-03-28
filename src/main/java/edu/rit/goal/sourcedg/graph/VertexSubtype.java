package edu.rit.goal.sourcedg.graph;

public enum VertexSubtype {
  PLUS, MINUS, MULT, DIV, LT, GT, LEQ, GEQ, EQ, NOT_EQ, MOD, AND, OR, INCR, DECR, SH_PLUS, SH_MINUS, SH_MULT, SH_DIV, PRINT, CALL, ARRAY_ACCESS,
  // Call of method within the same class
  SELF_CALL,
  // Call of method on another object or class
  SCOPED_CALL,
  // Creates an object
  NEW_OBJ;

  @Override
  public String toString() {
    String result = super.toString().toLowerCase();
    result = result.substring(0, 1).toUpperCase() + result.substring(1);
    result = result.replace("_", "-");
    return result;
  }

}
