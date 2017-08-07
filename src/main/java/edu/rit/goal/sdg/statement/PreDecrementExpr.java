package edu.rit.goal.sdg.statement;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

public class PreDecrementExpr extends Expr implements Stmt {

    public PreDecrementExpr(final ParseTree ast) {
	super(ast);
    }

    @Override
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
