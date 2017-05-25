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
	final Expression rightHandSide = new Expression(ctx.expression());
	result = new Assignment(leftHandSide, operator, rightHandSide);
	return result;
    }

}
