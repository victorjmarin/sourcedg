package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.Vertex;

public class Vc extends Stmt {

    public Vertex v;

    public Vc(final Vertex v) {
	super();
	this.v = v;
    }

    @Override
    public String toString() {
	return "vc " + v;
    }

}
