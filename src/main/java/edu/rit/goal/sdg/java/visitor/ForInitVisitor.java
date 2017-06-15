package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java.statement.Statement;

public class ForInitVisitor extends Java8BaseVisitor<List<Statement>> {

    @Override
    public List<Statement> visitForInit(final Java8Parser.ForInitContext ctx) {
	final List<Statement> result = new ArrayList<>();
	if (ctx == null)
	    return result;
	if (isLocalVarDecl(ctx)) {
	    final LocalVariableDeclarationVisitor visitor = new LocalVariableDeclarationVisitor();
	    final List<Statement> stmnts = visitor.visit(ctx.localVariableDeclaration());
	    result.addAll(stmnts);
	} else {
	    final StatementExpressionListVisitor visitor = new StatementExpressionListVisitor();
	    final List<Statement> stmnts = visitor.visit(ctx.statementExpressionList());
	    result.addAll(stmnts);
	}
	return result;
    }

    private boolean isLocalVarDecl(final Java8Parser.ForInitContext ctx) {
	final LocalVariableDeclarationContext stmnt = ctx.localVariableDeclaration();
	return stmnt != null;
    }

}
