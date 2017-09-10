package edu.rit.goal.sdg.interpreter.stmt;

import java.util.LinkedHashSet;

import edu.rit.goal.sdg.graph.Vertex;

public class ParamIn extends Stmt {

    public String x;
    public int i;
    public LinkedHashSet<Vertex> V;

    public ParamIn(final String x, final int i, final LinkedHashSet<Vertex> v) {
	super();
	this.x = x;
	this.i = i;
	V = v;
    }

    @Override
    public String toString() {
	return "paramin " + x + " " + i + " " + V;
    }

}
