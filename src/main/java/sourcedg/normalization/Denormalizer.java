package sourcedg.normalization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr.Operator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.ModifierVisitor;

public class Denormalizer {

  private static final String VAR_PREFIX = Normalizer.VAR_PREFIX;
  private static final String COMP_VAR_PREFIX = "!" + VAR_PREFIX;

  private CompilationUnit cu;
  private final List<ModifierVisitor<Void>> visitors;

  public Denormalizer(final CompilationUnit cu) {
    this.cu = cu;
    visitors = new ArrayList<>();
    visitors.add(new BinaryExprVisitor());
    visitors.add(new ConditionalExprVisitor());
    visitors.add(new DoStmtVisitor());
    visitors.add(new MethodCallVisitor());
    visitors.add(new IfStmtVisitor());
    visitors.add(new AssignExprVisitor());
    visitors.add(new VariableDeclaratorVisitor());
    visitors.add(new CommentVisitor());
    visitors.add(new ConstructorDeclarationVisitor());
    visitors.add(new ObjectCreationExprVisitor());
    visitors.add(new ArrayCreationLevelVisitor());
    visitors.add(new CastExprVisitor());
    visitors.add(new WhileStmtVisitor());
    visitors.add(new ForStmtVisitor());
    visitors.add(new ReturnStmtVisitor());
    visitors.add(new DanglingAssignExprVisitor());
    visitors.add(new DanglingVariableDeclarationExprVisitor());

  }

  public CompilationUnit denormalize() {
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

  private class ConditionalExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ConditionalExpr expr, final Void args) {
      super.visit(expr, args);
      final Expression cond = expr.getCondition();
      final Expression thenExpr = expr.getThenExpr();
      final Expression elseExpr = expr.getElseExpr();
      expr.setCondition(denormExpr(cond));
      expr.setThenExpr(denormExpr(thenExpr));
      expr.setElseExpr(denormExpr(elseExpr));
      return expr;
    }
  }

  private class CastExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final CastExpr expr, final Void args) {
      super.visit(expr, args);
      final Expression e = expr.getExpression();
      expr.setExpression(denormExpr(e));
      return expr;
    }
  }

  private class CommentVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final LineComment comment, final Void args) {
      super.visit(comment, args);
      return null;
    }
  }

  private class AssignExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final AssignExpr expr, final Void args) {
      super.visit(expr, args);
      final Expression value = expr.getValue();
      final Expression denormExpr = denormExpr(value);
      expr.setValue(denormExpr);
      return expr;
    }
  }

  private class VariableDeclaratorVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final VariableDeclarator expr, final Void args) {
      super.visit(expr, args);
      final Expression vInit = expr.getInitializer().orElse(null);
      final Expression denormExpr = denormExpr(vInit);
      expr.setInitializer(denormExpr);
      return expr;
    }
  }

  private class IfStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final IfStmt stmt, final Void args) {
      super.visit(stmt, args);
      final Expression cond = stmt.getCondition();
      final Expression denormExpr = denormExpr(cond);
      stmt.setCondition(denormExpr);
      return stmt;
    }
  }

  private class ObjectCreationExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ObjectCreationExpr expr, final Void args) {
      super.visit(expr, args);
      denormArgs(expr.getArguments());
      return expr;
    }
  }

  private class ArrayCreationLevelVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ArrayCreationLevel expr, final Void args) {
      super.visit(expr, args);
      final Expression dim = expr.getDimension().orElse(null);
      final Expression denormExpr = denormExpr(dim);
      expr.setDimension(denormExpr);
      return expr;
    }
  }

  private class ConstructorDeclarationVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ObjectCreationExpr expr, final Void args) {
      super.visit(expr, args);
      denormArgs(expr.getArguments());
      return expr;
    }
  }

  private class MethodCallVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final MethodCallExpr expr, final Void args) {
      super.visit(expr, args);
      final Expression scope = expr.getScope().orElse(null);
      final Expression denormExpr = denormExpr(scope);
      expr.setScope(denormExpr);
      denormArgs(expr.getArguments());
      return expr;
    }
  }

  private class DoStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final DoStmt stmt, final Void args) {
      super.visit(stmt, args);
      final Expression cond = stmt.getCondition();
      stmt.setCondition(denormExpr(cond));
      return stmt;
    }
  }

  private class WhileStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final WhileStmt stmt, final Void args) {
      super.visit(stmt, args);
      final Expression cond = stmt.getCondition();
      stmt.setCondition(denormExpr(cond));
      return stmt;
    }
  }

  private class ForStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ForStmt stmt, final Void args) {
      super.visit(stmt, args);
      final NodeList<Expression> init = stmt.getInitialization();
      final Expression cond = stmt.getCompare().orElse(null);
      final NodeList<Expression> update = stmt.getUpdate();
      denormArgs(init);
      stmt.setCompare(denormExpr(cond));
      denormArgs(update);
      return stmt;
    }
  }

  private class ReturnStmtVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final ReturnStmt stmt, final Void args) {
      super.visit(stmt, args);
      final Expression expr = stmt.getExpression().orElse(null);
      if (expr != null)
        stmt.setExpression(denormExpr(expr));
      return stmt;
    }
  }

  private class BinaryExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final BinaryExpr expr, final Void args) {
      super.visit(expr, args);
      final Expression left = expr.getLeft();
      final Expression right = expr.getRight();
      final Expression denormExprL = denormExpr(left);
      final Expression denormExprR = denormExpr(right);
      expr.setLeft(denormExprL);
      expr.setRight(denormExprR);
      return expr;
    }
  }

  private class DanglingAssignExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final AssignExpr expr, final Void args) {
      super.visit(expr, args);
      final String var = expr.getTarget().toString();
      if (var.startsWith(VAR_PREFIX) || var.startsWith(COMP_VAR_PREFIX))
        return null;
      return expr;
    }
  }

  private class DanglingVariableDeclarationExprVisitor extends ModifierVisitor<Void> {
    @Override
    public Node visit(final VariableDeclarationExpr expr, final Void args) {
      super.visit(expr, args);
      final String var = expr.getVariable(0).getNameAsString();
      if (var.startsWith(VAR_PREFIX) || var.startsWith(COMP_VAR_PREFIX))
        return null;
      return expr;
    }
  }

  private Expression findVarDef(final String str) {
    final VariableDeclarator result = cu
        .findFirst(VariableDeclarator.class, v -> v.getName().toString().equals(str)).orElse(null);
    final AssignExpr ae =
        cu.findFirst(AssignExpr.class, v -> v.getTarget().toString().equals(str)).orElse(null);
    if (result == null && ae == null)
      throw new IllegalStateException("Cannot find definition for " + str);
    return result != null ? result.getInitializer().orElse(null) : ae.getValue();
  }

  private Expression getInitForVarAndRemoveParent(final String var) {
    final Expression result = findVarDef(var);
    return new EnclosedExpr(result);
  }

  private Expression getInitForComplementVarAndRemoveParent(final String var) {
    final Expression varDef = findVarDef(var);
    final UnaryExpr result = new UnaryExpr(varDef, Operator.LOGICAL_COMPLEMENT);
    return new EnclosedExpr(result);
  }

  private Expression denormExpr(final Expression expr) {
    if (expr == null)
      return null;
    Expression result = expr;
    final String var = expr.toString();
    if (expr instanceof NameExpr && var.startsWith(VAR_PREFIX)) {
      result = getInitForVarAndRemoveParent(var);
    } else if (expr instanceof UnaryExpr && var.startsWith(COMP_VAR_PREFIX)) {
      result = getInitForComplementVarAndRemoveParent(var.substring(1));
    }
    return result;
  }

  private void denormArgs(final List<Expression> args) {
    final List<Expression> finalArgs = new ArrayList<>();
    for (final Iterator<Expression> it = args.iterator(); it.hasNext();) {
      final Expression e = it.next();
      if (e.toString().startsWith(VAR_PREFIX)) {
        final String var = e.toString();
        final Expression init = getInitForVarAndRemoveParent(var);
        finalArgs.add(init);
      } else if (e.toString().startsWith(COMP_VAR_PREFIX)) {
        final String var = e.toString().substring(1);
        final Expression init = getInitForComplementVarAndRemoveParent(var);
        finalArgs.add(init);
      } else {
        finalArgs.add(e);
      }
      it.remove();
    }
    args.addAll(finalArgs);
  }

}
