package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.statement.Stmt;

public class DoWhile implements Stmt {

    public Expr e;
    public Stmt s;

    public DoWhile(final Expr e, final Stmt s) {
	super();
	this.e = e;
	this.s = s;
    }

    @Override
    public String toString() {
	return "do {" + e + "} while (" + s + ")";
    }

}
