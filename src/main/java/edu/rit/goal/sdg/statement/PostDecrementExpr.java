package edu.rit.goal.sdg.statement;

import org.antlr.v4.runtime.tree.ParseTree;

public class PostDecrementExpr extends Expr implements Stmt {

    public PostDecrementExpr(final ParseTree ast) {
	super(ast);
    }
    
}
