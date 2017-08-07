package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementWithoutTrailingSubstatementContext;
import edu.rit.goal.sdg.statement.Stmt;

public class StmtNoShortIfVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    public List<Stmt> visitStatementNoShortIf(final Java8Parser.StatementNoShortIfContext ctx) {
	List<Stmt> result = null;
	final StatementWithoutTrailingSubstatementContext stmntWoTrailSubstmnt = ctx
		.statementWithoutTrailingSubstatement();
	if (stmntWoTrailSubstmnt != null) {
	    final StmtWithoutTrailingSubstmtVisitor visitor = new StmtWithoutTrailingSubstmtVisitor();
	    result = visitor.visit(stmntWoTrailSubstmnt);
	}
	return result;
    }

}
