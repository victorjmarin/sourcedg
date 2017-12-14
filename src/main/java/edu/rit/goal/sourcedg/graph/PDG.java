package edu.rit.goal.sourcedg.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jgrapht.graph.DefaultDirectedGraph;

public class PDG extends DefaultDirectedGraph<Vertex, Edge> {

  /**
   * 
   */
  private static final long serialVersionUID = 4208713030783810103L;

  public PDG() {
    super(Edge.class);
  }

  public Set<Vertex> slice(final Set<Vertex> S) {
    final Set<Vertex> result = new HashSet<>();
    final Set<Vertex> worklist = new HashSet<>(S);
    while (!worklist.isEmpty()) {
      final Vertex v = next(worklist);
      result.add(v);
      final Set<Vertex> w = incidents(result, v);
      worklist.addAll(w);
    }
    return result;
  }

  private Set<Vertex> incidents(final Collection<Vertex> marked, final Vertex v) {
    final Set<Vertex> result = new HashSet<>();
    final Set<Edge> edges = incomingEdgesOf(v);
    for (final Edge e : edges) {
      if ((e.getType().equals(EdgeType.DATA) || e.getType().isControl())
          && !marked.contains(e.getSource()))
        result.add(e.getSource());
    }
    return result;
  }

  private Vertex next(final Set<Vertex> S) {
    final Iterator<Vertex> it = S.iterator();
    final Vertex result = it.next();
    it.remove();
    return result;
  }

}
