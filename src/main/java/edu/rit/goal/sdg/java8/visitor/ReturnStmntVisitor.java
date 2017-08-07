package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.ReturnStmnt;

public class ReturnStmntVisitor extends Java8BaseVisitor<ReturnStmnt> {

    @Override
    public ReturnStmnt visitReturnStatement(final Java8Parser.ReturnStatementContext ctx) {
	// We only support one exit point per method currently
	VisitorUtils.checkForMultipleExitPoints(MethodDeclaratorVisitor.currentMethodName, ctx);
	Expr returnedExpr = null;
	final ExpressionContext exprCtx = ctx.expression();
	if (exprCtx != null) {
	    // Check for function call/assignment in return
	    VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	    final ExprVisitor visitor = new ExprVisitor();
	    returnedExpr = visitor.visit(exprCtx);
	}
	final ReturnStmnt result = new ReturnStmnt(returnedExpr);
	return result;
    }

}
