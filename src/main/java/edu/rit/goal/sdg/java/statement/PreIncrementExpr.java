package edu.rit.goal.sdg.java.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PreIncrementExpr extends Expression implements Statement {

    public PreIncrementExpr(final ParseTree ast) {
	super(ast);
    }

}
