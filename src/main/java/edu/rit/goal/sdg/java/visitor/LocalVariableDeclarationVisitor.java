package edu.rit.goal.sdg.java.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.VariableDeclaratorListContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.VariableDecl;

public class LocalVariableDeclarationVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitLocalVariableDeclaration(final Java8Parser.LocalVariableDeclarationContext ctx) {
	final List<Statement> result = new LinkedList<>();
	final VariableDeclaratorListContext varDeclLstCtx = ctx.variableDeclaratorList();
	final List<VariableDeclaratorContext> varDeclCtx = varDeclLstCtx.variableDeclarator();
	varDeclCtx.forEach(v -> {
	    final String variableDeclaratorId = v.variableDeclaratorId().getText();
	    // TODO: Support for arrays
	    final Expression variableInitializer = new Expression(v.variableInitializer().expression());
	    final Statement stmnt = new VariableDecl(variableDeclaratorId, variableInitializer);
	    result.add(stmnt);
	});
	return result;
    }
}