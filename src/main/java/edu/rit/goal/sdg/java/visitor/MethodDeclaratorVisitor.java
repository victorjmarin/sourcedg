package edu.rit.goal.sdg.java.visitor;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.statement.FormalParameter;
import edu.rit.goal.sdg.java.statement.MethodSignature;

public class MethodDeclaratorVisitor extends Java8BaseVisitor<MethodSignature> {

    @Override
    public MethodSignature visitMethodDeclarator(final Java8Parser.MethodDeclaratorContext ctx) {
	final TerminalNode identifier = ctx.Identifier();
	final String methodName = identifier.getText();
	final FormalParameterListVisitor parameterVisitor = new FormalParameterListVisitor();
	final List<FormalParameter> params = parameterVisitor.visit(ctx.formalParameterList());
	final MethodSignature result = new MethodSignature(methodName, params);
	return result;
    }

}