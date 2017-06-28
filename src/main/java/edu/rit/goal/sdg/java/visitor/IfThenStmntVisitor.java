package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;

public class IfThenStmntVisitor extends Java8BaseVisitor<IfThenStmnt> {

    @Override
    public IfThenStmnt visitIfThenStatement(final Java8Parser.IfThenStatementContext ctx) {
	final ExpressionVisitor exprVisitor = new ExpressionVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	// Check for function call/assignment as guard
	VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	final Expression condition = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StatementVisitor visitor = new StatementVisitor();
	final List<Statement> thenBranch = visitor.visit(stmntCtx);
	final IfThenStmnt result = new IfThenStmnt(condition, thenBranch);
	return result;
    }

}
