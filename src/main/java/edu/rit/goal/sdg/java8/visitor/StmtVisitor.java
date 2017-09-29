package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Break;
import edu.rit.goal.sdg.interpreter.stmt.Call;
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
import edu.rit.goal.sdg.java8.antlr.JavaParser.CatchClauseContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.CreatorContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FinallyBlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ForControlContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ForInitContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.StatementContext;

public class StmtVisitor {

    private final String className;

    public StmtVisitor(final String className) {
	this.className = className;
    }

    public Stmt visit(final StatementContext ctx) {
	// Returning skip currently instead of null because of semicolons. If
	// contemplating semicolons, they override other statements like
	// System.out.print(k);.
	Stmt result = new Skip();
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
	    final BlockContextVisitor visitor = new BlockContextVisitor(className);
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
	    result = _try(ctx);
	} else if (switchStmt != null) {
	    result = _switch(ctx);
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
	    result = expr(ctx.statementExpression);
	} else if (identifierLbl != null) {
	    Translator.unsupported(ctx);
	}
	if (result == null) {
	    System.out.println(1);
	}
	return result;
    }

    public Stmt _for(final StatementContext ctx) {
	Stmt result = null;
	final Stmt s = new StmtVisitor(className).visit(ctx.statement(0));
	final ForControlContext forCtrlCtx = ctx.forControl();
	final ForInitContext forInitCtx = forCtrlCtx.forInit();
	// TODO: Support for initialization and update of multiple variables
	Stmt si = new Skip();
	Stmt sc = new Skip();
	Stmt su = new Skip();
	if (forInitCtx != null) {
	    final LocalVariableDeclarationContext lclVarDeclCtx = forInitCtx.localVariableDeclaration();
	    if (lclVarDeclCtx != null)
		si = new LocalVarDeclVisitor(className).visit(lclVarDeclCtx);
	    else
		// We are assumming only one expression
		si = expr(forInitCtx.expressionList().expression(0));
	}
	final ExpressionContext exprCtx = forCtrlCtx.expression();
	if (exprCtx != null)
	    sc = expr(exprCtx);
	final ExpressionListContext exprListCtx = forCtrlCtx.expressionList();
	if (exprListCtx != null)
	    // We are assumming only one expression
	    su = expr(exprListCtx.expression(0));
	result = new For(si, sc, su, s);
	return result;
    }

    public Stmt expr(final ExpressionContext exprCtx) {
	Stmt result = null;
	final Token bop = exprCtx.bop;
	final boolean isMethodCall = exprCtx.expressionList() != null || Translator.isEmptyArgCall(exprCtx);
	final boolean isCreator = exprCtx.creator() != null;
	// Distinguish between assignment, call, pre-, post- operators
	if (bop != null) {
	    final boolean isShortHand = Translator.isShortHandOperator(bop.getText());
	    final boolean isAssign = isShortHand || "=".equals(bop.getText());
	    if (isAssign) {
		result = assign(exprCtx, isShortHand);
	    } else {
		// TODO: Assuming a plain condition
		result = new Str(exprCtx);
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
	    result = Translator.call(exprCtx, className);
	} else if (isCreator) {
	    result = creator(exprCtx.creator());
	} else {
	    // TODO: Assuming multiple conditions
	    result = new Str(exprCtx.getText());
	    final Set<String> uses = JavaUtils.uses(exprCtx);
	    result.setUses(uses);
	}
	return result;
    }

    public Stmt assign(final ExpressionContext exprCtx, final boolean isShortHand) {
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
	    s1 = new StmtVisitor(className).visit(thenBranch);
	}
	if (size > 1) {
	    final StatementContext elseBranch = ctx.statement(1);
	    s2 = new StmtVisitor(className).visit(elseBranch);
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
	final Expr e = new Str(exprCtx);
	final StatementContext stmtCtx = ctx.statement(0);
	final Stmt s = new StmtVisitor(className).visit(stmtCtx);
	final DoWhile result = new DoWhile(s, e);
	final Set<String> uses = JavaUtils.uses(exprCtx);
	result.setUses(uses);
	return result;
    }

    public Stmt _while(final StatementContext ctx) {
	final ExpressionContext exprCtx = ctx.parExpression().expression();
	final Expr e = new Str(exprCtx);
	final StatementContext stmtCtx = ctx.statement(0);
	final Stmt s = new StmtVisitor(className).visit(stmtCtx);
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

    public Stmt creator(final CreatorContext ctx) {
	final String methodName = ctx.createdName().getText();
	final ExpressionListContext exprLstCtx = ctx.classCreatorRest().arguments().expressionList();
	final List<Str> params = Translator.params(exprLstCtx);
	final Param p = Translator.param(params, false);
	final String x = Translator.fullMethodName(methodName, className);
	final Call result = new Call(x, p);
	// Using ctx instead of exprLstCtx to compute used because we are interested in
	// retrieving referenced objects, e.g., in s.close() we want s as a use.
	final Set<String> uses = JavaUtils.uses(ctx);
	result.setUses(uses);
	return result;
    }

    // TODO: Try catch blocks are just treated as sequences
    public Stmt _try(final StatementContext ctx) {
	final List<Stmt> stmts = new ArrayList<>();
	final BlockContext blkCtx = ctx.block();
	final BlockContextVisitor blkCtxVisitor = new BlockContextVisitor(className);
	final Stmt tryBlock = blkCtxVisitor.visit(blkCtx);
	stmts.add(tryBlock);
	final List<CatchClauseContext> catchClauseCtx = ctx.catchClause();
	if (catchClauseCtx != null) {
	    for (final CatchClauseContext ccc : catchClauseCtx) {
		final BlockContextVisitor blkCtxVisitor2 = new BlockContextVisitor(className);
		final Stmt s = blkCtxVisitor2.visit(ccc.block());
		stmts.add(s);
	    }
	}
	final FinallyBlockContext finallyBlkCtx = ctx.finallyBlock();
	if (finallyBlkCtx != null) {
	    final BlockContext blkCtx2 = finallyBlkCtx.block();
	    final BlockContextVisitor blkCtxVisitor3 = new BlockContextVisitor(className);
	    final Stmt finallyStmt = blkCtxVisitor3.visit(blkCtx2);
	    stmts.add(finallyStmt);
	}
	return Translator.seq(stmts);
    }

    public Stmt _switch(final StatementContext ctx) {
	final ExpressionContext exprCtx = ctx.parExpression().expression();
	return null;
    }
}
