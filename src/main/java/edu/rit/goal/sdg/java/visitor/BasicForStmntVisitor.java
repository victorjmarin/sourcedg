package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;

public class BasicForStmntVisitor extends Java8BaseVisitor<BasicForStmnt> {

    @Override
    public BasicForStmnt visitBasicForStatement(final Java8Parser.BasicForStatementContext ctx) {
	final ForInitVisitor initVisitor = new ForInitVisitor();
	final List<Statement> init = initVisitor.visit(ctx.forInit());
	final StatementExpressionListVisitor updateVisitor = new StatementExpressionListVisitor();
	final List<Statement> update = updateVisitor.visit(ctx.forUpdate());
	final ExpressionVisitor exprVisitor = new ExpressionVisitor();
	final Expression condition = exprVisitor.visit(ctx.expression());
	final StatementContext stmntCtx = ctx.statement();
	final StatementVisitor visitor = new StatementVisitor();
	final List<Statement> body = visitor.visit(stmntCtx);
	final BasicForStmnt result = new BasicForStmnt(init, condition, update, body);
	return result;
    }

}
