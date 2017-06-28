package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementNoShortIfContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;

public class IfThenElseStmntVisitor extends Java8BaseVisitor<IfThenElseStmnt> {

    @Override
    public IfThenElseStmnt visitIfThenElseStatement(final Java8Parser.IfThenElseStatementContext ctx) {
	final ExpressionVisitor exprVisitor = new ExpressionVisitor();
	final ExpressionContext exprCtx = ctx.expression();
	// Check for function call/assignment as guard
	VisitorUtils.checkForUnsupportedFeatures(exprCtx);
	final Expression condition = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StatementNoShortIfContext stmntNoShortIfCtx = ctx.statementNoShortIf();
	final StatementNoShortIfVisitor thenVisitor = new StatementNoShortIfVisitor();
	final StatementVisitor elseVisitor = new StatementVisitor();
	final List<Statement> thenBranch = thenVisitor.visit(stmntNoShortIfCtx);
	final List<Statement> elseBranch = elseVisitor.visit(stmntCtx);
	final IfThenElseStmnt result = new IfThenElseStmnt(condition, thenBranch, elseBranch);
	return result;
    }

}
