package edu.rit.goal.sourcedg.benchmarking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm.Matching;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.ClosestFirstIterator;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import edu.rit.goal.graal.Alignment;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.Vertex;

public class BBAlign {

  private BasicBlockCFG cfg1;
  private BasicBlockCFG cfg2;

  public BBAlign(BasicBlockCFG cfg1, BasicBlockCFG cfg2) {
    this.cfg1 = cfg1;
    this.cfg2 = cfg2;
  }

  public Set<Alignment<Vertex>> align() {
    Set<Alignment<Vertex>> alignments = new HashSet<>();

    Graph<BasicBlock, DefaultWeightedEdge> bipartiteGraph =
        new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    for (BasicBlock bb1 : cfg1.vertexSet()) {
      bipartiteGraph.addVertex(bb1);
      for (BasicBlock bb2 : cfg2.vertexSet()) {
        bipartiteGraph.addVertex(bb2);
        double cost = .25 * semanticCost(bb1, bb2) + .75 * topologicalCost(bb1, bb2);
        DefaultWeightedEdge edge = new DefaultWeightedEdge();
        bipartiteGraph.addEdge(bb1, bb2, edge);
        bipartiteGraph.setEdgeWeight(edge, (int) 100 * (1.0 - cost));
      }
    }

    MaximumWeightBipartiteMatching<BasicBlock, DefaultWeightedEdge> matching =
        new MaximumWeightBipartiteMatching<>(bipartiteGraph, cfg1.vertexSet(), cfg2.vertexSet());

    Matching<BasicBlock, DefaultWeightedEdge> result = matching.getMatching();

    for (DefaultWeightedEdge e : result.getEdges()) {
      BasicBlock source = bipartiteGraph.getEdgeSource(e);
      BasicBlock target = bipartiteGraph.getEdgeTarget(e);
      InducedSubgraphView<Vertex, Edge> isv1 =
          new InducedSubgraphView<>(cfg1.getPDG(), Sets.newHashSet(source.getVertices()));
      InducedSubgraphView<Vertex, Edge> isv2 =
          new InducedSubgraphView<>(cfg2.getPDG(), Sets.newHashSet(target.getVertices()));

      BasicBlockFastCost fc = new BasicBlockFastCost(isv1, isv2, 0.75, 2);

      Graph<Vertex, DefaultWeightedEdge> bipGraph =
          new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

      for (Vertex v1 : isv1.vertexSet()) {
        bipGraph.addVertex(v1);
        for (Vertex v2 : isv2.vertexSet()) {
          bipGraph.addVertex(v2);
          double cost = fc.getCostMatrix().get(v1, v2);
          DefaultWeightedEdge edge = new DefaultWeightedEdge();
          bipGraph.addEdge(v1, v2, edge);
          bipGraph.setEdgeWeight(edge, (int) 100 * (1.0 - cost));
        }
      }

      MaximumWeightBipartiteMatching<Vertex, DefaultWeightedEdge> matching2 =
          new MaximumWeightBipartiteMatching<>(bipGraph, isv1.vertexSet(), isv2.vertexSet());

      Matching<Vertex, DefaultWeightedEdge> result2 = matching2.getMatching();

      for (DefaultWeightedEdge edge : result2.getEdges())
        alignments.add(new Alignment<>(bipGraph.getEdgeSource(edge), bipGraph.getEdgeTarget(edge),
            fc.getCostMatrix().get(bipGraph.getEdgeSource(edge), bipGraph.getEdgeTarget(edge))));
    }
    return alignments;
  }

  private double semanticCost(BasicBlock bb1, BasicBlock bb2) {
    List<String> lbls1 = Lists.newArrayList();
    List<String> lbls2 = Lists.newArrayList();
    for (Vertex v : bb1.getVertices()) {
      lbls1.add(v.getType().toString());
      lbls1.addAll(v.getSubtypes());
    }
    for (Vertex v : bb2.getVertices()) {
      lbls2.add(v.getType().toString());
      lbls2.addAll(v.getSubtypes());
    }
    HashMultiset<String> ms1 = HashMultiset.create(lbls1);
    HashMultiset<String> ms2 = HashMultiset.create(lbls2);
    Multiset<String> result =
        Multisets.filter(Multisets.sum(ms1, ms2), Predicates.in(Multisets.intersection(ms1, ms2)));
    double graphLength = lbls1.size() + lbls2.size();
    double sim = result.size() / graphLength;
    double cost = 1 - sim;
    return cost;
  }

  private double topologicalCost2(BasicBlock bb1, BasicBlock bb2) {
    InducedSubgraphView<Vertex, Edge> isv1 =
        new InducedSubgraphView<>(cfg1.getPDG(), Sets.newHashSet(bb1.getVertices()));
    InducedSubgraphView<Vertex, Edge> isv2 =
        new InducedSubgraphView<>(cfg2.getPDG(), Sets.newHashSet(bb2.getVertices()));
    List<String> lbls1 =
        isv1.edgeSet().stream().map(e -> e.getType().toString()).collect(Collectors.toList());
    List<String> lbls2 =
        isv2.edgeSet().stream().map(e -> e.getType().toString()).collect(Collectors.toList());
    HashMultiset<String> ms1 = HashMultiset.create(lbls1);
    HashMultiset<String> ms2 = HashMultiset.create(lbls2);
    Multiset<String> result =
        Multisets.filter(Multisets.sum(ms1, ms2), Predicates.in(Multisets.intersection(ms1, ms2)));
    double graphLength = lbls1.size() + lbls2.size();
    double sim = result.size() / graphLength;
    double cost = 1 - sim;
    return cost;
  }

  // TODO: khopnodes can be memoized for every vertex
  private double topologicalCost(BasicBlock bb1, BasicBlock bb2) {
    Vertex v1 = bb1.getVertices().get(0);
    Vertex v2 = bb2.getVertices().get(0);
    double mincost = 1.0;

    // Find most similar nodes in blocks
    for (Vertex va : bb1.getVertices()) {
      for (Vertex vb : bb2.getVertices()) {
        double cost = BasicBlockFastCost.semanticCost(va, vb);
        if (cost < mincost) {
          mincost = cost;
          v1 = va;
          v2 = vb;
        }
      }
    }

    Set<Vertex> kHopsNodes1 = new HashSet<>();
    AsUndirectedGraph<Vertex, Edge> undg1 = new AsUndirectedGraph<>(cfg1.getPDG());

    ClosestFirstIterator<Vertex, Edge> cfi = new ClosestFirstIterator<>(undg1, v1, 2);
    kHopsNodes1 = Sets.union(kHopsNodes1, Sets.newHashSet(cfi));

    Set<Vertex> kHopsNodes2 = new HashSet<>();
    AsUndirectedGraph<Vertex, Edge> undg2 = new AsUndirectedGraph<>(cfg2.getPDG());
    ClosestFirstIterator<Vertex, Edge> cfi2 = new ClosestFirstIterator<>(undg2, v2, 2);
    kHopsNodes2 = Sets.union(kHopsNodes2, Sets.newHashSet(cfi2));

    InducedSubgraphView<Vertex, Edge> isv1 = new InducedSubgraphView<>(undg1, kHopsNodes1);
    InducedSubgraphView<Vertex, Edge> isv2 = new InducedSubgraphView<>(undg2, kHopsNodes2);
    List<String> lbls1 =
        isv1.edgeSet().stream().map(e -> e.getType().toString()).collect(Collectors.toList());
    List<String> lbls2 =
        isv2.edgeSet().stream().map(e -> e.getType().toString()).collect(Collectors.toList());
    HashMultiset<String> ms1 = HashMultiset.create(lbls1);
    HashMultiset<String> ms2 = HashMultiset.create(lbls2);
    Multiset<String> result =
        Multisets.filter(Multisets.sum(ms1, ms2), Predicates.in(Multisets.intersection(ms1, ms2)));
    double graphLength = lbls1.size() + lbls2.size();
    double sim = result.size() / graphLength;
    double cost = 1 - sim;
    return cost;
  }

}
