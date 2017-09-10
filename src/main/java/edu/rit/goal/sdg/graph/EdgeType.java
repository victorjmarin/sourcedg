package edu.rit.goal.sdg.graph;

public enum EdgeType {

    DATA, CTRL_TRUE, CTRL_FALSE, DEF_ORDER, CALL, PARAM_IN, PARAM_OUT;

    public boolean isCtrl() {
	return CTRL_TRUE.equals(this) || CTRL_FALSE.equals(this);
    }

}
