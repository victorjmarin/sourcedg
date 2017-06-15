package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.DoStmnt;

public class DoStmntVisitor extends Java8BaseVisitor<DoStmnt> {

    @Override
    public DoStmnt visitDoStatement(final Java8Parser.DoStatementContext ctx) {
	final StatementVisitor stmntVisitor = new StatementVisitor();
	final StatementContext stmntCtx = ctx.statement();
	final List<Statement> body = stmntVisitor.visit(stmntCtx);
	final ExpressionVisitor exprVisitor = new ExpressionVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	final Expression condition = exprVisitor.visit(exprCtx);
	final DoStmnt result = new DoStmnt(body, condition);
	return result;
    }

}
