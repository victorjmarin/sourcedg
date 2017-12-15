package edu.rit.goal.sourcedg.graph;

import java.util.Set;
import org.jgrapht.graph.DefaultDirectedGraph;
import edu.rit.goal.sourcedg.analysis.PDGSlicer;

public class PDG extends DefaultDirectedGraph<Vertex, Edge> {

  /**
   * 
   */
  private static final long serialVersionUID = 4208713030783810103L;

  public PDG() {
    super(Edge.class);
  }

  public Vertex actualOut(final Vertex v) {
    final Set<Edge> edges = outgoingEdgesOf(v);
    Vertex successor = null;
    for (final Edge e : edges) {
      successor = e.getTarget();
      if (VertexType.ACTUAL_OUT.equals(successor.getType())) {
        return successor;
      }
    }
    return null;
  }

  public Set<Vertex> backwardSlice(final Set<Vertex> S) {
    return PDGSlicer.backward(this, S);
  }

  public Set<Vertex> forwardSlice(final Set<Vertex> S) {
    return PDGSlicer.forward(this, S);
  }

}
