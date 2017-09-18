package edu.rit.goal.sdg.java8.visitor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Break;
import edu.rit.goal.sdg.interpreter.stmt.Continue;
import edu.rit.goal.sdg.interpreter.stmt.DoWhile;
import edu.rit.goal.sdg.interpreter.stmt.Expr;
import edu.rit.goal.sdg.interpreter.stmt.For;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.PostOp;
import edu.rit.goal.sdg.interpreter.stmt.PreOp;
import edu.rit.goal.sdg.interpreter.stmt.Return;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.interpreter.stmt.While;
import edu.rit.goal.sdg.java8.JavaUtils;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ForControlContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ForInitContext;
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
	final TerminalNode syncStmt = ctx.SYNCHRONIZED();
	final TerminalNode returnStmt = ctx.RETURN();
	final TerminalNode throwStmt = ctx.THROW();
	final TerminalNode breakStmt = ctx.BREAK();
	final TerminalNode continueStmt = ctx.CONTINUE();
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
	    result = _for(ctx);
	} else if (doStmt != null) {
	    result = doWhile(ctx);
	} else if (whileStmt != null) {
	    result = _while(ctx);
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
	    result = new Break();
	} else if (continueStmt != null) {
	    result = new Continue();
	} else if (exprCtx != null) {
	    result = expr(ctx, ctx.statementExpression);
	} else if (identifierLbl != null) {
	    Translator.unsupported(ctx);
	}
	return result;
    }

    public Stmt _for(final StatementContext ctx) {
	Stmt result = null;
	final Stmt s = new StmtVisitor().visit(ctx.statement(0));
	final ForControlContext forCtrlCtx = ctx.forControl();
	final ForInitContext forInitCtx = forCtrlCtx.forInit();
	// TODO: Support for initialization and update of multiple variables
	Stmt si = new Skip();
	Stmt sc = new Skip();
	Stmt su = new Skip();
	if (forInitCtx != null)
	    si = new LocalVarDeclVisitor().visit(forInitCtx.localVariableDeclaration());
	final ExpressionContext exprCtx = forCtrlCtx.expression();
	if (exprCtx != null)
	    sc = expr(ctx, exprCtx);
	final ExpressionListContext exprListCtx = forCtrlCtx.expressionList();
	if (exprListCtx != null)
	    su = expr(ctx, exprListCtx.expression(0));
	result = new For(si, sc, su, s);
	return result;
    }

    public Stmt expr(final StatementContext ctx, final ExpressionContext exprCtx) {
	Stmt result = null;
	final Token bop = exprCtx.bop;
	final boolean isMethodCall = exprCtx.expressionList() != null || Translator.isEmptyArgCall(exprCtx);
	// Distinguish between assignment, call, pre-, post- operators
	if (bop != null) {
	    final boolean isShortHand = Translator.isShortHandOperator(bop.getText());
	    final boolean isAssign = isShortHand || "=".equals(bop.getText());
	    if (isAssign) {
		result = assign(ctx, isShortHand);
	    } else {
		// TODO: Assuming a plain condition
		result = new Str(exprCtx.getText());
		final Set<String> uses = JavaUtils.uses(exprCtx);
		result.setUses(uses);
	    }
	} else if (exprCtx.prefix != null) {
	    final ExpressionContext exprCtx2 = exprCtx.expression(0);
	    final String x = exprCtx2.getText();
	    result = new PreOp(x, exprCtx.prefix.getText());
	    result.setDef(x);
	    final Set<String> uses = new HashSet<>();
	    uses.add(x);
	    result.setUses(uses);
	} else if (exprCtx.postfix != null) {
	    final ExpressionContext exprCtx2 = exprCtx.expression(0);
	    final String x = exprCtx2.getText();
	    result = new PostOp(x, exprCtx.postfix.getText());
	    result.setDef(x);
	    final Set<String> uses = new HashSet<>();
	    uses.add(x);
	    result.setUses(uses);
	} else if (isMethodCall) {
	    result = Translator.call(exprCtx);
	}
	return result;
    }

    public Stmt assign(final StatementContext ctx, final boolean isShortHand) {
	final ExpressionContext exprCtx = ctx.expression(0);
	final String x = exprCtx.expression(0).getText();
	final String op = exprCtx.bop.getText();
	final Str e = new Str(exprCtx.expression(1).getText());
	final Assign result = new Assign(x, op, e);
	final Set<String> uses = JavaUtils.uses(exprCtx.expression(1));
	result.setDef(x);
	result.setUses(uses);
	if (isShortHand)
	    result.getUses().add(x);
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

    public Stmt doWhile(final StatementContext ctx) {
	final ExpressionContext exprCtx = ctx.parExpression().expression();
	final Expr e = new Str(exprCtx.getText());
	final StatementContext stmtCtx = ctx.statement(0);
	final Stmt s = new StmtVisitor().visit(stmtCtx);
	final DoWhile result = new DoWhile(s, e);
	final Set<String> uses = JavaUtils.uses(exprCtx);
	result.setUses(uses);
	return result;
    }

    public Stmt _while(final StatementContext ctx) {
	final ExpressionContext exprCtx = ctx.parExpression().expression();
	final Expr e = new Str(exprCtx.getText());
	final StatementContext stmtCtx = ctx.statement(0);
	final Stmt s = new StmtVisitor().visit(stmtCtx);
	final While result = new While(e, s);
	final Set<String> uses = JavaUtils.uses(exprCtx);
	result.setUses(uses);
	return result;
    }

    public Stmt ret(final StatementContext ctx) {
	final List<ExpressionContext> expr = ctx.expression();
	String e = "";
	Set<String> uses = new HashSet<>();
	if (expr.size() == 1) {
	    e = expr.get(0).getText();
	    uses = JavaUtils.uses(expr.get(0));
	}
	final Return result = new Return(e);
	result.setUses(uses);
	return result;
    }
}
