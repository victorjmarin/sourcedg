package edu.rit.goal.sdg.interpreter.stmt;

import java.util.HashSet;
import java.util.Set;

import edu.rit.goal.sdg.graph.Vertex;

public class Io extends Stmt {

    public Set<Vertex> I;
    public Set<Vertex> O;

    public Io(final Vertex i, final Set<Vertex> O) {
	final Set<Vertex> s = new HashSet<>();
	s.add(i);
	I = s;
	this.O = O;
    }

    public Io(final Set<Vertex> I, final Vertex o) {
	final Set<Vertex> s = new HashSet<>();
	s.add(o);
	this.I = I;
	O = s;
    }

    public Io(final Vertex i, final Vertex o) {
	final Set<Vertex> s1 = new HashSet<>();
	s1.add(i);
	final Set<Vertex> s2 = new HashSet<>();
	s2.add(o);
	I = s1;
	O = s2;
    }

    public Io(final Set<Vertex> I, final Set<Vertex> O) {
	this.I = I;
	this.O = O;
    }

    @Override
    public String toString() {
	return "io " + I + " " + O;
    }

}
