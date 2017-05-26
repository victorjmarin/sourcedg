package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;

public class WhileStmntVisitor extends Java8BaseVisitor<WhileStmnt> {

    @Override
    public WhileStmnt visitWhileStatement(final Java8Parser.WhileStatementContext ctx) {
	final ExpressionVisitor exprVisitor = new ExpressionVisitor();
	final Expression condition = exprVisitor.visit(ctx.expression());
	final StatementContext stmntCtx = ctx.statement();
	final StatementVisitor visitor = new StatementVisitor();
	final List<Statement> body = visitor.visit(stmntCtx);
	final WhileStmnt result = new WhileStmnt(condition, body);
	return result;
    }

}
