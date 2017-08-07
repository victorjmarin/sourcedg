package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.statement.Expr;

public class ExprVisitor extends Java8BaseVisitor<Expr> {

    @Override
    public Expr visitExpression(final Java8Parser.ExpressionContext ctx) {
	final Expr result = new Expr(ctx);
	return result;
    }

}
