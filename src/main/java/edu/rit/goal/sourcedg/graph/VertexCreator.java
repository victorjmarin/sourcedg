package edu.rit.goal.sourcedg.graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

public class VertexCreator {

  private int id = 0;

  public Vertex classOrInterfaceDeclaration(final ClassOrInterfaceDeclaration n) {
    final String label = n.getNameAsString();
    final Vertex result = new Vertex(VertexType.CLASS, label, n);
    setId(result);
    return result;
  }

  public Vertex methodDeclaration(final MethodDeclaration n) {
    final String label = n.getNameAsString();
    final Vertex result = new Vertex(VertexType.ENTRY, label, n);
    setId(result);
    return result;
  }

  public Vertex parameter(final Parameter n) {
    final String label = n.getNameAsString();
    final Vertex result = new Vertex(VertexType.FORMAL_IN, label, n);
    setId(result);
    setDef(n, result);
    return result;
  }

  public Vertex ifStmt(final IfStmt n) {
    final Expression cond = n.getCondition();
    final String label = cond.toString();
    final Vertex result = new Vertex(VertexType.CTRL, label, n);
    setId(result);
    setUses(cond, result);
    return result;
  }

  public Vertex forStmt(final ForStmt n) {
    final Optional<Expression> cond = n.getCompare();
    String label = "";
    if (cond.isPresent())
      label = cond.get().toString();
    final Vertex result = new Vertex(VertexType.CTRL, label, n);
    setId(result);
    if (cond.isPresent())
      setUses(cond.get(), result);
    return result;
  }

  public Vertex whileStmt(final WhileStmt n) {
    final Expression cond = n.getCondition();
    final String label = cond.toString();
    final Vertex result = new Vertex(VertexType.CTRL, label, n);
    setId(result);
    setUses(cond, result);
    return result;
  }

  public Vertex doStmt(final DoStmt n) {
    final Expression cond = n.getCondition();
    final String label = cond.toString();
    final Vertex result = new Vertex(VertexType.CTRL, label, n);
    setId(result);
    setUses(cond, result);
    return result;
  }

  public Vertex variableDeclarator(final VariableDeclarator n) {
    final String label = n.toString();
    final Optional<Expression> init = n.getInitializer();
    final Vertex result = new Vertex(VertexType.ASSIGN, label, n);
    setId(result);
    setDef(n.getName(), result);
    if (init.isPresent())
      setUses(init.get(), result);
    return result;
  }

  // TODO: Assign call?
  public Vertex assignExpr(final AssignExpr n) {
    final String label = n.toString();
    final Vertex result = new Vertex(VertexType.ASSIGN, label, n);
    setId(result);
    setDef(n.getTarget(), result);
    setUses(n.getValue(), result);
    return result;
  }

  public Vertex methodCallExpr(final MethodCallExpr n) {
    final String label = n.toString();
    final Vertex result = new Vertex(VertexType.CALL, label, n);
    setId(result);
    return result;
  }

  public Vertex argumentExpr(final Expression n) {
    final String label = n.toString();
    final Vertex result = new Vertex(VertexType.FORMAL_IN, label, n);
    setId(result);
    setUses(n, result);
    return result;
  }


  public Vertex unaryExpr(final UnaryExpr n) {
    final String label = n.toString();
    final Vertex result = new Vertex(VertexType.ASSIGN, label, n);
    setId(result);
    setDef(n, result);
    setUses(n, result);
    return result;
  }

  public Vertex returnStmt(final ReturnStmt n) {
    final String label = n.toString();
    final Optional<Expression> expr = n.getExpression();
    final Vertex result = new Vertex(VertexType.RETURN, label, n);
    setId(result);
    if (expr.isPresent())
      setUses(expr.get(), result);
    return result;
  }

  public void setId(final Vertex v) {
    v.setId(id++);
  }

  private void setDef(final Node n, final Vertex v) {
    final String def = first(names(n));
    v.setDef(def);
  }

  private void setUses(final Node n, final Vertex v) {
    final Set<String> uses = names(n);
    v.setUses(uses);
  }

  // TODO: This is a preliminary construction for def and uses. Be more thorough.
  private Set<String> names(final Node ast) {
    if (ast == null)
      return new HashSet<>();
    return ast.findAll(SimpleName.class).stream().map(n -> n.getIdentifier())
        .collect(Collectors.toSet());
  }

  private String first(final Set<String> set) {
    if (set == null || set.isEmpty())
      return null;
    return set.iterator().next();
  }

  public int getId() {
    return id;
  }

}
