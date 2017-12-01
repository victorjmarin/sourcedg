package edu.rit.goal.sdg.graph;

public enum VertexSubtype {
  PLUS, MINUS, MULT, DIV, LT, GT, LEQ, GEQ, EQ, NOT_EQ, MOD, AND, OR, INCR, DECR, SH_PLUS, SH_MINUS, SH_MULT, SH_DIV, PRINT, CALL,
  // Call of method within the same class
  SELF_CALL,
  // Call of method on another object
  OBJECT_CALL,
  // Number of parameters in call
  NO_PARAMS, SINGLE_PARAM, TWO_PARAMS, THREE_PARAMS, FOUR_PARAMS, FIVE_PARAMS, PLUS_FIVE_PARAMS;

  @Override
  public String toString() {
    String result = super.toString().toLowerCase();
    result = result.substring(0, 1).toUpperCase() + result.substring(1);
    result = result.replace("_", "-");
    return result;
  }

}
