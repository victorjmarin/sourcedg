package edu.rit.goal.sourcedg.graph;

public enum EdgeType {

  DATA, CTRL_TRUE, CTRL_FALSE, DEF_ORDER, CALL, PARAM_IN, PARAM_OUT, MEMBER_OF;

  @Override
  public String toString() {
    String result = super.toString().toLowerCase();
    result = result.substring(0, 1).toUpperCase() + result.substring(1);
    result = result.replace("_", "-");
    return result;
  }

}
