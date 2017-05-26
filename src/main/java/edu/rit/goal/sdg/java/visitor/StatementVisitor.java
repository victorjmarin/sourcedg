package edu.rit.goal.sdg.java.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BasicForStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.EnhancedForStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ForStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.IfThenElseStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.IfThenStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementWithoutTrailingSubstatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.WhileStatementContext;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;

public class StatementVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitStatement(final Java8Parser.StatementContext ctx) {
	final List<Statement> result = new LinkedList<>();
	if (isIfThen(ctx)) {
	    final IfThenStmntVisitor visitor = new IfThenStmntVisitor();
	    final IfThenStmnt stmnt = visitor.visit(ctx.ifThenStatement());
	    result.add(stmnt);
	} else if (isIfThenElse(ctx)) {
	    final IfThenElseStmntVisitor visitor = new IfThenElseStmntVisitor();
	    final IfThenElseStmnt stmnt = visitor.visit(ctx.ifThenElseStatement());
	    result.add(stmnt);
	} else if (isBasicFor(ctx)) {
	    final BasicForStmntVisitor visitor = new BasicForStmntVisitor();
	    final BasicForStmnt stmnt = visitor.visit(ctx.forStatement());
	    result.add(stmnt);
	} else if (isEnhancedFor(ctx)) {
	    // TODO
	} else if (isWhile(ctx)) {
	    final WhileStmntVisitor visitor = new WhileStmntVisitor();
	    final WhileStmnt stmnt = visitor.visit(ctx.whileStatement());
	    result.add(stmnt);
	} else if (isStmntWoTrailSubstmnt(ctx)) {
	    final StmntWithoutTrailingSubstmntVisitor visitor = new StmntWithoutTrailingSubstmntVisitor();
	    final List<Statement> stmnts = visitor.visit(ctx.statementWithoutTrailingSubstatement());
	    result.addAll(stmnts);
	}
	return result;
    }

    private boolean isIfThen(final Java8Parser.StatementContext ctx) {
	final IfThenStatementContext stmnt = ctx.ifThenStatement();
	return stmnt != null;
    }

    private boolean isIfThenElse(final Java8Parser.StatementContext ctx) {
	final IfThenElseStatementContext stmnt = ctx.ifThenElseStatement();
	return stmnt != null;
    }

    private boolean isWhile(final Java8Parser.StatementContext ctx) {
	final WhileStatementContext stmnt = ctx.whileStatement();
	return stmnt != null;
    }

    private boolean isBasicFor(final Java8Parser.StatementContext ctx) {
	BasicForStatementContext stmnt = null;
	final ForStatementContext forStmnt = ctx.forStatement();
	if (forStmnt != null)
	    stmnt = forStmnt.basicForStatement();
	return stmnt != null;
    }

    private boolean isEnhancedFor(final Java8Parser.StatementContext ctx) {
	EnhancedForStatementContext stmnt = null;
	final ForStatementContext forStmnt = ctx.forStatement();
	if (forStmnt != null)
	    stmnt = forStmnt.enhancedForStatement();
	return stmnt != null;
    }

    private boolean isStmntWoTrailSubstmnt(final Java8Parser.StatementContext ctx) {
	final StatementWithoutTrailingSubstatementContext stmnt = ctx.statementWithoutTrailingSubstatement();
	return stmnt != null;
    }

}
