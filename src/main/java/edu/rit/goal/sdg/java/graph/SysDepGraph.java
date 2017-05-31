package edu.rit.goal.sdg.java.graph;

import org.jgrapht.graph.DefaultDirectedGraph;

public class SysDepGraph extends DefaultDirectedGraph<Vertex, Edge> {

    private static final long serialVersionUID = 5502017877788689016L;

    public SysDepGraph() {
	super(Edge.class);
    }

    public Edge addEdge(final Vertex source, final Vertex target, final EdgeType type) {
	final Edge edge = new Edge(source, target, type);
	addEdge(source, target, edge);
	return edge;
    }

    public Vertex getVertexByLabel(final String lookupId) {
	final Vertex result = vertexSet().stream().filter(v -> lookupId.equals(v.getLookupId())).findFirst()
		.orElse(null);
	return result;
    }

}