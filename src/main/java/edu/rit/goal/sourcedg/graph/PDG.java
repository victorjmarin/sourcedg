package edu.rit.goal.sourcedg.graph;

import org.jgrapht.graph.DefaultDirectedGraph;

public class PDG extends DefaultDirectedGraph<Vertex, Edge> {

  /**
   * 
   */
  private static final long serialVersionUID = 4208713030783810103L;
  
  public PDG() {
    super(Edge.class);
  }

}
