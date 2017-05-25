package edu.rit.goal.sdg.java.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.IfThenElseStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.IfThenStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementWithoutTrailingSubstatementContext;
import edu.rit.goal.sdg.java.statement.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.Statement;

public class StatementVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitStatement(final Java8Parser.StatementContext ctx) {
	List<Statement> result = null;
	final IfThenStatementContext ifThenStmntCtx = ctx.ifThenStatement();
	final IfThenElseStatementContext ifThenElseStmntCtx = ctx.ifThenElseStatement();
	final StatementWithoutTrailingSubstatementContext stmntWoTrailSubstmntCtx = ctx
		.statementWithoutTrailingSubstatement();
	if (ifThenStmntCtx != null) {
	    final IfThenStmntVisitor visitor = new IfThenStmntVisitor();
	    final IfThenStmnt ifThenStmnt = visitor.visit(ifThenStmntCtx);
	    result = new LinkedList<>();
	    result.add(ifThenStmnt);
	} else if (ifThenElseStmntCtx != null) {
	} else if (stmntWoTrailSubstmntCtx != null) {
	    final StmntWithoutTrailingSubstmntVisitor visitor = new StmntWithoutTrailingSubstmntVisitor();
	    result = visitor.visit(stmntWoTrailSubstmntCtx);
	}
	return result;
    }

}
