package edu.rit.goal.sdg.python3.walker;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.Expr_stmtContext;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.Small_stmtContext;
import edu.rit.goal.sdg.statement.Stmt;

public class SimpleStmtWalker {

    public List<Stmt> walk(final Python3Parser.Simple_stmtContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	Stmt stmt;
	for (final Small_stmtContext smallStmtCtx : ctx.small_stmt()) {
	    if (isExprStmt(smallStmtCtx)) {
		final ExprStmtWalker exprStmtWalker = new ExprStmtWalker();
		stmt = exprStmtWalker.walk(smallStmtCtx.expr_stmt());
		result.add(stmt);
	    }
	}
	return result;
    }

    private boolean isExprStmt(final Python3Parser.Small_stmtContext ctx) {
	final Expr_stmtContext stmt = ctx.expr_stmt();
	return stmt != null;
    }

}
