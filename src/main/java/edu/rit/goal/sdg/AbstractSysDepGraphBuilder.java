package edu.rit.goal.sdg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;
import edu.rit.goal.sdg.statement.ArrayAccessAssignment;
import edu.rit.goal.sdg.statement.Assignment;
import edu.rit.goal.sdg.statement.BreakStmt;
import edu.rit.goal.sdg.statement.ContinueStmt;
import edu.rit.goal.sdg.statement.MethodInvocation;
import edu.rit.goal.sdg.statement.MethodInvocationAssignment;
import edu.rit.goal.sdg.statement.MethodSignature;
import edu.rit.goal.sdg.statement.NotImplementedStmt;
import edu.rit.goal.sdg.statement.PostDecrementExpr;
import edu.rit.goal.sdg.statement.PostIncrementExpr;
import edu.rit.goal.sdg.statement.PreDecrementExpr;
import edu.rit.goal.sdg.statement.PreIncrementExpr;
import edu.rit.goal.sdg.statement.ReturnStmt;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.VariableDecl;
import edu.rit.goal.sdg.statement.control.BasicForStmt;
import edu.rit.goal.sdg.statement.control.DoStmt;
import edu.rit.goal.sdg.statement.control.EnhancedForStmt;
import edu.rit.goal.sdg.statement.control.IfThenElseStmt;
import edu.rit.goal.sdg.statement.control.IfThenStmt;
import edu.rit.goal.sdg.statement.control.SwitchStmt;
import edu.rit.goal.sdg.statement.control.WhileStmt;

public abstract class AbstractSysDepGraphBuilder implements SysDepGraphBuilder {

    protected Vertex currentEnterVertex;
    protected Vertex currentResultOutVertex;
    protected final Map<String, List<Vertex>> formalParameters = new HashMap<>();
    protected Deque<Vertex> ctrlStack = new ArrayDeque<>();
    protected Map<Vertex, List<Vertex>> ctrlVtxVarDeclMap = new HashMap<>();
    protected boolean currentCtrlIsDoStmt;
    private List<Stmt> stmts;
    private Set<String> methods;

    @Override
    public SysDepGraph from(final String source) {
	// Transform representation
	stmts = StmtsBuilder.from(source);
	// Build graph
	final SysDepGraph result = _build(stmts);
	methods = result.vertexSet().stream().filter(v -> v.getType().equals("ENTER")).map(v -> v.getLabel())
		.collect(Collectors.toSet());
	return result;
    }

    private SysDepGraph _build(final List<Stmt> stmts) {
	final SysDepGraph result = new SysDepGraph();
	final List<Stmt> methods = stmts.stream().filter(s -> s instanceof MethodSignature)
		.collect(Collectors.toList());
	// Process methods first so that they are available for reference
	methods.forEach(m -> methodSignature((MethodSignature) m, result));
	build(stmts, result, false, false, false);
	doFinally(result);
	return result;
    }

    protected List<Vertex> build(final List<Stmt> stmts, final SysDepGraph sdg, final boolean isNested,
	    final boolean isForStmtHeader, final boolean isLoopBody) {
	final List<Stmt> scope = stmts;
	final List<Vertex> result = new ArrayList<>();
	for (final Stmt s : stmts) {
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
		} else if (s instanceof BasicForStmt) {
		    final List<Vertex> vtcs = basicForStmt((BasicForStmt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof EnhancedForStmt) {
		    final List<Vertex> vtcs = enhancedForStmt((EnhancedForStmt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof IfThenElseStmt) {
		    final List<Vertex> vtcs = ifThenElseStmt((IfThenElseStmt) s, sdg, isNested, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof IfThenStmt) {
		    final List<Vertex> vtcs = ifThenStmt((IfThenStmt) s, sdg, isNested, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof WhileStmt) {
		    final List<Vertex> vtcs = whileStmt((WhileStmt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof DoStmt) {
		    final List<Vertex> vtcs = doStmt((DoStmt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof SwitchStmt) {
		    final List<Vertex> vtcs = switchStmt((SwitchStmt) s, sdg, isNested, isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof VariableDecl) {
		    final List<Vertex> vtcs = variableDeclaration((VariableDecl) s, sdg, isNested, scope,
			    isForStmtHeader);
		    result.addAll(vtcs);
		} else if (s instanceof ArrayAccessAssignment) {
		    final List<Vertex> vtcs = arrayAccessAssignment((ArrayAccessAssignment) s, sdg, isNested, scope,
			    isForStmtHeader);
		    result.addAll(vtcs);
		} else if (s instanceof Assignment) {
		    final List<Vertex> vtcs = assignment((Assignment) s, sdg, isNested, scope, isForStmtHeader,
			    isLoopBody);
		    result.addAll(vtcs);
		    // Has to be called before MethodInvocation because
		    // MethodInvocationAssignment is a subclass
		} else if (s instanceof MethodInvocationAssignment) {
		    final List<Vertex> vtcs = methodInvocationAssignment((MethodInvocationAssignment) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof MethodInvocation) {
		    final List<Vertex> vtcs = methodInvocation((MethodInvocation) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof ReturnStmt) {
		    final List<Vertex> vtcs = returnStmt((ReturnStmt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof BreakStmt) {
		    final List<Vertex> vtcs = breakStmt((BreakStmt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof ContinueStmt) {
		    final List<Vertex> vtcs = continueStmt((ContinueStmt) s, sdg, isNested);
		    result.addAll(vtcs);
		} else if (s instanceof PostIncrementExpr) {
		    final List<Vertex> vtcs = postIncrementExpr((PostIncrementExpr) s, sdg, isNested, isForStmtHeader,
			    isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof PostDecrementExpr) {
		    final List<Vertex> vtcs = postDecrementExpr((PostDecrementExpr) s, sdg, isNested, isForStmtHeader,
			    isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof PreIncrementExpr) {
		    final List<Vertex> vtcs = preIncrementExpr((PreIncrementExpr) s, sdg, isNested, isForStmtHeader,
			    isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof PreDecrementExpr) {
		    final List<Vertex> vtcs = preDecrementExpr((PreDecrementExpr) s, sdg, isNested, isForStmtHeader,
			    isLoopBody);
		    result.addAll(vtcs);
		} else if (s instanceof NotImplementedStmt) {
		    notImplementedStmt((NotImplementedStmt) s, sdg);
		}
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

    private void notImplementedStmt(final NotImplementedStmt notImplementedStmt, final SysDepGraph sdg) {
	System.out.println(notImplementedStmt.toString());
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

    protected void putVarWriting(final Vertex vtx, final boolean isForStmtHeader) {
	// System.out.println("putting var: " + vtx);
	final Vertex currCtrlVtx = ctrlStack.getLast();
	putVarWriting(vtx, currCtrlVtx, isForStmtHeader);
	if (currentCtrlIsDoStmt) {
	    final Vertex outerCtrlVtx = getOuterCtrlVtx();
	    putVarWriting(vtx, outerCtrlVtx, isForStmtHeader);
	}
    }

    private void putVarWriting(final Vertex vtx, final Vertex ctrlVtx, final boolean isForStmtHeader) {
	List<Vertex> vtcs = ctrlVtxVarDeclMap.get(ctrlVtx);
	if (vtcs == null) {
	    vtcs = new ArrayList<>();
	    ctrlVtxVarDeclMap.put(ctrlVtx, vtcs);
	}
	final String lookupId = vtx.getLookupId();
	// Keep both the initialization and update vertices in for statements.
	if (!isForStmtHeader) {
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

    protected Vertex createDeclVtx(final String label, final String lookupId, final boolean isForStmtHeader) {
	final Vertex result = new Vertex(VertexType.DECL, label, lookupId);
	putVarWriting(result, isForStmtHeader);
	return result;
    }

    protected Vertex createAssignVtx(final String label, final String lookupId, final boolean isForStmtHeader) {
	final Vertex result = new Vertex(VertexType.ASSIGN, label, lookupId);
	putVarWriting(result, isForStmtHeader);
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

    public Set<String> getMethods() {

	return null;
    }

}
