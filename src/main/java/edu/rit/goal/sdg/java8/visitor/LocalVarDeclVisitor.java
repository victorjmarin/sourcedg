package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.JavaUtils;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.VariableDeclaratorsContext;

public class LocalVarDeclVisitor {

    public Stmt visit(final LocalVariableDeclarationContext ctx) {
	final List<Stmt> result = new ArrayList<>();
	final VariableDeclaratorsContext varDeclCtx = ctx.variableDeclarators();
	final List<VariableDeclaratorContext> varDeclLst = varDeclCtx.variableDeclarator();
	for (final VariableDeclaratorContext vdc : varDeclLst) {
	    final String x = vdc.variableDeclaratorId().getText();
	    final ExpressionContext exprCtx = vdc.variableInitializer().expression();
	    // Method call
	    final ExpressionListContext exprLstCtx = exprCtx.expressionList();
	    if (exprLstCtx != null) {
		final Call e = Translator.call(exprCtx);
		final Assign assign = new Assign(x, e);
		assign.setDef(x);
		assign.setUses(e.getUses());
		result.add(assign);
	    }
	    // Regular assignment
	    else {
		final Assign assign = new Assign(x, new Str(exprCtx));
		final Set<String> uses = JavaUtils.uses(exprCtx);
		assign.setDef(x);
		assign.setUses(uses);
		result.add(assign);
	    }

	}
	return Translator.seq(result);
    }

}
