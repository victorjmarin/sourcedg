package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Set;

import edu.rit.goal.sdg.graph.Vertex;

public class Io extends Stmt {

    public Set<Vertex> I;
    public Set<Vertex> O;

    public Io(final Set<Vertex> I, final Set<Vertex> O) {
	this.I = I;
	this.O = O;
    }

    @Override
    public String toString() {
	return "io " + I + " " + O;
    }

}
