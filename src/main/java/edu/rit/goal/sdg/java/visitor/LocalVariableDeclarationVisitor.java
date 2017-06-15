package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.VariableDeclaratorListContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.MethodInvocationAssignment;
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
	    final String methodName = VisitorUtils.getMethodName(ctx);
	    final boolean isMethodInvocation = methodName != null;
	    if (isMethodInvocation) {
		final String outVar = variableDeclaratorId;
		final List<Expression> rhsList = new ArrayList<>();
		rhsList.add(variableInitializer);
		final ArgumentListContext argListCtx = VisitorUtils.getArgListCtx(v.variableInitializer());
		final List<Expression> inVars = argListCtx.expression().stream().map(exp -> visitor.visit(exp))
			.collect(Collectors.toList());
		stmnt = new MethodInvocationAssignment(methodName, outVar, inVars);
	    } else {
		stmnt = new VariableDecl(variableDeclaratorId, variableInitializer);
	    }
	    result.add(stmnt);
	});
	return result;
    }

}