package edu.rit.goal.sdg.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import edu.rit.goal.sdg.java.graph.EdgeType;
import edu.rit.goal.sdg.java.graph.PrimitiveType;
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

/**
 * Implementation of features not described in the paper by Horwitz and Reps.
 */
public class MarinSysDepGraphBuilder extends AbstractSysDepGraphBuilder {

    @Override
    public void methodSignature(final MethodSignature methodSignature, final SysDepGraph sdg) {
	// Create ENTER vertex
	final String methodName = methodSignature.getName();
	final Vertex enterVtx = new Vertex(VertexType.ENTER, methodName, methodName);
	sdg.addVertex(enterVtx);
	// Result vertex placeholder
	final PrimitiveType returnType = methodSignature.getReturnType();
	if (returnType != PrimitiveType.VOID) {
	    // Result out
	    final String resultOutVtxName = getResultOutVtxName(methodName);
	    final Vertex resultOutVtx = new Vertex(VertexType.FORMAL_OUT, resultOutVtxName, resultOutVtxName);
	    sdg.addVertex(resultOutVtx);
	    notNestedStmntEdge(enterVtx, resultOutVtx, sdg);
	    // Result in
	    final String resultInVtxName = getResultInVtxName(methodName);
	    final Vertex resultInVtx = new Vertex(VertexType.FORMAL_IN, resultInVtxName, resultInVtxName);
	    sdg.addVertex(resultInVtx);
	    notNestedStmntEdge(enterVtx, resultInVtx, sdg);
	}
	// Create formal parameter vertices
	final List<FormalParameter> params = methodSignature.getParams();
	if (params != null) {
	    for (final FormalParameter fp : params) {
		final String fpName = fp.getVariableDeclaratorId();
		final Vertex formalParamVtx = new Vertex(VertexType.FORMAL_IN, fpName, methodName + fpName);
		sdg.addVertex(formalParamVtx);
		// Keep mapping method -> {params}
		putFormalParameter(methodName, formalParamVtx);
		// Control edge from method to parameter
		notNestedStmntEdge(enterVtx, formalParamVtx, sdg);
	    }
	}
    }

    @Override
    public List<Vertex> variableDeclaration(final VariableDecl variableDecl, final SysDepGraph sdg,
	    final boolean isNested, final List<Statement> scope) {
	final String varName = variableDecl.getVariableDeclaratorId();
	final Expression varInit = variableDecl.getVariableInitializer();
	final Vertex declVtx = new Vertex(VertexType.DECL, variableDecl.toString(), lookupId(varName));
	sdg.addVertex(declVtx);
	putVarWriting(declVtx);
	dataDependencies(declVtx, varInit.getReadingVars(), sdg, isNested);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(declVtx, sdg);
	}
	return list(declVtx);
    }

    @Override
    public List<Vertex> basicForStmnt(final BasicForStmnt basicForStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	final List<Statement> init = basicForStmnt.getInit();
	final Expression cond = basicForStmnt.getCondition();
	final List<Statement> update = basicForStmnt.getUpdate();
	final List<Statement> body = basicForStmnt.getBody();
	return forStmnt(init, cond, update, body, sdg, isNested);
    }

    @Override
    public List<Vertex> enhancedForStmnt(final EnhancedForStmnt enhancedForStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	final String var = enhancedForStmnt.getVar();
	// Condition
	final Expression iterable = enhancedForStmnt.getIterable();
	final Vertex condVtx = new Vertex(VertexType.COND, iterable.toString());
	sdg.addVertex(condVtx);
	// Update. Needs to be executed after updating the control stack, so we pass it as
	// a function
	final Function<Void, Void> f = arg -> {
	    final Vertex updVtx = createDeclVtx(var, var);
	    sdg.addVertex(updVtx);
	    sdg.addEdge(condVtx, updVtx, EdgeType.CTRL_TRUE);
	    return null;
	};
	// Body
	final List<Statement> body = enhancedForStmnt.getBody();
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, f, isNested);
	// Loop
	sdg.addEdge(condVtx, condVtx, EdgeType.CTRL_TRUE);
	return result;
    }

    public List<Vertex> forStmnt(final List<Statement> init, final Expression cond, final List<Statement> update,
	    final List<Statement> body, final SysDepGraph sdg, final boolean isNested) {
	// Initialization. Needs to be executed after updating the control stack, so we
	// pass it as a function
	final Function<Void, Void> f = arg -> {
	    _build(init, sdg, isNested);
	    return null;
	};
	// Condition
	final Vertex condVtx = new Vertex(VertexType.COND, cond.toString());
	sdg.addVertex(condVtx);
	// Loop
	sdg.addEdge(condVtx, condVtx, EdgeType.CTRL_TRUE);
	// Update
	final List<Vertex> updateVtcs = _build(update, sdg, isNested);
	ctrlTrueEdges(condVtx, updateVtcs, sdg);
	// Body
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, f, isNested);
	// Data dependencies
	dataDependencies(condVtx, cond.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> ifThenElseStmnt(final IfThenElseStmnt ifThenElseStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	// Condition
	final Expression condition = ifThenElseStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	// Then branch
	final List<Statement> thenBranch = ifThenElseStmnt.getThenBranch();
	final List<Vertex> result = ctrlStructureTrue(condVtx, thenBranch, sdg, isNested);
	// Else branch
	final List<Statement> elseBranch = ifThenElseStmnt.getElseBranch();
	final List<Vertex> elseVtcs = _build(elseBranch, sdg, true);
	ctrlFalseEdges(condVtx, elseVtcs, sdg);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> ifThenStmnt(final IfThenStmnt ifThenStmnt, final SysDepGraph sdg, final boolean isNested) {
	// Condition
	final Expression condition = ifThenStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	// Then branch
	final List<Statement> thenBranch = ifThenStmnt.getThenBranch();
	final List<Vertex> result = ctrlStructureTrue(condVtx, thenBranch, sdg, isNested);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> whileStmnt(final WhileStmnt whileStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expression condition = whileStmnt.getCondition();
	final List<Statement> body = whileStmnt.getBody();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, isNested);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> doStmnt(final DoStmnt doStmnt, final SysDepGraph sdg, final boolean isNested) {
	// Condition
	final Expression condition = doStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	// Body
	final List<Statement> body = doStmnt.getBody();
	final boolean isDoStmnt = true;
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, isDoStmnt, null, isNested);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> arrayAccessAssignment(final ArrayAccessAssignment arrayAccessAssignment, final SysDepGraph sdg,
	    final boolean isNested, final List<Statement> scope) {
	final String assignedVar = arrayAccessAssignment.getExpressionName();
	final Vertex assignVtx = new Vertex(VertexType.ASSIGN, arrayAccessAssignment.toString(), lookupId(assignedVar));
	sdg.addVertex(assignVtx);
	putVarWriting(assignVtx);
	final List<Expression> exprs = new ArrayList<>();
	exprs.add(arrayAccessAssignment.getIndex());
	exprs.add(arrayAccessAssignment.getRightHandSide());
	dataDependencies(assignVtx, exprs, sdg, isNested);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(assignVtx, sdg);
	}
	return list(assignVtx);
    }

    @Override
    public List<Vertex> assignment(final Assignment assignment, final SysDepGraph sdg, final boolean isNested,
	    final List<Statement> scope) {
	final String outVar = assignment.getOutVar();
	final Vertex assignVtx = new Vertex(VertexType.ASSIGN, assignment.toString(), lookupId(outVar));
	final Set<String> inVars = assignment.getInVars();
	sdg.addVertex(assignVtx);
	putVarWriting(assignVtx);
	// Data dependencies
	dataDependencies(assignVtx, inVars, sdg, isNested);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(assignVtx, sdg);
	}
	return list(assignVtx);
    }

    @Override
    public List<Vertex> methodInvocation(final MethodInvocation methodInvocation, final SysDepGraph sdg,
	    final boolean isNested) {
	final Vertex invocationVtx = new Vertex(VertexType.CALL, methodInvocation.toString());
	sdg.addVertex(invocationVtx);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(invocationVtx, sdg);
	}
	final String methodName = methodInvocation.getMethodName();
	final Vertex methodVertex = sdg.getFirstVertexByLabel(methodName);
	// Add edge if the method is part of the program itself and not some other API,
	// like System.out.println
	if (methodVertex != null) {
	    sdg.addEdge(invocationVtx, methodVertex, EdgeType.CALL);
	}
	// PARAM_IN edges
	final List<Expression> inVars = methodInvocation.getInVars();
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
	    // Data dependencies
	    dataDependencies(invocationVtx, inVars, sdg, isNested);
	}
	return list(invocationVtx);
    }

    @Override
    public List<Vertex> methodInvocationAssignment(final MethodInvocationAssignment methodInvocationAssignment,
	    final SysDepGraph sdg, final boolean isNested) {
	// Invocation vertex
	final List<Vertex> invocationVtxLst = methodInvocation(methodInvocationAssignment, sdg, isNested);
	final Vertex invocationVtx = invocationVtxLst.get(0);
	final String methodName = methodInvocationAssignment.getMethodName();
	final String outVar = methodInvocationAssignment.getOutVar();
	// Actual out
	final Vertex actualOutVtx = new Vertex(VertexType.ACTUAL_OUT, outVar, lookupId(outVar));
	sdg.addVertex(actualOutVtx);
	putVarWriting(actualOutVtx);
	sdg.addEdge(invocationVtx, actualOutVtx, EdgeType.CTRL_TRUE);
	final Vertex resultVtx = sdg.getFirstVertexByLabel(getResultOutVtxName(methodName));
	sdg.addEdge(resultVtx, actualOutVtx, EdgeType.PARAM_OUT);
	// Actual in
	final Vertex actualInVtx = new Vertex(VertexType.ACTUAL_IN, outVar);
	sdg.addVertex(actualInVtx);
	sdg.addEdge(invocationVtx, actualInVtx, EdgeType.CTRL_TRUE);
	final Vertex calledResultInVertex = sdg.getFirstVertexByLabel(getResultInVtxName(methodName));
	sdg.addEdge(actualInVtx, calledResultInVertex, EdgeType.PARAM_IN);
	return list(invocationVtx);
    }

    @Override
    public List<Vertex> breakStmnt(final BreakStmnt breakStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Vertex breakVtx = new Vertex(VertexType.BREAK, "break");
	sdg.addVertex(breakVtx);
	// Get outer control vertex
	Vertex outerCtrlVtx = getOuterCtrlVtx();
	// Check if outer vertex exists or just use the result vertex
	if (outerCtrlVtx == getCurrentEnterVertex()) {
	    outerCtrlVtx = getCurrentResultVertex();
	}
	sdg.addEdge(breakVtx, outerCtrlVtx, EdgeType.CTRL_TRUE);
	return list(breakVtx);
    }

    @Override
    public List<Vertex> continueStmnt(final ContinueStmnt continueStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	final Vertex continueVtx = new Vertex(VertexType.CONTINUE, "continue");
	sdg.addVertex(continueVtx);
	final Vertex ctrlVtx = ctrlStack.getLast();
	sdg.addEdge(continueVtx, ctrlVtx, EdgeType.CTRL_TRUE);
	return list(continueVtx);
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
    public List<Vertex> returnStmnt(final ReturnStmnt returnStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expression returnedExpr = returnStmnt.getReturnedExpr();
	final Vertex returnVtx = new Vertex(VertexType.RETURN, returnedExpr.toString());
	sdg.addVertex(returnVtx);
	// Data dependencies
	dataDependencies(returnVtx, returnedExpr.getReadingVars(), sdg, isNested);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(returnVtx, sdg);
	}
	// Edge from RETURN vertex to FORMAL_OUT result vertex
	sdg.addEdge(returnVtx, getCurrentResultVertex(), EdgeType.FLOW);
	return list(returnVtx);
    }

    @Override
    protected void doFinally(final SysDepGraph sdg) {
	// FORMAL_OUT data dependencies
	// final Set<Vertex> vertices = sdg.vertexSet();
	// final List<Vertex> paramVtcs = vertices.stream().filter(v ->
	// v.getType().equals(VertexType.FORMAL_OUT))
	// .collect(Collectors.toList());
	// for (final Vertex v: paramVtcs) {
	// dataDependencies(v, exprs, sdg, isNested);
	// }
    }

    // Helper methods

    private List<Vertex> shortHandExpr(final Expression expr, final SysDepGraph sdg, final boolean isNested) {
	final String lookupId = expr.getReadingVars().iterator().next();
	final Vertex vtx = new Vertex(VertexType.ASSIGN, expr.toString(), lookupId(lookupId));
	sdg.addVertex(vtx);
	// Data dependencies
	dataDependencies(vtx, expr.getReadingVars(), sdg, isNested);
	return list(vtx);
    }

    private void notNestedStmntEdge(final Vertex vertex, final SysDepGraph sdg) {
	final Vertex enterVtx = getCurrentEnterVertex();
	notNestedStmntEdge(enterVtx, vertex, sdg);
    }

    private void notNestedStmntEdge(final Vertex enterVtx, final Vertex vertex, final SysDepGraph sdg) {
	sdg.addEdge(enterVtx, vertex, EdgeType.CTRL_TRUE);
    }

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Statement> body,
	    final SysDepGraph sdg, final boolean isNested) {
	return ctrlStructureTrue(conditionVtx, body, sdg, false, null, isNested);
    }

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Statement> body,
	    final SysDepGraph sdg, final Function<Void, Void> f, final boolean isNested) {
	return ctrlStructureTrue(conditionVtx, body, sdg, false, f, isNested);
    }

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Statement> body,
	    final SysDepGraph sdg, final boolean isDoStmnt, final Function<Void, Void> f, final boolean isNested) {
	List<Vertex> result = new ArrayList<>();
	List<Vertex> bodyVtcs = new ArrayList<>();
	ctrlStack.add(conditionVtx);
	// System.out.println("Ctrl changed: " + conditionVtx);
	if (f != null)
	    f.apply(null);
	// Will be used to generate data dependencies
	if (isDoStmnt) {
	    currentCtrlIsDoStmnt = true;
	}
	bodyVtcs = _build(body, sdg, true);
	currentCtrlIsDoStmnt = false;
	// System.out.println(ctrlVtxVarDeclMap);
	final Vertex currentCtrlVtx = ctrlStack.pollLast();
	removeScopedVarDecl(currentCtrlVtx);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(conditionVtx, sdg);
	    result = bodyVtcs;
	} else {
	    result.add(conditionVtx);
	}
	ctrlTrueEdges(conditionVtx, bodyVtcs, sdg);
	// Add extra control edges if do stmnt because the body will execute at least once
	// no matter the condition.
	if (isDoStmnt) {
	    ctrlTrueEdges(currentCtrlVtx, bodyVtcs, sdg);
	}
	return result;
    }

    private void ctrlTrueEdges(final Vertex source, final List<Vertex> targets, final SysDepGraph sdg) {
	for (final Vertex v : targets) {
	    sdg.addEdge(source, v, EdgeType.CTRL_TRUE);
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
	    final List<Vertex> vtcs = getAllVerticesInScope(lookupId(s));
	    if (!vtcs.isEmpty()) {
		vtcs.forEach(v -> sdg.addEdge(v, vertex, EdgeType.FLOW));
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

    private void ctrlFalseEdges(final Vertex source, final List<Vertex> targets, final SysDepGraph sdg) {
	for (final Vertex v : targets) {
	    sdg.addEdge(source, v, EdgeType.CTRL_FALSE);
	}
    }

}