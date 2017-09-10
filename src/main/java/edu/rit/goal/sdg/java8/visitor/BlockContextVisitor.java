package edu.rit.goal.sdg.java8.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockStatementContext;

public class BlockContextVisitor {

    public Stmt visit(final BlockContext ctx) {
	Stmt result = null;
	if (ctx != null) {
	    final List<Stmt> stmts = new LinkedList<>();
	    final List<BlockStatementContext> blockStmtCtx = ctx.blockStatement();
	    // Not empty block
	    if (blockStmtCtx != null) {
		for (final BlockStatementContext bsc : blockStmtCtx) {
		    final BlockStmtVisitor visitor = new BlockStmtVisitor();
		    final Stmt blockStmt = visitor.visit(bsc);
		    stmts.add(blockStmt);
		}
		result = Translator.seq(stmts);
	    }
	}
	return result;
    }

}
