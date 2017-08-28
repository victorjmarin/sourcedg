package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.Vertex;

public class CtrlVertex {

    public enum CtrlType {
	LOOP, SEQ
    }

    public Vertex v;
    public CtrlType ct;

    public CtrlVertex(final Vertex v, final CtrlType ct) {
	super();
	this.v = v;
	this.ct = ct;
    }

    @Override
    public String toString() {
	return "(" + v + ", " + ct + ")";
    }

}
