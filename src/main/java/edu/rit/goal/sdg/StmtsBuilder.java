package edu.rit.goal.sdg;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.goal.sdg.java8.antlr.Java8Lexer;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.visitor.ClassBodyVisitor;
import edu.rit.goal.sdg.python3.antlr.Python3Lexer;
import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.walker.FileInputVisitor;
import edu.rit.goal.sdg.statement.Stmt;

public class StmtsBuilder {

    private boolean notWrapped = true;

    private enum Language {
	JAVA, PYTHON
    }

    public List<Stmt> from(final String source) {
	List<Stmt> result = null;
	final Lexer lexer;
	CommonTokenStream tokens;
	ParseTree tree;
	Parser parser;
	AbstractParseTreeVisitor<List<Stmt>> visitor;
	final Language lang = detectLang(source);
	final CharStream chrStream = CharStreams.fromString(source);
	switch (lang) {
	case JAVA:
	    lexer = new Java8Lexer(chrStream);
	    tokens = new CommonTokenStream(lexer);
	    parser = new Java8Parser(tokens);
	    tree = ((Java8Parser) parser).compilationUnit();
	    visitor = new ClassBodyVisitor();
	    result = visitor.visit(tree);
	    break;
	case PYTHON:
	    lexer = new Python3Lexer(chrStream);
	    tokens = new CommonTokenStream(lexer);
	    parser = new Python3Parser(tokens);
	    tree = ((Python3Parser) parser).file_input();
	    visitor = new FileInputVisitor();
	    result = visitor.visit(tree);
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

}
