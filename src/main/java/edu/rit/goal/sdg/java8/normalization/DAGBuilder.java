package edu.rit.goal.sdg.java8.normalization;

import java.util.HashMap;
import java.util.Objects;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;

public class DAGBuilder {

  private final HashMap<Integer, DAGNode> m = new HashMap<>();

  public DAGNode astToDag(final Expression e) {
    if (e == null)
      return null;
    if (e instanceof NullLiteralExpr) {
      final String value = "null";
      final DAGNode result = new DAGNode(value, e);
      return result;
    } else if (e instanceof AssignExpr) {
      final AssignExpr assignExpr = (AssignExpr) e;
      final com.github.javaparser.ast.expr.AssignExpr.Operator op = assignExpr.getOperator();
      final DAGNode left = astToDag(assignExpr.getTarget());
      final DAGNode right = astToDag(assignExpr.getValue());
      final DAGNode result = nodeFor(op.toString(), e, left, right);
      return result;
      // } else if (e instanceof MethodCallExpr) {
      // final MethodCallExpr callExpr = (MethodCallExpr) e;
      // final String methodName = callExpr.getName().getIdentifier();
      // final String op = "." + methodName;
      // final Expression scope = callExpr.getScope().get();
      // final DAGNode left = astToDag(scope);
      // callExpr.getarg
      // final DAGNode right = astToDag(callExpr.getName());
      // final DAGNode result = nodeFor(op.toString(), e, left, right);
      // return null;
    } else if (e instanceof IntegerLiteralExpr) {
      final String value = ((IntegerLiteralExpr) e).getValue();
      final DAGNode result = new DAGNode(value, e);
      return result;
    } else if (e instanceof NameExpr) {
      final String value = ((NameExpr) e).getNameAsString();
      final DAGNode result = nodeFor(value, e);
      return result;
    } else if (e instanceof EnclosedExpr) {
      final Expression expr = ((EnclosedExpr) e).getInner();
      final DAGNode result = astToDag(expr);
      return result;
    } else if (e instanceof BinaryExpr) {
      final BinaryExpr binExpr = (BinaryExpr) e;
      final Operator op = binExpr.getOperator();
      final DAGNode left = astToDag(binExpr.getLeft());
      final DAGNode right = astToDag(binExpr.getRight());
      // TODO: Check when left and right are operators
      final DAGNode result = nodeFor(op.toString(), e, left, right);
      return result;
    } else {
      final String value = e.toString();
      final DAGNode result = new DAGNode(value, e);
      return result;
    }
  }

  private DAGNode nodeFor(final String value, final Expression expr) {
    final DAGNode leaf = new DAGNode(value, expr);
    final DAGNode current = m.putIfAbsent(value.hashCode(), leaf);
    final DAGNode result = current != null ? current : leaf;
    return result;
  }

  private DAGNode nodeFor(final String op, final Expression expr, final DAGNode left,
      final DAGNode right) {
    final DAGNode leaf = new DAGNode(op, expr, left, right);
    final int hash = hash(op, left, right);
    final DAGNode current = m.putIfAbsent(hash, leaf);
    final DAGNode result = current != null ? current : leaf;
    left.setParent(result);
    right.setParent(result);
    return result;
  }

  private int hash(final String op, final DAGNode left, final DAGNode right) {
    return Objects.hash(left, op, right);
  }

}
