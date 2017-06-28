package edu.rit.goal.sdg.java.visitor;

import java.util.List;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodInvocation_lfno_primaryContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodNameContext;
import edu.rit.goal.sdg.java.exception.InvocationArgException;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.Statement;

public class MethodInvocationVisitor extends Java8BaseVisitor<Statement> {

    @Override
    public Statement visitMethodInvocation(final Java8Parser.MethodInvocationContext ctx) {
	List<Expression> args = null;
	Statement result = null;
	String refVar = null;
	String methodName = null;
	final MethodNameContext methodNameCtx = ctx.methodName();
	if (methodNameCtx != null) {
	    methodName = methodNameCtx.getText();
	} else {
	    // Calling method on a referenced object
	    refVar = ctx.typeName().getText();
	    methodName = ctx.Identifier().getText();
	}
	final ArgumentListContext argListCtx = ctx.argumentList();
	if (argListCtx != null) {
	    final List<ExpressionContext> exprCtx = argListCtx.expression();
	    final ExpressionVisitor visitor = new ExpressionVisitor();
	    args = exprCtx.stream().map(e -> {
		final MethodInvocation_lfno_primaryContext methodInvocationCtx = VisitorUtils.getMethodInvCtx(e);
		// Function call as function argument
		if (methodInvocationCtx != null) {
		    throw new InvocationArgException(e);
		}
		return visitor.visit(e);
	    }).collect(Collectors.toList());
	}
	result = new MethodInvocation(refVar, methodName, args);
	return result;
    }

}
