package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.statement.Stmt;

public class IfThenElse implements Stmt {

    public String e;
    public Stmt s1;
    public Stmt s2;

    public IfThenElse(final String e, final Stmt s1, final Stmt s2) {
	super();
	this.e = e;
	this.s1 = s1;
	this.s2 = s2;
    }

    @Override
    public String toString() {
	return "if (" + e + ") {" + s1 + "} else {" + s2 + "}";
    }

}
