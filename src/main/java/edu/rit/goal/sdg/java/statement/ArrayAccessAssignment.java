package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class ArrayAccessAssignment implements Statement {

    private final String expressionName;
    private final Expression index;
    private final String operator;
    private final Expression rightHandSide;

    public ArrayAccessAssignment(final String expressionName, final Expression index, final String operator,
	    final Expression rightHandSide) {
	super();
	this.expressionName = expressionName;
	this.index = index;
	this.operator = operator;
	this.rightHandSide = rightHandSide;
    }

    public String getExpressionName() {
	return expressionName;
    }

    public Expression getIndex() {
	return index;
    }

    public String getOperator() {
	return operator;
    }

    public Expression getRightHandSide() {
	return rightHandSide;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append(expressionName);
	sb.append("[");
	sb.append(index);
	sb.append("] ");
	sb.append(operator);
	sb.append(" ");
	sb.append(rightHandSide);
	return sb.toString();
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
