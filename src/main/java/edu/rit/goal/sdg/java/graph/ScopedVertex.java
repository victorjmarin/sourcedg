package edu.rit.goal.sdg.java.graph;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Statement;

public class ScopedVertex {

    private Vertex vertex;
    private List<Statement> scope;

    public ScopedVertex(final Vertex vertex, final List<Statement> scope) {
	super();
	this.vertex = vertex;
	this.scope = scope;
    }

    public Vertex getVertex() {
	return vertex;
    }

    public void setVertex(final Vertex vertex) {
	this.vertex = vertex;
    }

    public List<Statement> getScope() {
	return scope;
    }

    public void setScope(final List<Statement> scope) {
	this.scope = scope;
    }

}
