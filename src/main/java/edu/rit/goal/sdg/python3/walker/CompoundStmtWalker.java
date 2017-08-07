package edu.rit.goal.sdg.python3.walker;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.FuncdefContext;
import edu.rit.goal.sdg.statement.Stmt;

public class CompoundStmtWalker {

    public List<Stmt> walk(final Python3Parser.Compound_stmtContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	List<Stmt> stmts = null;
	if (isFuncDef(ctx)) {
	    final FuncDefWalker walker = new FuncDefWalker();
	    stmts = walker.walk(ctx.funcdef());
	}
	result.addAll(stmts);
	return result;
    }

    private boolean isFuncDef(final Python3Parser.Compound_stmtContext ctx) {
	final FuncdefContext stmt = ctx.funcdef();
	return stmt != null;
    }

}
