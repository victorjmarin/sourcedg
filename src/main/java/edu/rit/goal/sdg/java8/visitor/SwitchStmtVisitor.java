package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.control.SwitchBlockStmtGroup;
import edu.rit.goal.sdg.statement.control.SwitchStmt;

public class SwitchStmtVisitor {

    public SwitchStmt visitSwitchContext(final Java8Parser.SwitchStatementContext ctx) {
	final ExpressionContext exprCtx = ctx.expression();
	final ExprVisitor visitor = new ExprVisitor();
	final Expr expr = visitor.visit(exprCtx);
	final SwitchBlockVisitor switchBlockVisitor = new SwitchBlockVisitor();
	final List<SwitchBlockStmtGroup> blocks = switchBlockVisitor.visitSwitchBlock(ctx.switchBlock());
	final SwitchStmt result = new SwitchStmt(expr, blocks);
	return result;
    }

}
