package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.VariableDeclaratorIdContext;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.EnhancedForStmt;

public class EnhancedForStmtVisitor extends Java8BaseVisitor<EnhancedForStmt> {

    @Override
    public EnhancedForStmt visitEnhancedForStatement(final Java8Parser.EnhancedForStatementContext ctx) {
	final VariableDeclaratorIdContext varDeclIdCtx = ctx.variableDeclaratorId();
	final String var = varDeclIdCtx.getText();
	final ExpressionContext exprCtx = ctx.expression();
	final ExprVisitor exprVisitor = new ExprVisitor();
	final Expr iterable = exprVisitor.visit(exprCtx);
	final StatementContext stmntCtx = ctx.statement();
	final StmtVisitor visitor = new StmtVisitor();
	final List<Stmt> body = visitor.visit(stmntCtx);
	final EnhancedForStmt result = new EnhancedForStmt(var, iterable, body);
	return result;
    }
}
