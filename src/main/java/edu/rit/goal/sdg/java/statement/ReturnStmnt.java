package edu.rit.goal.sdg.java.statement;

public class ReturnStmnt implements Statement {

    private final Expression returnedExpr;

    public ReturnStmnt(final Expression returnedExpr) {
	super();
	this.returnedExpr = returnedExpr;
    }

    public Expression getReturnedExpr() {
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
