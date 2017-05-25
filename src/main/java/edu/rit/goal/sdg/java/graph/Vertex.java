package edu.rit.goal.sdg.java.graph;

public class Vertex {

    private final VertexType type;
    private final String ast;

    public Vertex(final VertexType type, final String ast) {
	super();
	this.type = type;
	this.ast = ast;
    }

    public VertexType getType() {
	return type;
    }

    public String getAst() {
	return ast;
    }

    @Override
    public String toString() {
	return type + "-" + ast;
    }

}
