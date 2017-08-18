package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.statement.Stmt;

public class Assign implements Stmt {

    public String x;
    public Expr e;

    public Assign(final String x, final Expr e) {
	super();
	this.x = x;
	this.e = e;
    }

    @Override
    public String toString() {
	return x + ":=" + e;
    }

}
