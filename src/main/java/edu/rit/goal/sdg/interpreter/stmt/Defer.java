package edu.rit.goal.sdg.interpreter.stmt;

public class Defer extends Stmt {

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
