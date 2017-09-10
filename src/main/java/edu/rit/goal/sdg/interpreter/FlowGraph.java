package edu.rit.goal.sdg.interpreter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.EdgeType;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;

public class FlowGraph {

    private final Set<Vertex> visited = new HashSet<>();

    public final DirectedGraph<Vertex, Edge> graph = new DefaultDirectedGraph<>(Edge.class);

    public FlowGraph(final DirectedGraph<Vertex, Edge> g) {
	buildFlowGraph(g);
    }

    public void buildFlowGraph(final DirectedGraph<Vertex, Edge> g) {
	final Set<Vertex> V = g.vertexSet();
	final List<Vertex> L = V.stream().collect(Collectors.toList());
	final Comparator<Vertex> byId = (final Vertex v1, final Vertex v2) -> {
	    final Integer id1 = v1.getId();
	    final Integer id2 = v2.getId();
	    return id1.compareTo(id2);
	};
	L.sort(byId);
	while (!L.isEmpty()) {
	    final Vertex v = L.remove(0);
	    process(v, L, g, null);
	}
    }

    public void process(final Vertex v, final List<Vertex> L, final DirectedGraph<Vertex, Edge> g,
	    final List<Vertex> ignore) {
	final Comparator<Vertex> byId = (final Vertex v1, final Vertex v2) -> {
	    final Integer id1 = v1.getId();
	    final Integer id2 = v2.getId();
	    return id1.compareTo(id2);
	};
	switch (v.getType()) {
	case CTRL_IF:
	    final Set<Edge> edgeSet = g.outgoingEdgesOf(v);
	    final List<Vertex> ctrlTrue = edgeSet.stream().filter(e -> e.getType().equals(EdgeType.CTRL_TRUE))
		    .map(e -> e.getTarget()).sorted(byId).collect(Collectors.toList());
	    // ctrlFalse
	    final List<Vertex> ctrlFalse = edgeSet.stream().filter(e -> e.getType().equals(EdgeType.CTRL_FALSE))
		    .map(e -> e.getTarget()).sorted(byId).collect(Collectors.toList());
	    graph.addVertex(v);
	    if (!ctrlTrue.isEmpty()) {
		final Vertex first = ctrlTrue.get(0);
		graph.addVertex(first);
		graph.addEdge(v, first, new Edge(v, first, EdgeType.CTRL_TRUE));
		for (final Vertex vtx : ctrlTrue) {
		    L.remove(vtx);
		    process(vtx, L, g, ctrlFalse);
		}
	    }
	    if (!ctrlFalse.isEmpty()) {
		graph.addVertex(ctrlFalse.get(0));
		graph.addEdge(v, ctrlFalse.get(0), new Edge(v, ctrlFalse.get(0), EdgeType.CTRL_FALSE));
		for (final Vertex vtx : ctrlFalse) {
		    L.remove(vtx);
		    process(vtx, L, g, null);
		}
	    }
	    break;
	default:
	    if (!L.isEmpty()) {
		graph.addVertex(v);
		int i = 0;
		Vertex v2 = L.get(i);
		while (ignore != null && ignore.contains(v2)) {
		    i++;
		    v2 = L.get(i);
		}
		graph.addVertex(v2);
		graph.addEdge(v, v2, new Edge(v, v2, EdgeType.CTRL_TRUE));
	    }
	}
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
	return graph.vertexSet().stream().filter(v -> VertexType.ENTRY.equals(v.getType())).findFirst().get();
    }

    public void traverse(final SysDepGraph sdg) {
	final List<Vertex> entryVtcs = sdg.vertexSet().stream().filter(v -> v.getType().equals(VertexType.ENTRY))
		.collect(Collectors.toList());
	final Comparator<Vertex> byId = (final Vertex v1, final Vertex v2) -> {
	    final Integer id1 = v1.getId();
	    final Integer id2 = v2.getId();
	    return id1.compareTo(id2);
	};
	final Vertex mainVtx = entryVtcs.stream().min(byId).get();
	dfs(sdg, mainVtx);
    }

    private void dfs(final SysDepGraph sdg, final Vertex v) {
	visited.add(v);
	if (!v.getType().isActualParam()) {
	    System.out.println(v);
	}
	final Set<Edge> s = sdg.outgoingEdgesOf(v).stream().filter(e -> e.getType().isCtrl())
		.collect(Collectors.toSet());
	final Comparator<Edge> byId = (final Edge e1, final Edge e2) -> {
	    final Integer id1 = e1.getTarget().getId();
	    final Integer id2 = e2.getTarget().getId();
	    return id1.compareTo(id2);
	};
	final SortedSet<Edge> ss = new TreeSet<>(byId);
	ss.addAll(s);
	for (final Edge e : ss) {
	    final Vertex target = e.getTarget();
	    if (!visited.contains(target)) {
		dfs(sdg, target);
	    }
	}
    }

}
