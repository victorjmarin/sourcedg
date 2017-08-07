package edu.rit.goal.sdg.statement;

import java.util.List;

public class ReturnStmt implements Stmt {

    private final Expr returnedExpr;

    public ReturnStmt(final Expr returnedExpr) {
	super();
	this.returnedExpr = returnedExpr;
    }

    public Expr getReturnedExpr() {
	return returnedExpr;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("return");
	if (returnedExpr != null) {
	    sb.append(" ");
	    sb.append(returnedExpr);
	}
	return sb.toString();
    }

    @Override
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
