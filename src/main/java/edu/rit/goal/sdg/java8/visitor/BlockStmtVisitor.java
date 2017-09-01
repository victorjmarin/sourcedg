package edu.rit.goal.sdg.java8.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockStatementContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.StatementContext;

public class BlockStmtVisitor {

    public List<Stmt> visit(final BlockStatementContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	final LocalVariableDeclarationContext localVarDeclCtx = ctx.localVariableDeclaration();
	final StatementContext statementCtx = ctx.statement();
	// Local variable declaration
	if (localVarDeclCtx != null) {
	    final LocalVarDeclVisitor visitor = new LocalVarDeclVisitor();
	    final List<Stmt> varDeclStmnts = visitor.visit(localVarDeclCtx);
	    result.addAll(varDeclStmnts);
	}
	// Statement
	else if (statementCtx != null) {
	    // final StmtVisitor visitor = new StmtVisitor();
	    // result.addAll(visitor.visit(statementCtx));

	}
	return result;
    }

}
