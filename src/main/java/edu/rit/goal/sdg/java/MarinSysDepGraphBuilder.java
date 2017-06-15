package edu.rit.goal.sdg.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.rit.goal.sdg.java.graph.EdgeType;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.graph.VertexType;
import edu.rit.goal.sdg.java.statement.Assignment;
import edu.rit.goal.sdg.java.statement.BreakStmnt;
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
	final String resultOutVtxName = getResultOutVtxName(methodName);
	final Vertex resultOutVtx = new Vertex(VertexType.FORMAL_OUT, resultOutVtxName, resultOutVtxName);
	sdg.addVertex(resultOutVtx);
	notNestedStmntEdge(enterVtx, resultOutVtx, sdg);
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
	varStack.add(declVtx);
	if (!isNested) {
	    notNestedStmntEdge(declVtx, sdg);
	}
	return list(declVtx);
    }

    @Override
    public List<Vertex> basicForStmnt(final BasicForStmnt basicForStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	// Initialization
	final List<Statement> init = basicForStmnt.getInit();
	_build(init, sdg, isNested);
	// Condition
	final Expression cond = basicForStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, cond.toString());
	// Body
	final List<Statement> body = basicForStmnt.getBody();
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, isNested);
	// Loop
	sdg.addEdge(condVtx, condVtx, EdgeType.CTRL_TRUE);
	// Update
	final List<Statement> update = basicForStmnt.getUpdate();
	final List<Vertex> updateVtcs = _build(update, sdg, isNested);
	ctrlTrueEdges(condVtx, updateVtcs, sdg);
	// Data dependencies
	dataDependencies(condVtx, cond.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> ifThenElseStmnt(final IfThenElseStmnt ifThenElseStmnt, final SysDepGraph sdg,
	    final boolean isNested) {
	// Condition
	final Expression condition = ifThenElseStmnt.getCondition();
	final Vertex conditionVtx = new Vertex(VertexType.COND, condition.toString());
	// Then branch
	final List<Statement> thenBranch = ifThenElseStmnt.getThenBranch();
	final List<Vertex> result = ctrlStructureTrue(conditionVtx, thenBranch, sdg, isNested);
	// Else branch
	final List<Statement> elseBranch = ifThenElseStmnt.getElseBranch();
	final List<Vertex> elseVtcs = _build(elseBranch, sdg, true);
	ctrlFalseEdges(conditionVtx, elseVtcs, sdg);
	// dataDependencies(conditionVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> ifThenStmnt(final IfThenStmnt ifThenStmnt, final SysDepGraph sdg, final boolean isNested) {
	// Condition
	final Expression condition = ifThenStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	// Then branch
	final List<Statement> thenBranch = ifThenStmnt.getThenBranch();
	final List<Vertex> result = ctrlStructureTrue(condVtx, thenBranch, sdg, isNested);
	// dataDependencies(conditionVtx, condition.getReadingVars(), sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> whileStmnt(final WhileStmnt whileStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Expression condition = whileStmnt.getCondition();
	final List<Statement> body = whileStmnt.getBody();
	final Vertex conditionVtx = new Vertex(VertexType.COND, condition.toString());
	final List<Vertex> result = ctrlStructureTrue(conditionVtx, body, sdg, isNested);
	return result;
    }

    @Override
    public List<Vertex> doStmnt(final DoStmnt doStmnt, final SysDepGraph sdg, final boolean isNested) {
	// Condition
	final Expression condition = doStmnt.getCondition();
	final Vertex condVtx = new Vertex(VertexType.COND, condition.toString());
	// Body
	final List<Statement> body = doStmnt.getBody();
	final boolean isDoStmnt = true;
	final List<Vertex> result = ctrlStructureTrue(condVtx, body, sdg, isDoStmnt, isNested);
	return result;
    }

    @Override
    public List<Vertex> assignment(final Assignment assignment, final SysDepGraph sdg, final boolean isNested,
	    final List<Statement> scope) {
	final String assignedVar = assignment.getLeftHandSide();
	final Vertex assignVtx = new Vertex(VertexType.ASSIGN, assignment.toString(), lookupId(assignedVar));
	sdg.addVertex(assignVtx);
	varStack.add(assignVtx);
	return list(assignVtx);
    }

    @Override
    public List<Vertex> methodInvocation(final MethodInvocation methodInvocation, final SysDepGraph sdg,
	    final boolean isNested) {
	final Vertex invocationVtx = new Vertex(VertexType.CALL, methodInvocation.toString());
	sdg.addVertex(invocationVtx);
	return list(invocationVtx);
    }

    @Override
    public List<Vertex> methodInvocationAssignment(final MethodInvocationAssignment methodInvocationAssignment,
	    final SysDepGraph sdg, final boolean isNested) {
	// Invocation vertex
	final Vertex invocationVtx = new Vertex(VertexType.CALL, methodInvocationAssignment.toString());
	sdg.addVertex(invocationVtx);
	return list(invocationVtx);
    }

    @Override
    public List<Vertex> breakStmnt(final BreakStmnt breakStmnt, final SysDepGraph sdg, final boolean isNested) {
	final Vertex breakVtx = new Vertex(VertexType.BREAK, "break");
	sdg.addVertex(breakVtx);
	Vertex ctrlVtx = getCurrentControlVertex();
	if (ctrlVtx == getCurrentEnterVertex()) {
	    ctrlVtx = getCurrentResultVertex();
	}
	sdg.addEdge(breakVtx, ctrlVtx, EdgeType.CTRL_TRUE);
	return list(breakVtx);
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
	final Vertex v = new Vertex(VertexType.RETURN, returnedExpr.toString());
	sdg.addVertex(v);
	if (!isNested) {
	    notNestedStmntEdge(v, sdg);
	}
	return list(v);
    }

    @Override
    protected void doFinally() {
	// TODO Auto-generated method stub

    }

    // Helper methods

    private List<Vertex> shortHandExpr(final Expression expr, final SysDepGraph sdg, final boolean isNested) {
	final String lookupId = expr.getReadingVars().iterator().next();
	final Vertex vtx = new Vertex(VertexType.ASSIGN, expr.toString(), lookupId(lookupId));
	sdg.addVertex(vtx);
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
	return ctrlStructureTrue(conditionVtx, body, sdg, false, isNested);
    }

    protected List<Vertex> ctrlStructureTrue(final Vertex conditionVtx, final List<Statement> body,
	    final SysDepGraph sdg, final boolean isDoStmnt, final boolean isNested) {
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
	// Add extra control edges if do stmnt because the body will execute at least once
	// no matter the condition.
	if (isDoStmnt) {
	    final Vertex ctrlVtx = getCurrentControlVertex();
	    ctrlTrueEdges(ctrlVtx, bodyVtcs, sdg);
	}
	setCurrentControlVertex(conditionVtx);
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
	    // TODO: Check scope of variables?
	    final List<Vertex> vtcs = sdg.getAllVerticesByLabel(lookupId(s));
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

    private void ctrlFalseEdges(final Vertex source, final List<Vertex> targets, final SysDepGraph sdg) {
	for (final Vertex v : targets) {
	    sdg.addEdge(source, v, EdgeType.CTRL_FALSE);
	}
    }

}