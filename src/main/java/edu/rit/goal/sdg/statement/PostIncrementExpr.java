package edu.rit.goal.sdg.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PostIncrementExpr extends Expr implements Stmt {

    public PostIncrementExpr(final ParseTree ast) {
	super(ast);
    }

}
