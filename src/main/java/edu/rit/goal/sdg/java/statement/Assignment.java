package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class Assignment implements Statement {

    private final Expression leftHandSide;
    private final String operator;
    private final Expression rightHandSide;

    public Assignment(final Expression leftHandSide, final String operator, final Expression rightHandSide) {
	super();
	this.leftHandSide = leftHandSide;
	this.operator = operator;
	this.rightHandSide = rightHandSide;
    }

    public Expression getLeftHandSide() {
	return leftHandSide;
    }

    public String getOperator() {
	return operator;
    }

    public Expression getRightHandSide() {
	return rightHandSide;
    }

    @Override
    public String toString() {
	return leftHandSide + operator + rightHandSide;
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
