package edu.rit.goal.sdg.java.statement;

import java.util.List;
import java.util.Set;

public class Assignment implements Statement {

    private final String outVar;
    private final String operator;
    private final Expression rightHandSide;

    public Assignment(final String outVar, final String operator, final Expression rightHandSide) {
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

    public Expression getRightHandSide() {
	return rightHandSide;
    }

    @Override
    public String toString() {
	return outVar + operator + rightHandSide;
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
