package edu.rit.goal.sdg.statement;

import java.util.List;

public class ArrayAccessAssignment implements Stmt {

    private final String expressionName;
    private final Expr index;
    private final String operator;
    private final Expr rightHandSide;

    public ArrayAccessAssignment(final String expressionName, final Expr index, final String operator,
	    final Expr rightHandSide) {
	super();
	this.expressionName = expressionName;
	this.index = index;
	this.operator = operator;
	this.rightHandSide = rightHandSide;
    }

    public String getExpressionName() {
	return expressionName;
    }

    public Expr getIndex() {
	return index;
    }

    public String getOperator() {
	return operator;
    }

    public Expr getRightHandSide() {
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
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
