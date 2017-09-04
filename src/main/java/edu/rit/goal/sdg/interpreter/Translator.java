package edu.rit.goal.sdg.interpreter;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaLexer;
import edu.rit.goal.sdg.java8.antlr.JavaParser;
import edu.rit.goal.sdg.java8.visitor.ClassBodyVisitor;

public class Translator {
    private boolean notWrapped = true;

    private enum Language {
	JAVA, PYTHON
    }

    public Stmt from(final String source) {
	Stmt result = null;
	final Lexer lexer;
	CommonTokenStream tokens;
	ParseTree tree;
	Parser parser;
	AbstractParseTreeVisitor<Stmt> visitor;
	final Language lang = detectLang(source);
	final CharStream chrStream = CharStreams.fromString(source);
	switch (lang) {
	case JAVA:
	    lexer = new JavaLexer(chrStream);
	    tokens = new CommonTokenStream(lexer);
	    parser = new JavaParser(tokens);
	    tree = ((JavaParser) parser).compilationUnit();
	    visitor = new ClassBodyVisitor();
	    result = visitor.visit(tree);
	    break;
	case PYTHON:
	    break;
	}
	if (result == null && notWrapped) {
	    notWrapped = false;
	    result = from(wrap(source));
	}
	notWrapped = true;
	return result;
    }

    protected Language detectLang(final String source) {
	Language result = Language.JAVA;
	if ((source.contains("def ") && source.contains(":"))
		|| (!source.contains("{") && !source.contains("}") && !source.contains(";"))) {
	    result = Language.PYTHON;
	}
	return result;
    }

    public static String wrap(final String source) {
	final String cls = "public class WrapperClass {";
	final StringBuilder sb = new StringBuilder(cls);
	sb.append(source);
	sb.append("}");
	return sb.toString();
    }

    public static Param param(final List<Str> params) {
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

    public static Stmt seq(final List<Stmt> stmts) {
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
