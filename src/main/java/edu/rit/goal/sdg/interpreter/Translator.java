package edu.rit.goal.sdg.interpreter;

import java.util.ArrayList;
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
import edu.rit.goal.sdg.java8.visitor.ClassBodyVisitor;

public class Translator {
    private boolean notWrapped = true;

    private enum Language {
	JAVA, PYTHON
    }

    public Stmt from(final String source) {
	Stmt result = null;
	final Lexer lexer;
	CommonTokenStream tokens;
	ParseTree tree;
	Parser parser;
	AbstractParseTreeVisitor<Stmt> visitor;
	final Language lang = detectLang(source);
	final CharStream chrStream = CharStreams.fromString(source);
	switch (lang) {
	case JAVA:
	    lexer = new JavaLexer(chrStream);
	    tokens = new CommonTokenStream(lexer);
	    parser = new JavaParser(tokens);
	    tree = ((JavaParser) parser).compilationUnit();
	    visitor = new ClassBodyVisitor();
	    result = visitor.visit(tree);
	    break;
	case PYTHON:
	    break;
	}
	if (result == null && notWrapped) {
	    notWrapped = false;
	    result = from(wrap(source));
	}
	notWrapped = true;
	return result;
    }

    protected Language detectLang(final String source) {
	Language result = Language.JAVA;
	if ((source.contains("def ") && source.contains(":"))
		|| (!source.contains("{") && !source.contains("}") && !source.contains(";"))) {
	    result = Language.PYTHON;
	}
	return result;
    }

    public static String wrap(final String source) {
	final String cls = "public class WrapperClass {";
	final StringBuilder sb = new StringBuilder(cls);
	sb.append(source);
	sb.append("}");
	return sb.toString();
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
    public static List<Str> params(final ParseTree ctx) {
	final List<Str> result = new ArrayList<>();
	if (ctx == null)
	    return result;
	if (ctx.getChildCount() == 0 && !",".equals(ctx.getText())) {
	    final Str str = new Str(ctx);
	    result.add(str);
	    return result;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result.addAll(params(child));
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

    public static Call call(final ExpressionContext ctx) {
	final List<ExpressionContext> exprCtxLst = ctx.expression();
	final String x = exprCtxLst.stream().map(c -> c.getText()).collect(Collectors.joining());
	final ExpressionListContext exprLstCtx = ctx.expressionList();
	final List<Str> params = Translator.params(exprLstCtx);
	final Param p = Translator.param(params, false);
	final Call result = new Call(x, p);
	final Set<String> uses = JavaUtils.uses(exprLstCtx);
	result.setUses(uses);
	return result;
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
