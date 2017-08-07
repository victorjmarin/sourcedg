package edu.rit.goal.sdg.java8.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.BasicForStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.EnhancedForStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ForStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.IfThenElseStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.IfThenStatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.StatementWithoutTrailingSubstatementContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.WhileStatementContext;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.BasicForStmt;
import edu.rit.goal.sdg.statement.control.EnhancedForStmt;
import edu.rit.goal.sdg.statement.control.IfThenElseStmt;
import edu.rit.goal.sdg.statement.control.IfThenStmt;
import edu.rit.goal.sdg.statement.control.WhileStmt;

public class StmtVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    public List<Stmt> visitStatement(final Java8Parser.StatementContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	if (isIfThen(ctx)) {
	    final IfThenStmtVisitor visitor = new IfThenStmtVisitor();
	    final IfThenStmt stmnt = visitor.visit(ctx.ifThenStatement());
	    result.add(stmnt);
	} else if (isIfThenElse(ctx)) {
	    final IfThenElseStmtVisitor visitor = new IfThenElseStmtVisitor();
	    final IfThenElseStmt stmnt = visitor.visit(ctx.ifThenElseStatement());
	    result.add(stmnt);
	} else if (isBasicFor(ctx)) {
	    final BasicForStmtVisitor visitor = new BasicForStmtVisitor();
	    final BasicForStmt stmnt = visitor.visit(ctx.forStatement());
	    result.add(stmnt);
	} else if (isEnhancedFor(ctx)) {
	    final EnhancedForStmtVisitor visitor = new EnhancedForStmtVisitor();
	    final EnhancedForStmt stmnt = visitor.visit(ctx.forStatement());
	    result.add(stmnt);
	} else if (isWhile(ctx)) {
	    final WhileStmtVisitor visitor = new WhileStmtVisitor();
	    final WhileStmt stmnt = visitor.visit(ctx.whileStatement());
	    result.add(stmnt);
	} else if (isStmntWoTrailSubstmnt(ctx)) {
	    final StmtWithoutTrailingSubstmtVisitor visitor = new StmtWithoutTrailingSubstmtVisitor();
	    final List<Stmt> stmnts = visitor.visit(ctx.statementWithoutTrailingSubstatement());
	    result.addAll(stmnts);
	}
	return result;
    }

    private boolean isIfThen(final Java8Parser.StatementContext ctx) {
	final IfThenStatementContext stmnt = ctx.ifThenStatement();
	return stmnt != null;
    }

    private boolean isIfThenElse(final Java8Parser.StatementContext ctx) {
	final IfThenElseStatementContext stmnt = ctx.ifThenElseStatement();
	return stmnt != null;
    }

    private boolean isWhile(final Java8Parser.StatementContext ctx) {
	final WhileStatementContext stmnt = ctx.whileStatement();
	return stmnt != null;
    }

    private boolean isBasicFor(final Java8Parser.StatementContext ctx) {
	BasicForStatementContext stmnt = null;
	final ForStatementContext forStmnt = ctx.forStatement();
	if (forStmnt != null)
	    stmnt = forStmnt.basicForStatement();
	return stmnt != null;
    }

    private boolean isEnhancedFor(final Java8Parser.StatementContext ctx) {
	EnhancedForStatementContext stmnt = null;
	final ForStatementContext forStmnt = ctx.forStatement();
	if (forStmnt != null)
	    stmnt = forStmnt.enhancedForStatement();
	return stmnt != null;
    }

    private boolean isStmntWoTrailSubstmnt(final Java8Parser.StatementContext ctx) {
	final StatementWithoutTrailingSubstatementContext stmnt = ctx.statementWithoutTrailingSubstatement();
	return stmnt != null;
    }

}
