package edu.rit.goal.sdg.java.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PostDecrementExpr extends Expression implements Statement {

    public PostDecrementExpr(final ParseTree ast) {
	super(ast);
    }
    
}
