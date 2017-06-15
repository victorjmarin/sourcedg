package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BlockContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BlockStatementsContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BreakStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ContinueStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.DoStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ReturnStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementExpressionContext;
import edu.rit.goal.sdg.java.statement.BreakStmnt;
import edu.rit.goal.sdg.java.statement.ContinueStmnt;
import edu.rit.goal.sdg.java.statement.ReturnStmnt;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.DoStmnt;

public class StmntWithoutTrailingSubstmntVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitStatementWithoutTrailingSubstatement(
	    final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	List<Statement> result = new ArrayList<>();
	if (isExpression(ctx)) {
	    final ExpressionStatementContext exprStmntCtx = ctx.expressionStatement();
	    final StatementExpressionContext stmntExprCtx = exprStmntCtx.statementExpression();
	    final StatementExpressionVisitor visitor = new StatementExpressionVisitor();
	    final Statement stmnt = visitor.visit(stmntExprCtx);
	    result.add(stmnt);
	} else if (isBlock(ctx)) {
	    final BlockContext blockCtx = ctx.block();
	    final BlockStatementsContext blockStmntsCtx = blockCtx.blockStatements();
	    final BlockStatementsVisitor visitor = new BlockStatementsVisitor();
	    result = visitor.visit(blockStmntsCtx);
	} else if (isReturn(ctx)) {
	    final ReturnStmntVisitor visitor = new ReturnStmntVisitor();
	    final ReturnStmnt stmnt = visitor.visit(ctx.returnStatement());
	    result.add(stmnt);
	} else if (isBreak(ctx)) {
	    final BreakStmntVisitor visitor = new BreakStmntVisitor();
	    final BreakStmnt stmnt = visitor.visit(ctx.breakStatement());
	    result.add(stmnt);
	} else if (isContinue(ctx)) {
	    final ContinueStmntVisitor visitor = new ContinueStmntVisitor();
	    final ContinueStmnt stmnt = visitor.visit(ctx.continueStatement());
	    result.add(stmnt);
	} else if (isDoWhile(ctx)) {
	    final DoStmntVisitor visitor = new DoStmntVisitor();
	    final DoStmnt stmnt = visitor.visit(ctx.doStatement());
	    result.add(stmnt);
	}
	return result;
    }

    private boolean isExpression(final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	final ExpressionStatementContext stmnt = ctx.expressionStatement();
	return stmnt != null;
    }

    private boolean isBlock(final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	final BlockContext stmnt = ctx.block();
	return stmnt != null;
    }

    private boolean isReturn(final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	final ReturnStatementContext stmnt = ctx.returnStatement();
	return stmnt != null;
    }

    private boolean isBreak(final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	final BreakStatementContext stmnt = ctx.breakStatement();
	return stmnt != null;
    }

    private boolean isContinue(final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	final ContinueStatementContext stmnt = ctx.continueStatement();
	return stmnt != null;
    }

    private boolean isDoWhile(final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	final DoStatementContext stmnt = ctx.doStatement();
	return stmnt != null;
    }

}
