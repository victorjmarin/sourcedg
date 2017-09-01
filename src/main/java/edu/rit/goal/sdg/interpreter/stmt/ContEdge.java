package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Deque;

import edu.rit.goal.sdg.graph.Vertex;

public class ContEdge implements Stmt {

    public CtrlVertex cv;
    public Vertex v;
    public Deque<CtrlVertex> S;

    public ContEdge(final CtrlVertex cv, final Vertex v, final Deque<CtrlVertex> S) {
	super();
	this.cv = cv;
	this.v = v;
	this.S = S;
    }

    @Override
    public String toString() {
	return "contedge " + cv + " " + v + " " + S;
    }

}
