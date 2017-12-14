package edu.rit.goal.sourcedg.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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

  public List<Vertex> slice(final Set<Vertex> S) {
    final Set<Vertex> marked = new HashSet<>();
    final List<Vertex> worklist = new ArrayList<>(S);
    while (!worklist.isEmpty()) {
      final Vertex v = worklist.remove(0);
      marked.add(v);
      final Set<Vertex> w = incidents(marked, v);
      worklist.addAll(w);
    }
    final List<Vertex> result = new ArrayList<>(marked);
    Collections.sort(result, Comparator.comparing(Vertex::getId));
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

}
