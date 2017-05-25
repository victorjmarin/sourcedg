package edu.rit.goal.sdg.java.graph;

import org.jgrapht.graph.DefaultDirectedGraph;

public class SysDepGraph extends DefaultDirectedGraph<Vertex, Edge> {

    private static final long serialVersionUID = 5502017877788689016L;

    public SysDepGraph() {
	super(Edge.class);
    }

}