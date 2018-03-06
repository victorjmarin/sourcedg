package edu.rit.goal.sourcedg.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.EdgeReversedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Dominators;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.Vertex;

public class CFGDominator {

  public List<SimpleDirectedGraph<Vertex, DefaultEdge>> buildCDG(final Collection<CFG> cfgs) {
    final List<SimpleDirectedGraph<Vertex, DefaultEdge>> result = new ArrayList<>();
    for (final CFG cfg : cfgs) {
      final EdgeReversedGraph<Vertex, Edge> revCfg = new EdgeReversedGraph<>(cfg);
      final Dominators<Vertex, Edge> d = new Dominators<>(revCfg, cfg.getExit());
      result.add(d.getDominatorTree());
      final Hashtable<Vertex, Vertex> idoms = d.getIDoms();
      for (final Vertex v : revCfg.vertexSet()) {
        System.out.println(idoms.get(v) + " -> " + v);
      }
    }
    return result;
  }

  public List<Hashtable<Vertex, Vertex>> getIdoms(final Collection<CFG> cfgs) {
    final List<Hashtable<Vertex, Vertex>> result = new ArrayList<>();
    for (final CFG cfg : cfgs) {
      final EdgeReversedGraph<Vertex, Edge> revCfg = new EdgeReversedGraph<>(cfg);
      final Dominators<Vertex, Edge> d = new Dominators<>(revCfg, cfg.getExit());
      result.add(d.getIDoms());
      final Hashtable<Vertex, Vertex> idoms = d.getIDoms();
      for (final Vertex v : revCfg.vertexSet()) {
        System.out.println(idoms.get(v) + " -> " + v);
      }
    }
    return result;
  }

  public DefaultDirectedGraph<Vertex, Edge> cdg(final DefaultDirectedGraph<Vertex, Edge> cfg,
      final Vertex entry, final Vertex exit) {
    final EdgeReversedGraph<Vertex, Edge> revCfg = new EdgeReversedGraph<>(cfg);
    final Dominators<Vertex, Edge> d = new Dominators<>(revCfg, exit);
    final Hashtable<Vertex, Vertex> idoms = d.getIDoms();

    final DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
    final AllDirectedPaths<Vertex, Edge> paths = new AllDirectedPaths<>(cfg);
    for (final Vertex x : cfg.vertexSet()) {
      for (final Vertex y : cfg.vertexSet()) {
        if (x == y || x == entry || y == entry || x == exit || y == exit)
          continue;
        result.addVertex(x);
        result.addVertex(y);
        final List<GraphPath<Vertex, Edge>> p = paths.getAllPaths(x, y, true, null);
        for (final GraphPath<Vertex, Edge> gp : p) {
          if (!gp.getVertexList().contains(idoms.get(x)))
            result.addEdge(x, y, new Edge(x, y));
        }
      }
    }
    result.addVertex(entry);
    final List<Vertex> isolated =
        result.vertexSet().stream().filter(v -> result.inDegreeOf(v) + result.outDegreeOf(v) == 0)
            .collect(Collectors.toList());
    for (final Vertex v : isolated) {
      if (v == entry || v == exit)
        continue;
      result.addEdge(entry, v, new Edge(entry, v));
    }

    final List<Vertex> entriesToComponents = result.vertexSet().stream()
        .filter(v -> result.inDegreeOf(v) == 0 && v != entry).collect(Collectors.toList());
    for (final Vertex v : entriesToComponents) {
      result.addEdge(entry, v, new Edge(entry, v));
    }

    return result;
  }


}
