package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.statement.Stmt;

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
	sb.append(p.toString());
	return sb.toString();
    }

}
