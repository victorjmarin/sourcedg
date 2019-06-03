package sourcedg.builder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr.Operator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.utils.Pair;

import sourcedg.graph.CFG;
import sourcedg.graph.Edge;
import sourcedg.graph.EdgeType;
import sourcedg.graph.PDG;
import sourcedg.graph.Vertex;
import sourcedg.graph.VertexCreator;

/*
 * Builds the Control Dependence Subgraph
 */
public class CDGBuilder {

	private Map<String, Integer> unmatchedAstNodes;

	private final CompilationUnit cu;
	private VertexCreator vtxCreator;
	private CFGBuilder cfgBuilder;
	private Deque<String> clsStack;
	private HashMap<String, Pair<Vertex, List<Vertex>>> methodParams;
	private HashMap<String, Set<Pair<Vertex, List<Vertex>>>> calls;
	private HashMap<Vertex, Vertex> methodFormalOut;

	private PDG cdg;
	private Deque<List<Vertex>> inScopeStack;
	private List<Vertex> inScope;
	private Deque<Vertex> loopStack;
	private Deque<Vertex> formalOutStack;

	public CDGBuilder(final CompilationUnit cu) {
		this.cu = cu;
	}

	public void build(PDGBuilderConfig cfg) {
		unmatchedAstNodes = new HashMap<>();
		vtxCreator = new VertexCreator(cfg);
		vtxCreator.setId(cfg.getInitialVertexId());
		cfgBuilder = new CFGBuilder();
		cdg = new PDG();
		clsStack = new ArrayDeque<>();
		inScopeStack = new ArrayDeque<>();
		inScope = new ArrayList<>();
		loopStack = new ArrayDeque<>();
		formalOutStack = new ArrayDeque<>();
		methodParams = new HashMap<>();
		calls = new HashMap<>();
		methodFormalOut = new HashMap<>();
		final List<Node> children = cu.getChildNodes();
		for (final Node n : children)
			_build(n);
	}

	private ControlFlow _build(final Node n) {
		ControlFlow result = null;
		if (n instanceof ClassOrInterfaceDeclaration)
			result = classOrInterfaceDeclaration((ClassOrInterfaceDeclaration) n);
		else if (n instanceof ConstructorDeclaration)
			result = constructorDeclaration((ConstructorDeclaration) n);
		else if (n instanceof ExplicitConstructorInvocationStmt)
			result = explicitConstructorInvocationStmt((ExplicitConstructorInvocationStmt) n);
		else if (n instanceof BlockStmt)
			result = blockStmt((BlockStmt) n);
		else if (n instanceof ExpressionStmt)
			result = expressionStmt((ExpressionStmt) n);
		else if (n instanceof MethodDeclaration)
			result = methodDeclaration((MethodDeclaration) n);
		else if (n instanceof Parameter)
			result = parameter((Parameter) n);
		else if (n instanceof IfStmt)
			result = ifStmt((IfStmt) n);
		else if (n instanceof ForStmt)
			result = forStmt((ForStmt) n);
		else if (n instanceof ForEachStmt)
			result = forEachStmt((ForEachStmt) n);
		else if (n instanceof WhileStmt)
			result = whileStmt((WhileStmt) n);
		else if (n instanceof DoStmt)
			result = doStmt((DoStmt) n);
		else if (n instanceof VariableDeclarationExpr)
			result = variableDeclarationExpr((VariableDeclarationExpr) n);
		else if (n instanceof VariableDeclarator) {
			VariableDeclarator vd = (VariableDeclarator) n;
			// Ignore declarations without initialization.
			if (vd.getInitializer().isPresent())
				result = variableDeclarator(vd);
		} else if (n instanceof AssignExpr)
			result = assignExpr((AssignExpr) n);
		else if (n instanceof MethodCallExpr)
			result = methodCallExpr((MethodCallExpr) n);
		else if (n instanceof UnaryExpr)
			result = unaryExpr((UnaryExpr) n);
		else if (n instanceof ReturnStmt)
			result = returnStmt((ReturnStmt) n);
		else if (n instanceof BreakStmt)
			result = breakStmt((BreakStmt) n);
		else if (n instanceof ContinueStmt)
			result = continueStmt((ContinueStmt) n);
		else if (n instanceof TryStmt)
			result = tryStmt((TryStmt) n);
		else if (n instanceof CatchClause)
			result = catchClause((CatchClause) n);
		else if (n instanceof ThrowStmt)
			result = throwStmt((ThrowStmt) n);
		else if (n instanceof LabeledStmt)
			result = labeledStmt((LabeledStmt) n);
		else
			logUnmatched(n);
		return result;
	}

	private void logUnmatched(Node n) {
		final String nodeName = n.getClass().getSimpleName();
		PDGBuilder.LOGGER.warning("No match for " + nodeName + "\n" + n.toString());
		Integer count = unmatchedAstNodes.get(nodeName);
		if (count == null)
			count = 0;
		unmatchedAstNodes.put(nodeName, count + 1);
	}

	private ControlFlow blockStmt(final BlockStmt n) {
		final List<ControlFlow> flow = new ArrayList<>();
		for (final Statement s : n.getStatements())
			flow.add(_build(s));
		return cfgBuilder.seq(flow);
	}

	private ControlFlow labeledStmt(LabeledStmt n) {
		logUnmatched(n);
		return _build(n.getStatement());
	}

	private ControlFlow expressionStmt(final ExpressionStmt n) {
		final Expression expr = n.getExpression();
		return _build(expr);
	}

	private ControlFlow classOrInterfaceDeclaration(final ClassOrInterfaceDeclaration n) {
		clsStack.push(n.getNameAsString());
		final Vertex v = vtxCreator.classOrInterfaceDeclaration(n);
		cdg.addVertex(v);
		// Constructors
		final List<ConstructorDeclaration> constructors = n.getConstructors();
		for (final ConstructorDeclaration c : constructors)
			_build(c);
		// Methods
		final List<MethodDeclaration> methods = n.getMethods();
		for (final MethodDeclaration m : methods)
			_build(m);
		final List<ClassOrInterfaceDeclaration> classes = n.findAll(ClassOrInterfaceDeclaration.class,
				t -> !t.equals(n));
		for (final ClassOrInterfaceDeclaration c : classes)
			_build(c);
		addEdges(EdgeType.MEMBER_OF, v, inScope);
		clsStack.pop();
		return null;
	}

	private ControlFlow constructorDeclaration(final ConstructorDeclaration n) {
		final Vertex v = vtxCreator.constructorDeclaration(n);
		cdg.addVertex(v);
		inScope.add(v);
		pushScope();
		final NodeList<Parameter> params = n.getParameters();
		final List<ControlFlow> paramFlow = params(params, v, n.getNameAsString());
		final ControlFlow bodyFlow = _build(n.getBody());
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		popScope();
		// CFG
		final ControlFlow result = cfgBuilder.methodDeclaration(v, paramFlow, bodyFlow);
		cfgBuilder.put(v);
		return result;
	}

	private ControlFlow explicitConstructorInvocationStmt(final ExplicitConstructorInvocationStmt n) {
		final Vertex v = vtxCreator.explicitConstructorInvocationStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		return args(v, n);
	}

	private List<ControlFlow> params(final NodeList<Parameter> params, final Vertex v, final String name) {
		final List<ControlFlow> result = new ArrayList<>();
		final List<Vertex> paramVtcs = new ArrayList<>();
		for (final Parameter p : params) {
			final ControlFlow f = _build(p);
			result.add(f);
			final Vertex paramVtx = f.getIn();
			paramVtcs.add(paramVtx);
		}
		final String methodName = callName(name);
		methodParams.put(methodName, new Pair<>(v, paramVtcs));
		return result;
	}

	private ControlFlow methodDeclaration(final MethodDeclaration n) {
		final Vertex v = vtxCreator.methodDeclaration(n);
		cdg.addVertex(v);
		inScope.add(v);
		pushScope();
		final Vertex out = formalOut(n);
		if (out != null) {
			formalOutStack.push(out);
			methodFormalOut.put(v, out);
		}
		final NodeList<Parameter> params = n.getParameters();
		final List<ControlFlow> paramFlow = params(params, v, n.getNameAsString());
		final Optional<BlockStmt> body = n.getBody();
		ControlFlow bodyFlow = null;
		if (body.isPresent())
			bodyFlow = _build(body.get());
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		popScope();
		// CFG
		Vertex exit = vtxCreator.exit();
		ControlFlow exitFlow = new ControlFlow(exit, exit);
		bodyFlow = cfgBuilder.seq(bodyFlow, exitFlow);
		final ControlFlow result = cfgBuilder.methodDeclaration(v, paramFlow, bodyFlow);
		cfgBuilder.put(v);
		if (out != null)
			formalOutStack.pop();
		return result;
	}

	private ControlFlow parameter(final Parameter n) {
		final Vertex v = vtxCreator.parameter(n);
		cdg.addVertex(v);
		inScope.add(v);
		return new ControlFlow(v, v);
	}

	private Vertex formalOut(final MethodDeclaration n) {
		if ("void".equals(n.getType().asString()))
			return null;
		final Vertex v = vtxCreator.formalOut();
		cdg.addVertex(v);
		inScope.add(v);
		return v;
	}

	private ControlFlow actualOut(final Vertex v, final Node n) {
		final Vertex a = vtxCreator.actualOut(n);
		cdg.addVertex(a);
		addEdge(EdgeType.CTRL_TRUE, v, a);
		return new ControlFlow(a, a);
	}

	private ControlFlow arrayIdx(final Vertex v, final Node n) {
		final Vertex a = vtxCreator.arrayIdx(n);
		cdg.addVertex(a);
		addEdge(EdgeType.CTRL_TRUE, v, a);
		return new ControlFlow(a, a);
	}

	private ControlFlow variableDeclarationExpr(final VariableDeclarationExpr n) {
		final List<ControlFlow> flow = new ArrayList<>();
		for (final VariableDeclarator v : n.getVariables())
			flow.add(_build(v));
		return cfgBuilder.seq(flow);
	}

	private ControlFlow variableDeclarator(final VariableDeclarator n) {
		final Vertex v = vtxCreator.variableDeclarator(n);
		cdg.addVertex(v);
		inScope.add(v);
		ControlFlow result = new ControlFlow(v, v);

		// Check for call
		final Optional<Expression> init = n.getInitializer();
		if (init.isPresent() && init.get() instanceof MethodCallExpr) {
			final MethodCallExpr call = (MethodCallExpr) init.get();
			result = delegatedMethodCall(call, v, n.getName());
		} else if (init.isPresent() && init.get() instanceof ArrayAccessExpr) {
			ArrayAccessExpr arrAccess = (ArrayAccessExpr) init.get();
			Expression idx = arrAccess.getIndex();
			result = cfgBuilder.seq(arrayIdx(v, idx), result);
			// Uses are set in the ARRAY_IDX vertex
			v.clearUses();
		} else if (init.isPresent() && init.get() instanceof ArrayCreationExpr) {
			ArrayCreationExpr expr = (ArrayCreationExpr) init.get();
			NodeList<ArrayCreationLevel> levels = expr.getLevels();
			List<ControlFlow> flows = new ArrayList<>();
			Set<String> arrRefs = new HashSet<>();
			for (ArrayCreationLevel lvl : levels) {
				ControlFlow arrIdxCf = arrayIdx(v, lvl);
				flows.add(arrIdxCf);
				arrRefs.addAll(arrIdxCf.getIn().getUses());
			}
			flows.add(result);
			result = cfgBuilder.seq(flows);
			// Uses are set in the ARRAY_IDX vertices
			v.getUses().removeAll(arrRefs);
		}

		return result;
	}

	private ControlFlow assignExpr(final AssignExpr n) {
		final Vertex v = vtxCreator.assignExpr(n);
		cdg.addVertex(v);
		inScope.add(v);
		ControlFlow result = new ControlFlow(v, v);

		final Expression target = n.getTarget();
		if (target instanceof ArrayAccessExpr) {
			ArrayAccessExpr arrAccess = (ArrayAccessExpr) target;
			v.getUses().addAll(VertexCreator.names(arrAccess.getIndex()));
//			Expression idx = arrAccess.getIndex();
//			result = cfgBuilder.seq(arrayIdx(v, idx), result);
		}

		// Check for call
		final Expression value = n.getValue();
		if (value instanceof MethodCallExpr) {
			final MethodCallExpr call = (MethodCallExpr) value;
			result = delegatedMethodCall(call, v, n.getTarget());
		} else if (value instanceof ArrayAccessExpr) {
			ArrayAccessExpr arrAccess = (ArrayAccessExpr) value;
			Expression idx = arrAccess.getIndex();
			ControlFlow arrIdxCf = arrayIdx(v, idx);
			result = cfgBuilder.seq(arrIdxCf, result);
			// Uses are set in the ARRAY_IDX vertex
			v.getUses().removeAll(arrIdxCf.getIn().getUses());
		}
		return result;
	}

	private ControlFlow delegatedMethodCall(MethodCallExpr call, Vertex v, Node n) {
		// Def and uses are set in the corresponding ACTUAL_OUT and ACTUAL_IN vertices
		v.clearDefUses();
		// Set uses w.r.t. invoked objects
		final Optional<Expression> scope = call.getScope();
		if (scope.isPresent()) {
			String scopeVar = scope.get().toString();
			final Set<String> uses = new HashSet<>();
			uses.add(scopeVar);
			v.setUses(uses);
		}
		final ControlFlow inFlow = args(v, call);
		final ControlFlow outFlow = actualOut(v, n);
		return cfgBuilder.seq(inFlow, outFlow);
	}

	private ControlFlow methodCallExpr(final MethodCallExpr n) {
		final Vertex v = vtxCreator.methodCallExpr(n);
		cdg.addVertex(v);
		inScope.add(v);
		return args(v, n);
	}

	private Vertex argumentExpr(final Expression e) {
		final Vertex v = vtxCreator.argumentExpr(e);
		cdg.addVertex(v);
		return v;
	}

	private ControlFlow unaryExpr(final UnaryExpr n) {
		final Operator op = n.getOperator();
		switch (op) {
		case PREFIX_INCREMENT:
		case PREFIX_DECREMENT:
		case POSTFIX_INCREMENT:
		case POSTFIX_DECREMENT:
			final Vertex v = vtxCreator.unaryExpr(n);
			cdg.addVertex(v);
			inScope.add(v);
			return new ControlFlow(v, v);
		default:
			PDGBuilder.LOGGER.warning("Operation " + op + " not considered.");
		}
		return null;
	}

	private ControlFlow returnStmt(final ReturnStmt n) {
		final Vertex v = vtxCreator.returnStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		if (n.getExpression().isPresent()) {
			final Vertex formalOut = formalOutStack.peek();
			if (formalOut == null)
				throw new IllegalStateException(
						"Is " + n + " (line " + n.getRange().get().begin.line + ") inside a method that returns void?");
			else
				addEdge(EdgeType.DATA, v, formalOut);
		}
		return new ControlFlow(v, CFGBuilder.EXIT);
	}

	private ControlFlow breakStmt(final BreakStmt n) {
		final Vertex v = vtxCreator.breakStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		final ControlFlow result = new ControlFlow(v, CFGBuilder.EXIT);
		result.getBreaks().add(v);
		return result;
	}

	private ControlFlow continueStmt(final ContinueStmt n) {
		final Vertex v = vtxCreator.continueStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		final ControlFlow result = cfgBuilder.continueStmt(v, loopStack.peek());
		return result;
	}

	private ControlFlow tryStmt(final TryStmt n) {
		final Vertex v = vtxCreator.tryStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		pushScope();
		// Try-block
		final BlockStmt tryBlk = n.getTryBlock();
		final ControlFlow tryBlkFlow = _build(tryBlk);
		// Catch clauses
		final NodeList<CatchClause> catches = n.getCatchClauses();
		final List<ControlFlow> catchFlowLst = new ArrayList<>();
		for (final CatchClause c : catches)
			catchFlowLst.add(_build(c));
		final ControlFlow catchFlow = cfgBuilder.seq(catchFlowLst);
		ControlFlow outFlow = catchFlow;
		// Finally
		final Optional<BlockStmt> finallyBlk = n.getFinallyBlock();
		if (finallyBlk.isPresent())
			outFlow = finallyBlock(finallyBlk.get());
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		popScope();
		final ControlFlow result = cfgBuilder.tryStmt(v, tryBlkFlow, catchFlow, outFlow);
		return result;
	}

	private ControlFlow finallyBlock(final BlockStmt n) {
		final Vertex v = vtxCreator.finallyBlock(n);
		cdg.addVertex(v);
		inScope.add(v);
		pushScope();
		final ControlFlow finallyFlow = _build(n);
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		popScope();
		final ControlFlow result = cfgBuilder.finallyBlock(v, finallyFlow);
		return result;
	}

	private ControlFlow catchClause(final CatchClause n) {
		final Vertex v = vtxCreator.catchClause(n);
		cdg.addVertex(v);
		inScope.add(v);
		pushScope();
		final BlockStmt body = n.getBody();
		final ControlFlow bodyFlow = _build(body);
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		popScope();
		final ControlFlow result = cfgBuilder.catchClause(v, bodyFlow);
		return result;
	}

	private ControlFlow throwStmt(final ThrowStmt n) {
		final Vertex v = vtxCreator.throwStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		final ControlFlow result = new ControlFlow(v, CFGBuilder.EXIT);
		return result;
	}

	private ControlFlow forStmt(final ForStmt n) {
		final NodeList<Expression> init = n.getInitialization();
		final List<ControlFlow> initFlow = new ArrayList<>();
		for (final Expression e : init)
			initFlow.add(_build(e));
		final Vertex v = vtxCreator.forStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		pushScope();
		final NodeList<Expression> update = n.getUpdate();
		final List<ControlFlow> updateFlow = new ArrayList<>();
		for (final Expression e : update)
			updateFlow.add(_build(e));
		// Control flow after a continue should go to update node if present, and guard
		// otherwise.
		final Vertex loopVtx = !updateFlow.isEmpty() ? updateFlow.get(0).getIn() : v;
		loopStack.push(loopVtx);
		final Statement body = n.getBody();
		final ControlFlow bodyFlow = _build(body);
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		// Self edge
		addEdge(EdgeType.CTRL_TRUE, v, v);
		loopStack.pop();
		popScope();
		final ControlFlow result = cfgBuilder.forStmt(v, initFlow, updateFlow, bodyFlow);
		return result;
	}

	private ControlFlow forEachStmt(final ForEachStmt n) {
		final Vertex v = vtxCreator.forEachStmt(n);
		cdg.addVertex(v);
		loopStack.push(v);
		inScope.add(v);
		pushScope();
		final Statement body = n.getBody();
		final ControlFlow bodyFlow = _build(body);
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		// Self edge
		addEdge(EdgeType.CTRL_TRUE, v, v);
		loopStack.pop();
		popScope();
		final ControlFlow result = cfgBuilder.foreachStmt(v, bodyFlow);
		return result;
	}

	private ControlFlow whileStmt(final WhileStmt n) {
		final Vertex v = vtxCreator.whileStmt(n);
		cdg.addVertex(v);
		loopStack.push(v);
		inScope.add(v);
		pushScope();
		final Statement body = n.getBody();
		final ControlFlow bodyFlow = _build(body);
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		// Self edge
		addEdge(EdgeType.CTRL_TRUE, v, v);
		loopStack.pop();
		popScope();
		final ControlFlow result = cfgBuilder.whileStmt(v, bodyFlow);
		return result;
	}

	private ControlFlow doStmt(final DoStmt n) {
		final Vertex v = vtxCreator.doStmt(n);
		cdg.addVertex(v);
		loopStack.push(v);
		inScope.add(v);
		pushScope();
		final Statement body = n.getBody();
		final ControlFlow bodyFlow = _build(body);
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		// Self edge
		addEdge(EdgeType.CTRL_TRUE, v, v);
		loopStack.pop();
		final List<Vertex> oldScope = new ArrayList<>(inScope);
		popScope();
		// Restore old scope so that edges from the outer control vertex are created
		inScope.addAll(oldScope);
		final ControlFlow result = cfgBuilder.doStmt(v, bodyFlow);
		return result;
	}

	private ControlFlow ifStmt(final IfStmt n) {
		final Vertex v = vtxCreator.ifStmt(n);
		cdg.addVertex(v);
		inScope.add(v);
		pushScope();
		// Then branch
		final Statement thenStmt = n.getThenStmt();
		final ControlFlow thenFlow = _build(thenStmt);
		addEdges(EdgeType.CTRL_TRUE, v, inScope);
		// Reset scope
		clearScope();
		// Else branch
		final Optional<Statement> elseStmt = n.getElseStmt();
		// Control flow
		ControlFlow elseFlow = null;
		if (elseStmt.isPresent()) {
			elseFlow = _build(elseStmt.get());
			addEdges(EdgeType.CTRL_FALSE, v, inScope);
		}
		popScope();
		final ControlFlow result = cfgBuilder.ifStmt(v, thenFlow, elseFlow);
		return result;
	}

	private ControlFlow args(final Vertex v, final MethodCallExpr n) {
		return args(v, n.getArguments(), n.getNameAsString(), n.getScope().orElse(null));
	}

	private ControlFlow args(final Vertex v, final ExplicitConstructorInvocationStmt n) {
		return args(v, n.getArguments(), "super", null);
	}

	private ControlFlow args(final Vertex v, final NodeList<Expression> args, final String name,
			final Expression scope) {
		final List<ControlFlow> result = new ArrayList<>();
		final List<Vertex> paramVtcs = new ArrayList<>();
		for (final Expression e : args) {
			final Vertex a = argumentExpr(e);

			// Remove uses in parent node.
			v.getUses().removeAll(v.getUses());

			addEdge(EdgeType.CTRL_TRUE, v, a);
			result.add(new ControlFlow(a, a));
			paramVtcs.add(a);
		}
		result.add(new ControlFlow(v, v));
		final String methodName = callName(name, scope);
		putCall(methodName, new Pair<>(v, paramVtcs));
		return cfgBuilder.seq(result);
	}

	private String callName(final String name) {
		return clsStack.peek() + "." + name;
	}

	private String callName(final String name, final Expression scope) {
		String result = clsStack.peek() + ".";
		if (scope != null)
			result = scope.toString() + ".";
		result += name;
		return result;
	}

	private void addEdge(final EdgeType type, final Vertex source, final Vertex target) {
		cdg.addEdge(source, target, new Edge(source, target, type));
	}

	private void addEdges(final EdgeType type, final Vertex source, final List<Vertex> target) {
		for (final Vertex v : target)
			addEdge(type, source, v);
	}

	private void pushScope() {
		inScopeStack.push(inScope);
		clearScope();
	}

	private void clearScope() {
		inScope = new ArrayList<>();
	}

	private void popScope() {
		inScope = inScopeStack.pop();
	}

	private void putCall(final String method, final Pair<Vertex, List<Vertex>> pair) {
		Set<Pair<Vertex, List<Vertex>>> callPairs = calls.get(method);
		if (callPairs == null) {
			callPairs = new HashSet<>();
			calls.put(method, callPairs);
		}
		callPairs.add(pair);
	}

	public List<CFG> getCfgs() {
		return cfgBuilder.getCfgs();
	}

	public long getVertexId() {
		return vtxCreator.getId();
	}

	public PDG getCDG() {
		return cdg;
	}

	public HashMap<String, Pair<Vertex, List<Vertex>>> getMethodParams() {
		return methodParams;
	}

	public HashMap<String, Set<Pair<Vertex, List<Vertex>>>> getCalls() {
		return calls;
	}

	public HashMap<Vertex, Vertex> getMethodFormalOut() {
		return methodFormalOut;
	}

	public Map<String, Integer> getUnmatchedAstNodes() {
		return unmatchedAstNodes;
	}

}
