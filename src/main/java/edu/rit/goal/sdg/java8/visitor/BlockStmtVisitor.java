package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockStatementContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.StatementContext;

public class BlockStmtVisitor {

    public Stmt visit(final BlockStatementContext ctx) {
	Stmt result = null;
	final LocalVariableDeclarationContext localVarDeclCtx = ctx.localVariableDeclaration();
	final StatementContext statementCtx = ctx.statement();
	// Local variable declaration
	if (localVarDeclCtx != null) {
	    final LocalVarDeclVisitor visitor = new LocalVarDeclVisitor();
	    final Stmt varDeclStmnt = visitor.visit(localVarDeclCtx);
	    result = varDeclStmnt;
	}
	// Statement
	else if (statementCtx != null) {
	    final StmtVisitor visitor = new StmtVisitor();
	    final Stmt stmtStmnts = visitor.visit(statementCtx);
	    result = stmtStmnts;
	}
	return result;
    }

}
