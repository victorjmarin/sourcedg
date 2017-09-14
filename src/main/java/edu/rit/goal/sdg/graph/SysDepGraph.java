package edu.rit.goal.sdg.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;

import edu.rit.goal.sdg.interpreter.FlowGraph;
import edu.rit.goal.sdg.interpreter.Interpreter;

public class SysDepGraph extends DefaultDirectedGraph<Vertex, Edge> {

    private static final long serialVersionUID = 5502017877788689016L;

    private boolean hasDataFlow = false;
    private final List<Vertex> vertices;

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

    public void computeDataFlow() {
	if (!hasDataFlow) {
	    hasDataFlow = true;
	    final Map<String, DirectedGraph<Vertex, Edge>> methodSubgraphs = getMethodSubgraphs();
	    for (final Entry<String, DirectedGraph<Vertex, Edge>> e : methodSubgraphs.entrySet()) {
		final FlowGraph fg = new FlowGraph(e.getValue());
		final Map<String, List<Vertex>> verticesByDef = fg.getVerticesByDef();
		final Map<String, List<Vertex>> verticesByUse = fg.getVerticesByUse();
		for (final Entry<String, List<Vertex>> vbu : verticesByUse.entrySet()) {
		    final String use = vbu.getKey();
		    final List<Vertex> def = verticesByDef.get(use);
		    for (final Vertex useVtx : vbu.getValue()) {
			// No def found for use. Create initial state vtx
			if (def == null) {
			    final Vertex v = new Vertex(Interpreter.VTX_ID++, VertexType.INITIAL_STATE, use);
			    v.setAssignedVariable(use);
			    addVertex(v);
			    addEdge(v, useVtx, new Edge(v, useVtx, EdgeType.DATA));
			    // Add ctrl edge w.r.t. entry vtx
			    final Vertex entryVtx = fg.getEntryVertex();
			    addEdge(entryVtx, v, new Edge(entryVtx, v, EdgeType.CTRL_TRUE));
			} else {
			    for (final Vertex defVtx : def) {
				// Find all paths from the def to the use vtx
				final AllDirectedPaths<Vertex, Edge> adp = new AllDirectedPaths<>(fg.graph);
				final List<GraphPath<Vertex, Edge>> paths = adp.getAllPaths(defVtx, useVtx, true,
					Integer.MAX_VALUE);
				// Add edge if we can find one x-clear path
				boolean xClear = false;
				for (final GraphPath<Vertex, Edge> p : paths) {
				    xClear = pathIsXClear(p.getVertexList(), defVtx, useVtx, use);
				    if (xClear) {
					addEdge(defVtx, useVtx, new Edge(defVtx, useVtx, EdgeType.DATA));
					break;
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

    public boolean pathIsXClear(final List<Vertex> path, final Vertex start, final Vertex finish, final String x) {
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

    private final Set<Vertex> visited = new HashSet<>();

    public void makeUndirected(final DirectedGraph<Vertex, Edge> graph) {
	final List<Vertex> entryVtcs = graph.vertexSet().stream().filter(v -> v.getType().equals(VertexType.ENTRY))
		.collect(Collectors.toList());
	final Comparator<Vertex> byId = (final Vertex v1, final Vertex v2) -> {
	    final Integer id1 = v1.getId();
	    final Integer id2 = v2.getId();
	    return id1.compareTo(id2);
	};
	final Vertex mainVtx = entryVtcs.stream().min(byId).get();
	_makeUndirected(graph, mainVtx);
    }

    private void _makeUndirected(final DirectedGraph<Vertex, Edge> graph, final Vertex v) {
	visited.add(v);
	final Set<Edge> s = graph.outgoingEdgesOf(v);
	for (final Edge e : s) {
	    final Vertex target = e.getTarget();
	    graph.addEdge(target, v, new Edge(target, v, e.getType()));
	    if (!visited.contains(target)) {
		_makeUndirected(graph, target);
	    }
	}
    }

    public void dataFlow() {
	if (!hasDataFlow) {
	    hasDataFlow = true;
	    final Map<String, DirectedGraph<Vertex, Edge>> methodSubgraphs = getMethodSubgraphs();
	    for (final Entry<String, DirectedGraph<Vertex, Edge>> e : methodSubgraphs.entrySet()) {
		final Map<String, List<Vertex>> verticesByDef = getVerticesByDef(e.getValue());
		final Map<String, List<Vertex>> verticesByUse = getVerticesByUse(e.getValue());
		for (final Entry<String, List<Vertex>> vbu : verticesByUse.entrySet()) {
		    final String use = vbu.getKey();
		    final List<Vertex> def = verticesByDef.get(use);
		    for (final Vertex useVtx : vbu.getValue()) {
			// No def found for use. Create initial state vtx
			if (def == null) {
			    final Vertex v = new Vertex(Interpreter.VTX_ID++, VertexType.INITIAL_STATE, use);
			    v.setAssignedVariable(use);
			    addVertex(v);
			    addEdge(v, useVtx, new Edge(v, useVtx, EdgeType.DATA));
			    // Add ctrl edge w.r.t. entry vtx
			    final Vertex entryVtx = getEntryVertex(e.getValue());
			    addEdge(entryVtx, v, new Edge(entryVtx, v, EdgeType.CTRL_TRUE));
			} else {
			    for (final Vertex defVtx : def) {
				// Find all paths from the def to the use vtx
				final DirectedGraph<Vertex, Edge> undirected = e.getValue();
				makeUndirected(e.getValue());
				final AllDirectedPaths<Vertex, Edge> adp = new AllDirectedPaths<>(undirected);
				final List<GraphPath<Vertex, Edge>> paths = adp.getAllPaths(defVtx, useVtx, true,
					Integer.MAX_VALUE);
				// Add edge if we can find one x-clear path
				boolean xClear = false;
				for (final GraphPath<Vertex, Edge> p : paths) {
				    xClear = pathIsXClear(p.getVertexList(), defVtx, useVtx, use);
				    if (xClear) {
					addEdge(defVtx, useVtx, new Edge(defVtx, useVtx, EdgeType.DATA));
					break;
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

    public Vertex getEntryVertex(final DirectedGraph<Vertex, Edge> graph) {
	return graph.vertexSet().stream().filter(v -> VertexType.ENTRY.equals(v.getType())).findFirst().get();
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
		g.addEdge(e.getSource(), e.getTarget(), new Edge(e.getSource(), e.getTarget(), e.getType()));
	    }
	    result.put(methodName, g);
	}
	for (final Edge e : callEdges) {
	    addEdge(e.getSource(), e.getTarget(), new Edge(e.getSource(), e.getTarget(), e.getType()));
	}
	return result;
    }

    private Set<Edge> removeCallEdges() {
	final Set<Edge> callEdges = edgeSet().stream().filter(e -> e.getType().equals(EdgeType.CALL)
		|| e.getType().equals(EdgeType.PARAM_IN) || e.getType().equals(EdgeType.PARAM_OUT))
		.collect(Collectors.toSet());
	removeAllEdges(callEdges);
	return callEdges;
    }

}