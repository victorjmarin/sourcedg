package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.graph.PrimitiveType;
import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.MethodDeclaratorContext;
import edu.rit.goal.sdg.statement.MethodSignature;

public class MethodHeaderVisitor extends Java8BaseVisitor<MethodSignature> {

    @Override
    public MethodSignature visitMethodHeader(final Java8Parser.MethodHeaderContext ctx) {
	final MethodDeclaratorContext methodDeclarator = ctx.methodDeclarator();
	final MethodDeclaratorVisitor methodDeclVisitor = new MethodDeclaratorVisitor();
	final MethodSignature result = methodDeclVisitor.visit(methodDeclarator);
	final PrimitiveType returnType = getReturnType(ctx.result());
	result.setReturnType(returnType);
	return result;
    }

    private PrimitiveType getReturnType(final Java8Parser.ResultContext ctx) {
	// TODO: Implement the rest of types
	PrimitiveType result = PrimitiveType.UNK;
	if (ctx.getText().equals("void"))
	    result = PrimitiveType.VOID;
	return result;
    }

}
