package edu.rit.goal.sdg.java8.antlr4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Break;
import edu.rit.goal.sdg.interpreter.stmt.CUnit;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Cls;
import edu.rit.goal.sdg.interpreter.stmt.Continue;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.DoWhile;
import edu.rit.goal.sdg.interpreter.stmt.Expr;
import edu.rit.goal.sdg.interpreter.stmt.For;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.Import;
import edu.rit.goal.sdg.interpreter.stmt.Pkg;
import edu.rit.goal.sdg.interpreter.stmt.PostOp;
import edu.rit.goal.sdg.interpreter.stmt.PreOp;
import edu.rit.goal.sdg.interpreter.stmt.Return;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.interpreter.stmt.While;
import edu.rit.goal.sdg.java8.JavaUtils;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ArrayInitializerContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.BlockStatementContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.CatchClauseContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ClassBodyContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ClassBodyDeclarationContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ClassOrInterfaceModifierContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.CreatorContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.FinallyBlockContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ForControlContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ForInitContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.FormalParameterContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.FormalParameterListContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ImportDeclarationContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.LocalVariableDeclarationContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.MemberDeclarationContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.PackageDeclarationContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.StatementContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.TypeDeclarationContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.TypeParametersContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.TypeTypeContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.TypeTypeOrVoidContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.VariableDeclaratorContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.VariableDeclaratorsContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.VariableInitializerContext;

public class SourceDGJavaVisitor extends JavaParserBaseVisitor<ParseResult> {

  private String className;

  @Override
  protected ParseResult aggregateResult(final ParseResult aggregate, final ParseResult nextResult) {
    return nextResult != null ? nextResult : aggregate;
  }

  /*
   * Terminal rules
   * 
   * Rules that return a SourceDG statement to be interpreted.
   */

  @Override
  public ParseResult visitCompilationUnit(final JavaParser.CompilationUnitContext ctx) {
    final List<Stmt> stmts = new ArrayList<>();
    ParseResult pr = null;
    final PackageDeclarationContext pkgDeclCtx = ctx.packageDeclaration();
    if (pkgDeclCtx != null) {
      pr = visitPackageDeclaration(pkgDeclCtx);
      stmts.add(pr.getStmt());
    }
    for (final ImportDeclarationContext c : ctx.importDeclaration()) {
      pr = visitImportDeclaration(c);
      stmts.add(pr.getStmt());
    }
    for (final TypeDeclarationContext c : ctx.typeDeclaration()) {
      pr = visit(c);
      stmts.add(pr.getStmt());
    }
    final Stmt seq = Translator.seq(stmts);
    final CUnit res = new CUnit(seq);
    return new ParseResult(res);
  }

  @Override
  public ParseResult visitImportDeclaration(final JavaParser.ImportDeclarationContext ctx) {
    final TerminalNode mod = ctx.STATIC();
    final Import imp = new Import(ctx.qualifiedName().getText());
    if (mod != null)
      imp.mod = mod.getText();
    return new ParseResult(imp);
  }

  @Override
  public ParseResult visitPackageDeclaration(final JavaParser.PackageDeclarationContext ctx) {
    final Pkg pkg = new Pkg(ctx.qualifiedName().getText());
    return new ParseResult(pkg);
  }

  @Override
  public ParseResult visitClassTypeDeclaration(final JavaParser.ClassTypeDeclarationContext ctx) {
    final List<String> modifiers = new ArrayList<>();
    for (final ClassOrInterfaceModifierContext modCtx : ctx.classOrInterfaceModifier()) {
      final ParseResult pr = visitClassOrInterfaceModifier(modCtx);
      modifiers.add(pr.getValue());
    }
    final ParseResult pr = visitClassDeclaration(ctx.classDeclaration());
    final Cls cls = (Cls) pr.getStmt();
    cls.mods = modifiers;
    return new ParseResult(cls);
  }

  @Override
  public ParseResult visitClassDeclaration(final JavaParser.ClassDeclarationContext ctx) {
    className = ctx.IDENTIFIER().getText();
    // Class body
    final ClassBodyContext clsBodyCtx = ctx.classBody();
    final List<ClassBodyDeclarationContext> clsBodyDeclCtx = clsBodyCtx.classBodyDeclaration();
    final List<Stmt> clsBodyStmts = new ArrayList<>();
    for (final ClassBodyDeclarationContext c : clsBodyDeclCtx) {
      final ParseResult pr = visit(c);
      clsBodyStmts.add(pr.getStmt());
    }
    final Stmt clsBody = Translator.seq(clsBodyStmts);
    final Cls cls = new Cls(className, clsBody);
    return new ParseResult(cls);
  }

  /*
   * Non-terminal rules
   * 
   * Helper rule to produce statements.
   */

  @Override
  public ParseResult visitClassOrInterfaceModifier(
      final JavaParser.ClassOrInterfaceModifierContext ctx) {
    return new ParseResult(ctx.getText());
  }

  @Override
  public ParseResult visitClassBodyDeclMemberDecl(
      final JavaParser.ClassBodyDeclMemberDeclContext ctx) {
    final MemberDeclarationContext memberDeclCtx = ctx.memberDeclaration();
    final ParseResult result = visit(memberDeclCtx);
    return result;
  }

  @Override
  public ParseResult visitClassBodyDeclBlock(final JavaParser.ClassBodyDeclBlockContext ctx) {
    printUnsupported("visitClassBodyDeclBlock", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitMemberDeclMethodDecl(final JavaParser.MemberDeclMethodDeclContext ctx) {
    return visitMethodDeclaration(ctx.methodDeclaration());
  }

  @Override
  public ParseResult visitMethodDeclaration(final JavaParser.MethodDeclarationContext ctx) {
    final TypeTypeOrVoidContext typeCtx = ctx.typeTypeOrVoid();
    final ParseResult pr1 = visit(typeCtx);
    final String returnType = pr1.getValue();
    final String methodName = ctx.IDENTIFIER().getText();
    final ParseResult pr2 = visitFormalParameters(ctx.formalParameters());
    final Param params = (Param) pr2.getStmt();
    final boolean b = !returnType.equals("void");
    final ParseResult pr3 = visit(ctx.methodBody());
    final Stmt body = pr3.getStmt();
    final Def def = new Def(b, methodName, params, body);
    // Start and end lines of the method
    def.startLine = ctx.getStart().getLine();
    def.endLine = ctx.getStop().getLine();
    return new ParseResult(def);
  }

  @Override
  public ParseResult visitConstructorDeclaration(
      final JavaParser.ConstructorDeclarationContext ctx) {
    final String methodName = ctx.IDENTIFIER().getText();
    final ParseResult pr2 = visitFormalParameters(ctx.formalParameters());
    final Param params = (Param) pr2.getStmt();
    final ParseResult pr3 = visit(ctx.constructorBody);
    final Stmt body = pr3.getStmt();
    final Def def = new Def(false, methodName, params, body);
    // Start and end lines of the constructor
    def.startLine = ctx.getStart().getLine();
    def.endLine = ctx.getStop().getLine();
    return new ParseResult(def);
  }

  @Override
  public ParseResult visitBlock(final JavaParser.BlockContext ctx) {
    Stmt result = new Skip();
    final List<Stmt> stmts = new LinkedList<>();
    final List<BlockStatementContext> blockStmtCtx = ctx.blockStatement();
    ParseResult pr = null;
    // Non-empty block
    if (blockStmtCtx != null) {
      for (final BlockStatementContext bsc : blockStmtCtx) {
        pr = visit(bsc);
        stmts.add(pr.getStmt());
      }
      result = Translator.seq(stmts);
    }
    return new ParseResult(result);
  }

  @Override
  public ParseResult visitBlockStmtLocalVarDecl(final JavaParser.BlockStmtLocalVarDeclContext ctx) {
    return visitLocalVariableDeclaration(ctx.localVariableDeclaration());
  }

  @Override
  public ParseResult visitBlockStmtStmt(final JavaParser.BlockStmtStmtContext ctx) {
    return visit(ctx.statement());
  }

  @Override
  public ParseResult visitBlockStmtLocalTypeDecl(
      final JavaParser.BlockStmtLocalTypeDeclContext ctx) {
    printUnsupported("visitBlockStmtLocalTypeDecl", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitMethodBodyBlock(final JavaParser.MethodBodyBlockContext ctx) {
    return visitBlock(ctx.block());
  }

  @Override
  public ParseResult visitMethodBodySemicolon(final JavaParser.MethodBodySemicolonContext ctx) {
    printUnsupported("visitMethodBodySemicolon", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitFormalParameters(final JavaParser.FormalParametersContext ctx) {
    final FormalParameterListContext formalParamCtxLst = ctx.formalParameterList();
    if (formalParamCtxLst == null)
      return new ParseResult(new EmptyParam());
    return visit(formalParamCtxLst);
  }

  @Override
  public ParseResult visitFormalParameterListParams(
      final JavaParser.FormalParameterListParamsContext ctx) {
    final List<FormalParameterContext> formalParamCtx = ctx.formalParameter();
    final List<Str> paramsLst = Translator.formalParams(formalParamCtx);
    final Param params = Translator.param(paramsLst, true);
    // TODO: Model modifiers and types of parameters
    // TODO: Support for lastFormalParameter
    return new ParseResult(params);
  }

  @Override
  public ParseResult visitFormalParameterListVarArgs(
      final JavaParser.FormalParameterListVarArgsContext ctx) {
    return visitLastFormalParameter(ctx.lastFormalParameter());
  }

  @Override
  public ParseResult visitFormalParameter(final JavaParser.FormalParameterContext ctx) {
    printUnsupported("visitFormalParameter", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitLastFormalParameter(final JavaParser.LastFormalParameterContext ctx) {
    printUnsupported("visitLastFormalParameter", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitMemberDeclGenericMethodDecl(
      final JavaParser.MemberDeclGenericMethodDeclContext ctx) {
    return visitGenericMethodDeclaration(ctx.genericMethodDeclaration());
  }

  @Override
  public ParseResult visitMemberDeclFieldDecl(final JavaParser.MemberDeclFieldDeclContext ctx) {
    printUnsupported("visitMemberDeclFieldDecl", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitMemberDeclConstuctorDecl(
      final JavaParser.MemberDeclConstuctorDeclContext ctx) {
    return visitConstructorDeclaration(ctx.constructorDeclaration());
  }

  @Override
  public ParseResult visitMemberDeclGenericConstructorDecl(
      final JavaParser.MemberDeclGenericConstructorDeclContext ctx) {
    printUnsupported("visitMemberDeclGenericConstructorDecl", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitMemberDeclInterfaceDecl(
      final JavaParser.MemberDeclInterfaceDeclContext ctx) {
    printUnsupported("visitMemberDeclInterfaceDecl", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitMemberDeclAnnotationTypeDecl(
      final JavaParser.MemberDeclAnnotationTypeDeclContext ctx) {
    printUnsupported("visitMemberDeclAnnotationTypeDecl", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitMemberDeclClassDecl(final JavaParser.MemberDeclClassDeclContext ctx) {
    printUnsupported("visitMemberDeclClassDecl", ctx.getText());
    return visitChildren(ctx);
  }


  @Override
  public ParseResult visitMemberDeclEnumDecl(final JavaParser.MemberDeclEnumDeclContext ctx) {
    printUnsupported("visitMemberDeclEnumDecl", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitTypeTypeOrVoidVoid(final JavaParser.TypeTypeOrVoidVoidContext ctx) {
    final String type = ctx.VOID().getText();
    return new ParseResult(type);
  }

  @Override
  public ParseResult visitTypeTypeOrVoidTypeType(
      final JavaParser.TypeTypeOrVoidTypeTypeContext ctx) {
    final TypeTypeContext typeTypeCtx = ctx.typeType();
    final ParseResult result = visitTypeType(typeTypeCtx);
    return result;
  }

  @Override
  public ParseResult visitGenericMethodDeclaration(
      final JavaParser.GenericMethodDeclarationContext ctx) {
    final TypeParametersContext typeParamCtx = ctx.typeParameters();
    return visitMethodDeclaration(ctx.methodDeclaration());
  }

  @Override
  public ParseResult visitLocalVariableDeclaration(
      final JavaParser.LocalVariableDeclarationContext ctx) {
    final List<Stmt> result = new ArrayList<>();
    final VariableDeclaratorsContext varDeclCtx = ctx.variableDeclarators();
    final List<VariableDeclaratorContext> varDeclLst = varDeclCtx.variableDeclarator();
    for (final VariableDeclaratorContext vdc : varDeclLst) {
      final String x = vdc.variableDeclaratorId().getText();
      final VariableInitializerContext varInitCtx = vdc.variableInitializer();
      // Skip variables that are not being initialized at declaration
      if (varInitCtx == null)
        continue;
      final ExpressionContext exprCtx = varInitCtx.expression();
      final ArrayInitializerContext arrayInitCtx = varInitCtx.arrayInitializer();
      ExpressionListContext exprLstCtx = null;
      if (exprCtx != null) {
        exprLstCtx = exprCtx.expressionList();
        // Method call
        if (exprLstCtx != null) {
          final Call e = Translator.call(exprCtx, className);
          final Assign assign = new Assign(x, e);
          assign.setDef(x);
          assign.setUses(e.getUses());
          result.add(assign);
        }
        // Regular assignment
        else {
          final Assign assign = new Assign(x, new Str(exprCtx));
          final Set<String> uses = JavaUtils.uses(exprCtx);
          assign.setDef(x);
          assign.setUses(uses);
          result.add(assign);
        }
        // Array initialization
      } else if (arrayInitCtx != null) {
        final Assign assign = new Assign(x, new Str(arrayInitCtx));
        final Set<String> uses = JavaUtils.uses(arrayInitCtx);
        assign.setDef(x);
        assign.setUses(uses);
        result.add(assign);
      }

    }
    final Stmt seq = Translator.seq(result);
    return new ParseResult(seq);
  }

  @Override
  public ParseResult visitTypeType(final JavaParser.TypeTypeContext ctx) {
    return new ParseResult(ctx.getText());
  }

  @Override
  public ParseResult visitBlockStmt(final JavaParser.BlockStmtContext ctx) {
    return visitBlock(ctx.block());
  }

  @Override
  public ParseResult visitAssertStmt(final JavaParser.AssertStmtContext ctx) {
    printUnsupported("visitAssertStmt", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitIfStmt(final JavaParser.IfStmtContext ctx) {
    final int size = ctx.statement().size();
    Stmt s1 = new Skip();
    Stmt s2 = new Skip();
    if (size > 0) {
      final StatementContext thenBranch = ctx.statement(0);
      s1 = visit(thenBranch).getStmt();
    }
    if (size > 1) {
      final StatementContext elseBranch = ctx.statement(1);
      s2 = visit(elseBranch).getStmt();
    }
    final ExpressionContext exprCtx = ctx.parExpression().expression();
    final Expr e = new Str(exprCtx);
    final IfThenElse ifThenElse = new IfThenElse(e, s1, s2);
    final Set<String> uses = JavaUtils.uses(exprCtx);
    ifThenElse.setUses(uses);
    return new ParseResult(ifThenElse);
  }

  @Override
  public ParseResult visitForStmt(final JavaParser.ForStmtContext ctx) {
    Stmt result = null;
    final Stmt s = visit(ctx.statement()).getStmt();
    final ForControlContext forCtrlCtx = ctx.forControl();
    final ForInitContext forInitCtx = forCtrlCtx.forInit();
    // TODO: Support for initialization and update of multiple variables
    Stmt si = new Skip();
    Stmt sc = new Str("");
    Stmt su = new Skip();
    if (forInitCtx != null) {
      final LocalVariableDeclarationContext lclVarDeclCtx = forInitCtx.localVariableDeclaration();
      if (lclVarDeclCtx != null)
        si = visitLocalVariableDeclaration(lclVarDeclCtx).getStmt();
      else
        // We are assumming only one expression
        si = expr(forInitCtx.expressionList().expression(0));
    }
    final ExpressionContext exprCtx = forCtrlCtx.expression();
    if (exprCtx != null)
      sc = expr(exprCtx);
    final ExpressionListContext exprListCtx = forCtrlCtx.expressionList();
    if (exprListCtx != null)
      // We are assumming only one expression
      su = expr(exprListCtx.expression(0));
    result = new For(si, sc, su, s);
    return new ParseResult(result);
  }

  @Override
  public ParseResult visitWhileStmt(final JavaParser.WhileStmtContext ctx) {
    final ExpressionContext exprCtx = ctx.parExpression().expression();
    final Expr e = new Str(exprCtx);
    final StatementContext stmtCtx = ctx.statement();
    final Stmt s = visit(stmtCtx).getStmt();
    final While result = new While(e, s);
    final Set<String> uses = JavaUtils.uses(exprCtx);
    result.setUses(uses);
    return new ParseResult(result);
  }

  @Override
  public ParseResult visitDoStmt(final JavaParser.DoStmtContext ctx) {
    final ExpressionContext exprCtx = ctx.parExpression().expression();
    final Expr e = new Str(exprCtx);
    final StatementContext stmtCtx = ctx.statement();
    final Stmt s = visit(stmtCtx).getStmt();
    final DoWhile result = new DoWhile(s, e);
    final Set<String> uses = JavaUtils.uses(exprCtx);
    result.setUses(uses);
    return new ParseResult(result);
  }

  @Override
  public ParseResult visitTryStmt(final JavaParser.TryStmtContext ctx) {
    final List<Stmt> stmts = new ArrayList<>();
    final BlockContext blkCtx = ctx.block();
    final Stmt tryBlock = visitBlock(blkCtx).getStmt();
    stmts.add(tryBlock);
    final List<CatchClauseContext> catchClauseCtx = ctx.catchClause();
    if (catchClauseCtx != null) {
      for (final CatchClauseContext ccc : catchClauseCtx) {
        final Stmt s = visitBlock(ccc.block()).getStmt();
        stmts.add(s);
      }
    }
    final FinallyBlockContext finallyBlkCtx = ctx.finallyBlock();
    if (finallyBlkCtx != null) {
      final BlockContext blkCtx2 = finallyBlkCtx.block();
      final Stmt finallyStmt = visitBlock(blkCtx2).getStmt();
      stmts.add(finallyStmt);
    }
    final Stmt result = Translator.seq(stmts);
    return new ParseResult(result);
  }

  @Override
  public ParseResult visitTryWithResourcesStmt(final JavaParser.TryWithResourcesStmtContext ctx) {
    printUnsupported("visitTryWithResourcesStmt", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitSwitchStmt(final JavaParser.SwitchStmtContext ctx) {
    printUnsupported("visitSwitchStmt", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitSynchronizedStmt(final JavaParser.SynchronizedStmtContext ctx) {
    printUnsupported("visitSynchronizedStmt", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitReturnStmt(final JavaParser.ReturnStmtContext ctx) {
    final ExpressionContext expr = ctx.expression();
    final String e = expr.getText();
    final Set<String> uses = JavaUtils.uses(expr);
    final Return ret = new Return(e);
    ret.setUses(uses);
    return new ParseResult(ret);
  }

  @Override
  public ParseResult visitThrowStmt(final JavaParser.ThrowStmtContext ctx) {
    printUnsupported("visitThrowStmt", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitBreakStmt(final JavaParser.BreakStmtContext ctx) {
    final Break br = new Break();
    return new ParseResult(br);
  }

  @Override
  public ParseResult visitContinueStmt(final JavaParser.ContinueStmtContext ctx) {
    final Continue cont = new Continue();
    return new ParseResult(cont);
  }

  @Override
  public ParseResult visitSemicolonStmt(final JavaParser.SemicolonStmtContext ctx) {
    printUnsupported("visitSemicolonStmt", ctx.getText());
    return visitChildren(ctx);
  }

  @Override
  public ParseResult visitExpressionStmt(final JavaParser.ExpressionStmtContext ctx) {
    final Stmt expr = expr(ctx.expression());
    return new ParseResult(expr);
  }

  @Override
  public ParseResult visitLabeledStmt(final JavaParser.LabeledStmtContext ctx) {
    printUnsupported("visitLabeledStmt", ctx.getText());
    return visitChildren(ctx);
  }

  // Helper methods

  public Stmt expr(final ExpressionContext exprCtx) {
    Stmt result = null;
    final Token bop = exprCtx.bop;
    final boolean isMethodCall =
        exprCtx.expressionList() != null || Translator.isEmptyArgCall(exprCtx);
    final boolean isCreator = exprCtx.creator() != null;
    // Distinguish between assignment, call, pre-, post- operators
    if (bop != null) {
      final boolean isShortHand = Translator.isShortHandOperator(bop.getText());
      final boolean isAssign = isShortHand || "=".equals(bop.getText());
      if (isAssign && isAssignCall(exprCtx)) {
        final String x = exprCtx.expression(0).getText();
        final Call e = Translator.call(exprCtx.expression(1), className);
        final Assign assignCall = new Assign(x, e);
        assignCall.setDef(x);
        assignCall.setUses(e.getUses());
        if (isShortHand)
          assignCall.getUses().add(x);
        result = assignCall;
      } else if (isAssign) {
        result = assign(exprCtx, isShortHand);
      } else {
        // TODO: Assuming a plain condition
        result = new Str(exprCtx);
        final Set<String> uses = JavaUtils.uses(exprCtx);
        result.setUses(uses);
      }
    } else if (exprCtx.prefix != null) {
      final ExpressionContext exprCtx2 = exprCtx.expression(0);
      final String x = exprCtx2.getText();
      result = new PreOp(x, exprCtx.prefix.getText());
      result.setDef(x);
      final Set<String> uses = new HashSet<>();
      uses.add(x);
      result.setUses(uses);
    } else if (exprCtx.postfix != null) {
      final ExpressionContext exprCtx2 = exprCtx.expression(0);
      final String x = exprCtx2.getText();
      result = new PostOp(x, exprCtx.postfix.getText());
      result.setDef(x);
      final Set<String> uses = new HashSet<>();
      uses.add(x);
      result.setUses(uses);
    } else if (isMethodCall) {
      result = Translator.call(exprCtx, className);
    } else if (isCreator) {
      result = creator(exprCtx.creator());
    } else {
      // TODO: Assuming multiple conditions
      result = new Str(exprCtx);
      final Set<String> uses = JavaUtils.uses(exprCtx);
      result.setUses(uses);
    }
    return result;
  }

  public Stmt assign(final ExpressionContext exprCtx, final boolean isShortHand) {
    final String x = exprCtx.expression(0).getText();
    final String op = exprCtx.bop.getText();
    final Str e = new Str(exprCtx.expression(1).getText());
    final Assign result = new Assign(x, op, e);
    final Set<String> uses = JavaUtils.uses(exprCtx.expression(1));
    result.setDef(x);
    result.setUses(uses);
    if (isShortHand)
      result.getUses().add(x);
    return result;
  }

  private boolean isAssignCall(final ExpressionContext ctx) {
    if (ctx.expression().size() > 1) {
      final ExpressionContext exprCtx = ctx.expression(1);
      return exprCtx.expressionList() != null;
    }
    return false;
  }

  public Stmt creator(final CreatorContext ctx) {
    final String methodName = ctx.createdName().getText();
    final ExpressionListContext exprLstCtx = ctx.classCreatorRest().arguments().expressionList();
    final List<Str> params = Translator.params(exprLstCtx);
    final Param p = Translator.param(params, false);
    final String x = Translator.fullMethodName(methodName, className);
    final Call result = new Call(x, p);
    // Using ctx instead of exprLstCtx to compute used because we are interested in
    // retrieving referenced objects, e.g., in s.close() we want s as a use.
    final Set<String> uses = JavaUtils.uses(ctx);
    result.setUses(uses);
    return result;
  }

  private void printUnsupported(final String methodName, final String sourceCode) {
    System.err.println(methodName + "\n\t" + sourceCode);
  }


}
