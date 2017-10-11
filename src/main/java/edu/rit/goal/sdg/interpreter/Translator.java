package edu.rit.goal.sdg.interpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import edu.rit.goal.sdg.DefUsesUtils;
import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.JavaUtils;
import edu.rit.goal.sdg.java8.antlr.JavaLexer;
import edu.rit.goal.sdg.java8.antlr.JavaParser;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParameterContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParameterListContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParametersContext;
import edu.rit.goal.sdg.java8.visitor.CompilationUnitVisitor;

public class Translator {

  private enum Language {
    JAVA, PYTHON
  }

  public Stmt from(final String fileName) throws IOException {
    Stmt result = null;
    final Lexer lexer;
    CommonTokenStream tokens;
    ParseTree tree;
    Parser parser;
    AbstractParseTreeVisitor<Stmt> visitor;
    final Language lang = detectLang(fileName);
    final CharStream chrStream = CharStreams.fromFileName(fileName);
    switch (lang) {
      case JAVA:
        lexer = new JavaLexer(chrStream);
        tokens = new CommonTokenStream(lexer);
        parser = new JavaParser(tokens);
        tree = ((JavaParser) parser).compilationUnit();
        visitor = new CompilationUnitVisitor();
        result = visitor.visit(tree);
        break;
      case PYTHON:
        break;
    }
    return result;
  }

  public Stmt fromSource(final String source) throws IOException {
    Stmt result = null;
    final Lexer lexer;
    CommonTokenStream tokens;
    ParseTree tree;
    Parser parser;
    AbstractParseTreeVisitor<Stmt> visitor;
    final Language lang = Language.JAVA;
    final CharStream chrStream = CharStreams.fromString(source);
    switch (lang) {
      case JAVA:
        lexer = new JavaLexer(chrStream);
        tokens = new CommonTokenStream(lexer);
        parser = new JavaParser(tokens);
        tree = ((JavaParser) parser).compilationUnit();
        visitor = new CompilationUnitVisitor();
        result = visitor.visit(tree);
        break;
      case PYTHON:
        break;
    }
    return result;
  }

  protected Language detectLang(final String fileName) {
    final Language result = Language.JAVA;
    return result;
  }

  public static Param param(final List<Str> params, final boolean isFormal) {
    Param result = null;
    if (params == null || params.isEmpty()) {
      result = new EmptyParam();
    } else if (params.size() == 1) {
      result = params.remove(0);
      DefUsesUtils.strDefUses((Str) result, isFormal);
    } else {
      final Str str = params.remove(0);
      final String x = str.value;
      result = new Params(x, param(params, isFormal));
      DefUsesUtils.paramInDefUses((Params) result, str, isFormal);
    }
    return result;
  }

  // TODO: Make more complete conversion. There will be cases in which there will be
  // nested calls as an argument, for example. This will not work in such cases.
  public static List<Str> params(final ExpressionListContext ctx) {
    final List<Str> result = new ArrayList<>();
    if (ctx == null)
      return result;
    for (final ExpressionContext exprCtx : ctx.expression()) {
      final Str str = new Str(exprCtx);
      result.add(str);
    }
    return result;
  }

  public static List<Str> formalParams(final FormalParametersContext ctx) {
    final List<Str> result = new LinkedList<>();
    final FormalParameterListContext formalParamListCtx = ctx.formalParameterList();
    if (formalParamListCtx != null) {
      for (final FormalParameterContext formalParamCtx : formalParamListCtx.formalParameter()) {
        final Str str = new Str(formalParamCtx.variableDeclaratorId().IDENTIFIER());
        result.add(str);
      }
    }
    return result;
  }

  public static Stmt seq(final List<Stmt> stmts) {
    Stmt result = null;
    if (stmts.isEmpty()) {
      result = new Skip();
    } else if (stmts.size() == 1) {
      result = stmts.remove(0);
    } else {
      final Stmt s = stmts.remove(0);
      result = new Seq(s, seq(stmts));
    }
    return result;
  }

  public static Call call(final ExpressionContext ctx, final String className) {
    final List<ExpressionContext> exprCtxLst = ctx.expression();
    final String methodName =
        exprCtxLst.stream().map(c -> c.getText()).collect(Collectors.joining());
    final ExpressionListContext exprLstCtx = ctx.expressionList();
    final List<Str> params = Translator.params(exprLstCtx);
    final Param p = Translator.param(params, false);
    final String x = fullMethodName(methodName, className);
    final Call result = new Call(x, p);
    // Using ctx instead of exprLstCtx to compute used because we are interested in
    // retrieving references objects, e.g., in s.close() we want s as a use.
    final Set<String> uses = JavaUtils.uses(ctx);
    result.setUses(uses);
    return result;
  }

  public static String fullMethodName(final String x, final String className) {
    return className + "." + x;
  }

  public static String removeClassName(final String fullMethodName) {
    final int dotPos = fullMethodName.indexOf(".");
    if (dotPos != -1)
      return fullMethodName.substring(dotPos + 1, fullMethodName.length());
    return fullMethodName;
  }

  public static void unsupported(final ParseTree ctx) {
    System.out.println("Unsupported stmt: " + ctx.getText());
  }

  public static boolean isShortHandOperator(final String operator) {
    switch (operator) {
      case "=":
        return false;
      case "*=":
      case "/=":
      case "%=":
      case "+=":
      case "-=":
      case "<<=":
      case ">>=":
      case ">>>=":
      case "&=":
      case "^=":
      case "|=":
        return true;
      default:
        return false;
    }
  }

  public static boolean isEmptyArgCall(final ExpressionContext ctx) {
    boolean result = false;
    if (ctx.getChildCount() > 2) {
      final String leftParen = ctx.getChild(1).getText();
      final String rightParen = ctx.getChild(2).getText();
      if ("(".equals(leftParen) && ")".equals(rightParen))
        result = true;
    }
    return result;
  }

}
