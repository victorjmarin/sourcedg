package edu.rit.goal.sdg.java.visitor;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.statement.Assignment;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class AssignmentVisitor extends Java8BaseVisitor<Statement> {

    @Override
    public Statement visitAssignment(final Java8Parser.AssignmentContext ctx) {
	Statement result = null;
	final String leftHandSide = ctx.leftHandSide().getText();
	final String operator = ctx.assignmentOperator().getText();
	final ExpressionVisitor visitor = new ExpressionVisitor();
	final Expression rightHandSide = visitor.visit(ctx.expression());
	result = new Assignment(leftHandSide, operator, rightHandSide);
	// Add dependency w.r.t. variable being assigned if it is a short-hand operator
	if (isShortHandOperator(operator)) {
	    rightHandSide.getReadingVars().add(leftHandSide);
	}
	return result;
    }

    private boolean isShortHandOperator(final String operator) {
	switch (operator) {
	case "=":
	    return false;
	case "*=":
	case "/=":
	case "%=":
	case "+=":
	case "-=":
	case "<<=":
	case ">>=":
	case ">>>=":
	case "&=":
	case "^=":
	case "|=":
	    return true;
	default:
	    return false;
	}
    }

}
