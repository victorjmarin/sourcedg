package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaParser;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ClassBodyDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParameterContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParameterListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParametersContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.GenericMethodDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MemberDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MethodBodyContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MethodDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeTypeContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeTypeOrVoidContext;

public class ClassBodyVisitor {

    public Stmt visit(final JavaParser.ClassBodyContext ctx, final String className) {
	final List<Stmt> defStmts = new LinkedList<>();
	for (final ClassBodyDeclarationContext clsBodyDeclCtx : ctx.classBodyDeclaration()) {
	    final MemberDeclarationContext memberDeclCtx = clsBodyDeclCtx.memberDeclaration();
	    MethodDeclarationContext methodDeclCtx = memberDeclCtx.methodDeclaration();
	    final GenericMethodDeclarationContext genMethodDecl = memberDeclCtx.genericMethodDeclaration();
	    // Attempt to retrieve generic method is regular method declaration is null
	    if (methodDeclCtx == null)
		methodDeclCtx = genMethodDecl.methodDeclaration();
	    // We are only interested in methods currently
	    if (methodDeclCtx != null) {
		final TypeTypeOrVoidContext typeVoidCtx = methodDeclCtx.typeTypeOrVoid();
		final TypeTypeContext typeCtx = typeVoidCtx.typeType();
		// Method has return type
		final Boolean b = typeCtx != null ? true : false;
		// Method name with format className.methodName
		final String x = className + "." + methodDeclCtx.IDENTIFIER().getText();
		final MethodBodyContext methodBodyCtx = methodDeclCtx.methodBody();
		// Formal parameters
		final List<Str> params = new ArrayList<>();
		final FormalParametersContext formalParamsCtx = methodDeclCtx.formalParameters();
		final FormalParameterListContext formalParamListCtx = formalParamsCtx.formalParameterList();
		if (formalParamListCtx != null) {
		    for (final FormalParameterContext formalParamCtx : formalParamListCtx.formalParameter()) {
			final Str str = new Str(formalParamCtx.variableDeclaratorId().IDENTIFIER());
			params.add(str);
		    }
		}
		// Method body
		final BlockContext blockCtx = methodBodyCtx.block();
		Stmt s = null;
		final BlockContextVisitor visitor = new BlockContextVisitor();
		s = visitor.visit(blockCtx);
		final Def def = new Def(b, x, Translator.param(params, true), s);
		defStmts.add(def);
	    }
	}
	return Translator.seq(defStmts);
    }

}
