package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.BlockStatementsContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.BreakStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ContinueStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.DoStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ReturnStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.SwitchStatementContext;
import edu.rit.goal.sdg.statement.BreakStmt;
import edu.rit.goal.sdg.statement.ContinueStmt;
import edu.rit.goal.sdg.statement.ReturnStmt;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.DoStmt;
import edu.rit.goal.sdg.statement.control.SwitchStmt;

public class StmtWithoutTrailingSubstmtVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    public List<Stmt> visitStatementWithoutTrailingSubstatement(
	    final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	List<Stmt> result = new ArrayList<>();
	if (isExpression(ctx)) {
	    final ExpressionStatementContext exprStmntCtx = ctx.expressionStatement();
	    final StatementExpressionContext stmntExprCtx = exprStmntCtx.statementExpression();
	    final StmtExprVisitor visitor = new StmtExprVisitor();
	    final Stmt stmnt = visitor.visit(stmntExprCtx);
	    result.add(stmnt);
	} else if (isBlock(ctx)) {
	    final BlockContext blockCtx = ctx.block();
	    final BlockStatementsContext blockStmntsCtx = blockCtx.blockStatements();
	    // Check block body is not empty
	    if (blockStmntsCtx != null) {
		final BlockStmtsVisitor visitor = new BlockStmtsVisitor();
		result = visitor.visit(blockStmntsCtx);
	    }
	} else if (isReturn(ctx)) {
	    final ReturnStmtVisitor visitor = new ReturnStmtVisitor();
	    final ReturnStmt stmnt = visitor.visit(ctx.returnStatement());
	    result.add(stmnt);
	} else if (isBreak(ctx)) {
	    final BreakStmtVisitor visitor = new BreakStmtVisitor();
	    final BreakStmt stmnt = visitor.visit(ctx.breakStatement());
	    result.add(stmnt);
	} else if (isContinue(ctx)) {
	    final ContinueStmtVisitor visitor = new ContinueStmtVisitor();
	    final ContinueStmt stmnt = visitor.visit(ctx.continueStatement());
	    result.add(stmnt);
	} else if (isDoWhile(ctx)) {
	    final DoStmtVisitor visitor = new DoStmtVisitor();
	    final DoStmt stmnt = visitor.visit(ctx.doStatement());
	    result.add(stmnt);
	} else if (isSwitch(ctx)) {
	    final SwitchStmtVisitor visitor = new SwitchStmtVisitor();
	    final SwitchStmt stmnt = visitor.visitSwitchContext(ctx.switchStatement());
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

    private boolean isSwitch(final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	final SwitchStatementContext stmnt = ctx.switchStatement();
	return stmnt != null;
    }

}
