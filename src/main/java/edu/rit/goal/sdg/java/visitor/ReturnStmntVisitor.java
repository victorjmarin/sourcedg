package edu.rit.goal.sdg.java.visitor;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.ReturnStmnt;

public class ReturnStmntVisitor extends Java8BaseVisitor<ReturnStmnt> {

    @Override
    public ReturnStmnt visitReturnStatement(final Java8Parser.ReturnStatementContext ctx) {
	Expression returnedExpr = null;
	final ExpressionContext exprCtx = ctx.expression();
	if (exprCtx != null) {
	    final ExpressionVisitor visitor = new ExpressionVisitor();
	    returnedExpr = visitor.visit(exprCtx);
	}
	final ReturnStmnt result = new ReturnStmnt(returnedExpr);
	return result;
    }

}
