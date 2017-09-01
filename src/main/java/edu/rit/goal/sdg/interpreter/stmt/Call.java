package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.interpreter.params.Param;

public class Call implements Stmt, Expr {

    public String x;
    public Param p;

    public Call(final String x, final Param p) {
	super();
	this.x = x;
	this.p = p;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append(x);
	sb.append("(");
	sb.append(p);
	sb.append(")");
	return sb.toString();
    }

}
