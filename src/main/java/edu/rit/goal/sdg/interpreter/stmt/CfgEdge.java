package edu.rit.goal.sdg.interpreter.stmt;

public class CfgEdge extends Stmt {

    public Stmt s1;
    public Stmt s2;

    public CfgEdge(final Stmt s1, final Stmt s2) {
	this.s1 = s1;
	this.s2 = s2;
    }

    public String toString() {
	return "cfgedge " + s1 + " " + s2;
    }

}
