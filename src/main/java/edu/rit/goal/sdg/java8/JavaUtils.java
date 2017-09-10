package edu.rit.goal.sdg.java8;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import edu.rit.goal.sdg.java8.antlr.JavaParser;

public class JavaUtils {

    public static Set<String> uses(final ParseTree ctx) {
	final Set<String> result = new HashSet<>();
	final int childCount = ctx.getChildCount();
	if (ctx instanceof TerminalNodeImpl) {
	    // Currently only works for local variable definitions. Will not work properly
	    // when referencing member variables, for example (this.X)
	    final int type = ((TerminalNodeImpl) ctx).getSymbol().getType();
	    if (type == JavaParser.IDENTIFIER) {
		result.add(ctx.getText());
	    }
	} else {
	    for (int i = 0; i < childCount; i++)
		result.addAll(uses(ctx.getChild(i)));
	}
	return result;
    }

}
