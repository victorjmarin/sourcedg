package edu.rit.goal.sdg.interpreter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jgrapht.DirectedGraph;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;

public class FlowGraph {

  public DirectedGraph<Vertex, Edge> graph;

  public FlowGraph(final DirectedGraph<Vertex, Edge> g) {
    graph = g;
  }

  public Map<String, List<Vertex>> getVerticesByUse() {
    final Map<String, List<Vertex>> result = new HashMap<>();
    for (final Vertex v : graph.vertexSet()) {
      final Set<String> uses = v.getReadingVariables();
      if (uses != null) {
        for (final String s : uses) {
          List<Vertex> l = result.get(s);
          if (l == null) {
            l = new LinkedList<>();
            l.add(v);
            result.put(s, l);
          } else {
            l.add(v);
          }
        }
      }
    }
    return result;
  }

  public Map<String, List<Vertex>> getVerticesByDef() {
    final Map<String, List<Vertex>> result = new HashMap<>();
    for (final Vertex v : graph.vertexSet()) {
      final String def = v.getAssignedVariable();
      if (def != null) {
        List<Vertex> l = result.get(def);
        if (l == null) {
          l = new LinkedList<>();
          l.add(v);
          result.put(def, l);
        } else {
          l.add(v);
        }
      }
    }
    return result;
  }

  public Vertex getEntryVertex() {
    return graph.vertexSet().stream().filter(v -> VertexType.ENTRY.equals(v.getType())).findFirst()
        .get();
  }

}
