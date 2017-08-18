package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.statement.Stmt;

public class While implements Stmt {

    public String e;
    public Stmt s;

    public While(final String e, final Stmt s) {
	super();
	this.e = e;
	this.s = s;
    }

    @Override
    public String toString() {
	return "while (" + e + ") {" + s + "}";
    }

}
