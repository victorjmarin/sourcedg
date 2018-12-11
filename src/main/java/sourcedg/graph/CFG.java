package sourcedg.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.jgrapht.graph.DefaultDirectedGraph;

public class CFG extends DefaultDirectedGraph<Vertex, Edge> {

  /**
   * 
   */
  private static final long serialVersionUID = -85173637837652634L;

  public CFG() {
    super(Edge.class);
  }

  public Vertex getEntry() {
    return vertexSet().stream()
        .filter(v -> VertexType.ENTRY.equals(v.getType()) || VertexType.INIT.equals(v.getType()))
        .findFirst().orElse(null);
  }

  public Vertex getExit() {
    final List<Vertex> vtcs = new ArrayList<>(vertexSet());
    final Comparator<Vertex> cmp = (v1, v2) -> Long.compare(v2.getId(), v1.getId());
    Collections.sort(vtcs, cmp);
    return vtcs.get(0);
  }

  public Vertex getVertexWithId(final int id) {
    final Optional<Vertex> v = vertexSet().stream().filter(u -> u.getId() == id).findFirst();
    if (v.isPresent())
      return v.get();
    return null;
  }

  public int cyclomaticComplexity() {
    return edgeSet().size() - vertexSet().size() + 2;
  }

}
