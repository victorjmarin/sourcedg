package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementNoShortIfContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.IfThenElseStmt;

public class IfThenElseStmtVisitor extends Java8BaseVisitor<IfThenElseStmt> {

    @Override
    public IfThenElseStmt visitIfThenElseStatement(final Java8Parser.IfThenElseStatementContext ctx) {
	final ExprVisitor exprVisitor = new ExprVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	// Check for function call/assignment as guard
	VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	final Expr condition = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StatementNoShortIfContext stmntNoShortIfCtx = ctx.statementNoShortIf();
	final StmtNoShortIfVisitor thenVisitor = new StmtNoShortIfVisitor();
	final StmtVisitor elseVisitor = new StmtVisitor();
	final List<Stmt> thenBranch = thenVisitor.visit(stmntNoShortIfCtx);
	final List<Stmt> elseBranch = elseVisitor.visit(stmntCtx);
	final IfThenElseStmt result = new IfThenElseStmt(condition, thenBranch, elseBranch);
	return result;
    }

}
