package edu.rit.goal.sdg.java.statement;

public class Assignment implements Statement {

    private final String leftHandSide;
    private final String operator;
    private final Expression rightHandSide;

    public Assignment(final String leftHandSide, final String operator, final Expression rightHandSide) {
	super();
	this.leftHandSide = leftHandSide;
	this.operator = operator;
	this.rightHandSide = rightHandSide;
    }

    public String getLeftHandSide() {
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
	return leftHandSide + " " + operator + " " + rightHandSide;
    }

}
