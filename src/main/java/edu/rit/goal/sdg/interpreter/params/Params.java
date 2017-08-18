package edu.rit.goal.sdg.interpreter.params;

public class Params implements Param {

    public String x;
    public Param p;

    public Params(final String x, final Param p) {
	super();
	this.x = x;
	this.p = p;
    }

    @Override
    public String toString() {
	return "(" + x + ", " + p + ")";
    }

}
