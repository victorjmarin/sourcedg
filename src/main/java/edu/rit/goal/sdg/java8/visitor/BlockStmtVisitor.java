package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockStatementContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.StatementContext;

public class BlockStmtVisitor {

    private final String className;

    public BlockStmtVisitor(final String className) {
	this.className = className;
    }

    public Stmt visit(final BlockStatementContext ctx) {
	Stmt result = null;
	final LocalVariableDeclarationContext localVarDeclCtx = ctx.localVariableDeclaration();
	final StatementContext statementCtx = ctx.statement();
	// Local variable declaration
	if (localVarDeclCtx != null) {
	    final LocalVarDeclVisitor visitor = new LocalVarDeclVisitor(className);
	    final Stmt varDeclStmnt = visitor.visit(localVarDeclCtx);
	    result = varDeclStmnt;
	}
	// Statement
	else if (statementCtx != null) {
	    final StmtVisitor visitor = new StmtVisitor(className);
	    final Stmt stmtStmnts = visitor.visit(statementCtx);
	    result = stmtStmnts;
	}
	return result;
    }

}
