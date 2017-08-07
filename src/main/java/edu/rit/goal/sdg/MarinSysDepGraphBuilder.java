package edu.rit.goal.sdg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import edu.rit.goal.sdg.graph.EdgeType;
import edu.rit.goal.sdg.graph.PrimitiveType;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;
import edu.rit.goal.sdg.java8.visitor.VisitorUtils;
import edu.rit.goal.sdg.statement.ArrayAccessAssignment;
import edu.rit.goal.sdg.statement.Assignment;
import edu.rit.goal.sdg.statement.BreakStmnt;
import edu.rit.goal.sdg.statement.ContinueStmnt;
import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.FormalParameter;
import edu.rit.goal.sdg.statement.MethodInvocation;
import edu.rit.goal.sdg.statement.MethodInvocationAssignment;
import edu.rit.goal.sdg.statement.MethodSignature;
import edu.rit.goal.sdg.statement.PostDecrementExpr;
import edu.rit.goal.sdg.statement.PostIncrementExpr;
import edu.rit.goal.sdg.statement.PreDecrementExpr;
import edu.rit.goal.sdg.statement.PreIncrementExpr;
import edu.rit.goal.sdg.statement.ReturnStmnt;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.VariableDecl;
import edu.rit.goal.sdg.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.statement.control.DoStmnt;
import edu.rit.goal.sdg.statement.control.EnhancedForStmnt;
import edu.rit.goal.sdg.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.statement.control.SwitchBlockStmntGroup;
import edu.rit.goal.sdg.statement.control.SwitchStmnt;
import edu.rit.goal.sdg.statement.control.WhileStmnt;

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
	    final boolean isNested, final List<Stmt> scope, final boolean isForStmntHeader) {
	final String varName = variableDecl.getVariableDeclaratorId();
	final Expr varInit = variableDecl.getVariableInitializer();
	final Vertex declVtx = new Vertex(VertexType.DECL, variableDecl.toString(), lookupId(varName));
	sdg.addVertex(declVtx);
	putVarWriting(declVtx, isForStmntHeader);
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
	final List<Stmt> init = basicForStmnt.getInit();
	final Expr cond = basicForStmnt.getCondition();
	final List<Stmt> update = basicForStmnt.getUpdate();
	final List<Stmt> body = basicForStmnt.getBody();
	return forStmnt(init, cond, update, body, sdg, isNested);
    }

    @Override
    public List<Vertex> enhancedForStmnt(final EnhancedForStmnt enhancedForStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	final String var = enhancedForStmnt.getVar();
	// Condition
	final Expr iterable = enhancedForStmnt.getIterable();
	final Vertex condVtx = new Vertex(VertexType.COND, iterable.toString());
	sdg.addVertex(condVtx);
	// Update. Needs to be executed after updating the control stack, so we pass it as
	// a function
	final Function<Void, Void> f = arg -> {
	    final Vertex updVtx = createDeclVtx(var, var, false);
	    sdg.addVertex(updVtx);
	    sdg.addEdge(condVtx, updVtx, EdgeType.CTRL_TRUE);
	    return null;
	};
	// Body
	final boolean isLoopBody = true;
	final List<Stmt> body = enhancedForStmnt.getBody();
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, f, isNested, isLoopBody);
	// Loop
	sdg.addEdge(condVtx, condVtx, EdgeType.CTRL_TRUE);
	return result;
    }

    public List<Vertex> forStmnt(final List<Stmt> init, final Expr cond, final List<Stmt> update,
	    final List<Stmt> body, final SysDepGraph sdg, final boolean isNested) {
	// Condition
	final Vertex condVtx = new Vertex(VertexType.COND, cond.toString());
	sdg.addVertex(condVtx);
	// Loop
	sdg.addEdge(condVtx, condVtx, EdgeType.CTRL_TRUE);
	final List<Vertex> updateVtcs = new ArrayList<>();
	final Function<Void, Void> f = arg -> {
	    final boolean isForStmntHeader = true;
	    // Initialization. Needs to be executed after updating the control stack so
	    // that
	    // the mapping ctrlVtxVarDeclMap is correct.
	    build(init, sdg, isNested, isForStmntHeader, false);
	    // Update. Setting isNested to true will prevent a CTRL_TRUE edge from the
	    // outer conditional vertex to the update vertex, which depends on the
	    // conditional vertex of this for.
	    updateVtcs.addAll(build(update, sdg, true, isForStmntHeader, false));
	    // Data dependencies
	    dataDependencies(condVtx, cond.getReadingVars(), sdg, isNested);
	    return null;
	};
	// Body
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, f, isNested, true);
	ctrlTrueEdges(condVtx, updateVtcs, sdg);
	return result;
    }

    @Override
    public List<Vertex> ifThenElseStmnt(final IfThenElseStmnt ifThenElseStmnt, final SysDepGraph sdg,
	    final boolean isNested, final boolean isLoopBody) {
	// Condition
	final Expr condition = ifThenElseStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	// Then branch
	final List<Stmt> thenBranch = ifThenElseStmnt.getThenBranch();
	final List<Vertex> result = ctrlStructureTrue(condVtx, thenBranch, sdg, isNested, isLoopBody);
	// Else branch
	final List<Stmt> elseBranch = ifThenElseStmnt.getElseBranch();
	final List<Vertex> elseVtcs = build(elseBranch, sdg, true, false, isLoopBody);
	ctrlFalseEdges(condVtx, elseVtcs, sdg);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> ifThenStmnt(final IfThenStmnt ifThenStmnt, final SysDepGraph sdg, final boolean isNested,
	    final boolean isLoopBody) {
	// Condition
	final Expr condition = ifThenStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	// Then branch
	final List<Stmt> thenBranch = ifThenStmnt.getThenBranch();
	final List<Vertex> result = ctrlStructureTrue(condVtx, thenBranch, sdg, isNested, isLoopBody);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> whileStmnt(final WhileStmnt whileStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expr condition = whileStmnt.getCondition();
	final List<Stmt> body = whileStmnt.getBody();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	final boolean isLoopBody = true;
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, isNested, isLoopBody);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> doStmnt(final DoStmnt doStmnt, final SysDepGraph sdg, final boolean isNested) {
	// Condition
	final Expr condition = doStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	sdg.addVertex(condVtx);
	// Body
	final List<Stmt> body = doStmnt.getBody();
	final boolean isDoStmnt = true;
	final boolean isLoopBody = true;
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, isDoStmnt, null, isNested, isLoopBody);
	// Data dependencies
	dataDependencies(condVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> switchStmnt(final SwitchStmnt switchStmnt, final SysDepGraph sdg, final boolean isNested,
	    final boolean isLoopBody) {
	final List<Vertex> result = new ArrayList<>();
	final String switchExpr = switchStmnt.getExpr().toString();
	final List<SwitchBlockStmntGroup> blocks = switchStmnt.getBlocks();
	final List<String> labels = new ArrayList<>();
	for (final SwitchBlockStmntGroup b : blocks) {
	    labels.addAll(b.getSwitchLabels());
	    final String vtxLabel = labels.stream().map(l -> switchExpr + "==" + l).collect(Collectors.joining("||"));
	    final Vertex condVtx = new Vertex(VertexType.COND, vtxLabel);
	    sdg.addVertex(condVtx);
	    result.addAll(ctrlStructureTrue(condVtx, b.getBlockStmnts(), sdg, isNested, isLoopBody));
	    for (final Stmt stmnt : b.getBlockStmnts()) {
		if (stmnt instanceof BreakStmnt) {
		    labels.clear();
		    break;
		}
	    }
	}
	return result;
    }

    @Override
    public List<Vertex> arrayAccessAssignment(final ArrayAccessAssignment arrayAccessAssignment, final SysDepGraph sdg,
	    final boolean isNested, final List<Stmt> scope, final boolean isForStmntHeader) {
	final String assignedVar = arrayAccessAssignment.getExpressionName();
	final Vertex assignVtx = new Vertex(VertexType.ASSIGN, arrayAccessAssignment.toString(), lookupId(assignedVar));
	sdg.addVertex(assignVtx);
	putVarWriting(assignVtx, isForStmntHeader);
	final List<Expr> exprs = new ArrayList<>();
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
	    final List<Stmt> scope, final boolean isForStmntHeader, final boolean isLoopBody) {
	final String outVar = assignment.getOutVar();
	final Vertex assignVtx = new Vertex(VertexType.ASSIGN, assignment.toString(), lookupId(outVar));
	final Set<String> inVars = assignment.getInVars();
	sdg.addVertex(assignVtx);
	// Data dependencies. Depending on if it is a for header, swap the order of
	// function calls. It ensures correct flow dependencies.
	// Check this example -> for (int i = 0; i < 10; i += 1) {i += 4;}
	if (isForStmntHeader) {
	    putVarWriting(assignVtx, isForStmntHeader);
	    dataDependencies(assignVtx, inVars, sdg, isNested);
	} else {
	    dataDependencies(assignVtx, inVars, sdg, isNested);
	    putVarWriting(assignVtx, isForStmntHeader);
	}
	// Loop-carried flow dependence
	if (isLoopBody && VisitorUtils.isSelfAssignment(assignment)) {
	    sdg.addEdge(assignVtx, assignVtx, EdgeType.FLOW);
	}
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
	final List<Expr> inVars = methodInvocation.getInVars();
	if (inVars != null) {
	    for (int i = 0; i < inVars.size(); i++) {
		final Vertex formalParam = getFormalParameter(methodName, i);
		// Only if the method is part of the program itself and not some other API
		if (formalParam != null) {
		    final Expr var = inVars.get(i);
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
	// Set adequate type
	invocationVtx.setType(VertexType.ASSIGN);
	final String methodName = methodInvocationAssignment.getMethodName();
	final String outVar = methodInvocationAssignment.getOutVar();
	// Actual out
	final Vertex actualOutVtx = new Vertex(VertexType.ACTUAL_OUT, outVar, lookupId(outVar));
	sdg.addVertex(actualOutVtx);
	putVarWriting(actualOutVtx, false);
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
	final Vertex outerCtrlVtx = getOuterCtrlVtx();
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
	    final boolean isNested, final boolean isForStmntHeader, final boolean isLoopBody) {
	return shortHandExpr(postIncrementExpr, sdg, isNested, isForStmntHeader, isLoopBody);
    }

    @Override
    public List<Vertex> postDecrementExpr(final PostDecrementExpr postDecrementExpr, final SysDepGraph sdg,
	    final boolean isNested, final boolean isForStmntHeader, final boolean isLoopBody) {
	return shortHandExpr(postDecrementExpr, sdg, isNested, isForStmntHeader, isLoopBody);

    }

    @Override
    public List<Vertex> preIncrementExpr(final PreIncrementExpr preIncrementExpr, final SysDepGraph sdg,
	    final boolean isNested, final boolean isForStmntHeader, final boolean isLoopBody) {
	return shortHandExpr(preIncrementExpr, sdg, isNested, isForStmntHeader, isLoopBody);

    }

    @Override
    public List<Vertex> preDecrementExpr(final PreDecrementExpr preDecrementExpr, final SysDepGraph sdg,
	    final boolean isNested, final boolean isForStmntHeader, final boolean isLoopBody) {
	return shortHandExpr(preDecrementExpr, sdg, isNested, isForStmntHeader, isLoopBody);

    }

    @Override
    public List<Vertex> returnStmnt(final ReturnStmnt returnStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expr returnedExpr = returnStmnt.getReturnedExpr();
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

    private List<Vertex> shortHandExpr(final Expr expr, final SysDepGraph sdg, final boolean isNested,
	    final boolean isForStmntHeader, final boolean isLoopBody) {
	final String lookupId = expr.getReadingVars().iterator().next();
	final Vertex vtx = new Vertex(VertexType.ASSIGN, expr.toString(), lookupId(lookupId));
	sdg.addVertex(vtx);
	if (!isNested) {
	    // Method entry dependency
	    notNestedStmntEdge(vtx, sdg);
	}
	// Data dependencies
	dataDependencies(vtx, expr.getReadingVars(), sdg, isNested);
	// Self flow dependence if inside a loop and is a short-hand expression
	if (isLoopBody) {
	    sdg.addEdge(vtx, vtx, EdgeType.FLOW);
	}
	putVarWriting(vtx, isForStmntHeader);
	return list(vtx);
    }

    private void notNestedStmntEdge(final Vertex vertex, final SysDepGraph sdg) {
	final Vertex enterVtx = getCurrentEnterVertex();
	notNestedStmntEdge(enterVtx, vertex, sdg);
    }

    private void notNestedStmntEdge(final Vertex enterVtx, final Vertex vertex, final SysDepGraph sdg) {
	sdg.addEdge(enterVtx, vertex, EdgeType.CTRL_TRUE);
    }

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Stmt> body,
	    final SysDepGraph sdg, final boolean isNested, final boolean isLoopBody) {
	return ctrlStructureTrue(conditionVtx, body, sdg, false, null, isNested, isLoopBody);
    }

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Stmt> body,
	    final SysDepGraph sdg, final Function<Void, Void> f, final boolean isNested, final boolean isLoopBody) {
	return ctrlStructureTrue(conditionVtx, body, sdg, false, f, isNested, isLoopBody);
    }

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Stmt> body,
	    final SysDepGraph sdg, final boolean isDoStmnt, final Function<Void, Void> f, final boolean isNested,
	    final boolean isLoopBody) {
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
	final boolean isForStmntHeader = false;
	bodyVtcs = build(body, sdg, true, isForStmntHeader, isLoopBody);
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
	    // Add extra control edges if do stmnt because the body will execute at least
	    // once
	    // no matter the condition.
	    if (isDoStmnt) {
		result.addAll(bodyVtcs);
	    }
	}
	ctrlTrueEdges(conditionVtx, bodyVtcs, sdg);
	return result;
    }

    private void ctrlTrueEdges(final Vertex source, final List<Vertex> targets, final SysDepGraph sdg) {
	for (final Vertex v : targets) {
	    sdg.addEdge(source, v, EdgeType.CTRL_TRUE);
	}
    }

    private void dataDependencies(final Vertex vertex, final List<Expr> exprs, final SysDepGraph sdg,
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