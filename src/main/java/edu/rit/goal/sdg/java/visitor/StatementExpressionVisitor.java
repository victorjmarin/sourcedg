package edu.rit.goal.sdg.java.visitor;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.AssignmentContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ClassInstanceCreationExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodInvocationContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.PostDecrementExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.PostIncrementExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.PreDecrementExpressionContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.PreIncrementExpressionContext;
import edu.rit.goal.sdg.java.statement.NotImplementedStmnt;
import edu.rit.goal.sdg.java.statement.PostDecrementExpr;
import edu.rit.goal.sdg.java.statement.PostIncrementExpr;
import edu.rit.goal.sdg.java.statement.PreDecrementExpr;
import edu.rit.goal.sdg.java.statement.PreIncrementExpr;
import edu.rit.goal.sdg.java.statement.Statement;

public class StatementExpressionVisitor extends Java8BaseVisitor<Statement> {

    @Override
    public Statement visitStatementExpression(final Java8Parser.StatementExpressionContext ctx) {
	Statement result = null;
	if (isAssignment(ctx)) {
	    final AssignmentVisitor visitor = new AssignmentVisitor();
	    result = visitor.visit(ctx.assignment());
	} else if (isMethodInv(ctx)) {
	    final MethodInvocationVisitor visitor = new MethodInvocationVisitor();
	    result = visitor.visit(ctx.methodInvocation());
	} else if (isPreIncr(ctx)) {
	    result = new PreIncrementExpr(ctx.preIncrementExpression());
	} else if (isPostIncr(ctx)) {
	    result = new PostIncrementExpr(ctx.postIncrementExpression());
	} else if (isPreDecr(ctx)) {
	    result = new PreDecrementExpr(ctx.preDecrementExpression());
	} else if (isPostDecr(ctx)) {
	    result = new PostDecrementExpr(ctx.postDecrementExpression());
	} else if (isInstanceCreation(ctx)) {
	    // TODO: implement
	    result = new NotImplementedStmnt(this, ctx.classInstanceCreationExpression());
	}
	return result;
    }

    private boolean isAssignment(final Java8Parser.StatementExpressionContext ctx) {
	final AssignmentContext stmnt = ctx.assignment();
	return stmnt != null;
    }

    private boolean isMethodInv(final Java8Parser.StatementExpressionContext ctx) {
	final MethodInvocationContext stmnt = ctx.methodInvocation();
	return stmnt != null;
    }

    private boolean isPostIncr(final Java8Parser.StatementExpressionContext ctx) {
	final PostIncrementExpressionContext stmnt = ctx.postIncrementExpression();
	return stmnt != null;
    }

    private boolean isPreIncr(final Java8Parser.StatementExpressionContext ctx) {
	final PreIncrementExpressionContext stmnt = ctx.preIncrementExpression();
	return stmnt != null;
    }

    private boolean isPostDecr(final Java8Parser.StatementExpressionContext ctx) {
	final PostDecrementExpressionContext stmnt = ctx.postDecrementExpression();
	return stmnt != null;
    }

    private boolean isPreDecr(final Java8Parser.StatementExpressionContext ctx) {
	final PreDecrementExpressionContext stmnt = ctx.preDecrementExpression();
	return stmnt != null;
    }

    private boolean isInstanceCreation(final Java8Parser.StatementExpressionContext ctx) {
	final ClassInstanceCreationExpressionContext stmnt = ctx.classInstanceCreationExpression();
	return stmnt != null;
    }

}
