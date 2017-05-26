package edu.rit.goal.sdg.java.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PreDecrementExpr extends Expression implements Statement {

    public PreDecrementExpr(final ParseTree ast) {
	super(ast);
    }

}
