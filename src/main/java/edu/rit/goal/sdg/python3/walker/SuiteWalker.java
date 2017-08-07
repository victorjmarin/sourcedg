package edu.rit.goal.sdg.python3.walker;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.Simple_stmtContext;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.StmtContext;
import edu.rit.goal.sdg.statement.Stmt;

public class SuiteWalker {

    public List<Stmt> walk(final Python3Parser.SuiteContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	List<Stmt> stmts = null;
	if (isSimpleStmt(ctx)) {
	    final SimpleStmtWalker simpleStmtWalker = new SimpleStmtWalker();
	    stmts = simpleStmtWalker.walk(ctx.simple_stmt());
	    result.addAll(stmts);
	} else if (isStmt(ctx)) {
	    final StmtWalker walker = new StmtWalker();
	    for (final StmtContext stmtCtx : ctx.stmt()) {
		stmts = walker.walk(stmtCtx);
		result.addAll(stmts);
	    }
	}
	return result;
    }

    private boolean isSimpleStmt(final Python3Parser.SuiteContext ctx) {
	final Simple_stmtContext stmt = ctx.simple_stmt();
	return stmt != null;
    }

    private boolean isStmt(final Python3Parser.SuiteContext ctx) {
	final List<StmtContext> stmt = ctx.stmt();
	return stmt != null;
    }

}
