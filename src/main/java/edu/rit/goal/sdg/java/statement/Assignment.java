package edu.rit.goal.sdg.java.statement;

public class Assignment implements Statement {

    private String leftHandSide;
    private String operator;
    private Expression rightHandSide;

    public Assignment(final String leftHandSide, final String operator, final Expression rightHandSide) {
	super();
	this.leftHandSide = leftHandSide;
	this.operator = operator;
	this.rightHandSide = rightHandSide;
    }

    public String getLeftHandSide() {
	return leftHandSide;
    }

    public void setLeftHandSide(final String leftHandSide) {
	this.leftHandSide = leftHandSide;
    }

    public String getOperator() {
	return operator;
    }

    public void setOperator(final String operator) {
	this.operator = operator;
    }

    public Expression getRightHandSide() {
	return rightHandSide;
    }

    public void setRightHandSide(final Expression rightHandSide) {
	this.rightHandSide = rightHandSide;
    }

    @Override
    public String toString() {
	return leftHandSide + " " + operator + " " + rightHandSide;
    }

}
