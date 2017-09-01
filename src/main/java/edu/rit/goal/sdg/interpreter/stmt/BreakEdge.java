package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Deque;

import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.interpreter.stmt.CtrlVertex.CtrlType;

public class BreakEdge implements Stmt {

    public CtrlType ct;
    public Vertex v;
    public Deque<CtrlVertex> S;

    public BreakEdge(final CtrlType ct, final Vertex v, final Deque<CtrlVertex> S) {
	super();
	this.ct = ct;
	this.v = v;
	this.S = S;
    }

    @Override
    public String toString() {
	return "breakedge " + ct + " " + v + " " + S;
    }

}
