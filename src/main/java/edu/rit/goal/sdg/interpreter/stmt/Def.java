package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.interpreter.params.NoParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.statement.Stmt;

public class Def implements Stmt {

    // Flag indicating if the function returns other than void
    public boolean b;
    public String x;
    public Param p;
    public Stmt s;

    public Def(final boolean b, final String x, final Stmt s) {
	super();
	this.b = b;
	this.x = x;
	p = new NoParam();
	this.s = s;
    }

    public Def(final boolean b, final String x, final Param p, final Stmt s) {
	super();
	this.b = b;
	this.x = x;
	this.p = p;
	this.s = s;
    }

    @Override
    public String toString() {
	return "def " + b + " " + x + " " + p + ": " + s;
    }

}
