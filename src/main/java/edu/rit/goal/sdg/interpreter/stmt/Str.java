package edu.rit.goal.sdg.interpreter.stmt;

public class Str implements Expr {

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
