package edu.rit.goal.sdg.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PreDecrementExpr extends Expr implements Stmt {

    public PreDecrementExpr(final ParseTree ast) {
	super(ast);
    }

}
