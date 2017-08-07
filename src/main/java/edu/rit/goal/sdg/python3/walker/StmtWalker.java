package edu.rit.goal.sdg.python3.walker;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.Compound_stmtContext;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.Simple_stmtContext;
import edu.rit.goal.sdg.statement.Stmt;

public class StmtWalker {

    public List<Stmt> walk(final Python3Parser.StmtContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	List<Stmt> stmts = null;
	if (isSimpleStmt(ctx)) {
	    final SimpleStmtWalker simpleStmtWalker = new SimpleStmtWalker();
	    stmts = simpleStmtWalker.walk(ctx.simple_stmt());
	} else if (isCompoundStmt(ctx)) {
	    final CompoundStmtWalker compoundStmtWalker = new CompoundStmtWalker();
	    stmts = compoundStmtWalker.walk(ctx.compound_stmt());
	}
	result.addAll(stmts);
	return result;
    }

    private boolean isSimpleStmt(final Python3Parser.StmtContext ctx) {
	final Simple_stmtContext stmt = ctx.simple_stmt();
	return stmt != null;
    }

    private boolean isCompoundStmt(final Python3Parser.StmtContext ctx) {
	final Compound_stmtContext stmt = ctx.compound_stmt();
	return stmt != null;
    }

}
