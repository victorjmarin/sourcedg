package edu.rit.goal.sdg.java.visitor;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.goal.sdg.java.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.AssignmentContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodInvocation_lfno_primaryContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodNameContext;
import edu.rit.goal.sdg.java.exception.AssignmentGuardException;
import edu.rit.goal.sdg.java.exception.InvocationArgException;
import edu.rit.goal.sdg.java.exception.MultipleExitPointsException;

public class VisitorUtils {

    private static Map<String, Boolean> methodNameReturnCount = new HashMap<>();

    public static String getMethodName(final ParseTree ctx) {
	String result = null;
	if (ctx instanceof MethodNameContext) {
	    result = ((MethodNameContext) ctx).getText();
	    return result;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result = getMethodName(child);
	    if (result != null)
		break;
	}
	return result;
    }

    public static ArgumentListContext getArgListCtx(final ParseTree ctx) {
	ArgumentListContext result = null;
	if (ctx instanceof ArgumentListContext) {
	    return (ArgumentListContext) ctx;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result = getArgListCtx(child);
	    if (result != null)
		break;
	}
	return result;
    }

    public static MethodInvocation_lfno_primaryContext getMethodInvCtx(final ParseTree ctx) {
	MethodInvocation_lfno_primaryContext result = null;
	if (ctx instanceof MethodInvocation_lfno_primaryContext) {
	    return (MethodInvocation_lfno_primaryContext) ctx;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result = getMethodInvCtx(child);
	    if (result != null)
		break;
	}
	return result;
    }

    public static AssignmentContext getAssignmentCtx(final ParseTree ctx) {
	AssignmentContext result = null;
	if (ctx instanceof AssignmentContext) {
	    return (AssignmentContext) ctx;
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result = getAssignmentCtx(child);
	    if (result != null)
		break;
	}
	return result;
    }

    public static boolean isMethodInvOrAssignment(final ParseTree ctx) {
	return getAssignmentCtx(ctx) != null || getMethodInvCtx(ctx) != ctx;
    }

    public static void checkForUnsupportedFeatures(final ParserRuleContext ctx) {
	final boolean isMethodInvocation = VisitorUtils.getMethodInvCtx(ctx) != null;
	// Method/function call as argument
	if (isMethodInvocation) {
	    throw new InvocationArgException(ctx);
	}
	final boolean isAssignment = VisitorUtils.getAssignmentCtx(ctx) != null;
	// Assignment in guard
	if (isAssignment) {
	    throw new AssignmentGuardException(ctx);
	}
    }

    public static void checkForMultipleExitPoints(final String methodName, final ParserRuleContext ctx) {
	final Boolean hasExitPoint = methodNameReturnCount.get(methodName);
	if (hasExitPoint != null)
	    throw new MultipleExitPointsException(ctx);
	methodNameReturnCount.put(methodName, true);
    }

}
