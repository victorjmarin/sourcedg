package edu.rit.goal.sdg.java.visitor;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.AssignmentContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodInvocationContext;
import edu.rit.goal.sdg.java.statement.Statement;

public class StatementExpressionVisitor extends Java8BaseVisitor<Statement> {

    @Override
    public Statement visitStatementExpression(final Java8Parser.StatementExpressionContext ctx) {
	Statement result = null;
	final AssignmentContext assignmentCtx = ctx.assignment();
	final MethodInvocationContext methodInvCtx = ctx.methodInvocation();
	if (assignmentCtx != null) {
	    final AssignmentVisitor visitor = new AssignmentVisitor();
	    result = visitor.visit(assignmentCtx);
	} else if (methodInvCtx != null) {
	    final MethodInvocationVisitor visitor = new MethodInvocationVisitor();
	    result = visitor.visit(methodInvCtx);
	}
	return result;
    }

}
