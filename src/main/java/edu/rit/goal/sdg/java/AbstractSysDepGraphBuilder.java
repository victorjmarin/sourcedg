package edu.rit.goal.sdg.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import edu.rit.goal.sdg.java.graph.VertexType;
import edu.rit.goal.sdg.java.statement.ArrayAccessAssignment;
import edu.rit.goal.sdg.java.statement.Assignment;
import edu.rit.goal.sdg.java.statement.BreakStmnt;
import edu.rit.goal.sdg.java.statement.ContinueStmnt;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.MethodInvocationAssignment;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.NotImplementedStmnt;
import edu.rit.goal.sdg.java.statement.PostDecrementExpr;
import edu.rit.goal.sdg.java.statement.PostIncrementExpr;
import edu.rit.goal.sdg.java.statement.PreDecrementExpr;
import edu.rit.goal.sdg.java.statement.PreIncrementExpr;
import edu.rit.goal.sdg.java.statement.ReturnStmnt;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.VariableDecl;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.DoStmnt;
import edu.rit.goal.sdg.java.statement.control.EnhancedForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;
import edu.rit.goal.sdg.java.visitor.ClassBodyVisitor;

public abstract class AbstractSysDepGraphBuilder implements SysDepGraphBuilder {

    protected Vertex currentEnterVertex;
    protected Vertex currentResultOutVertex;
    protected final Map<String, List<Vertex>> formalParameters = new HashMap<>();
    protected Deque<Vertex> ctrlStack = new ArrayDeque<>();
    protected Map<Vertex, List<Vertex>> ctrlVtxVarDeclMap = new HashMap<>();
    protected boolean currentCtrlIsDoStmnt;
    private List<Statement> stmnts;

    @Override
    public SysDepGraph fromSource(final String program) {
	final CharStream chrStream = CharStreams.fromString(program);
	final Lexer lexer = new Java8Lexer(chrStream);
	final CommonTokenStream tokens = new CommonTokenStream(lexer);
	final Java8Parser parser = new Java8Parser(tokens);
	final ClassBodyVisitor visitor = new ClassBodyVisitor();
	stmnts = visitor.visit(parser.classDeclaration());
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
	_build(stmnts, result, false, false, false);
	doFinally(result);
	return result;
    }

    protected List<Vertex> _build(final List<Statement> stmnts, final SysDepGraph sdg, final boolean isNested,
	    final boolean isForStmntHeader, final boolean isLoopBody) {
	final List<Statement> scope = stmnts;
	final List<Vertex> result = new ArrayList<>();
	for (final Statement s : stmnts) {
	    if (s != null) {
		if (s instanceof MethodSignature) {
		    ctrlStack.clear();
		    ctrlVtxVarDeclMap.clear();
		    final MethodSignature methodSignature = (MethodSignature) s;
		    final String methodName = methodSignature.getName();
		    currentEnterVertex = sdg.getFirstVertexByLabel(methodName);
		    // Make formal parameters available in ctrl vtx -> var decl mapping
		    ctrlVtxVarDeclMap.put(currentEnterVertex, formalParameters.get(methodName));
		    ctrlStack.add(currentEnterVertex);
		    currentResultOutVertex = sdg.getFirstVertexByLabel(getResultOutVtxName(methodName));
		} else if (s instanceof BasicForStmnt) {
		    final List<Vertex> vtcs = basicForStmnt((BasicForStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof EnhancedForStmnt) {
		    final List<Vertex> vtcs = enhancedForStmnt((EnhancedForStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof IfThenElseStmnt) {
		    final List<Vertex> vtcs = ifThenElseStmnt((IfThenElseStmnt) s, sdg, isNested, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof IfThenStmnt) {
		    final List<Vertex> vtcs = ifThenStmnt((IfThenStmnt) s, sdg, isNested, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof WhileStmnt) {
		    final List<Vertex> vtcs = whileStmnt((WhileStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof DoStmnt) {
		    final List<Vertex> vtcs = doStmnt((DoStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof VariableDecl) {
		    final List<Vertex> vtcs = variableDeclaration((VariableDecl) s, sdg, isNested, scope,
			    isForStmntHeader);
		    result.addAll(vtcs);
		} else if (s instanceof ArrayAccessAssignment) {
		    final List<Vertex> vtcs = arrayAccessAssignment((ArrayAccessAssignment) s, sdg, isNested, scope,
			    isForStmntHeader);
		    result.addAll(vtcs);
		} else if (s instanceof Assignment) {
		    final List<Vertex> vtcs = assignment((Assignment) s, sdg, isNested, scope, isForStmntHeader, isLoopBody);
		    result.addAll(vtcs);
		    // Has to be called before MethodInvocation because
		    // MethodInvocationAssignment is a subclass
		} else if (s instanceof MethodInvocationAssignment) {
		    final List<Vertex> vtcs = methodInvocationAssignment((MethodInvocationAssignment) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof MethodInvocation) {
		    final List<Vertex> vtcs = methodInvocation((MethodInvocation) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof ReturnStmnt) {
		    final List<Vertex> vtcs = returnStmnt((ReturnStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof BreakStmnt) {
		    final List<Vertex> vtcs = breakStmnt((BreakStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof ContinueStmnt) {
		    final List<Vertex> vtcs = continueStmnt((ContinueStmnt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof PostIncrementExpr) {
		    final List<Vertex> vtcs = postIncrementExpr((PostIncrementExpr) s, sdg, isNested, isForStmntHeader, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof PostDecrementExpr) {
		    final List<Vertex> vtcs = postDecrementExpr((PostDecrementExpr) s, sdg, isNested, isForStmntHeader, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof PreIncrementExpr) {
		    final List<Vertex> vtcs = preIncrementExpr((PreIncrementExpr) s, sdg, isNested, isForStmntHeader, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof PreDecrementExpr) {
		    final List<Vertex> vtcs = preDecrementExpr((PreDecrementExpr) s, sdg, isNested, isForStmntHeader, isLoopBody);
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
	return currentResultOutVertex;
    }

    protected abstract void doFinally(SysDepGraph sdg);

    protected String getResultOutVtxName(final String methodName) {
	return methodName + "ResultOut";
    }

    protected String getResultInVtxName(final String methodName) {
	return methodName + "ResultIn";
    }

    protected List<Vertex> list(final Vertex... vertices) {
	final List<Vertex> result = new ArrayList<>(vertices.length);
	for (final Vertex v : vertices) {
	    result.add(v);
	}
	return result;
    }

    protected String lookupId(final String lookupId) {
	return getCurrentEnterVertex().getLabel() + lookupId;
    }

    protected Vertex getOuterCtrlVtx() {
	// Get outer control vertex
	final Vertex currentCtrlVtx = ctrlStack.pollLast();
	final Vertex result = ctrlStack.getLast();
	// Restore last vertex
	ctrlStack.add(currentCtrlVtx);
	return result;
    }

    protected void putVarWriting(final Vertex vtx, final boolean isForStmntHeader) {
	// System.out.println("putting var: " + vtx);
	final Vertex currCtrlVtx = ctrlStack.getLast();
	putVarWriting(vtx, currCtrlVtx, isForStmntHeader);
	if (currentCtrlIsDoStmnt) {
	    final Vertex outerCtrlVtx = getOuterCtrlVtx();
	    putVarWriting(vtx, outerCtrlVtx, isForStmntHeader);
	}
    }

    private void putVarWriting(final Vertex vtx, final Vertex ctrlVtx, final boolean isForStmntHeader) {
	List<Vertex> vtcs = ctrlVtxVarDeclMap.get(ctrlVtx);
	if (vtcs == null) {
	    vtcs = new ArrayList<>();
	    ctrlVtxVarDeclMap.put(ctrlVtx, vtcs);
	}
	final String lookupId = vtx.getLookupId();
	// Keep both the initialization and update vertices in for statements.
	if (!isForStmntHeader) {
	    final List<Vertex> olderRefs = vtcs.stream().filter(v -> lookupId.equals(v.getLookupId()))
		    .collect(Collectors.toList());
	    vtcs.removeAll(olderRefs);
	}
	vtcs.add(vtx);
    }

    protected void removeScopedVarDecl(final Vertex ctrlVtx) {
	// System.out.println("removing scoped var. decl. inside " + ctrlVtx);
	final List<Vertex> vtcs = ctrlVtxVarDeclMap.get(ctrlVtx);
	final Set<String> scopedVarsLookupId = new HashSet<>();
	if (vtcs == null)
	    return;
	List<Vertex> updatedVtcs = new ArrayList<>(vtcs.size());
	for (final Vertex v : vtcs) {
	    if (!v.getType().equals(VertexType.DECL))
		updatedVtcs.add(v);
	    else
		scopedVarsLookupId.add(v.getLookupId());
	}
	if (updatedVtcs.size() == 0) {
	    ctrlVtxVarDeclMap.remove(ctrlVtx);
	} else {
	    // Remove assignment vertices for variables locally declared
	    updatedVtcs = updatedVtcs.stream().filter(v -> !scopedVarsLookupId.contains(v.getLookupId()))
		    .collect(Collectors.toList());
	    ctrlVtxVarDeclMap.put(ctrlVtx, updatedVtcs);
	}
    }

    protected Vertex createDeclVtx(final String label, final String lookupId, final boolean isForStmntHeader) {
	final Vertex result = new Vertex(VertexType.DECL, label, lookupId);
	putVarWriting(result, isForStmntHeader);
	return result;
    }

    protected Vertex createAssignVtx(final String label, final String lookupId, final boolean isForStmntHeader) {
	final Vertex result = new Vertex(VertexType.ASSIGN, label, lookupId);
	putVarWriting(result, isForStmntHeader);
	return result;
    }

    protected List<Vertex> getAllVerticesInScope(final String lookupId) {
	final List<Vertex> result = new ArrayList<>();
	for (final List<Vertex> l : ctrlVtxVarDeclMap.values()) {
	    if (l != null) {
		for (final Vertex v : l) {
		    if (lookupId.equals(v.getLookupId())) {
			result.add(v);
		    }
		}
	    }
	}
	return result;
    }

}
