package edu.rit.goal.sdg.interpreter.stmt;

public class Assign extends Stmt {

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
