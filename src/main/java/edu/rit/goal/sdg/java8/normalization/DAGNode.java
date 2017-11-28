package edu.rit.goal.sdg.java8.normalization;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;

public class DAGNode {

  private String value;
  private Expression expr;
  private DAGNode parent;
  private DAGNode left;
  private DAGNode right;

  public DAGNode(final String value, final Expression expr) {
    this.value = value;
    this.expr = expr;
    parent = null;
    left = null;
    right = null;
  }

  public DAGNode(final String value, final Expression expr, final DAGNode left,
      final DAGNode right) {
    this.value = value;
    this.expr = expr;
    this.left = left;
    this.right = right;
  }

  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }

  public Expression getExpr() {
    return expr;
  }

  public void setExpr(final Expression expr) {
    this.expr = expr;
  }

  public void setLeft(final DAGNode left) {
    if (left == null) {
      this.left = null;
    } else {
      this.left.value = left.value;
      this.left.expr = left.expr;
      this.left.left = left.left;
      this.left.right = left.right;
    }
  }

  public DAGNode getLeft() {
    return left;
  }

  public void setRight(final DAGNode right) {
    if (right == null) {
      this.right = null;
    } else {
      this.right.value = right.value;
      this.right.expr = right.expr;
      this.right.left = right.left;
      this.right.right = right.right;
    }
  }

  public DAGNode getRight() {
    return right;
  }

  public boolean isLeftChild() {
    return parent != null && this == parent.getLeft();
  }

  public boolean isLeaf() {
    return expr instanceof NameExpr || expr instanceof IntegerLiteralExpr
        || expr instanceof NullLiteralExpr || expr instanceof MethodCallExpr;
  }

  public DAGNode getParent() {
    return parent;
  }

  public void setParent(final DAGNode parent) {
    this.parent = parent;
  }

  @Override
  public String toString() {
    return value;
  }

}
