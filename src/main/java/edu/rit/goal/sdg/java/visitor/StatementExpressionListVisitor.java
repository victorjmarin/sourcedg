package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementExpressionContext;
import edu.rit.goal.sdg.java.statement.Statement;

public class StatementExpressionListVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitStatementExpressionList(final Java8Parser.StatementExpressionListContext ctx) {
	final List<Statement> result = new ArrayList<>();
	final List<StatementExpressionContext> stmntExprCtx = ctx.statementExpression();
	final StatementExpressionVisitor visitor = new StatementExpressionVisitor();
	stmntExprCtx.forEach(s -> result.add(visitor.visit(s)));
	return result;
    }

}
