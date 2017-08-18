package edu.rit.goal.sdg.interpreter.stmt;

import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.statement.Stmt;

public class ParamOut implements Stmt {

    public Vertex v;
    public String x;

    public ParamOut(final Vertex v, final String x) {
	super();
	this.v = v;
	this.x = x;
    }

    @Override
    public String toString() {
	return "paramout " + v + " " + x;
    }

}
