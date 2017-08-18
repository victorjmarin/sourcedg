package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.statement.Stmt;

public class Defer implements Stmt {

    public Stmt s;

    public Defer(final Stmt s) {
	super();
	this.s = s;
    }

    @Override
    public String toString() {
	return "defer " + s;
    }

}
