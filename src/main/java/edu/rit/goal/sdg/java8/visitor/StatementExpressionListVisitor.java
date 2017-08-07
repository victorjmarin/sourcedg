package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementExpressionContext;
import edu.rit.goal.sdg.statement.Stmt;

public class StatementExpressionListVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    public List<Stmt> visitStatementExpressionList(final Java8Parser.StatementExpressionListContext ctx) {
	final List<Stmt> result = new ArrayList<>();
	final List<StatementExpressionContext> stmntExprCtx = ctx.statementExpression();
	final StatementExpressionVisitor visitor = new StatementExpressionVisitor();
	stmntExprCtx.forEach(s -> result.add(visitor.visit(s)));
	return result;
    }

}
