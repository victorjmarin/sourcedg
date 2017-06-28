package edu.rit.goal.sdg.java.visitor;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.statement.Expression;

public class ExpressionVisitor extends Java8BaseVisitor<Expression> {

    @Override
    public Expression visitExpression(final Java8Parser.ExpressionContext ctx) {
	final Expression result = new Expression(ctx);
	return result;
    }

}
