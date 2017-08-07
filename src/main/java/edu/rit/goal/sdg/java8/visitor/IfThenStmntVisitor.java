package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.IfThenStmnt;

public class IfThenStmntVisitor extends Java8BaseVisitor<IfThenStmnt> {

    @Override
    public IfThenStmnt visitIfThenStatement(final Java8Parser.IfThenStatementContext ctx) {
	final ExprVisitor exprVisitor = new ExprVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	// Check for function call/assignment as guard
	VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	final Expr condition = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StatementVisitor visitor = new StatementVisitor();
	final List<Stmt> thenBranch = visitor.visit(stmntCtx);
	final IfThenStmnt result = new IfThenStmnt(condition, thenBranch);
	return result;
    }

}
