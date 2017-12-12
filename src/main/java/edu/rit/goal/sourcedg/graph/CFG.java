package edu.rit.goal.sourcedg.graph;

import org.jgrapht.graph.DefaultDirectedGraph;

public class CFG extends DefaultDirectedGraph<Vertex, Edge> {

  /**
   * 
   */
  private static final long serialVersionUID = -85173637837652634L;

  public CFG() {
    super(Edge.class);
  }

}
