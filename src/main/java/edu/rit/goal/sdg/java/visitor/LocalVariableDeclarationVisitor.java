package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodNameContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.VariableDeclaratorListContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.VariableDecl;

public class LocalVariableDeclarationVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitLocalVariableDeclaration(final Java8Parser.LocalVariableDeclarationContext ctx) {
	final List<Statement> result = new LinkedList<>();
	final VariableDeclaratorListContext varDeclLstCtx = ctx.variableDeclaratorList();
	final List<VariableDeclaratorContext> varDeclCtx = varDeclLstCtx.variableDeclarator();
	varDeclCtx.forEach(v -> {
	    Statement stmnt = null;
	    final String variableDeclaratorId = v.variableDeclaratorId().getText();
	    // TODO: Support for arrays
	    final ExpressionVisitor visitor = new ExpressionVisitor();
	    final Expression variableInitializer = visitor.visit(v.variableInitializer().expression());
	    // Method call
	    final String methodName = getMethodName(ctx);
	    final boolean isMethodInvocation = methodName != null;
	    if (isMethodInvocation) {
		final String outVar = variableDeclaratorId;
		final List<Expression> rhsList = new ArrayList<>();
		rhsList.add(variableInitializer);
		final ArgumentListContext argListCtx = getArgListCtx(v.variableInitializer());
		final List<Expression> inVars = argListCtx.expression().stream().map(exp -> visitor.visit(exp))
			.collect(Collectors.toList());
		stmnt = new MethodInvocation(methodName, outVar, inVars);
	    } else {
		stmnt = new VariableDecl(variableDeclaratorId, variableInitializer);
	    }
	    result.add(stmnt);
	});
	return result;
    }

    private String getMethodName(final ParseTree ctx) {
	String result = null;
	if (ctx instanceof MethodNameContext) {
	    result = ((MethodNameContext) ctx).getText();
	    return result;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result = getMethodName(child);
	    if (result != null)
		break;
	}
	return result;
    }

    private ArgumentListContext getArgListCtx(final ParseTree ctx) {
	ArgumentListContext result = null;
	if (ctx instanceof ArgumentListContext) {
	    return (ArgumentListContext) ctx;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result = getArgListCtx(child);
	    if (result != null)
		break;
	}
	return result;
    }

}