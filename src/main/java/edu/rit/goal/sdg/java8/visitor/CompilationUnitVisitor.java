package edu.rit.goal.sdg.java8.visitor;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser.CompilationUnitContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParserBaseVisitor;

public class CompilationUnitVisitor extends JavaParserBaseVisitor<Stmt> {

    @Override
    protected Stmt aggregateResult(final Stmt aggregate, final Stmt nextResult) {
	Stmt result = nextResult;
	if (result == null)
	    result = aggregate;
	return result;
    }

    @Override
    public Stmt visitCompilationUnit(final CompilationUnitContext ctx) {
	final List<Stmt> stmts = new LinkedList<>();
	for (final TypeDeclarationContext c : ctx.typeDeclaration()) {
	    final Stmt s = new TypeDeclarationVisitor().visit(c);
	    stmts.add(s);
	}
	return Translator.seq(stmts);
    }

}
