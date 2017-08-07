package edu.rit.goal.sdg.statement;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.goal.sdg.java8.antlr.Java8Parser.AmbiguousNameContext;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.ExpressionNameContext;;

public class Expr {

    private final ParseTree ast;
    private final Set<String> readingVars;

    public Expr(final ParseTree ast) {
	super();
	this.ast = ast;
	readingVars = getReadingVars(ast);
    }

    public ParseTree getAst() {
	return ast;
    }

    public Set<String> getReadingVars() {
	return readingVars;
    }

    private String getText() {
	return ast.getText();
    }

    @Override
    public String toString() {
	return getText();
    }

    private Set<String> getReadingVars(final ParseTree ctx) {
	final Set<String> result = new HashSet<>();
	if (ctx.getChildCount() == 0) {
	    final ParseTree parent = ctx.getParent();
	    if (parent instanceof ExpressionNameContext) {
		final ParseTree firstChild = parent.getChild(0);
		// Deal with a.length and such
		if (firstChild instanceof AmbiguousNameContext) {
		    result.add(firstChild.getText());
		} else {
		    result.add(parent.getText());
		}
		return result;
	    }
	}
	for (int i = 0; i < ctx.getChildCount(); i++) {
	    final ParseTree child = ctx.getChild(i);
	    result.addAll(getReadingVars(child));
	}
	return result;
    }

}
