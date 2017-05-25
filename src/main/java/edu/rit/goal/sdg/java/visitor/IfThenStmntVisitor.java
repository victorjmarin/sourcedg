package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.StatementContext;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.Statement;

public class IfThenStmntVisitor extends Java8BaseVisitor<IfThenStmnt> {

    @Override
    public IfThenStmnt visitIfThenStatement(final Java8Parser.IfThenStatementContext ctx) {
	final Expression condition = new Expression(ctx.expression());
	final StatementContext stmntCtx = ctx.statement();
	final StatementVisitor visitor = new StatementVisitor();
	final List<Statement> thenBranch = visitor.visit(stmntCtx);
	final IfThenStmnt result = new IfThenStmnt(condition, thenBranch);
	return result;
    }

}
