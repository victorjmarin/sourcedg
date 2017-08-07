package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ForInitContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.BasicForStmnt;

public class BasicForStmntVisitor extends Java8BaseVisitor<BasicForStmnt> {

    @Override
    public BasicForStmnt visitBasicForStatement(final Java8Parser.BasicForStatementContext ctx) {
	final ForInitVisitor initVisitor = new ForInitVisitor();
	final ForInitContext forInitCtx = ctx.forInit();
	final List<Stmt> init = initVisitor.visitForInit(forInitCtx);
	final StatementExpressionListVisitor updateVisitor = new StatementExpressionListVisitor();
	final List<Stmt> update = updateVisitor.visit(ctx.forUpdate());
	final ExprVisitor exprVisitor = new ExprVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	// Check for function call/assignment as guard
	VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	final Expr condition = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StatementVisitor visitor = new StatementVisitor();
	final List<Stmt> body = visitor.visit(stmntCtx);
	final BasicForStmnt result = new BasicForStmnt(init, condition, update, body);
	return result;
    }

}
