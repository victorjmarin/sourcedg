package edu.rit.goal.sdg.graph;

public class Edge {

    private Vertex source;
    private Vertex target;
    private EdgeType type;

    public Edge() {
    }

    public Edge(final Vertex source, final Vertex target, final EdgeType type) {
	this.source = source;
	this.target = target;
	this.type = type;
    }

    public Vertex getSource() {
	return source;
    }

    public Vertex getTarget() {
	return target;
    }

    public EdgeType getType() {
	return type;
    }

    @Override
    public String toString() {
	return type.toString();
    }

}
