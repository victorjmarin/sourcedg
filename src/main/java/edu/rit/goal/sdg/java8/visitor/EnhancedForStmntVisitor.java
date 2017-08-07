package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.VariableDeclaratorIdContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.EnhancedForStmnt;

public class EnhancedForStmntVisitor extends Java8BaseVisitor<EnhancedForStmnt> {

    @Override
    public EnhancedForStmnt visitEnhancedForStatement(final Java8Parser.EnhancedForStatementContext ctx) {
	final VariableDeclaratorIdContext varDeclIdCtx = ctx.variableDeclaratorId();
	final String var = varDeclIdCtx.getText();
	final ExpressionContext exprCtx = ctx.expression();
	final ExprVisitor exprVisitor = new ExprVisitor();
	final Expr iterable = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StatementVisitor visitor = new StatementVisitor();
	final List<Stmt> body = visitor.visit(stmntCtx);
	final EnhancedForStmnt result = new EnhancedForStmnt(var, iterable, body);
	return result;
    }
}
