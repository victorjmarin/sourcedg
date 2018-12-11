package sourcedg.normalization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.AssignExpr.Operator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import sourcedg.builder.PDGBuilder;

public class Normalizer {

  public static final String VAR_PREFIX = "_v";
  public static final String COMMENT_TAG = "@OL:";

  private int varId = 0;
  private CompilationUnit cu;
  private final List<ExpressionStmt> expressions = new ArrayList<>();
  private final List<ModifierVisitor<Void>> visitors;
  private final HashMap<DepKey, ExpressionStmt> mAss;

  public Normalizer(final CompilationUnit cu) {
    this.cu = cu;
    visitors = new ArrayList<>();
    mAss = new HashMap<>();
  }

  public CompilationUnit normalize() {
    lineComment(cu);
    ensureBlkStmts(cu);
    visitors.add(new LocalClassDeclarationStmtVisitor());
    // Not equivalent if for has continue. Would need jump to tag.
    // visitors.add(new ForStmtUpdateVisitor());
    // Gives problems when denormalizing. If collection -> iterator, if array -> index
    // visitors.add(new ForeachStmtVisitor());
    visitors.add(new MethodCallVisitor());
    visitors.add(new AssignExprVisitor());
    // Do stmts may be added inits in their body used in the condition, which will not compile but
    // we don't really care.
    visitors.add(new BinaryExprVisitor());
    visitors.add(new ArrayCreationExprVisitor());
    visitors.add(new WhileStmtVisitor());
    visitors.add(new ForStmtVisitor());
    // Not necessary
    // visitors.add(new DoStmtVisitor());
    visitors.add(new EnclosedExprVisitor());

    String newCu = null;
    for (final ModifierVisitor<Void> mv : visitors) {
      cu.accept(mv, null);
      newCu = cu.toString();
      // System.out.println(mv.getClass().getSimpleName());
      // System.out.println();
      // System.out.println(newCu);
      cu = JavaParser.parse(newCu);
    }
    return cu;
  }

  private void ensureBlkStmts(final CompilationUnit cu) {
    cu.accept(new ModifierVisitor<Void>() {
      @Override
      public Node visit(final ForStmt stmt, final Void args) {
        super.visit(stmt, args);
        final Statement body = changeNestedStmtToBlk(stmt.getBody());
        stmt.setBody(body);
        return stmt;
      }
    }, null);
    cu.accept(new ModifierVisitor<Void>() {
      @Override
      public Node visit(final ForEachStmt stmt, final Void args) {
        super.visit(stmt, args);
        final Statement body = changeNestedStmtToBlk(stmt.getBody());
        stmt.setBody(body);
        return stmt;
      }
    }, null);
    cu.accept(new ModifierVisitor<Void>() {
      @Override
      public Node visit(final WhileStmt stmt, final Void args) {
        super.visit(stmt, args);
        final Statement body = changeNestedStmtToBlk(stmt.getBody());
        stmt.setBody(body);
        return stmt;
      }
    }, null);
    cu.accept(new ModifierVisitor<Void>() {
      @Override
      public Node visit(final DoStmt stmt, final Void args) {
        super.visit(stmt, args);
        final Statement body = changeNestedStmtToBlk(stmt.getBody());
        stmt.setBody(body);
        return stmt;
      }
    }, null);
    cu.accept(new ModifierVisitor<Void>() {
      @Override
      public Node visit(final IfStmt stmt, final Void args) {
        super.visit(stmt, args);
        final Statement thenStmt = changeNestedStmtToBlk(stmt.getThenStmt());
        stmt.setThenStmt(thenStmt);
        final Optional<Statement> oElseStmt = stmt.getElseStmt();
        if (oElseStmt.isPresent()) {
          final Statement elseStmt = changeNestedStmtToBlk(oElseStmt.get());
          stmt.setElseStmt(elseStmt);
        }
        return stmt;
      }
    }, null);
  }

  // Comments each statement with its line number
  private void lineComment(final Node node) {
    if (!(node instanceof BlockStmt) && node instanceof Statement
        || node instanceof ClassOrInterfaceDeclaration || node instanceof MethodDeclaration
        || node instanceof CatchClause) {
      String comment = COMMENT_TAG + String.valueOf(getBeginLine(node));
      final Optional<Comment> optComm = node.getComment();
      if (optComm.isPresent())
        comment += " -" + optComm.get().getContent();
      withComment(node, comment);
    }
    for (final Node n : node.getChildNodes())
      lineComment(n);
  }

  // Removes inner classes
  private class LocalClassDeclarationStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final LocalClassDeclarationStmt stmt, final Void args) {
      return null;
    }
  }

  private class ArrayCreationExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ArrayCreationExpr expr, final Void args) {
      final int line = getBeginLine(expr);
      final Comment comment = getParentStmtComment(expr, line);
      super.visit(expr, args);
      final Node parent = expr.getParentNode().orElse(null);
      if (hasSuperParent(expr) || parent instanceof VariableDeclarator
          || parent instanceof AssignExpr)
        return expr;
      final String variableName = nextVarId();
      final NodeSearchResult sr = findBlockStmt(expr);
      if (sr == null)
        return expr;
      final VariableDeclarationExpr varDeclExpr = variableDeclaratorExpr(variableName, expr);
      final ExpressionStmt exprStmt = new ExpressionStmt(varDeclExpr);
      addToBody(sr.blk, exprStmt, sr.idx);
      withComment(exprStmt, comment);
      return new NameExpr(variableName);
    }
  }

  private class ForStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ForStmt stmt, final Void args) {
      super.visit(stmt, args);
      final Optional<Expression> cond = stmt.getCompare();
      if (cond.isPresent()) {
        final Statement newBody = solveCondDeps(cond.get(), stmt.getBody(), new HashSet<>());
        stmt.setBody(newBody);
      }
      return stmt;
    }
  }


  private class ForStmtUpdateVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ForStmt stmt, final Void args) {
      super.visit(stmt, args);
      // final NodeList<Expression> initNodeLst = stmt.getInitialization();
      // if (!initNodeLst.isEmpty()) {
      // final Expression init = initNodeLst.remove(0);
      // init.setComment(findParentComment(stmt));
      // if (init instanceof VariableDeclarationExpr) {
      // final NodeSearchResult sr = findBlockStmt(stmt);
      // addToBody(sr.blk, new ExpressionStmt(init), sr.idx);
      // }
      // }
      // Remove for update and put at the end of body
      final NodeList<Expression> updateNodeLst = stmt.getUpdate();
      if (!updateNodeLst.isEmpty()) {
        final Expression update = updateNodeLst.remove(0);
        update.setComment(findParentComment(stmt));
        stmt.setBody(addToBody(stmt.getBody(), update));
      }
      return stmt;
    }
  }

  private class WhileStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final WhileStmt stmt, final Void args) {
      super.visit(stmt, args);
      final Expression cond = stmt.getCondition();
      solveCondDeps(cond, stmt.getBody(), new HashSet<>());
      return stmt;
    }
  }

  private class DoStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final DoStmt stmt, final Void args) {
      super.visit(stmt, args);
      final Expression cond = stmt.getCondition();
      solveCondDeps(cond, stmt.getBody(), new HashSet<>());
      return stmt;
    }
  }

  private Statement solveCondDeps(Expression condition, final Statement body,
      final Set<String> alreadySolved) {
    Statement result = body;
    final Node parentStmt = findParentStmt(condition);
    while (condition instanceof EnclosedExpr) {
      condition = ((EnclosedExpr) condition).getInner();
    }
    if (condition instanceof NameExpr) {
      final List<ExpressionStmt> exprs = new ArrayList<>();
      solveDeps(condition, parentStmt, exprs, alreadySolved);
      AssignExpr assign = null;
      for (final ExpressionStmt n : exprs) {
        assign = varDecl2Assign(n);
        result = addToBody(body, assign);
      }
    } else if (condition instanceof BinaryExpr) {
      final BinaryExpr binCond = (BinaryExpr) condition;
      final Expression left = binCond.getLeft();
      final Expression right = binCond.getRight();
      final List<ExpressionStmt> leftExpr = new ArrayList<>();
      solveDeps(left, parentStmt, leftExpr, alreadySolved);
      final List<ExpressionStmt> rightExpr = new ArrayList<>();
      solveDeps(right, parentStmt, rightExpr, alreadySolved);

      AssignExpr assign = null;
      for (final ExpressionStmt n : leftExpr) {
        assign = varDecl2Assign(n);
        result = addToBody(body, assign);
      }
      for (final ExpressionStmt n : rightExpr) {
        assign = varDecl2Assign(n);
        result = addToBody(body, assign);
      }
    }
    return result;
  }

  private class ForeachStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ForEachStmt stmt, final Void args) {
      super.visit(stmt, args);
      final VariableDeclarationExpr varDeclExpr = stmt.getVariable();
      final VariableDeclarator varDecl = varDeclExpr.getVariable(0);
      final Type type = varDeclExpr.getElementType();
      final Expression iterator = getForIteratorStmt(type, stmt.getIterable());
      final NameExpr currentVar = new NameExpr(currentVarId());
      final MethodCallExpr hasNext = new MethodCallExpr(currentVar, "hasNext");
      final NodeList<Expression> initialization = new NodeList<>();
      initialization.add(iterator);
      final Expression nextExpr = getNextExpr(varDecl, type, currentVar);
      final ExpressionStmt exprStmt = new ExpressionStmt(nextExpr);
      final Statement body = addToBody(stmt.getBody(), exprStmt, 0);
      final ForStmt result = new ForStmt(initialization, hasNext, new NodeList<>(), body);
      withComment(exprStmt, stmt.getComment().get());
      withComment(result, stmt.getComment().get());
      return result;
    }
  }

  private VariableDeclarationExpr getNextExpr(final VariableDeclarator varDecl, final Type type,
      final NameExpr currentVar) {
    final MethodCallExpr value = new MethodCallExpr(currentVar, "next");
    final VariableDeclarator initVarDecl =
        new VariableDeclarator(type, varDecl.getNameAsString(), value);
    return new VariableDeclarationExpr(initVarDecl);
  }

  private Statement addToBody(final Statement body, final Expression n) {
    Statement result = body;
    if (body instanceof BlockStmt)
      result = addToBody((BlockStmt) body, n);
    else if (body instanceof ExpressionStmt)
      result = addToBody((ExpressionStmt) body, n);
    else if (body instanceof Statement) {
      final BlockStmt blk = new BlockStmt();
      blk.addStatement(body);
      blk.addStatement(n);
      result = blk;
    }
    return result;
  }

  private Statement addToBody(final Statement body, final Statement n, final int pos) {
    Statement result = body;
    if (body instanceof BlockStmt)
      result = addToBody((BlockStmt) body, n, pos);
    else if (body instanceof ExpressionStmt)
      result = addToBody((ExpressionStmt) body, n, pos);
    else if (body instanceof Statement) {
      result = _addToBody(body, n, pos);
    }
    return result;
  }

  private Statement _addToBody(final Statement body, final Statement n, final Integer pos) {
    final BlockStmt result = new BlockStmt();
    result.addStatement(body);
    result.addStatement(pos, n);
    return result;
  }

  private Statement addToBody(final ExpressionStmt body, final Expression n) {
    final BlockStmt result = new BlockStmt();
    result.addStatement(body.getExpression());
    result.addStatement(n);
    return result;
  }

  private Statement addToBody(final ExpressionStmt body, final Statement n, final int pos) {
    final BlockStmt result = new BlockStmt();
    result.addStatement(body.getExpression());
    result.addStatement(pos, n);
    return result;
  }

  private Statement addToBody(final BlockStmt body, final Expression n) {
    body.addStatement(n);
    return body;
  }

  private Statement addToBody(final BlockStmt body, final Statement n, final int pos) {
    body.addStatement(pos, n);
    return body;
  }

  private Expression getForIteratorStmt(final Type type, final Expression list) {
    final Type wrapperType = toWrapperType(type);
    final ClassOrInterfaceType itType =
        JavaParser.parseClassOrInterfaceType("Iterator<" + wrapperType + ">");
    final String name = nextVarId();
    final MethodCallExpr init = new MethodCallExpr(list, "iterator");
    final VariableDeclarator varDecl = new VariableDeclarator(itType, name, init);
    return new VariableDeclarationExpr(varDecl);
  }

  private class AssignExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final AssignExpr expr, final Void args) {
      final int line = getBeginLine(expr);
      final Comment comment = getParentStmtComment(expr, line);
      super.visit(expr, args);
      // Return if parent is not parenthesis
      final Node parent = expr.getParentNode().get();
      if (parent == null || !(parent instanceof EnclosedExpr))
        return expr;
      final NodeSearchResult sr = findBlockStmt(expr);
      final Expression result = recNorm(expr);
      for (final ExpressionStmt e : expressions) {
        sr.blk.addStatement(sr.idx, e);
        withComment(e, comment);
      }
      expressions.clear();
      return result;
    }
  }

  private class EnclosedExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final EnclosedExpr expr, final Void args) {
      super.visit(expr, args);
      // Remove parenthesis when only one child
      if (singleChildInParenthesis(expr))
        return expr.getChildNodes().get(0);
      return expr;
    }
  }

  private class MethodCallVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final MethodCallExpr expr, final Void args) {
      final int line = getBeginLine(expr);
      final Comment comment = getParentStmtComment(expr, line);
      super.visit(expr, args);
      // Do not extract to variable if parent is assign or super
      final Node parent = expr.getParentNode().orElse(null);
      if (parent instanceof Statement || parent instanceof AssignExpr
          || parent instanceof ExpressionStmt || parent instanceof CastExpr
          || parent instanceof VariableDeclarator
          || parent instanceof ForStmt && expr.equals(((ForStmt) parent).getCompare().orElse(null))
          || parent instanceof ArrayAccessExpr || hasSuperParent(parent))
        return expr;
      final NodeSearchResult sr = findBlockStmt(expr);
      if (sr == null)
        return expr;
      final Expression result = recNorm(expr);
      for (final ExpressionStmt e : expressions) {
        sr.blk.addStatement(sr.idx, e);
        withComment(e, comment);
      }
      expressions.clear();
      return result;
    }
  }

  private class BinaryExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final BinaryExpr expr, final Void args) {
      final int line = getBeginLine(expr);
      final Comment comment = getParentStmtComment(expr, line);
      super.visit(expr, args);
      final Expression left = expr.getLeft();
      final Expression right = expr.getRight();
      final Node parent = expr.getParentNode().get();
      // Do not simplify binary expressions that are already variables and literals
      // Avoid final int _v3 = a + b + c; or int _v0 = (a + 1) / 2; with parent and enclosed check
      if (nodeOrEnclosedChildIsNameOrLiteral(left) && nodeOrEnclosedChildIsNameOrLiteral(right)
          && !(parent instanceof BinaryExpr || parent instanceof EnclosedExpr)
          || hasSuperParent(parent))
        return expr;
      final NodeSearchResult sr = findBlockStmt(expr);
      if (sr == null)
        return expr;
      final Expression result = recNorm(expr);
      for (final ExpressionStmt e : expressions) {
        sr.blk.addStatement(sr.idx, e);
        withComment(e, comment);
      }
      expressions.clear();
      return result;
    }
  }

  private Comment getParentStmtComment(final Node n, final int line) {
    final Optional<Statement> o = n.findParent(Statement.class);
    if (o.isPresent() && o.get().getComment().isPresent()) {
      return o.get().getComment().get();
    }
    return new LineComment(COMMENT_TAG + String.valueOf(line));
  }

  private Node withComment(final Node n, final String comment) {
    n.setLineComment(comment);
    return n;
  }

  private Node withComment(final Node n, final Comment comment) {
    n.setLineComment(comment.getContent());
    return n;
  }

  private AssignExpr varDecl2Assign(final ExpressionStmt expr) {
    final Expression e = expr.getExpression();
    if (e instanceof AssignExpr)
      return (AssignExpr) e;
    final Node firstChild = e.getChildNodes().get(0);
    if (firstChild instanceof VariableDeclarator) {
      final VariableDeclarator varDecl = (VariableDeclarator) firstChild;
      final NameExpr name = new NameExpr(varDecl.getName().asString());
      final Expression init = varDecl.getInitializer().get();
      final AssignExpr result = new AssignExpr(name, init, Operator.ASSIGN);
      result.setComment(findParentComment(expr));
      return result;
    }
    return null;
  }

  private void solveDeps(Expression expr, final Node parentStmt, final List<ExpressionStmt> l,
      final Set<String> alreadySolved) {
    while (expr instanceof EnclosedExpr) {
      expr = ((EnclosedExpr) expr).getInner();
    }
    if (!(expr instanceof NameExpr))
      return;
    final NameExpr var = (NameExpr) expr;
    if (alreadySolved.contains(var.getNameAsString()))
      return;
    final DepKey dk = new DepKey(parentStmt, var);
    ExpressionStmt lastAssignmentOf = mAss.get(dk);
    lastAssignmentOf = lastAssignmentOf != null ? lastAssignmentOf : mAss.get(new DepKey(var));
    if (lastAssignmentOf == null)
      return;
    alreadySolved.add(var.getNameAsString());
    final Expression e = lastAssignmentOf.getExpression();
    if (e instanceof VariableDeclarationExpr) {
      final VariableDeclarator varDecl = (VariableDeclarator) e.getChildNodes().get(0);
      final Expression init = varDecl.getInitializer().get();
      if (init instanceof BinaryExpr) {
        solveDeps(((BinaryExpr) init).getLeft(), parentStmt, l, alreadySolved);
        solveDeps(((BinaryExpr) init).getRight(), parentStmt, l, alreadySolved);
      }
    }
    l.add(lastAssignmentOf);
  }

  private Expression recNorm(final AssignExpr expr) {
    final Node parentStmt = findParentStmt(expr);
    final ExpressionStmt assign = new ExpressionStmt(expr);
    expressions.add(assign);
    final Expression target = expr.getTarget();
    if (target instanceof NameExpr) {
      final NameExpr name = (NameExpr) target;
      final DepKey dk = new DepKey(parentStmt, name);
      mAss.put(dk, assign);
    } else
      PDGBuilder.LOGGER.warning("Not mapped -> " + target);
    final Expression result = expr.getTarget();
    return result;
  }

  private Expression recNorm(final MethodCallExpr expr) {
    final String variableName = nextVarId();
    final VariableDeclarator varDeclarator =
        new VariableDeclarator(typeFor(expr), variableName, expr);
    final VariableDeclarationExpr varDeclExpr = new VariableDeclarationExpr(varDeclarator);
    final ExpressionStmt assign = new ExpressionStmt(varDeclExpr);
    expressions.add(assign);
    final Expression result = new NameExpr(variableName);
    final DepKey dk = new DepKey((NameExpr) result);
    mAss.put(dk, assign);
    return result;
  }

  private Expression recNorm(final BinaryExpr binExpr) {
    Expression result = binExpr;
    final Expression left = binExpr.getLeft();
    final Expression right = binExpr.getRight();
    // if (left != null && isLeaf(left) && right != null && isLeaf(right)) {
    if (left != null && right != null) {
      final String variableName = nextVarId();
      final VariableDeclarationExpr varDeclExpr = variableDeclaratorExpr(variableName, binExpr);
      final ExpressionStmt expr = new ExpressionStmt(varDeclExpr);
      expressions.add(expr);
      result = new NameExpr(variableName);
      final DepKey dk = new DepKey((NameExpr) result);
      mAss.put(dk, expr);
    }
    return result;
  }

  private VariableDeclarationExpr variableDeclaratorExpr(final String variableName,
      final Expression initializer) {
    final Type type = typeFor(initializer);
    final VariableDeclarator varDeclarator =
        new VariableDeclarator(type, variableName, initializer);
    return new VariableDeclarationExpr(varDeclarator);
  }

  private NodeSearchResult findBlockStmt(final Node expr) {
    return findBlockStmt(expr, expr);
  }

  private NodeSearchResult findBlockStmt(final Node expr, final Node original) {
    // TODO: Body might be an expression instead of a block
    // Use method findParent?
    final Optional<Node> n = expr.getParentNode();
    if (!n.isPresent()) {
      PDGBuilder.LOGGER.warning("No parent block found for " + original.toString());
      return null;
    }
    if (n.get() instanceof DoStmt) {
      final DoStmt doStmt = (DoStmt) n.get();
      final BlockStmt blk = (BlockStmt) changeNestedStmtToBlk(doStmt.getBody());
      return new NodeSearchResult(blk, blk.getStatements().size(), expr);
    }
    if (n.get() instanceof BlockStmt) {
      final BlockStmt blk = (BlockStmt) n.get();
      final int indexOf = blk.getStatements().indexOf(expr);
      return new NodeSearchResult(blk, indexOf, expr);
    }
    return findBlockStmt(expr.getParentNode().get(), original);
  }

  private Node findParentStmt(final Node n) {
    if (n instanceof Statement)
      return n;
    final Node parent = n.getParentNode().orElse(null);
    if (parent == null)
      return null;
    return findParentStmt(parent);
  }

  private boolean hasSuperParent(final Node n) {
    if (n == null)
      return false;
    if (n instanceof ExplicitConstructorInvocationStmt)
      return true;
    final Optional<Node> parent = n.getParentNode();
    return hasSuperParent(parent.orElse(null));
  }

  private boolean hasForParent(final Node n) {
    if (n == null)
      return false;
    if (n instanceof ForStmt)
      return true;
    final Optional<Node> parent = n.getParentNode();
    return hasForParent(parent.orElse(null));
  }

  private Type typeFor(final Expression expr) {
    // TODO 0: Remove comments
    return defaultType();
//    try {
//      final ResolvedType typeOfTheNode = JavaParserFacade.get(typeSolver).getType(expr);
//      final String type = typeOfTheNode.describe();
//      switch (type) {
//        case "boolean":
//          return PrimitiveType.booleanType();
//        case "char":
//          return PrimitiveType.charType();
//        case "byte":
//          return PrimitiveType.byteType();
//        case "short":
//          return PrimitiveType.shortType();
//        case "int":
//          return PrimitiveType.intType();
//        case "long":
//          return PrimitiveType.longType();
//        case "float":
//          return PrimitiveType.floatType();
//        case "double":
//          return PrimitiveType.doubleType();
//        default:
//          return JavaParser.parseClassOrInterfaceType(type);
//      }
//    } catch (final Exception e) {
//      PDGBuilder.LOGGER
//          .warning("Could not resolve type for " + expr + ". Defaulting to " + defaultType());
//    }
//    return defaultType();
  }

  private Type toWrapperType(final Type type) {
    switch (type.asString()) {
      case "byte":
        return JavaParser.parseClassOrInterfaceType("Byte");
      case "short":
        return JavaParser.parseClassOrInterfaceType("Short");
      case "int":
        return JavaParser.parseClassOrInterfaceType("Integer");
      case "long":
        return JavaParser.parseClassOrInterfaceType("Long");
      case "float":
        return JavaParser.parseClassOrInterfaceType("Float");
      case "double":
        return JavaParser.parseClassOrInterfaceType("Double");
      case "char":
        return JavaParser.parseClassOrInterfaceType("Character");
      case "boolean":
        return JavaParser.parseClassOrInterfaceType("Boolean");
      default:
        return type;
    }
  }

  private String nextVarId() {
    return VAR_PREFIX + varId++;
  }

  private String currentVarId() {
    return VAR_PREFIX + (varId - 1);
  }

  private Type defaultType() {
    return JavaParser.parseClassOrInterfaceType("Object");
  }

  private int getBeginLine(final Node n) {
    return n.getBegin().get().line;
  }

  private Comment findParentComment(final Node node) {
    while (!node.getComment().isPresent()
        || !node.getComment().get().getContent().contains(COMMENT_TAG)) {
      final Node p = node.findParent(Node.class).get();
      return findParentComment(p);
    }
    return node.getComment().get();
  }

  private Statement changeNestedStmtToBlk(final Statement stmt) {
    if (!(stmt instanceof BlockStmt)) {
      final BlockStmt blk = new BlockStmt();
      blk.addStatement(stmt);
      return blk;
    }
    return stmt;
  }

  private boolean nodeIsNameOrLiteralOrUnary(final Node n) {
    return n instanceof NameExpr || n instanceof LiteralExpr || n instanceof UnaryExpr;
  }

  private boolean nodeOrEnclosedChildIsNameOrLiteral(final Node n) {
    return nodeIsNameOrLiteralOrUnary(n)
        || (singleChildInParenthesis(n) && nodeIsNameOrLiteralOrUnary(n.getChildNodes().get(0)));
  }

  private boolean singleChildInParenthesis(final Node n) {
    final List<Node> children = n.getChildNodes();
    if (children.size() == 1 && children.get(0) instanceof NameExpr)
      return true;
    return false;
  }

  class DepKey {
    public Node parentStmt;
    public NameExpr var;

    public DepKey(final Node parentStmt, final NameExpr var) {
      this.parentStmt = parentStmt;
      this.var = var;
    }

    public DepKey(final NameExpr var) {
      parentStmt = null;
      this.var = var;
    }

    @Override
    public int hashCode() {
      final Comment c = parentStmt == null ? null : parentStmt.getComment().orElse(null);
      return Objects.hash(c == null ? null : c.getContent(), var.getNameAsString());
    }

    @Override
    public boolean equals(final Object o) {
      if (o == this)
        return true;
      if (!(o instanceof DepKey))
        return false;
      final DepKey dk = (DepKey) o;
      if (parentStmt == null && dk.parentStmt == null)
        return var.getNameAsString().equals(dk.var.getNameAsString());
      if (parentStmt == null && dk.parentStmt != null)
        return false;
      if (parentStmt != null && dk.parentStmt == null)
        return false;
      return parentStmt.getComment().get().getContent()
          .equals(dk.parentStmt.getComment().get().getContent())
          && var.getNameAsString().equals(dk.var.getNameAsString());
    }

    @Override
    public String toString() {
      final String comment = parentStmt == null ? null : parentStmt.getComment().get().getContent();
      return "<" + comment + ", " + var.getNameAsString() + ">";
    }
  }

}
