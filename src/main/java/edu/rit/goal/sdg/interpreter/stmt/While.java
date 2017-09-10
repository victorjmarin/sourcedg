package edu.rit.goal.sdg.interpreter.stmt;

public class While extends Stmt {

    public Expr e;
    public Stmt s;

    public While(final Expr e, final Stmt s) {
	super();
	this.e = e;
	this.s = s;
    }

    @Override
    public String toString() {
	return "while (" + e + ") {" + s + "}";
    }

}
