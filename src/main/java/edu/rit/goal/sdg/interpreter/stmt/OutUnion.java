package edu.rit.goal.sdg.interpreter.stmt;

import java.util.HashSet;
import java.util.Set;

import edu.rit.goal.sdg.graph.Vertex;

public class OutUnion extends Stmt {

    public Set<Vertex> I;
    public Stmt s1;
    public Stmt s2;

    public OutUnion(final Vertex i, final Stmt s1, final Stmt s2) {
	final Set<Vertex> s = new HashSet<>();
	s.add(i);
	I = s;
	this.s1 = s1;
	this.s2 = s2;
    }
    
    public OutUnion(final Set<Vertex> I, final Stmt s1, final Stmt s2) {
	this.I = I;
	this.s1 = s1;
	this.s2 = s2;
    }

    @Override
    public String toString() {
	return "outunion " + I + " " + s1 + " " + s2;
    }

}
