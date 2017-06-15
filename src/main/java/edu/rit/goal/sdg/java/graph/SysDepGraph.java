package edu.rit.goal.sdg.java.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultDirectedGraph;

public class SysDepGraph extends DefaultDirectedGraph<Vertex, Edge> {

    private static final long serialVersionUID = 5502017877788689016L;

    private final List<Vertex> vertices;

    public SysDepGraph() {
	super(Edge.class);
	vertices = new ArrayList<>();
    }

    @Override
    public boolean addVertex(final Vertex v) {
	vertices.add(v);
	return super.addVertex(v);
    }

    @Override
    public boolean removeVertex(final Vertex v) {
	vertices.remove(v);
	return super.removeVertex(v);
    }

    public Edge addEdge(final Vertex source, final Vertex target, final EdgeType type) {
	final Edge edge = new Edge(source, target, type);
	addEdge(source, target, edge);
	return edge;
    }

    public Vertex getFirstVertexByLabel(final String lookupId) {
	final Vertex result = vertexSet().stream().filter(v -> lookupId.equals(v.getLookupId())).findFirst()
		.orElse(null);
	return result;
    }

    public int distance(final Vertex a, final Vertex b) {
	return Math.abs(vertices.indexOf(a) - vertices.indexOf(b));
    }

    public List<Vertex> getAllVerticesByLabel(final String lookupId) {
	final List<Vertex> result = vertexSet().stream().filter(v -> lookupId.equals(v.getLookupId()))
		.collect(Collectors.toList());
	return result;
    }

    public List<Vertex> getAllAssignmentVerticesByLabel(final String lookupId) {
	final List<Vertex> result = vertexSet().stream()
		.filter(v -> lookupId.equals(v.getLookupId())
			&& (v.getType().equals(VertexType.DECL) || v.getType().equals(VertexType.ASSIGN)))
		.collect(Collectors.toList());
	return result;
    }

}