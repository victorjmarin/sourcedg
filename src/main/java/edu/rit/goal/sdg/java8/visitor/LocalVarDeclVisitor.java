package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.VariableDeclaratorsContext;

public class LocalVarDeclVisitor {

    public List<Stmt> visit(final LocalVariableDeclarationContext ctx) {
	final List<Stmt> result = new ArrayList<>();
	final VariableDeclaratorsContext varDeclCtx = ctx.variableDeclarators();
	final List<VariableDeclaratorContext> varDeclLst = varDeclCtx.variableDeclarator();
	for (final VariableDeclaratorContext vdc : varDeclLst) {
	    final String varDeclId = vdc.variableDeclaratorId().getText();
	    final ExpressionContext exprCtx = vdc.variableInitializer().expression();
	    final List<ExpressionContext> exprCtxLst = exprCtx.expression();
	    // Regular assignment
	    if (exprCtxLst.isEmpty()) {
		final Assign assign = new Assign(varDeclId, new Str(exprCtx));
		result.add(assign);
	    }
	    // Method call
	    final ExpressionListContext exprLstCtx = exprCtx.expressionList();
	    if (exprLstCtx != null) {
		final String method = exprCtxLst.stream().map(c -> c.getText()).collect(Collectors.joining());
		final List<Str> params = params(exprLstCtx);
		final Param p = Translator.param(params);
		final Assign assign = new Assign(varDeclId, new Call(method, p));
		result.add(assign);
	    }
	}
	return result;
    }

    // TODO: Make more complete conversion. There will be cases in which there will be
    // nested calls as an argument, for example. This will not work in such cases.
    private List<Str> params(final ParseTree ctx) {
	final List<Str> result = new ArrayList<>();
	if (ctx.getChildCount() == 0 && !",".equals(ctx.getText())) {
	    final Str str = new Str(ctx.getText());
	    result.add(str);
	    return result;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result.addAll(params(child));
	}
	return result;
    }

}
