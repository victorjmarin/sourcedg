package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.control.SwitchBlockStmntGroup;
import edu.rit.goal.sdg.statement.control.SwitchStmnt;

public class SwitchStmntVisitor {

    public SwitchStmnt visitSwitchContext(final Java8Parser.SwitchStatementContext ctx) {
	final ExpressionContext exprCtx = ctx.expression();
	final ExprVisitor visitor = new ExprVisitor();
	final Expr expr = visitor.visit(exprCtx);
	final SwitchBlockVisitor switchBlockVisitor = new SwitchBlockVisitor();
	final List<SwitchBlockStmntGroup> blocks = switchBlockVisitor.visitSwitchBlock(ctx.switchBlock());
	final SwitchStmnt result = new SwitchStmnt(expr, blocks);
	return result;
    }

}
