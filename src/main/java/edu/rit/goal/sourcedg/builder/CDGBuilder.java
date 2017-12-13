package edu.rit.goal.sourcedg.builder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr.Operator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.EdgeType;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.graph.VertexCreator;

/*
 * Builds the Control Dependence Subgraph
 */
public class CDGBuilder {

  private final CompilationUnit cu;
  private final VertexCreator vtxCreator;
  private final CFGBuilder cfgBuilder;

  private final PDG cdg;
  private final Deque<List<Vertex>> inScopeStack;
  private List<Vertex> inScope;

  public CDGBuilder(final CompilationUnit cu) {
    this.cu = cu;
    vtxCreator = new VertexCreator();
    cfgBuilder = new CFGBuilder();
    cdg = new PDG();
    inScopeStack = new ArrayDeque<>();
    inScope = new ArrayList<>();
  }

  public ControlFlow _build(final Node n) {
    ControlFlow result = new ControlFlow();
    if (n instanceof ClassOrInterfaceDeclaration)
      result = classOrInterfaceDeclaration((ClassOrInterfaceDeclaration) n);
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
    else if (n instanceof WhileStmt)
      result = whileStmt((WhileStmt) n);
    else if (n instanceof DoStmt)
      result = doStmt((DoStmt) n);
    else if (n instanceof VariableDeclarationExpr)
      result = variableDeclarationExpr((VariableDeclarationExpr) n);
    else if (n instanceof VariableDeclarator)
      result = variableDeclarator((VariableDeclarator) n);
    else if (n instanceof AssignExpr)
      result = assignExpr((AssignExpr) n);
    else if (n instanceof MethodCallExpr)
      result = methodCallExpr((MethodCallExpr) n);
    else if (n instanceof UnaryExpr)
      result = unaryExpr((UnaryExpr) n);
    else if (n instanceof ReturnStmt)
      result = returnStmt((ReturnStmt) n);
    else {}
      //System.out.println("No match for " + n.getClass().getSimpleName());
    return result;
  }

  public void build() {
    final List<Node> children = cu.getChildNodes();
    for (final Node n : children)
      _build(n);
  }

  private ControlFlow blockStmt(final BlockStmt n) {
    final List<ControlFlow> flow = new ArrayList<>();
    for (final Statement s : n.getStatements())
      flow.add(_build(s));
    return cfgBuilder.seq(flow);
  }

  private ControlFlow expressionStmt(final ExpressionStmt n) {
    final Expression expr = n.getExpression();
    return _build(expr);
  }

  private ControlFlow classOrInterfaceDeclaration(final ClassOrInterfaceDeclaration n) {
    final Vertex v = vtxCreator.classOrInterfaceDeclaration(n);
    cdg.addVertex(v);
    final List<MethodDeclaration> methods = n.getMethods();
    ControlFlow mFlow = new ControlFlow();
    for (final MethodDeclaration m : methods) {
      mFlow = _build(m);
    }
    addEdges(EdgeType.MEMBER_OF, v, inScope);
    final ControlFlow result = new ControlFlow(v, mFlow.getOut());
    return result;
  }

  private ControlFlow methodDeclaration(final MethodDeclaration n) {
    final Vertex v = vtxCreator.methodDeclaration(n);
    cdg.addVertex(v);
    inScope.add(v);
    pushScope();
    final NodeList<Parameter> params = n.getParameters();
    final List<ControlFlow> paramFlow = new ArrayList<>();
    for (final Parameter p : params)
      paramFlow.add(_build(p));
    final Optional<BlockStmt> body = n.getBody();
    ControlFlow bodyFlow = null;
    if (body.isPresent())
      bodyFlow = _build(body.get());
    addEdges(EdgeType.CTRL_TRUE, v, inScope);
    popScope();
    // CFG
    final ControlFlow result = cfgBuilder.methodDeclaration(v, paramFlow, bodyFlow);
    cfgBuilder.put(v);
    return result;
  }

  private ControlFlow parameter(final Parameter n) {
    final Vertex v = vtxCreator.parameter(n);
    cdg.addVertex(v);
    inScope.add(v);
    return new ControlFlow(v, v);
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
    return new ControlFlow(v, v);
  }

  private ControlFlow assignExpr(final AssignExpr n) {
    final Vertex v = vtxCreator.assignExpr(n);
    cdg.addVertex(v);
    inScope.add(v);
    return new ControlFlow(v, v);
  }

  private ControlFlow methodCallExpr(final MethodCallExpr n) {
    final Vertex v = vtxCreator.methodCallExpr(n);
    cdg.addVertex(v);
    inScope.add(v);
    final NodeList<Expression> args = n.getArguments();
    final List<ControlFlow> argFlow = new ArrayList<>();
    argFlow.add(new ControlFlow(v, v));
    for (final Expression e : args) {
      final Vertex a = argumentExpr(e);
      addEdge(EdgeType.CTRL_TRUE, v, a);
      argFlow.add(new ControlFlow(a, a));
    }
    final ControlFlow result = cfgBuilder.seq(argFlow);
    return result;
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
        System.out.println("Operation " + op + " not considered.");
    }
    return new ControlFlow();
  }

  private ControlFlow returnStmt(final ReturnStmt n) {
    final Vertex v = vtxCreator.returnStmt(n);
    cdg.addVertex(v);
    inScope.add(v);
    return new ControlFlow(v, CFGBuilder.EXIT);
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
    final Statement body = n.getBody();
    final ControlFlow bodyFlow = _build(body);
    final NodeList<Expression> update = n.getUpdate();
    final List<ControlFlow> updateFlow = new ArrayList<>();
    for (final Expression e : update)
      updateFlow.add(_build(e));
    addEdges(EdgeType.CTRL_TRUE, v, inScope);
    // Self edge
    addEdge(EdgeType.CTRL_TRUE, v, v);
    popScope();
    final ControlFlow result = cfgBuilder.forStmt(v, initFlow, updateFlow, bodyFlow);
    return result;
  }

  private ControlFlow whileStmt(final WhileStmt n) {
    final Vertex v = vtxCreator.whileStmt(n);
    cdg.addVertex(v);
    inScope.add(v);
    pushScope();
    final Statement body = n.getBody();
    final ControlFlow bodyFlow = _build(body);
    addEdges(EdgeType.CTRL_TRUE, v, inScope);
    // Self edge
    addEdge(EdgeType.CTRL_TRUE, v, v);
    popScope();
    final ControlFlow result = cfgBuilder.whileStmt(v, bodyFlow);
    return result;
  }

  private ControlFlow doStmt(final DoStmt n) {
    final Vertex v = vtxCreator.doStmt(n);
    cdg.addVertex(v);
    inScope.add(v);
    pushScope();
    final Statement body = n.getBody();
    final ControlFlow bodyFlow = _build(body);
    addEdges(EdgeType.CTRL_TRUE, v, inScope);
    // Self edge
    addEdge(EdgeType.CTRL_TRUE, v, v);
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
    addEdges(EdgeType.CTRL_FALSE, v, inScope);
    popScope();
    // Control flow
    ControlFlow elseFlow = null;
    if (elseStmt.isPresent())
      elseFlow = _build(elseStmt.get());
    final ControlFlow result = cfgBuilder.ifStmt(v, thenFlow, elseFlow);
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

  public Collection<CFG> getCfgs() {
    return cfgBuilder.getCfgs();
  }

  public int getVertexId() {
    return vtxCreator.getId();
  }

  public PDG getCDG() {
    return cdg;
  }

}
