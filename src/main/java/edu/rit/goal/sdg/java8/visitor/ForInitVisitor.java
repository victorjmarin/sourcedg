package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.statement.Stmt;

public class ForInitVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    public List<Stmt> visitForInit(final Java8Parser.ForInitContext ctx) {
	final List<Stmt> result = new ArrayList<>();
	if (ctx == null)
	    return result;
	if (isLocalVarDecl(ctx)) {
	    final LocalVarDeclVisitor visitor = new LocalVarDeclVisitor();
	    final List<Stmt> stmnts = visitor.visit(ctx.localVariableDeclaration());
	    result.addAll(stmnts);
	} else {
	    final StmtExprListVisitor visitor = new StmtExprListVisitor();
	    final List<Stmt> stmnts = visitor.visit(ctx.statementExpressionList());
	    result.addAll(stmnts);
	}
	return result;
    }

    private boolean isLocalVarDecl(final Java8Parser.ForInitContext ctx) {
	final LocalVariableDeclarationContext stmnt = ctx.localVariableDeclaration();
	return stmnt != null;
    }

}
