package edu.rit.goal.sourcedg.graph;

public enum EdgeType {

  DATA, CTRL_TRUE, CTRL_FALSE, DEF_ORDER, CALL, PARAM_IN, PARAM_OUT, MEMBER_OF;

  public boolean isCtrl() {
    return CTRL_TRUE.equals(this) || CTRL_FALSE.equals(this);
  }

}
