package edu.rit.goal.sdg.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;

import edu.rit.goal.sdg.interpreter.FlowGraph;

public class SysDepGraph extends DefaultDirectedGraph<Vertex, Edge> {

  private static final long serialVersionUID = 5502017877788689016L;

  private boolean hasDataFlow = false;
  private final List<Vertex> vertices;
  private Map<String, DirectedGraph<Vertex, Edge>> cfgs;

  public SysDepGraph() {
    super(Edge.class);
    vertices = new ArrayList<>();
  }

  @Override
  public boolean addVertex(final Vertex v) {
    vertices.add(v);
    return super.addVertex(v);
  }

  @Override
  public boolean removeVertex(final Vertex v) {
    vertices.remove(v);
    return super.removeVertex(v);
  }

  public Edge addEdge(final Vertex source, final Vertex target, final EdgeType type) {
    final Edge edge = new Edge(source, target, type);
    addEdge(source, target, edge);
    return edge;
  }

  public void setCfgs(final Map<String, DirectedGraph<Vertex, Edge>> cfgs) {
    this.cfgs = cfgs;
  }

  public void computeDataFlow(int vtxId) {
    if (!hasDataFlow) {
      hasDataFlow = true;
      for (final Entry<String, DirectedGraph<Vertex, Edge>> e : cfgs.entrySet()) {
        final FlowGraph fg = new FlowGraph(e.getValue());
        final Map<String, List<Vertex>> verticesByDef = fg.getVerticesByDef();
        final Map<String, List<Vertex>> verticesByUse = fg.getVerticesByUse();
        for (final Entry<String, List<Vertex>> vbu : verticesByUse.entrySet()) {
          final String use = vbu.getKey();
          List<Vertex> def = verticesByDef.get(use);
          for (final Vertex useVtx : vbu.getValue()) {
            // No def found for use. Create initial state vtx
            if (def == null) {
              // Prevent method invocations from creating initial states.
              // Check comment in Translator.call for the reason why method
              // invocations get here as dependencies
              if (isMethod(use))
                continue;
              final Vertex v = new Vertex(vtxId++, VertexType.INITIAL_STATE, use);
              v.setAssignedVariable(use);
              final List<Vertex> initList = new LinkedList<>();
              initList.add(v);
              // Update to reuse the same initial state vertex for future
              // references
              verticesByDef.put(use, initList);
              addVertex(v);
              def = verticesByDef.get(use);
              addEdge(v, useVtx, new Edge(v, useVtx, EdgeType.DATA));
              // Add ctrl edge w.r.t. entry vtx
              final Vertex entryVtx = fg.getEntryVertex();
              addEdge(entryVtx, v, new Edge(entryVtx, v, EdgeType.CTRL_TRUE));
            } else {
              for (final Vertex defVtx : def) {
                if (VertexType.INITIAL_STATE.equals(defVtx.getType())) {
                  addEdge(defVtx, useVtx, new Edge(defVtx, useVtx, EdgeType.DATA));
                  continue;
                }

                // Find all paths from the def to the use vtx
                if (hasXClearPath(fg.graph, defVtx, useVtx, use))
                	addEdge(defVtx, useVtx, new Edge(defVtx, useVtx, EdgeType.DATA));

                // Find all paths from the def to the use vtx
//                final AllDirectedPaths<Vertex, Edge> adp = new AllDirectedPaths<>(fg.graph);
//                final List<GraphPath<Vertex, Edge>> paths =
//                    adp.getAllPaths(defVtx, useVtx, true, Integer.MAX_VALUE);
//                // Add edge if we can find one x-clear path
//                boolean xClear = false;
//                for (final GraphPath<Vertex, Edge> p : paths) {
//                  xClear = pathIsXClear(p.getVertexList(), defVtx, useVtx, use);
//                  if (xClear) {
//                    addEdge(defVtx, useVtx, new Edge(defVtx, useVtx, EdgeType.DATA));
//                    break;
//                  }
//                }
              }
            }
          }
        }
      }
    }
  }

  public boolean hasXClearPath(final DirectedGraph<Vertex, Edge> graph, final Vertex src, final Vertex tgt,
      final String use) {
    if (src.equals(tgt))
      return true;

    final Deque<List<Vertex>> incompletePaths = new LinkedList<>();
    final Set<Edge> outgoingEdges = graph.outgoingEdgesOf(src);
    for (final Edge edge : outgoingEdges) {
      final Vertex target = graph.getEdgeTarget(edge);

      final List<Vertex> path = new ArrayList<>();
      path.add(src);
      path.add(target);

      final boolean isXClear = pathIsXClear(path, src, target, use);
      if (isXClear)
        incompletePaths.add(path);

      if (target.equals(tgt) && isXClear)
        return true;
    }

    // Walk through the queue of incomplete paths
    for (List<Vertex> currentIncompletePath =
        null; (currentIncompletePath = incompletePaths.poll()) != null;) {
      final Integer lengthSoFar = currentIncompletePath.size();
      final Vertex leafNode = currentIncompletePath.get(lengthSoFar - 1);

      for (final Edge outEdge : graph.outgoingEdgesOf(leafNode)) {
        final Vertex target = graph.getEdgeTarget(outEdge);

        final List<Vertex> newPath = new ArrayList<>(currentIncompletePath);
        // Make sure this path isn't self-intersecting
        if (newPath.contains(target))
          continue;
        else {
          newPath.add(target);

          final boolean isXClear = pathIsXClear(newPath, src, target, use);

          // If this path reaches a target, mark it.
          if (isXClear) {
        	// Consider further extensions of this path.
            incompletePaths.addFirst(newPath);
            if (target.equals(tgt))
                return true;
          } 
        }
      }
    }

    return false;
  }

  public boolean pathIsXClear(final List<Vertex> path, final Vertex start, final Vertex finish,
      final String x) {
    boolean result = true;
    for (final Vertex v : path) {
      if (!v.equals(start) && !v.equals(finish) && x.equals(v.getAssignedVariable())) {
        result = false;
        break;
      }
    }
    return result;
  }

  public Map<String, List<Vertex>> getVerticesByUse(final DirectedGraph<Vertex, Edge> graph) {
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

  public Map<String, List<Vertex>> getVerticesByDef(final DirectedGraph<Vertex, Edge> graph) {
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

  private boolean isMethod(final String use) {
    final Set<Vertex> entryVtcs = getEntryVertices();
    final Set<String> methodNames =
        entryVtcs.stream().map(v -> v.getLabel()).collect(Collectors.toSet());
    return methodNames.contains(use);
  }

  public Set<Vertex> getEntryVertices() {
    return vertexSet().stream().filter(v -> VertexType.ENTRY.equals(v.getType()))
        .collect(Collectors.toSet());
  }

  public Map<String, SysDepGraph> splitMethodSubgraphs() {
    final Set<Edge> callEdges = removeCallEdges();
    final ConnectivityInspector<Vertex, Edge> conn = new ConnectivityInspector<>(this);
    final List<Set<Vertex>> connSets = conn.connectedSets();
    final Map<String, SysDepGraph> result = new HashMap<>();
    String methodName = null;
    for (final Set<Vertex> s : connSets) {
      final Set<Edge> edges = new HashSet<>();
      for (final Vertex v : s) {
        if (v.getType().equals(VertexType.ENTRY)) {
          methodName = v.getLabel();
        }
        edges.addAll(edgesOf(v));
      }
      final SysDepGraph g = new SysDepGraph();
      for (final Vertex v : s) {
        g.addVertex(v);
      }
      for (final Edge e : edges) {
        g.addEdge(e.getSource(), e.getTarget(),
            new Edge(e.getSource(), e.getTarget(), e.getType()));
      }
      result.put(methodName, g);
    }
    for (final Edge e : callEdges) {
      addEdge(e.getSource(), e.getTarget(), new Edge(e.getSource(), e.getTarget(), e.getType()));
    }
    return result;
  }

  public Map<String, DirectedGraph<Vertex, Edge>> getMethodSubgraphs() {
    final Set<Edge> callEdges = removeCallEdges();
    final ConnectivityInspector<Vertex, Edge> conn = new ConnectivityInspector<>(this);
    final List<Set<Vertex>> connSets = conn.connectedSets();
    final Map<String, DirectedGraph<Vertex, Edge>> result = new HashMap<>();
    String methodName = null;
    for (final Set<Vertex> s : connSets) {
      final Set<Edge> edges = new HashSet<>();
      for (final Vertex v : s) {
        if (v.getType().equals(VertexType.ENTRY)) {
          methodName = v.getLabel();
        }
        edges.addAll(edgesOf(v));
      }
      final DirectedGraph<Vertex, Edge> g = new DefaultDirectedGraph<>(Edge.class);
      for (final Vertex v : s) {
        g.addVertex(v);
      }
      for (final Edge e : edges) {
        g.addEdge(e.getSource(), e.getTarget(),
            new Edge(e.getSource(), e.getTarget(), e.getType()));
      }
      result.put(methodName, g);
    }
    for (final Edge e : callEdges) {
      addEdge(e.getSource(), e.getTarget(), new Edge(e.getSource(), e.getTarget(), e.getType()));
    }
    return result;
  }

  private Set<Edge> removeCallEdges() {
    final Set<Edge> callEdges = edgeSet()
        .stream().filter(e -> e.getType().equals(EdgeType.CALL)
            || e.getType().equals(EdgeType.PARAM_IN) || e.getType().equals(EdgeType.PARAM_OUT))
        .collect(Collectors.toSet());
    removeAllEdges(callEdges);
    return callEdges;
  }

}
