package edu.rit.goal.sdg.java8.visitor;

import java.util.List;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.MethodNameContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.MethodInvocation;
import edu.rit.goal.sdg.statement.Stmt;

public class MethodInvocationVisitor extends Java8BaseVisitor<Stmt> {

    @Override
    public Stmt visitMethodInvocation(final Java8Parser.MethodInvocationContext ctx) {
	List<Expr> args = null;
	Stmt result = null;
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
	    final ExprVisitor visitor = new ExprVisitor();
	    args = exprCtx.stream().map(e -> {
		// Check for function call/assignment as function argument
		VisitorUtils.checkForUnsupportedFeatures(e);
		return visitor.visit(e);
	    }).collect(Collectors.toList());
	}
	result = new MethodInvocation(refVar, methodName, args);
	return result;
    }

}
