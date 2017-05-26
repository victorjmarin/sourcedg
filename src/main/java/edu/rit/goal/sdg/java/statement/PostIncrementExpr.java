package edu.rit.goal.sdg.java.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PostIncrementExpr extends Expression implements Statement {

    public PostIncrementExpr(final ParseTree ast) {
	super(ast);
    }

}
