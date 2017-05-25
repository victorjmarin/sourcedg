package edu.rit.goal.sdg.java;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import edu.rit.goal.sdg.java.antlr.Java8Lexer;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.visitor.ClassBodyVisitor;

public class Java8SdgBuilder {

    private SysDepGraph sdg;
    
    public SysDepGraph buildSdg(final String program) {
	final CharStream chrStream = CharStreams.fromString(program);
	final Lexer lexer = new Java8Lexer(chrStream);
	final CommonTokenStream tokens = new CommonTokenStream(lexer);
	final Java8Parser parser = new Java8Parser(tokens);
	final ClassBodyVisitor visitor = new ClassBodyVisitor();
	sdg = visitor.visit(parser.classDeclaration());
	return sdg;
    }

    public String wrap(final String program) {
	final String cls = "public class WrapperClass {";
	final StringBuilder sb = new StringBuilder(cls);
	sb.append(program);
	sb.append("}");
	return sb.toString();
    }
    
}
