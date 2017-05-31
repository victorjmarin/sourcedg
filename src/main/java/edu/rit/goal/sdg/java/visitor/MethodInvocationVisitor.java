package edu.rit.goal.sdg.java.visitor;

import java.util.List;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodNameContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.Statement;

public class MethodInvocationVisitor extends Java8BaseVisitor<Statement> {

    @Override
    public Statement visitMethodInvocation(final Java8Parser.MethodInvocationContext ctx) {
	Statement result = null;
	String methodName = "";
	final MethodNameContext methodNameCtx = ctx.methodName();
	if (methodNameCtx != null) {
	    methodName = methodNameCtx.getText();
	} else {
	    methodName = ctx.typeName().getText() + "." + ctx.Identifier();
	}
	final ArgumentListContext argListCtx = ctx.argumentList();
	final List<ExpressionContext> exprCtx = argListCtx.expression();
	final ExpressionVisitor visitor = new ExpressionVisitor();
	final List<Expression> args = exprCtx.stream().map(e -> visitor.visit(e)).collect(Collectors.toList());
	result = new MethodInvocation(methodName, null, args);
	return result;
    }

}
