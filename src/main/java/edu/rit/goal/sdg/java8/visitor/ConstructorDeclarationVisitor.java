package edu.rit.goal.sdg.java8.visitor;

import java.util.List;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ConstructorDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParametersContext;

public class ConstructorDeclarationVisitor {

    private final String className;

    public ConstructorDeclarationVisitor(final String className) {
	this.className = className;
    }

    public Stmt visit(final ConstructorDeclarationContext ctx) {
	Stmt result = null;
	final BlockContext blockCtx = ctx.block();
	if (blockCtx != null) {
	    // Constructor body
	    Stmt s = null;
	    final BlockVisitor visitor = new BlockVisitor(className);
	    s = visitor.visit(blockCtx);
	    // Method name with format className.methodName
	    final String methodName = ctx.IDENTIFIER().getText();
	    final String x = Translator.fullMethodName(methodName, className);
	    // Formal parameters
	    final FormalParametersContext formalParamsCtx = ctx.formalParameters();
	    final List<Str> params = Translator.formalParams(formalParamsCtx);
	    // Treating constructor as def with returning type void
	    final Def def = new Def(false, x, Translator.param(params, true), s);
	    // Start and end lines of the method
	    def.startLine = ctx.getStart().getLine();
	    def.endLine = ctx.getStop().getLine();
	    result = def;
	}
	return result;
    }

}
