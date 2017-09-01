package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaParser;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockStatementContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ClassBodyDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParameterContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParameterListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParametersContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MemberDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MethodBodyContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MethodDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeTypeContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeTypeOrVoidContext;
import edu.rit.goal.sdg.java8.antlr.JavaParserBaseVisitor;

public class ClassBodyVisitor extends JavaParserBaseVisitor<Stmt> {

    @Override
    protected Stmt aggregateResult(final Stmt aggregate, final Stmt nextResult) {
	Stmt result = nextResult;
	if (result == null)
	    result = aggregate;
	return result;
    }

    @Override
    public Stmt visitClassBody(final JavaParser.ClassBodyContext ctx) {
	final List<Stmt> defStmts = new LinkedList<>();
	for (final ClassBodyDeclarationContext clsBodyDeclCtx : ctx.classBodyDeclaration()) {
	    final MemberDeclarationContext memberDeclCtx = clsBodyDeclCtx.memberDeclaration();
	    final MethodDeclarationContext methodDeclCtx = memberDeclCtx.methodDeclaration();
	    // We are only interested in methods currently
	    if (methodDeclCtx != null) {
		final TypeTypeOrVoidContext typeVoidCtx = methodDeclCtx.typeTypeOrVoid();
		final TypeTypeContext typeCtx = typeVoidCtx.typeType();
		// Method has return type
		final Boolean b = typeCtx != null ? true : false;
		// Method name
		final String x = methodDeclCtx.IDENTIFIER().getText();
		final MethodBodyContext methodBodyCtx = methodDeclCtx.methodBody();
		// Formal parameters
		final List<Str> params = new ArrayList<>();
		final FormalParametersContext formalParamsCtx = methodDeclCtx.formalParameters();
		final FormalParameterListContext formalParamListCtx = formalParamsCtx.formalParameterList();
		if (formalParamListCtx != null) {
		    for (final FormalParameterContext formalParamCtx : formalParamListCtx.formalParameter()) {
			final Str str = new Str(formalParamCtx.variableDeclaratorId().getText());
			params.add(str);
		    }
		}
		// Method body
		final BlockContext blockCtx = methodBodyCtx.block();
		// Not abstract method
		final List<Stmt> stmts = new LinkedList<>();
		if (blockCtx != null) {
		    final List<BlockStatementContext> blockStatementsCtx = blockCtx.blockStatement();
		    // Not empty method
		    if (blockStatementsCtx != null) {
			for (final BlockStatementContext bsc : blockStatementsCtx) {

			}
		    }

		}
		final Def def = new Def(b, x, param(params), seq(stmts));
		defStmts.add(def);
	    }
	}
	return seq(defStmts);
    }

    private Param param(final List<Str> params) {
	Param result = null;
	if (params.isEmpty()) {
	    result = new EmptyParam();
	} else if (params.size() == 1) {
	    result = params.remove(0);
	} else {
	    final String x = params.remove(0).value;
	    result = new Params(x, param(params));
	}
	return result;
    }

    private Stmt seq(final List<Stmt> stmts) {
	Stmt result = null;
	if (stmts.isEmpty()) {
	    result = new Skip();
	} else if (stmts.size() == 1) {
	    result = stmts.remove(0);
	} else {
	    final Stmt s = stmts.remove(0);
	    result = new Seq(s, seq(stmts));
	}
	return result;
    }

}
