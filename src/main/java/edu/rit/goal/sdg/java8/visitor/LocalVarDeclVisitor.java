package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.VariableDeclaratorsContext;

public class LocalVarDeclVisitor {

    public List<Stmt> visit(final LocalVariableDeclarationContext ctx) {
	final List<Stmt> result = new ArrayList<>();
	final VariableDeclaratorsContext varDeclCtx = ctx.variableDeclarators();
	final List<VariableDeclaratorContext> varDeclLst = varDeclCtx.variableDeclarator();
	for (final VariableDeclaratorContext vdc : varDeclLst) {
	    final String varDeclId = vdc.variableDeclaratorId().getText();
	    final ExpressionContext exprCtx = vdc.variableInitializer().expression();
	    final List<ExpressionContext> exprCtxLst = exprCtx.expression();
	    if (!exprCtxLst.isEmpty()) {
		final String method = exprCtxLst.stream().map(c -> c.getText()).collect(Collectors.joining());
		System.out.println(method);
	    }
	    // Method call
	    final ExpressionListContext exprLstCtx = exprCtx.expressionList();
	    if (exprLstCtx != null) {
		System.out.println(exprLstCtx.getText());

	    }

	}
	return result;
    }

}
