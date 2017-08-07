package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.DoStmnt;

public class DoStmntVisitor extends Java8BaseVisitor<DoStmnt> {

    @Override
    public DoStmnt visitDoStatement(final Java8Parser.DoStatementContext ctx) {
	final StatementVisitor stmntVisitor = new StatementVisitor();
	final StatementContext stmntCtx = ctx.statement();
	final List<Stmt> body = stmntVisitor.visit(stmntCtx);
	final ExprVisitor exprVisitor = new ExprVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	// Check for function call/assignment as guard
	VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	final Expr condition = exprVisitor.visit(exprCtx);
	final DoStmnt result = new DoStmnt(body, condition);
	return result;
    }

}
