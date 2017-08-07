package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.FormalParameterListContext;
import edu.rit.goal.sdg.statement.FormalParameter;
import edu.rit.goal.sdg.statement.MethodSignature;

public class MethodDeclaratorVisitor extends Java8BaseVisitor<MethodSignature> {

    public static String currentMethodName;

    @Override
    public MethodSignature visitMethodDeclarator(final Java8Parser.MethodDeclaratorContext ctx) {
	final TerminalNode identifier = ctx.Identifier();
	final String methodName = identifier.getText();
	currentMethodName = methodName;
	final FormalParamListVisitor parameterVisitor = new FormalParamListVisitor();
	final FormalParameterListContext formalParamListCtx = ctx.formalParameterList();
	List<FormalParameter> params = null;
	if (formalParamListCtx != null) {
	    params = parameterVisitor.visit(formalParamListCtx);
	}
	final MethodSignature result = new MethodSignature(methodName, params);
	return result;
    }

}