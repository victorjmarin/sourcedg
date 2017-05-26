package edu.rit.goal.sdg.java.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.statement.BreakStmnt;

public class BreakStmntVisitor extends Java8BaseVisitor<BreakStmnt> {

    @Override
    public BreakStmnt visitBreakStatement(final Java8Parser.BreakStatementContext ctx) {
	String gotoLabel = null;
	final TerminalNode identifier = ctx.Identifier();
	if (identifier != null) {
	    gotoLabel = identifier.getText();
	}
	final BreakStmnt result = new BreakStmnt(gotoLabel);
	return result;
    }

}
