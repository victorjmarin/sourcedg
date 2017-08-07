package edu.rit.goal.sdg.python3.walker;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.python3.antlr.Python3BaseVisitor;
import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.StmtContext;
import edu.rit.goal.sdg.statement.Stmt;

public class FileInputVisitor extends Python3BaseVisitor<List<Stmt>> {

    @Override
    protected List<Stmt> aggregateResult(final List<Stmt> aggregate, final List<Stmt> nextResult) {
	List<Stmt> result = nextResult;
	if (result == null)
	    result = aggregate;
	return result;
    }

    @Override
    public List<Stmt> visitFile_input(final Python3Parser.File_inputContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	List<Stmt> stmts;
	// Iterate over statements
	for (final StmtContext stmtCtx : ctx.stmt()) {
	    final StmtWalker stmtWalker = new StmtWalker();
	    stmts = stmtWalker.walk(stmtCtx);
	    result.addAll(stmts);
	}
	return result;

    }

}
