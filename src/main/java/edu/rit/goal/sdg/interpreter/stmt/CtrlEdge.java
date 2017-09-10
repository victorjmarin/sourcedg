package edu.rit.goal.sdg.interpreter.stmt;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.graph.Vertex;

public class CtrlEdge extends Stmt {

    public List<Boolean> B;
    public List<Vertex> N;
    public Stmt s;

    public CtrlEdge(final boolean b, final Vertex n, final Stmt s) {
	super();
	B = new ArrayList<>();
	N = new ArrayList<>();
	this.s = s;
	B.add(b);
	N.add(n);
    }

    public CtrlEdge(final boolean b, final List<Vertex> N, final Stmt s) {
	super();
	B = new ArrayList<>();
	this.N = N;
	this.s = s;
	B.add(b);
    }

    public CtrlEdge(final List<Boolean> B, final List<Vertex> N, final Stmt s) {
	super();
	this.B = B;
	this.N = N;
	this.s = s;
    }

    @Override
    public String toString() {
	return "ctrledge " + B + " " + N + " " + s;
    }

}
