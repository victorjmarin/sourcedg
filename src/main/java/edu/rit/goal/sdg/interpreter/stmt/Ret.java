package edu.rit.goal.sdg.interpreter.stmt;

public class Ret implements Stmt {

    public String e;

    public Ret(final String e) {
	super();
	this.e = e;
    }

    @Override
    public String toString() {
	return "ret " + e;
    }

}
