package edu.rit.goal.sdg.graph;

public class Edge {

    private final Vertex source;
    private final Vertex target;
    private final EdgeType type;

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
