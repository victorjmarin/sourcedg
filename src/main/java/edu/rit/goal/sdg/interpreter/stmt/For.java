package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.statement.Stmt;

public class For implements Stmt {

    public String i, c, u;
    public Stmt s;

    public For(final String i, final String c, final String u, final Stmt s) {
	super();
	this.i = i;
	this.c = c;
	this.u = u;
	this.s = s;
    }

    @Override
    public String toString() {
	return "for (" + i + ";" + c + ";u) {" + s + "}";
    }

}
