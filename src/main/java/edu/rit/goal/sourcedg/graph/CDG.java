package edu.rit.goal.sourcedg.graph;

import org.jgrapht.graph.DefaultDirectedGraph;

public class CDG extends DefaultDirectedGraph<Vertex, Edge> {

  /**
   * 
   */
  private static final long serialVersionUID = 7980355145636077214L;

  public CDG() {
    super(Edge.class);
  }

}
