package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.control.SwitchBlockStmntGroup;
import edu.rit.goal.sdg.java.statement.control.SwitchStmnt;

public class SwitchStmntVisitor {

    public SwitchStmnt visitSwitchContext(final Java8Parser.SwitchStatementContext ctx) {
	final ExpressionContext exprCtx = ctx.expression();
	final ExpressionVisitor visitor = new ExpressionVisitor();
	final Expression expr = visitor.visit(exprCtx);
	final SwitchBlockVisitor switchBlockVisitor = new SwitchBlockVisitor();
	final List<SwitchBlockStmntGroup> blocks = switchBlockVisitor.visitSwitchBlock(ctx.switchBlock());
	final SwitchStmnt result = new SwitchStmnt(expr, blocks);
	return result;
    }

}
