package edu.rit.goal.sdg.java8.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.BlockStatementsContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ClassBodyDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ClassMemberDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.MethodBodyContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.MethodDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.MethodHeaderContext;
import edu.rit.goal.sdg.statement.MethodSignature;
import edu.rit.goal.sdg.statement.Stmt;

public class ClassBodyVisitor extends Java8BaseVisitor<List<Stmt>> {

    @Override
    protected List<Stmt> aggregateResult(final List<Stmt> aggregate, final List<Stmt> nextResult) {
	List<Stmt> result = nextResult;
	if (result == null)
	    result = aggregate;
	return result;
    }

    @Override
    public List<Stmt> visitClassBody(final Java8Parser.ClassBodyContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	// Iterate over class body declarations
	for (final ClassBodyDeclarationContext classBody : ctx.classBodyDeclaration()) {
	    final ClassMemberDeclarationContext classMember = classBody.classMemberDeclaration();
	    final MethodDeclarationContext methodDeclaration = classMember.methodDeclaration();
	    // We are only interested in methods currently
	    if (methodDeclaration != null) {
		// Method signature
		final MethodHeaderContext methodHeader = methodDeclaration.methodHeader();
		final MethodHeaderVisitor methodHeaderVisitor = new MethodHeaderVisitor();
		final MethodSignature methodSig = methodHeaderVisitor.visit(methodHeader);
		result.add(methodSig);
		// Method body
		final MethodBodyContext methodBody = methodDeclaration.methodBody();
		final BlockContext blockCtx = methodBody.block();
		// Not abstract method
		if (blockCtx != null) {
		    final BlockStatementsContext blockStatementsCtx = blockCtx.blockStatements();
		    // Not empty method
		    if (blockStatementsCtx != null) {
			final BlockStatementsVisitor visitor = new BlockStatementsVisitor();
			final List<Stmt> blockStmnts = visitor.visit(blockStatementsCtx);
			result.addAll(blockStmnts);
		    }
		}
	    }
	}
	return result;
    }

}