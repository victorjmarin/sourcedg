package edu.rit.goal.sdg.interpreter;

import java.io.File;
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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import edu.rit.goal.sdg.DefUsesUtils;
import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.CUnit;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.JavaUtils;
import edu.rit.goal.sdg.java8.antlr4.JavaLexer;
import edu.rit.goal.sdg.java8.antlr4.JavaParser;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ExpressionContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.ExpressionListContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.FormalParameterContext;
import edu.rit.goal.sdg.java8.antlr4.JavaParser.VariableDeclaratorIdContext;
import edu.rit.goal.sdg.java8.antlr4.ParseResult;
import edu.rit.goal.sdg.java8.antlr4.SourceDGJavaVisitor;
import edu.rit.goal.sdg.java8.normalization.Normalizer;

public class Translator {

  private final String cUnitName;
  private final Language lang;
  private final CharStream charStream;

  private enum Language {
    JAVA, PYTHON
  }

  public Translator(final File file) {
    cUnitName = file.getName();
    final String path = file.getPath();
    lang = detectLang(path);
    final Normalizer exprNorm = new Normalizer(file);
    final String normalizedProgram = exprNorm.normalize();
    charStream = CharStreams.fromString(normalizedProgram);
  }

  public Stmt parse() {
    Stmt result = null;
    final Lexer lexer;
    CommonTokenStream tokens;
    ParseTree tree;
    Parser parser;
    final AbstractParseTreeVisitor<ParseResult> visitor;

    switch (lang) {
      case JAVA:
        lexer = new JavaLexer(charStream);
        tokens = new CommonTokenStream(lexer);
        parser = new JavaParser(tokens);
        tree = ((JavaParser) parser).compilationUnit();
        visitor = new SourceDGJavaVisitor(this);
        result = visitor.visit(tree).getStmt();
        ((CUnit) result).x = cUnitName;
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

  public static Param param(final Str param, final boolean isFormal) {
    final List<Str> lst = new ArrayList<>();
    lst.add(param);
    return param(lst, isFormal);
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

  public List<Str> params(final ExpressionListContext ctx) {
    final List<Str> result = new ArrayList<>();
    if (ctx == null)
      return result;
    for (final ExpressionContext exprCtx : ctx.expression()) {
      final String txt = originalCode(exprCtx);
      final Str str = new Str(txt, exprCtx);
      result.add(str);
    }
    return result;
  }

  public static Str formalParam(final VariableDeclaratorIdContext ctx) {
    final TerminalNode identifier = ctx.IDENTIFIER();
    final String paramName = identifier.getText();
    final Str result = new Str(paramName, identifier);
    return result;
  }

  public static List<Str> formalParams(final List<FormalParameterContext> ctx) {
    final List<Str> result = new LinkedList<>();
    if (ctx != null) {
      for (final FormalParameterContext formalParamCtx : ctx) {
        final TerminalNode identifier = formalParamCtx.variableDeclaratorId().IDENTIFIER();
        final String paramName = identifier.getText();
        final Str str = new Str(paramName, identifier);
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
      final Seq seq = new Seq();
      for (final Stmt stmt : stmts)
        seq.add(stmt);
      result = seq;
    }
    return result;
  }

  public Call call(final ExpressionContext ctx, final String className) {
    final List<ExpressionContext> exprCtxLst = ctx.expression();
    final String methodName =
        exprCtxLst.stream().map(c -> c.getText()).collect(Collectors.joining());
    final ExpressionListContext exprLstCtx = ctx.expressionList();
    final List<Str> params = params(exprLstCtx);
    final Param p = param(params, false);
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
    // System.out.println("Unsupported stmt: " + ctx.getText());
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

  // Returns the original code with whitespaces and comments
  public String originalCode(final ParserRuleContext ctx) {
    final int a = ctx.start.getStartIndex();
    final int b = ctx.stop.getStopIndex();
    final Interval interval = new Interval(a, b);
    return charStream.getText(interval);
  }

}
