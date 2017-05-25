package edu.rit.goal.sdg.java.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BlockContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BlockStatementsContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ExpressionStatementContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementExpressionContext;
import edu.rit.goal.sdg.java.statement.Statement;

public class StmntWithoutTrailingSubstmntVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitStatementWithoutTrailingSubstatement(
	    final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	List<Statement> result = null;
	final ExpressionStatementContext exprStmntCtx = ctx.expressionStatement();
	final BlockContext blockCtx = ctx.block();
	if (exprStmntCtx != null) {
	    final StatementExpressionContext stmntExprCtx = exprStmntCtx.statementExpression();
	    final StatementExpressionVisitor visitor = new StatementExpressionVisitor();
	    final Statement stmnt = visitor.visit(stmntExprCtx);
	    result = new LinkedList<>();
	    result.add(stmnt);
	} else if (blockCtx != null) {
	    final BlockStatementsContext blockStmntsCtx = blockCtx.blockStatements();
	    final BlockStatementsVisitor visitor = new BlockStatementsVisitor();
	    result = visitor.visit(blockStmntsCtx);
	}
	return result;
    }

}
