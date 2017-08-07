package edu.rit.goal.sdg.java8.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.statement.BreakStmt;

public class BreakStmtVisitor extends Java8BaseVisitor<BreakStmt> {

    @Override
    public BreakStmt visitBreakStatement(final Java8Parser.BreakStatementContext ctx) {
	String gotoLabel = null;
	final TerminalNode identifier = ctx.Identifier();
	if (identifier != null) {
	    gotoLabel = identifier.getText();
	}
	final BreakStmt result = new BreakStmt(gotoLabel);
	return result;
    }

}
