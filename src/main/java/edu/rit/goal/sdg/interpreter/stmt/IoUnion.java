package edu.rit.goal.sdg.interpreter.stmt;

public class IoUnion extends Stmt {

    public Stmt s1;
    public Stmt s2;

    public IoUnion(final Stmt s1, final Stmt s2) {
	this.s1 = s1;
	this.s2 = s2;
    }

    @Override
    public String toString() {
	return "iounion " + s1 + " " + s2;
    }

}
