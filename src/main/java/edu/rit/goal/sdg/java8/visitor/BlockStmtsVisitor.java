package edu.rit.goal.sdg.java8.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.BlockStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.LocalVariableDeclarationStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.statement.Stmt;

public class BlockStmtsVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    public List<Stmt> visitBlockStatements(final Java8Parser.BlockStatementsContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	final List<BlockStatementContext> blockStatementCtx = ctx.blockStatement();
	for (final BlockStatementContext bsc : blockStatementCtx) {
	    final LocalVariableDeclarationStatementContext lclVarDeclStmntCtx = bsc.localVariableDeclarationStatement();
	    final StatementContext statementCtx = bsc.statement();
	    // Local variable declaration
	    if (lclVarDeclStmntCtx != null) {
		final LocalVariableDeclarationContext lclVarDeclCtx = lclVarDeclStmntCtx.localVariableDeclaration();
		final LocalVarDeclVisitor visitor = new LocalVarDeclVisitor();
		final List<Stmt> varDeclStmnts = visitor.visit(lclVarDeclCtx);
		result.addAll(varDeclStmnts);
	    }
	    // Statement
	    else if (statementCtx != null) {
		final StmtVisitor visitor = new StmtVisitor();
		result.addAll(visitor.visit(statementCtx));
	    }
	}
	return result;
    }

}
