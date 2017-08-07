package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.IfThenStmt;

public class IfThenStmtVisitor extends Java8BaseVisitor<IfThenStmt> {

    @Override
    public IfThenStmt visitIfThenStatement(final Java8Parser.IfThenStatementContext ctx) {
	final ExprVisitor exprVisitor = new ExprVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	// Check for function call/assignment as guard
	VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	final Expr condition = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StmtVisitor visitor = new StmtVisitor();
	final List<Stmt> thenBranch = visitor.visit(stmntCtx);
	final IfThenStmt result = new IfThenStmt(condition, thenBranch);
	return result;
    }

}
