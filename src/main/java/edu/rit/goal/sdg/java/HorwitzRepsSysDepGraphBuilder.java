package edu.rit.goal.sdg.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.rit.goal.sdg.java.graph.EdgeType;
import edu.rit.goal.sdg.java.graph.PrimitiveType;
import edu.rit.goal.sdg.java.graph.ScopedVertex;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.graph.VertexType;
import edu.rit.goal.sdg.java.statement.ArrayAccessAssignment;
import edu.rit.goal.sdg.java.statement.Assignment;
import edu.rit.goal.sdg.java.statement.BreakStmnt;
import edu.rit.goal.sdg.java.statement.ContinueStmnt;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.FormalParameter;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.MethodInvocationAssignment;
import edu.rit.goal.sdg.java.statement.MethodSignature;
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

public class HorwitzRepsSysDepGraphBuilder extends AbstractSysDepGraphBuilder {

    private final Deque<ScopedVertex> defOrderVertices = new ArrayDeque<>();

    @Override
    public void methodSignature(final MethodSignature methodSignature, final SysDepGraph sdg) {
	final String methodName = methodSignature.getName();
	final Vertex enterVtx = new Vertex(VertexType.ENTER, methodName, methodName);
	sdg.addVertex(enterVtx);
	final PrimitiveType returnType = methodSignature.getReturnType();
	// Add result vertex placeholder
	if (returnType != PrimitiveType.VOID) {
	    final String resultOutVtxName = getResultOutVtxName(methodName);
	    final Vertex resultOutVtx = new Vertex(VertexType.FORMAL_OUT, resultOutVtxName, resultOutVtxName);
	    sdg.addVertex(resultOutVtx);
	    notNestedStmntEdge(enterVtx, resultOutVtx, sdg);
	    final String resultInVtxName = getResultInVtxName(methodName);
	    final Vertex resultInVtx = new Vertex(VertexType.FORMAL_IN, resultInVtxName, resultInVtxName);
	    sdg.addVertex(resultInVtx);
	    notNestedStmntEdge(enterVtx, resultInVtx, sdg);
	}
	final List<FormalParameter> params = methodSignature.getParams();
	if (params != null) {
	    params.forEach(p -> {
		final String variableName = p.getVariableDeclaratorId();
		final Vertex formalParam = new Vertex(VertexType.FORMAL_IN, variableName, variableName);
		sdg.addVertex(formalParam);
		putFormalParameter(methodName, formalParam);
		notNestedStmntEdge(enterVtx, formalParam, sdg);
	    });
	}
    }

    @Override
    public List<Vertex> basicForStmnt(final BasicForStmnt basicForStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	System.err.println("Basic for statement not implemented.");
	return list(new Vertex[1]);
    }

    @Override
    public List<Vertex> enhancedForStmnt(final EnhancedForStmnt enhancedForStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	System.err.println("Enhanced for statement not implemented.");
	return list(new Vertex[1]);
    }

    @Override
    public List<Vertex> ifThenElseStmnt(final IfThenElseStmnt ifThenElseStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	final Expression condition = ifThenElseStmnt.getCondition();
	final List<Statement> thenBranch = ifThenElseStmnt.getThenBranch();
	final Vertex conditionVtx = new Vertex(VertexType.COND, condition.toString());
	final List<Vertex> result = ctrlStructureTrue(conditionVtx, thenBranch, sdg, isNested);
	final List<Statement> elseBranch = ifThenElseStmnt.getElseBranch();
	final List<Vertex> elseVtcs = _build(elseBranch, sdg, true);
	ctrlFalseEdges(conditionVtx, elseVtcs, sdg);
	dataDependencies(conditionVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> ifThenStmnt(final IfThenStmnt ifThenStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expression condition = ifThenStmnt.getCondition();
	final List<Statement> thenBranch = ifThenStmnt.getThenBranch();
	final Vertex conditionVtx = new Vertex(VertexType.COND, condition.toString());
	final List<Vertex> result = ctrlStructureTrue(conditionVtx, thenBranch, sdg, isNested);
	dataDependencies(conditionVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> whileStmnt(final WhileStmnt whileStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expression condition = whileStmnt.getCondition();
	final List<Statement> body = whileStmnt.getBody();
	final Vertex conditionVtx = new Vertex(VertexType.COND, condition.toString());
	final List<Vertex> result = ctrlStructureTrue(conditionVtx, body, sdg, isNested);
	dataDependencies(conditionVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> doStmnt(final DoStmnt doStmnt, final SysDepGraph sdg, final boolean isNested) {
	// Condition
	final Expression condition = doStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	// Body
	final List<Statement> body = doStmnt.getBody();
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> variableDeclaration(final VariableDecl variableDecl, final SysDepGraph sdg,
	    final boolean isNested, final List<Statement> scope) {
	final String lookupId = variableDecl.getVariableDeclaratorId();
	final Expression initializer = variableDecl.getVariableInitializer();
	final Vertex v = new Vertex(VertexType.DECL, variableDecl.toString(), lookupId);
	sdg.addVertex(v);
	addDefOrderVertex(v, scope);
	dataDependencies(v, initializer.getReadingVars(), sdg, isNested);
	if (!isNested) {
	    notNestedStmntEdge(v, sdg);
	}
	return list(v);
    }

    @Override
    public List<Vertex> arrayAccessAssignment(final ArrayAccessAssignment arrayAccessAssignment, final SysDepGraph sdg,
	    final boolean isNested, final List<Statement> scope) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Vertex> assignment(final Assignment assignment, final SysDepGraph sdg, final boolean isNested,
	    final List<Statement> scope) {
	final String outVar = assignment.getOutVar();
	final Set<String> inVars = assignment.getInVars();
	final Vertex v = new Vertex(VertexType.ASSIGN, assignment.toString(), outVar);
	sdg.addVertex(v);
	// addDefOrderEdge(v, sdg, scope);
	addDefOrderVertex(v, scope);
	dataDependencies(v, inVars, sdg, isNested);
	if (!isNested) {
	    notNestedStmntEdge(v, sdg);
	}
	return list(v);
    }

    // TODO: This method will no longer receive method invocations that involve an
    // assignment or declaration.
    @Override
    public List<Vertex> methodInvocation(final MethodInvocation methodInvocation, final SysDepGraph sdg,
	    final boolean isNested) {
	final String methodName = methodInvocation.getMethodName();
	// final String outVar = methodInvocation.getOutVar();
	final String outVar = null;
	final List<Expression> inVars = methodInvocation.getInVars();
	// Invocation vertex
	final Vertex invocationVtx = new Vertex(VertexType.CALL, methodInvocation.toString());
	sdg.addVertex(invocationVtx);
	dataDependencies(invocationVtx, inVars, sdg, isNested);
	final Vertex methodVertex = sdg.getFirstVertexByLabel(methodName);
	// Add edge if the method is part of the program itself and not some other API,
	// like System.out.println
	if (methodVertex != null) {
	    sdg.addEdge(invocationVtx, methodVertex, EdgeType.CALL);
	}
	if (!isNested) {
	    notNestedStmntEdge(invocationVtx, sdg);
	}
	// Method invocation result assigned to variable
	if (outVar != null) {
	    final Vertex actualOutVtx = new Vertex(VertexType.ACTUAL_OUT, outVar, outVar);
	    sdg.addVertex(actualOutVtx);
	    sdg.addEdge(invocationVtx, actualOutVtx, EdgeType.CTRL_TRUE);
	    final Vertex resultVtx = sdg.getFirstVertexByLabel(getResultOutVtxName(methodName));
	    sdg.addEdge(resultVtx, actualOutVtx, EdgeType.PARAM_OUT);
	    // Actual in
	    final Vertex actualInVtx = new Vertex(VertexType.ACTUAL_IN, outVar, outVar);
	    sdg.addVertex(actualInVtx);
	    sdg.addEdge(invocationVtx, actualInVtx, EdgeType.CTRL_TRUE);
	    final Vertex calledResultInVertex = sdg.getFirstVertexByLabel(getResultInVtxName(methodName));
	    sdg.addEdge(actualInVtx, calledResultInVertex, EdgeType.PARAM_IN);
	}
	if (inVars != null) {
	    for (int i = 0; i < inVars.size(); i++) {
		final Vertex formalParam = getFormalParameter(methodName, i);
		// Only if the method is part of the program itself and not some other API
		if (formalParam != null) {
		    final Expression var = inVars.get(i);
		    final String paramName = var.toString();
		    final Vertex actualParam = new Vertex(VertexType.ACTUAL_IN, paramName, paramName);
		    sdg.addVertex(actualParam);
		    sdg.addEdge(actualParam, formalParam, EdgeType.PARAM_IN);
		    sdg.addEdge(invocationVtx, actualParam, EdgeType.CTRL_TRUE);
		    // Add edges w.r.t. variable declaration/assignments
		    final List<Vertex> paramAssignments = sdg.getAllAssignmentVerticesByLabel(paramName);
		    paramAssignments.forEach(p -> sdg.addEdge(p, actualParam, EdgeType.FLOW));
		}
	    }
	}
	return list(invocationVtx);
    }

    @Override
    public List<Vertex> methodInvocationAssignment(final MethodInvocationAssignment methodInvocationAssignment,
	    final SysDepGraph sdg, final boolean isNested) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Vertex> returnStmnt(final ReturnStmnt returnStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expression returnedExpr = returnStmnt.getReturnedExpr();
	final Vertex v = new Vertex(VertexType.RETURN, returnedExpr.toString());
	sdg.addVertex(v);
	dataDependencies(v, returnedExpr.getReadingVars(), sdg, isNested);
	// Result
	sdg.addEdge(v, currentResultOutVertex, EdgeType.FLOW);
	if (!isNested) {
	    notNestedStmntEdge(v, sdg);
	}
	return list(v);
    }

    @Override
    public List<Vertex> breakStmnt(final BreakStmnt breakStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Vertex v = new Vertex(VertexType.BREAK, "break");
	sdg.addVertex(v);
	return list(v);
    }

    @Override
    public List<Vertex> continueStmnt(final ContinueStmnt continueStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	return list(new Vertex());
    }

    @Override
    public List<Vertex> postIncrementExpr(final PostIncrementExpr postIncrementExpr, final SysDepGraph sdg,
	    final boolean isNested) {
	return shortHandExpr(postIncrementExpr, sdg, isNested);
    }

    @Override
    public List<Vertex> postDecrementExpr(final PostDecrementExpr postDecrementExpr, final SysDepGraph sdg,
	    final boolean isNested) {
	return shortHandExpr(postDecrementExpr, sdg, isNested);
    }

    @Override
    public List<Vertex> preIncrementExpr(final PreIncrementExpr preIncrementExpr, final SysDepGraph sdg,
	    final boolean isNested) {
	return shortHandExpr(preIncrementExpr, sdg, isNested);
    }

    @Override
    public List<Vertex> preDecrementExpr(final PreDecrementExpr preDecrementExpr, final SysDepGraph sdg,
	    final boolean isNested) {
	return shortHandExpr(preDecrementExpr, sdg, isNested);
    }

    @Override
    protected void doFinally(final SysDepGraph sdg) {
	defOrderEdges();
    }

    /*
     * Def-order
     */

    private void defOrderEdges() {

    }

    public void addDefOrderVertex(final Vertex vertex, final List<Statement> scope) {
	final ScopedVertex sv = new ScopedVertex(vertex, scope);
	defOrderVertices.push(sv);
    }

    public Deque<ScopedVertex> getDefOrderVertices() {
	return defOrderVertices;
    }

    public void addDefOrderEdge(final Vertex vertex, final SysDepGraph sdg, final List<Statement> scope) {
	final String lookupId = vertex.getLookupId();
	for (final ScopedVertex sv : defOrderVertices) {
	    final Vertex v = sv.getVertex();
	    if (lookupId.equals(v.getLookupId()) && v != vertex && sharedScope(sv, scope)) {
		sdg.addEdge(v, vertex, EdgeType.DEF_ORDER);
		break;
	    }
	}
    }

    /*
     * Helper methods
     */

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Statement> body,
	    final SysDepGraph sdg, final boolean isNested) {
	List<Vertex> result = new ArrayList<>();
	List<Vertex> bodyVtcs = new ArrayList<>();
	sdg.addVertex(conditionVtx);
	bodyVtcs = _build(body, sdg, true);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(conditionVtx, sdg);
	    result = bodyVtcs;
	} else {
	    result.add(conditionVtx);
	}
	ctrlTrueEdges(conditionVtx, bodyVtcs, sdg);
	return result;
    }

    private void notNestedStmntEdge(final Vertex vertex, final SysDepGraph sdg) {
	final Vertex enterVtx = getCurrentEnterVertex();
	notNestedStmntEdge(enterVtx, vertex, sdg);
    }

    private void notNestedStmntEdge(final Vertex enterVtx, final Vertex vertex, final SysDepGraph sdg) {
	sdg.addEdge(enterVtx, vertex, EdgeType.CTRL_TRUE);
    }

    private void ctrlTrueEdges(final Vertex source, final List<Vertex> targets, final SysDepGraph sdg) {
	for (final Vertex v : targets) {
	    sdg.addEdge(source, v, EdgeType.CTRL_TRUE);
	}
    }

    private void ctrlFalseEdges(final Vertex source, final List<Vertex> targets, final SysDepGraph sdg) {
	for (final Vertex v : targets) {
	    sdg.addEdge(source, v, EdgeType.CTRL_FALSE);
	}
    }

    private void dataDependencies(final Vertex vertex, final List<Expression> exprs, final SysDepGraph sdg,
	    final boolean isNested) {
	final Set<String> deps = exprs.stream().map(e -> e.getReadingVars()).reduce(new HashSet<>(), (s1, s2) -> {
	    s1.addAll(s2);
	    return s1;
	});
	dataDependencies(vertex, deps, sdg, isNested);
    }

    private void dataDependencies(final Vertex vertex, final Set<String> deps, final SysDepGraph sdg,
	    final boolean isNested) {
	for (final String s : deps) {
	    // TODO: Check scope of variables?
	    final List<Vertex> vtcs = sdg.getAllVerticesByLabel(s);
	    if (!vtcs.isEmpty()) {
		vtcs.forEach(v -> {
		    // Can't have a data dependecy w.r.t. ACTUAL_IN param.
		    if (!VertexType.ACTUAL_IN.equals(v.getType()))
			sdg.addEdge(v, vertex, EdgeType.FLOW);
		});
	    } else {
		// No declaration found. Create initial state
		final Vertex initialStateVtx = new Vertex(VertexType.INITIAL_STATE, s);
		sdg.addVertex(initialStateVtx);
		sdg.addEdge(initialStateVtx, vertex, EdgeType.FLOW);
		if (!isNested) {
		    // Method entry dependency
		    notNestedStmntEdge(initialStateVtx, sdg);
		}
	    }
	}
    }

    private List<Vertex> shortHandExpr(final Expression expr, final SysDepGraph sdg, final boolean isNested) {
	final Vertex vtx = new Vertex(VertexType.ASSIGN, expr.toString());
	sdg.addVertex(vtx);
	dataDependencies(vtx, expr.getReadingVars(), sdg, isNested);
	return list(vtx);
    }

}
