package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementWithoutTrailingSubstatementContext;
import edu.rit.goal.sdg.java.statement.Statement;

public class StatementNoShortIfVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitStatementNoShortIf(final Java8Parser.StatementNoShortIfContext ctx) {
	List<Statement> result = null;
	final StatementWithoutTrailingSubstatementContext stmntWoTrailSubstmnt = ctx
		.statementWithoutTrailingSubstatement();
	if (stmntWoTrailSubstmnt != null) {
	    final StmntWithoutTrailingSubstmntVisitor visitor = new StmntWithoutTrailingSubstmntVisitor();
	    result = visitor.visit(stmntWoTrailSubstmnt);
	}
	return result;
    }

}
