package edu.rit.goal.sdg.java.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BlockStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.LocalVariableDeclarationStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java.statement.Statement;

public class BlockStatementsVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitBlockStatements(final Java8Parser.BlockStatementsContext ctx) {
	final List<Statement> result = new LinkedList<>();
	final List<BlockStatementContext> blockStatementCtx = ctx.blockStatement();
	for (final BlockStatementContext bsc : blockStatementCtx) {
	    final LocalVariableDeclarationStatementContext lclVarDeclStmntCtx = bsc.localVariableDeclarationStatement();
	    final StatementContext statementCtx = bsc.statement();
	    // Local variable declaration
	    if (lclVarDeclStmntCtx != null) {
		final LocalVariableDeclarationContext lclVarDeclCtx = lclVarDeclStmntCtx.localVariableDeclaration();
		final LocalVariableDeclarationVisitor visitor = new LocalVariableDeclarationVisitor();
		final List<Statement> varDeclStmnts = visitor.visit(lclVarDeclCtx);
		result.addAll(varDeclStmnts);
	    }
	    // Statement
	    else if (statementCtx != null) {
		final StatementVisitor visitor = new StatementVisitor();
		result.addAll(visitor.visit(statementCtx));
	    }
	}
	return result;
    }

}
