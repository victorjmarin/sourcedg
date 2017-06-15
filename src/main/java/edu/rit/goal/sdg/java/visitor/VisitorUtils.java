package edu.rit.goal.sdg.java.visitor;

import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.goal.sdg.java.antlr.Java8Parser.ArgumentListContext;
import edu.rit.goal.sdg.java.antlr.Java8Parser.MethodNameContext;

public class VisitorUtils {

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
    
}
