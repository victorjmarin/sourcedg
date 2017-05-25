package edu.rit.goal.sdg.java.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BlockContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.BlockStatementsContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ClassBodyDeclarationContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.ClassMemberDeclarationContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodBodyContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodDeclarationContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodDeclaratorContext;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.Statement;

public class ClassBodyVisitor extends Java8BaseVisitor<SysDepGraph> {
    @Override
    public SysDepGraph visitClassBody(final Java8Parser.ClassBodyContext ctx) {
	final List<Statement> stmnts = new LinkedList<>();
	// Iterate over class body declarations
	for (final ClassBodyDeclarationContext classBody : ctx.classBodyDeclaration()) {
	    final ClassMemberDeclarationContext classMember = classBody.classMemberDeclaration();
	    final MethodDeclarationContext methodDeclaration = classMember.methodDeclaration();
	    // We are only interested in methods currently
	    if (methodDeclaration != null) {
		// Method signature
		final MethodDeclaratorContext methodDeclarator = methodDeclaration.methodHeader().methodDeclarator();
		final MethodDeclaratorVisitor methodDeclVisitor = new MethodDeclaratorVisitor();
		final MethodSignature methodSig = methodDeclVisitor.visit(methodDeclarator);
		stmnts.add(methodSig);
		// Method body
		final MethodBodyContext methodBody = methodDeclaration.methodBody();
		final BlockContext blockCtx = methodBody.block();
		// Not abstract method
		if (blockCtx != null) {
		    final BlockStatementsContext blockStatementsCtx = blockCtx.blockStatements();
		    // Not empty method
		    if (blockStatementsCtx != null) {
			final BlockStatementsVisitor visitor = new BlockStatementsVisitor();
			final List<Statement> blockStmnts = visitor.visit(blockStatementsCtx);
			stmnts.addAll(blockStmnts);
		    }
		}
	    }
	}
	final SysDepGraph result = buildSdg(stmnts);
	return result;
    }

    private SysDepGraph buildSdg(final List<Statement> stmnts) {
	final SysDepGraph result = new SysDepGraph();
	for (final Statement s : stmnts) {
	    if (s != null)
		s.buildSdg(result);
	}
	return result;
    }

}