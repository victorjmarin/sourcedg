package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ConstantExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.EnumConstantNameContext;

public class SwitchLabelVisitor {

    public String visitSwitchLabel(final Java8Parser.SwitchLabelContext ctx) {
	// default case
	String result = "default";
	if (isConstantExpr(ctx)) {
	    result = ctx.constantExpression().getText();
	} else if (isEnumConstantName(ctx)) {
	    result = ctx.enumConstantName().Identifier().getText();
	}
	return result;
    }

    private boolean isConstantExpr(final Java8Parser.SwitchLabelContext ctx) {
	final ConstantExpressionContext stmnt = ctx.constantExpression();
	return stmnt != null;
    }

    private boolean isEnumConstantName(final Java8Parser.SwitchLabelContext ctx) {
	final EnumConstantNameContext stmnt = ctx.enumConstantName();
	return stmnt != null;
    }

}
