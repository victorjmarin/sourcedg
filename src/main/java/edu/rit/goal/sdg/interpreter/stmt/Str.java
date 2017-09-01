package edu.rit.goal.sdg.interpreter.stmt;

public class Str implements Expr, edu.rit.goal.sdg.interpreter.params.Param {

    public String value;

    public Str(final String value) {
	super();
	this.value = value;
    }

    @Override
    public String toString() {
	return value;
    }

}
