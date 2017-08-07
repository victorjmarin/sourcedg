package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.MethodInvocation_lfno_primaryContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.TypeNameContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.VariableDeclaratorListContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.MethodInvocationAssignment;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.VariableDecl;

public class LocalVarDeclVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    public List<Stmt> visitLocalVariableDeclaration(final Java8Parser.LocalVariableDeclarationContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	final VariableDeclaratorListContext varDeclLstCtx = ctx.variableDeclaratorList();
	final List<VariableDeclaratorContext> varDeclCtx = varDeclLstCtx.variableDeclarator();
	varDeclCtx.forEach(v -> {
	    Stmt stmnt = null;
	    final String variableDeclaratorId = v.variableDeclaratorId().getText();
	    // TODO: Support for arrays
	    final ExprVisitor visitor = new ExprVisitor();
	    final Expr variableInitializer = visitor.visit(v.variableInitializer().expression());
	    // Method call
	    final String methodName = VisitorUtils.getMethodName(ctx);
	    final boolean isMethodInvocation = methodName != null;
	    if (isMethodInvocation) {
		final String outVar = variableDeclaratorId;
		final List<Expr> rhsList = new ArrayList<>();
		rhsList.add(variableInitializer);
		final ArgumentListContext argListCtx = VisitorUtils.getArgListCtx(v.variableInitializer());
		final List<Expr> inVars = argListCtx.expression().stream().map(exp -> {
		    // Check for function call/assignment as function argument
		    VisitorUtils.checkForUnsupportedFeatures(exp);
		    return visitor.visit(exp);
		}).collect(Collectors.toList());
		final MethodInvocation_lfno_primaryContext methodInvCtx = VisitorUtils.getMethodInvCtx(ctx);
		final TypeNameContext typeNameCtx = methodInvCtx.typeName();
		String refVar = null;
		// Calling method on a referenced object
		if (typeNameCtx != null)
		    refVar = typeNameCtx.getText();
		stmnt = new MethodInvocationAssignment(refVar, methodName, outVar, inVars);
	    } else {
		stmnt = new VariableDecl(variableDeclaratorId, variableInitializer);
	    }
	    result.add(stmnt);
	});
	return result;
    }

}