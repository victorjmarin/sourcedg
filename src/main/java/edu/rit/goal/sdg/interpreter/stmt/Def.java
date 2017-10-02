package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;

public class Def extends Stmt {

    // Flag indicating if the function returns other than void
    public boolean b;
    public String x;
    public Param p;
    public Stmt s;
    public Integer startLine;
    public Integer endLine;

    public Def(final boolean b, final String x, final Stmt s) {
	super();
	this.b = b;
	this.x = x;
	p = new EmptyParam();
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
	return "def " + b + " " + x + " (" + p + "): " + s;
    }

}
