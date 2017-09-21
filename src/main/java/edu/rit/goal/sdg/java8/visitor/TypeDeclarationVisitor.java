package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ClassBodyContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ClassDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeDeclarationContext;

public class TypeDeclarationVisitor {

    public Stmt visit(final TypeDeclarationContext ctx) {
	final ClassDeclarationContext clsDeclCtx = ctx.classDeclaration();
	final ClassBodyContext clsBodyCtx = clsDeclCtx.classBody();
	final String className = clsDeclCtx.IDENTIFIER().getText();
	return new ClassBodyVisitor().visit(clsBodyCtx, className);
    }

}
