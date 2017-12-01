package edu.rit.goal.sdg.graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.BaseStmt;
import edu.rit.goal.sdg.interpreter.stmt.Break;
import edu.rit.goal.sdg.interpreter.stmt.CUnit;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Cls;
import edu.rit.goal.sdg.interpreter.stmt.Continue;
import edu.rit.goal.sdg.interpreter.stmt.Decl;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.DoWhile;
import edu.rit.goal.sdg.interpreter.stmt.For;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.Import;
import edu.rit.goal.sdg.interpreter.stmt.Pkg;
import edu.rit.goal.sdg.interpreter.stmt.PostOp;
import edu.rit.goal.sdg.interpreter.stmt.PreOp;
import edu.rit.goal.sdg.interpreter.stmt.Return;
import edu.rit.goal.sdg.interpreter.stmt.While;

public class DefaultVertexCreator implements VertexCreator {

  private int id = 0;

  @Override
  public Vertex compilationUnitVertex(final CUnit cunit) {
    final Vertex result = new Vertex(VertexType.CUNIT, cunit.x);
    setId(result);
    return result;
  }

  @Override
  public Vertex packageVertex(final Pkg pkg) {
    final Vertex result = new Vertex(VertexType.PKG, pkg.x);
    setId(result);
    return result;
  }

  @Override
  public Vertex importVertex(final Import imp) {
    final String mod = imp.mod != null ? imp.mod + " " : "";
    final Vertex result = new Vertex(VertexType.IMPORT, mod + imp.x);
    setId(result);
    return result;
  }

  @Override
  public Vertex classVertex(final Cls cls) {
    final Vertex result = new Vertex(VertexType.CLASS, cls.x);
    setId(result);
    return result;
  }

  @Override
  public Vertex defVertex(final Def def) {
    final Vertex result = new Vertex(VertexType.ENTRY, def.methodName);
    setId(result);
    return result;
  }

  @Override
  public Vertex callVertex(final Call call) {
    final Vertex result = new Vertex(VertexType.CALL, call.toString());
    setId(result);
    setSubtypes(result, call);
    setCallSubtype(result, call);
    return result;
  }

  @Override
  public Vertex declVertex(final Decl decl) {
    final Vertex result = new Vertex(VertexType.DECL, decl.toString());
    setId(result);
    setSubtypes(result, decl);
    return result;
  }

  @Override
  public Vertex assignVertex(final Assign assign) {
    final Vertex result = new Vertex(VertexType.ASSIGN, assign.x + assign.op + assign.e);
    setId(result);
    setSubtypes(result, assign);
    return result;
  }

  @Override
  public Vertex assignCallVertex(final Assign assign) {
    final Call call = (Call) assign.e;
    final Vertex result = new Vertex(VertexType.ASSIGN, assign.x + assign.op + call.toString());
    setId(result);
    setSubtypes(result, assign);
    setCallSubtype(result, call);
    result.getSubtypes().add(VertexSubtype.CALL);
    return result;
  }

  @Override
  public Vertex preOpVertex(final PreOp preOp) {
    final Vertex result = new Vertex(VertexType.ASSIGN, preOp.op + preOp.x);
    setId(result);
    setSubtypes(result, preOp);
    return result;
  }

  @Override
  public Vertex postOpVertex(final PostOp postOp) {
    final Vertex result = new Vertex(VertexType.ASSIGN, postOp.x + postOp.op);
    setId(result);
    setSubtypes(result, postOp);
    return result;
  }

  @Override
  public Vertex returnVertex(final Return ret) {
    final Vertex result = new Vertex(VertexType.RETURN, ret.toString());
    setId(result);
    setSubtypes(result, ret);
    return result;
  }

  @Override
  public Vertex ifThenElseVertex(final IfThenElse ifThenElse) {
    final Vertex result = new Vertex(VertexType.CTRL, ifThenElse.e.toString());
    setId(result);
    setSubtypes(result, ifThenElse);
    return result;
  }

  @Override
  public Vertex whileVertex(final While _while) {
    final Vertex result = new Vertex(VertexType.CTRL, _while.e.toString());
    setId(result);
    setSubtypes(result, _while);
    return result;
  }

  @Override
  public Vertex doWhileVertex(final DoWhile doWhile) {
    final Vertex result = new Vertex(VertexType.CTRL, doWhile.e.toString());
    setId(result);
    setSubtypes(result, doWhile);
    return result;
  }

  @Override
  public Vertex forVertex(final For _for) {
    final Vertex result = new Vertex(VertexType.CTRL, _for.sc.toString());
    setId(result);
    setSubtypes(result, _for);
    return result;
  }

  @Override
  public Vertex breakVertex(final Break _break) {
    final Vertex result = new Vertex(VertexType.BREAK, _break.toString());
    setId(result);
    return result;
  }

  @Override
  public Vertex continueVertex(final Continue _continue) {
    final Vertex result = new Vertex(VertexType.CONTINUE, _continue.toString());
    setId(result);
    return result;
  }

  @Override
  public void setId(final Vertex v) {
    v.setId(id++);
  }

  private void setSubtypes(final Vertex v, final BaseStmt stmt) {
    final Set<VertexSubtype> subtypes = subtypesFromText(v.getLabel());
    if (stmt.ast != null)
      subtypes.addAll(subtypesFromAst(stmt.ast));
    v.setSubtypes(subtypes);
  }

  private Set<VertexSubtype> subtypesFromAst(final Expression ast) {
    final Set<VertexSubtype> result = new HashSet<>();
    final Optional<MethodCallExpr> optMethodCall = ast.findFirst(MethodCallExpr.class);
    if (optMethodCall.isPresent()) {
      final MethodCallExpr methodCall = optMethodCall.get();
      if (methodCall.getScope().isPresent())
        result.add(VertexSubtype.OBJECT_CALL);
    }
    return result;
  }

  private Set<VertexSubtype> subtypesFromText(final String text) {
    final Set<VertexSubtype> result = new HashSet<>();
    if (text.contains("+"))
      result.add(VertexSubtype.PLUS);
    if (text.contains("-"))
      result.add(VertexSubtype.MINUS);
    if (text.contains("*"))
      result.add(VertexSubtype.MULT);
    if (text.contains("/"))
      result.add(VertexSubtype.DIV);
    if (text.contains("<"))
      result.add(VertexSubtype.LT);
    if (text.contains(">"))
      result.add(VertexSubtype.GT);
    if (text.contains("<="))
      result.add(VertexSubtype.LEQ);
    if (text.contains(">="))
      result.add(VertexSubtype.GEQ);
    if (text.contains("=="))
      result.add(VertexSubtype.EQ);
    if (text.contains("!="))
      result.add(VertexSubtype.NOT_EQ);
    if (text.contains("%"))
      result.add(VertexSubtype.MOD);
    if (text.contains("&&"))
      result.add(VertexSubtype.AND);
    if (text.contains("||"))
      result.add(VertexSubtype.OR);
    if (text.contains("++"))
      result.add(VertexSubtype.INCR);
    if (text.contains("--"))
      result.add(VertexSubtype.DECR);
    if (text.contains("+="))
      result.add(VertexSubtype.SH_PLUS);
    if (text.contains("-="))
      result.add(VertexSubtype.SH_MINUS);
    if (text.contains("*="))
      result.add(VertexSubtype.SH_MULT);
    if (text.contains("/="))
      result.add(VertexSubtype.SH_DIV);
    if (text.contains(".print"))
      result.add(VertexSubtype.PRINT);
    return result;
  }

  private void setCallSubtype(final Vertex v, final Call call) {
    final Set<VertexSubtype> subtypes = v.getSubtypes();
    final int numberParam = call.p.size();
    if (numberParam == 0)
      subtypes.add(VertexSubtype.NO_PARAMS);
    else if (numberParam == 1)
      subtypes.add(VertexSubtype.SINGLE_PARAM);
    else if (numberParam == 2)
      subtypes.add(VertexSubtype.TWO_PARAMS);
    else if (numberParam == 3)
      subtypes.add(VertexSubtype.THREE_PARAMS);
    else if (numberParam == 4)
      subtypes.add(VertexSubtype.FOUR_PARAMS);
    else if (numberParam == 5)
      subtypes.add(VertexSubtype.FIVE_PARAMS);
    else
      subtypes.add(VertexSubtype.PLUS_FIVE_PARAMS);
  }

  @Override
  public void selfCall(final Vertex v) {
    v.getSubtypes().add(VertexSubtype.SELF_CALL);
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void resetId() {
    id = 0;
  }

}
