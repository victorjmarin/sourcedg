package edu.rit.goal.sdg.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import edu.rit.goal.sdg.java.antlr.Java8Lexer;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.graph.ScopedVertex;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.statement.Assignment;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.NotImplementedStmnt;
import edu.rit.goal.sdg.java.statement.ReturnStmnt;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.VariableDecl;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;
import edu.rit.goal.sdg.java.visitor.ClassBodyVisitor;

public abstract class AbstractSysDepGraphBuilder implements SysDepGraphBuilder {

    protected Vertex currentEnterVertex;
    protected Vertex currentResultVertex;
    protected final Map<String, List<Vertex>> formalParameters = new HashMap<>();
    protected final Map<Vertex, Vertex> methodResult = new HashMap<>();

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
	// Process methods first so that they are available for reference
	methods.forEach(m -> methodSignature((MethodSignature) m, result));
	_build(stmnts, result, false);
	doFinally();
	return result;
    }

    protected List<Vertex> _build(final List<Statement> stmnts, final SysDepGraph sdg, final boolean isNested) {
	final List<Statement> scope = stmnts;
	final List<Vertex> result = new ArrayList<>();
	for (final Statement s : stmnts) {
	    if (s != null) {
		if (s instanceof MethodSignature) {
		    final MethodSignature methodSignature = (MethodSignature) s;
		    final String methodName = methodSignature.getName();
		    currentEnterVertex = sdg.getFirstVertexByLabel(methodName);
		    currentResultVertex = sdg.getFirstVertexByLabel(methodName + "result");
		} else if (s instanceof BasicForStmnt) {
		    final List<Vertex> vtcs = basicForStmnt((BasicForStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof IfThenElseStmnt) {
		    final List<Vertex> vtcs = ifThenElseStmnt((IfThenElseStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof IfThenStmnt) {
		    final List<Vertex> vtcs = ifThenStmnt((IfThenStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof WhileStmnt) {
		    final List<Vertex> vtcs = whileStmnt((WhileStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof VariableDecl) {
		    final List<Vertex> vtcs = variableDeclaration((VariableDecl) s, sdg, isNested, scope);
		    result.addAll(vtcs);
		} else if (s instanceof Assignment) {
		    final List<Vertex> vtcs = assignment((Assignment) s, sdg, isNested, scope);
		    result.addAll(vtcs);
		} else if (s instanceof MethodInvocation) {
		    final List<Vertex> vtcs = methodInvocation((MethodInvocation) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof ReturnStmnt) {
		    final List<Vertex> vtcs = returnStmnt((ReturnStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof NotImplementedStmnt) {
		    notImplementedStmnt((NotImplementedStmnt) s, sdg);
		}
	    }
	}
	return result;
    }

    protected boolean sharedScope(final ScopedVertex sv, final List<Statement> scope) {
	final boolean result = sv.getScope() == scope || isOuterScope(sv.getScope(), scope);
	return result;
    }

    private boolean isOuterScope(final List<Statement> sc1, final List<Statement> sc2) {
	boolean result = false;
	if (expandScope(sc1).containsAll(sc2)) {
	    result = true;
	}
	return result;
    }

    private List<Statement> expandScope(final List<Statement> scope) {
	final List<Statement> result = new ArrayList<>();
	for (final Statement s : scope) {
	    final List<Statement> expanded = s.expandScope();
	    if (expanded != null) {
		result.addAll(expanded);
	    }
	}
	return result;
    }

    protected Vertex getFormalParameter(final String methodName, final int paramIdx) {
	Vertex result = null;
	final List<Vertex> params = formalParameters.get(methodName);
	if (params != null) {
	    result = params.get(paramIdx);
	}
	return result;
    }

    protected void putFormalParameter(final String methodName, final Vertex vtx) {
	List<Vertex> vrtcs = formalParameters.get(methodName);
	if (vrtcs == null) {
	    vrtcs = new ArrayList<>();
	    formalParameters.put(methodName, vrtcs);
	}
	vrtcs.add(vtx);
    }

    public Vertex getCurrentEnterVertex() {
	return currentEnterVertex;
    }

    private void notImplementedStmnt(final NotImplementedStmnt notImplementedStmnt, final SysDepGraph sdg) {
	System.out.println(notImplementedStmnt.toString());
    }

    public Vertex getCurrentResultVertex() {
	return methodResult.get(getCurrentEnterVertex());
    }

    protected abstract void doFinally();
}
