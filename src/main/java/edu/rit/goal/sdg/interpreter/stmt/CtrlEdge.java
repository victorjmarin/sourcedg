package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.statement.Stmt;

public class CtrlEdge implements Stmt {

    public boolean b;
    public Vertex v;
    public Stmt s;

    public CtrlEdge(final boolean b, final Vertex v, final Stmt s) {
	super();
	this.b = b;
	this.v = v;
	this.s = s;
    }

    @Override
    public String toString() {
	return "ctrledge " + b + " " + v + " " + s;
    }

}
