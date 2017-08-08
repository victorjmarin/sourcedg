package edu.rit.goal.sdg.statement;

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

}
