package edu.rit.goal.sdg.interpreter.stmt;

public class Assign extends Stmt {

    public String x;
    public String op;
    public Expr e;

    public Assign(final String x, final Expr e) {
	super();
	this.x = x;
	op = ":=";
	this.e = e;
    }

    public Assign(final String x, final String op, final Expr e) {
	super();
	this.x = x;
	if ("=".equals(op))
	    this.op = ":=";
	else
	    this.op = op;
	this.e = e;
    }

    @Override
    public String toString() {
	return x + op + e;
    }

}
