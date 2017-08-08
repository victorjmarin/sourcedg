package edu.rit.goal.sdg.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PreIncrementExpr extends Expr implements Stmt {

    public PreIncrementExpr(final ParseTree ast) {
	super(ast);
    }

}
