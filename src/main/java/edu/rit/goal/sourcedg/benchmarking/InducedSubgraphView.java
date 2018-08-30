package edu.rit.goal.sourcedg.benchmarking;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.Graphs;

public class InducedSubgraphView<V, E> implements Graph<V, E> {

  private Graph<V, E> G;
  private Set<V> V;

  private Set<E> edgeSet;

  public InducedSubgraphView(Graph<V, E> G, Set<V> V) {
    this.G = G;
    this.V = V;
  }

  @Override
  public Set<E> getAllEdges(V sourceVertex, V targetVertex) {
    return G.getAllEdges(sourceVertex, targetVertex);
  }

  @Override
  public E getEdge(V sourceVertex, V targetVertex) {
    return G.getEdge(sourceVertex, targetVertex);
  }

  @Override
  public EdgeFactory<V, E> getEdgeFactory() {
    return G.getEdgeFactory();
  }

  @Override
  public E addEdge(V sourceVertex, V targetVertex) {
    throw new UnsupportedOperationException("Cannot add edges to a subgraph view.");
  }

  @Override
  public boolean addEdge(V sourceVertex, V targetVertex, E e) {
    throw new UnsupportedOperationException("Cannot add edges to a subgraph view.");
  }

  @Override
  public boolean addVertex(V v) {
    throw new UnsupportedOperationException("Cannot add vertices to a subgraph view.");
  }

  @Override
  public boolean containsEdge(V sourceVertex, V targetVertex) {
    return G.containsEdge(sourceVertex, targetVertex);
  }

  @Override
  public boolean containsEdge(E e) {
    return edgeSet().contains(e);
  }

  @Override
  public boolean containsVertex(V v) {
    return V.contains(v);
  }

  @Override
  public Set<E> edgeSet() {
    if (edgeSet == null) {
      edgeSet = new HashSet<>();
      for (V v : V) {
        Set<E> edges = G.edgesOf(v);
        for (E e : edges) {
          V opposite = Graphs.getOppositeVertex(G, e, v);
          if (V.contains(opposite))
            edgeSet.add(e);
        }
      }
    }
    return edgeSet;
  }

  @Override
  public int degreeOf(V vertex) {
    int degree = 0;
    for (V n : Graphs.neighborListOf(G, vertex))
      if (V.contains(n))
        degree++;
    return degree;
  }

  @Override
  public Set<E> edgesOf(V vertex) {
    return G.edgesOf(vertex).stream()
        .filter(e -> V.contains(Graphs.getOppositeVertex(G, e, vertex)))
        .collect(Collectors.toSet());
  }

  @Override
  public int inDegreeOf(V vertex) {
    return degreeOf(vertex);
  }

  @Override
  public Set<E> incomingEdgesOf(V vertex) {
    Set<E> edges = new HashSet<>(G.incomingEdgesOf(vertex));
    edges.retainAll(edgeSet());
    return edges;
  }

  @Override
  public int outDegreeOf(V vertex) {
    return degreeOf(vertex);
  }

  @Override
  public Set<E> outgoingEdgesOf(V vertex) {
    Set<E> edges = new HashSet<>(G.outgoingEdgesOf(vertex));
    edges.retainAll(edgeSet());
    return edges;
  }

  @Override
  public boolean removeAllEdges(Collection<? extends E> edges) {
    throw new UnsupportedOperationException("Cannot remove edges from a subgraph view.");
  }

  @Override
  public Set<E> removeAllEdges(V sourceVertex, V targetVertex) {
    throw new UnsupportedOperationException("Cannot remove edges from a subgraph view.");
  }

  @Override
  public boolean removeAllVertices(Collection<? extends V> vertices) {
    throw new UnsupportedOperationException("Cannot remove vertices from a subgraph view.");
  }

  @Override
  public E removeEdge(V sourceVertex, V targetVertex) {
    throw new UnsupportedOperationException("Cannot remove edges from a subgraph view.");
  }

  @Override
  public boolean removeEdge(E e) {
    throw new UnsupportedOperationException("Cannot remove edges from a subgraph view.");
  }

  @Override
  public boolean removeVertex(V v) {
    throw new UnsupportedOperationException("Cannot remove vertices from a subgraph view.");
  }

  @Override
  public Set<V> vertexSet() {
    return V;
  }

  @Override
  public V getEdgeSource(E e) {
    return G.getEdgeSource(e);
  }

  @Override
  public V getEdgeTarget(E e) {
    return G.getEdgeTarget(e);
  }

  @Override
  public GraphType getType() {
    return G.getType();
  }

  @Override
  public double getEdgeWeight(E e) {
    return G.getEdgeWeight(e);
  }

  @Override
  public void setEdgeWeight(E e, double weight) {
    throw new UnsupportedOperationException("Cannot set edge weight from a subgraph view.");
  }

}
