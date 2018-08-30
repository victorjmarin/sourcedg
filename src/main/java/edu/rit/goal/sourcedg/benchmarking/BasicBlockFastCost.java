package edu.rit.goal.sourcedg.benchmarking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.traverse.ClosestFirstIterator;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.Vertex;

public class BasicBlockFastCost {

  public Graph<Vertex, Edge> g1, g2;
  private Table<Vertex, Vertex, Double> costMatrix;
  private int hops;
  private double beta;

  public BasicBlockFastCost(Graph<Vertex, Edge> g1, Graph<Vertex, Edge> g2, double beta, int hops) {
    this.g1 = new AsUndirectedGraph<>(g1);
    this.g2 = new AsUndirectedGraph<>(g2);
    this.beta = beta;
    this.hops = hops;
    this.costMatrix = ArrayTable.create(this.g1.vertexSet(), this.g2.vertexSet());
    buildCostMatrix();
  }

  public Table<Vertex, Vertex, Double> getCostMatrix() {
    return costMatrix;
  }

  private Map<Vertex, InducedSubgraphView<Vertex, Edge>> khops = new HashMap<>();

  private void buildCostMatrix() {
    for (Vertex v : g1.vertexSet()) {
      InducedSubgraphView<Vertex, Edge> s1 = kHopsSubgraph(g1, v, hops);
      for (Vertex u : g2.vertexSet()) {
        InducedSubgraphView<Vertex, Edge> s2 = khops.get(u);
        if (s2 == null) {
          s2 = kHopsSubgraph(g2, u, hops);
          khops.put(u, s2);
        }
        double topological = topologicalCost(s1.edgesOf(v), s2.edgesOf(u));
        double semantic = semanticCost(v, u);
        Cost cost = new Cost(beta, topological, semantic);
        costMatrix.put(v, u, cost.getCombined());
      }
    }
  }

  private InducedSubgraphView<Vertex, Edge> kHopsSubgraph(Graph<Vertex, Edge> g, Vertex v, int k) {
    ClosestFirstIterator<Vertex, Edge> cfi = new ClosestFirstIterator<>(g, v, k);
    Set<Vertex> kHopsNodes = Sets.newHashSet(cfi);
    return new InducedSubgraphView<>(g, kHopsNodes);
  }

  // once inside a basic block we only care about labels.
  private double topologicalCost(Set<Edge> e1, Set<Edge> e2) {
    return 0;
    // List<String> lbls1 = e1.stream().map(e ->
    // e.getType().toString()).collect(Collectors.toList());
    // List<String> lbls2 = e2.stream().map(e ->
    // e.getType().toString()).collect(Collectors.toList());
    // HashMultiset<String> ms1 = HashMultiset.create(lbls1);
    // HashMultiset<String> ms2 = HashMultiset.create(lbls2);
    // Multiset<String> result =
    // Multisets.filter(Multisets.sum(ms1, ms2), Predicates.in(Multisets.intersection(ms1, ms2)));
    // double graphLength = e1.size() + e2.size();
    // double div = result.size() / graphLength;
    // double cost = 1 - div;
    // return cost;
  }

  public static double semanticCost(Vertex v1, Vertex v2) {
    Set<String> v1Types = new HashSet<>(), v2Types = new HashSet<>();
    v1Types.add(v1.getType().toString());
    v1Types.addAll(v1.getSubtypes());
    v2Types.add(v2.getType().toString());
    v2Types.addAll(v2.getSubtypes());
    return jaccardDistance(v1Types, v2Types);
  }

  public static double jaccardIndex(Set<String> s1, Set<String> s2) {
    if (s1.isEmpty() && s2.isEmpty())
      return 1;
    Set<String> intersection = new HashSet<>(s1);
    intersection.retainAll(s2);

    Set<String> union = new HashSet<>(s1);
    union.addAll(s2);

    return (double) intersection.size() / union.size();
  }

  public static double jaccardDistance(Set<String> s1, Set<String> s2) {
    return 1 - jaccardIndex(s1, s2);
  }

}
