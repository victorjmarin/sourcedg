package edu.rit.goal.sdg.interpreter.stmt.sw;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class SingleSwitch implements ISwitchBody {

    public ICase cs;
    public Stmt s;

    public SingleSwitch(final ICase cs, final Stmt s) {
	super();
	this.cs = cs;
	this.s = s;
    }

    @Override
    public String toString() {
	return cs + ": " + s;
    }

}
