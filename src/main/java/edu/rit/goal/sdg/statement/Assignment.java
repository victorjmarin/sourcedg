package edu.rit.goal.sdg.statement;

import java.util.Set;

public class Assignment implements Stmt {

    private final String outVar;
    private final String operator;
    private final Expr rightHandSide;

    public Assignment(final String outVar, final String operator, final Expr rightHandSide) {
	super();
	this.outVar = outVar;
	this.operator = operator;
	this.rightHandSide = rightHandSide;
    }

    public String getOutVar() {
	return outVar;
    }

    public String getOperator() {
	return operator;
    }

    public Set<String> getInVars() {
	return rightHandSide.getReadingVars();
    }

    public Expr getRightHandSide() {
	return rightHandSide;
    }

    @Override
    public String toString() {
	return outVar + operator + rightHandSide;
    }

}
