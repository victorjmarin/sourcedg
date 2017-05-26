package edu.rit.goal.sdg.java.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.statement.ContinueStmnt;

public class ContinueStmntVisitor extends Java8BaseVisitor<ContinueStmnt> {

    @Override
    public ContinueStmnt visitContinueStatement(final Java8Parser.ContinueStatementContext ctx) {
	String gotoLabel = null;
	final TerminalNode identifier = ctx.Identifier();
	if (identifier != null) {
	    gotoLabel = identifier.getText();
	}
	final ContinueStmnt result = new ContinueStmnt(gotoLabel);
	return result;
    }

}
