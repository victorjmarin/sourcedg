package edu.rit.goal.sdg.graph;

public enum VertexType {

    ENTRY, FORMAL_IN, FORMAL_OUT, ACTUAL_IN, ACTUAL_OUT, CALL, DECL, ASSIGN, CTRL_IF, CTRL_WHILE, CTRL_DO, CTRL_FOR, RETURN, INITIAL_STATE, BREAK, CONTINUE;

    public boolean isCtrl() {
	return CTRL_IF.equals(this) || CTRL_WHILE.equals(this) || CTRL_DO.equals(this) || CTRL_FOR.equals(this);
    }

    public boolean isFormalParam() {
	return FORMAL_IN.equals(this) || FORMAL_OUT.equals(this);
    }

    public boolean isActualParam() {
	return ACTUAL_IN.equals(this) || ACTUAL_OUT.equals(this);
    }

    public boolean isParam() {
	return isFormalParam() || isActualParam();
    }

}
