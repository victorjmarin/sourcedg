package edu.rit.goal.sdg.java;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import edu.rit.goal.sdg.java.antlr.Java8Lexer;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;
import edu.rit.goal.sdg.java.visitor.ClassBodyVisitor;

public abstract class AbstractSysDepGraphBuilder implements SysDepGraphBuilder {

    @Override
    public SysDepGraph fromSource(final String program) {
	final CharStream chrStream = CharStreams.fromString(program);
	final Lexer lexer = new Java8Lexer(chrStream);
	final CommonTokenStream tokens = new CommonTokenStream(lexer);
	final Java8Parser parser = new Java8Parser(tokens);
	final ClassBodyVisitor visitor = new ClassBodyVisitor();
	final List<Statement> stmnts = visitor.visit(parser.classDeclaration());
	final SysDepGraph result = build(stmnts);
	return result;
    }

    private String wrap(final String program) {
	final String cls = "public class WrapperClass {";
	final StringBuilder sb = new StringBuilder(cls);
	sb.append(program);
	sb.append("}");
	return sb.toString();
    }

    private SysDepGraph build(final List<Statement> stmnts) {
	final SysDepGraph result = new SysDepGraph();
	final List<Statement> methods = stmnts.stream().filter(s -> s instanceof MethodSignature)
		.collect(Collectors.toList());
	// Process methods first so that they are available for use
	methods.forEach(m -> methodSignature((MethodSignature) m, result));
	for (final Statement s : stmnts) {
	    if (s != null) {
		if (s instanceof BasicForStmnt) {
		    basicForStmnt((BasicForStmnt) s, result);
		} else if (s instanceof IfThenElseStmnt) {
		    ifThenElseStmnt((IfThenElseStmnt) s, result);
		} else if (s instanceof IfThenStmnt) {
		    ifThenStmnt((IfThenStmnt) s, result);
		} else if (s instanceof WhileStmnt) {
		    whileStmnt((WhileStmnt) s, result);
		}
	    }
	}
	return result;
    }

}
