package edu.rit.goal.sourcedg.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jgrapht.graph.DefaultDirectedGraph;
import com.google.common.collect.Sets;

public class PDG extends DefaultDirectedGraph<Vertex, Edge> {

  /**
   * 
   */
  private static final long serialVersionUID = 4208713030783810103L;

  public PDG() {
    super(Edge.class);
  }

  public Set<Vertex> backwardSlice(final Set<Vertex> S) {
    final Set<Vertex> S2 = backwardSlice(S, Sets.newHashSet(EdgeType.PARAM_OUT));
    final Set<Vertex> result = backwardSlice(S2, Sets.newHashSet(EdgeType.PARAM_IN, EdgeType.CALL));
    result.addAll(S2);
    return result;
  }

  public Set<Vertex> backwardSlice(final Set<Vertex> S, final Set<EdgeType> kinds) {
    final Set<Vertex> result = new HashSet<>();
    final Set<Vertex> worklist = new HashSet<>(S);
    while (!worklist.isEmpty()) {
      final Vertex v = next(worklist);
      result.add(v);
      final Set<Vertex> w = backward(result, v, kinds);
      worklist.addAll(w);
    }
    return result;
  }

  private Set<Vertex> backward(final Collection<Vertex> marked, final Vertex v,
      final Set<EdgeType> kinds) {
    final Set<Vertex> result = new HashSet<>();
    final Set<Edge> edges = incomingEdgesOf(v);
    for (final Edge e : edges) {
      if (!kinds.contains(e.getType()) && !marked.contains(e.getSource()))
        result.add(e.getSource());
    }
    return result;
  }

  public Set<Vertex> forwardSlice(final Set<Vertex> S) {
    final Set<Vertex> S2 = forwardSlice(S, Sets.newHashSet(EdgeType.PARAM_IN, EdgeType.CALL));
    final Set<Vertex> result = forwardSlice(S2, Sets.newHashSet(EdgeType.PARAM_OUT));
    result.addAll(S2);
    return result;
  }

  public Set<Vertex> forwardSlice(final Set<Vertex> S, final Set<EdgeType> kinds) {
    final Set<Vertex> result = new HashSet<>();
    final Set<Vertex> worklist = new HashSet<>(S);
    while (!worklist.isEmpty()) {
      final Vertex v = next(worklist);
      result.add(v);
      final Set<Vertex> w = forward(result, v, kinds);
      worklist.addAll(w);
    }
    return result;
  }

  private Set<Vertex> forward(final Collection<Vertex> marked, final Vertex v,
      final Set<EdgeType> kinds) {
    final Set<Vertex> result = new HashSet<>();
    final Set<Edge> edges = outgoingEdgesOf(v);
    for (final Edge e : edges) {
      if (!kinds.contains(e.getType()) && !marked.contains(e.getTarget()))
        result.add(e.getTarget());
    }
    return result;
  }

  private Vertex next(final Set<Vertex> S) {
    final Iterator<Vertex> it = S.iterator();
    final Vertex result = it.next();
    it.remove();
    return result;
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

}
