package edu.rit.goal.sdg.java8.visitor;

import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Expr;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.Return;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.JavaUtils;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.StatementContext;

public class StmtVisitor {

    public Stmt visit(final StatementContext ctx) {
	Stmt result = null;
	final BlockContext blockLabel = ctx.blockLabel;
	final TerminalNode assertStmt = ctx.ASSERT();
	final TerminalNode ifStmt = ctx.IF();
	final TerminalNode forStmt = ctx.FOR();
	final TerminalNode whileStmt = ctx.WHILE();
	final TerminalNode doStmt = ctx.DO();
	final TerminalNode tryStmt = ctx.TRY();
	final TerminalNode switchStmt = ctx.SWITCH();
	final TerminalNode syncStmt = ctx.ASSERT();
	final TerminalNode returnStmt = ctx.RETURN();
	final TerminalNode throwStmt = ctx.ASSERT();
	final TerminalNode breakStmt = ctx.ASSERT();
	final TerminalNode continueStmt = ctx.ASSERT();
	final ExpressionContext exprCtx = ctx.statementExpression;
	final Token identifierLbl = ctx.identifierLabel;
	if (blockLabel != null) {
	    final BlockContextVisitor visitor = new BlockContextVisitor();
	    result = visitor.visit(ctx.block());
	} else if (assertStmt != null) {
	    Translator.unsupported(ctx);
	} else if (ifStmt != null) {
	    result = ifThenElse(ctx);
	} else if (forStmt != null) {
	    Translator.unsupported(ctx);
	} else if (whileStmt != null) {
	    Translator.unsupported(ctx);
	} else if (doStmt != null) {
	    Translator.unsupported(ctx);
	} else if (tryStmt != null) {
	    Translator.unsupported(ctx);
	} else if (switchStmt != null) {
	    Translator.unsupported(ctx);
	} else if (syncStmt != null) {
	    Translator.unsupported(ctx);
	} else if (returnStmt != null) {
	    result = ret(ctx);
	} else if (throwStmt != null) {
	    Translator.unsupported(ctx);
	} else if (breakStmt != null) {
	    Translator.unsupported(ctx);
	} else if (continueStmt != null) {
	    Translator.unsupported(ctx);
	} else if (exprCtx != null) {
	    // Distinguish between assignment and call
	    final Token bop = exprCtx.bop;
	    final boolean isMethodCall = exprCtx.expressionList() != null;
	    if (bop != null && bop.getText().equals("=")) {
		result = assign(ctx);
	    } else if (isMethodCall) {
		result = Translator.call(exprCtx);
	    }
	} else if (identifierLbl != null) {
	    Translator.unsupported(ctx);
	}
	return result;
    }

    public Stmt assign(final StatementContext ctx) {
	final ExpressionContext exprCtx = ctx.expression(0);
	final String x = exprCtx.expression(0).getText();
	final Str e = new Str(exprCtx.expression(1).getText());
	final Assign result = new Assign(x, e);
	final Set<String> uses = JavaUtils.uses(exprCtx.expression(1));
	result.setDef(x);
	result.setUses(uses);
	return result;
    }

    public Stmt ifThenElse(final StatementContext ctx) {
	final int size = ctx.statement().size();
	Stmt s1 = new Skip();
	Stmt s2 = new Skip();
	if (size > 0) {
	    final StatementContext thenBranch = ctx.statement(0);
	    s1 = new StmtVisitor().visit(thenBranch);
	}
	if (size > 1) {
	    final StatementContext elseBranch = ctx.statement(1);
	    s2 = new StmtVisitor().visit(elseBranch);
	}
	final ExpressionContext exprCtx = ctx.parExpression().expression();
	final Expr e = new Str(exprCtx.getText());
	final IfThenElse result = new IfThenElse(e, s1, s2);
	final Set<String> uses = JavaUtils.uses(exprCtx);
	result.setUses(uses);
	return result;
    }

    public Stmt ret(final StatementContext ctx) {
	final List<ExpressionContext> expr = ctx.expression();
	String e = "";
	if (expr.size() == 1) {
	    e = expr.get(0).getText();
	}
	final Return result = new Return(e);
	final Set<String> uses = JavaUtils.uses(expr.get(0));
	result.setUses(uses);
	return result;
    }
}
