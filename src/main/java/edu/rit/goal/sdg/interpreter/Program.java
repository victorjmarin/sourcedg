package edu.rit.goal.sdg.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.interpreter.stmt.CtrlVertex;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class Program {

    SysDepGraph sdg;
    // Temporal CFG
    DefaultDirectedGraph<Vertex, Edge> cfg;
    Set<Vertex> Vc;
    Map<String, LinkedHashSet<Vertex>> P;
    Deque<CtrlVertex> C;
    String m;
    // Mapping from method name to CFG
    Map<String, DirectedGraph<Vertex, Edge>> F;
    Stmt s;
    List<Stmt> defers = new LinkedList<>();

    public Program(final Stmt s) {
	sdg = new SysDepGraph();
	cfg = new DefaultDirectedGraph<>(Edge.class);
	Vc = new HashSet<>();
	P = new HashMap<>();
	C = new ArrayDeque<CtrlVertex>();
	F = new HashMap<>();
	this.s = s;
    }

    public Program(final SysDepGraph sdg, final Set<Vertex> Vc, final Map<String, LinkedHashSet<Vertex>> P,
	    final Deque<CtrlVertex> C, final Stmt s) {
	super();
	this.sdg = sdg;
	cfg = null;
	this.Vc = Vc;
	this.P = P;
	this.C = C;
	this.s = s;
    }

    public Program(final SysDepGraph sdg, final DefaultDirectedGraph<Vertex, Edge> cfg, final Set<Vertex> Vc,
	    final Map<String, LinkedHashSet<Vertex>> P, final Map<String, DirectedGraph<Vertex, Edge>> F,
	    final Deque<CtrlVertex> C, final String m, final List<Stmt> defers, final Stmt s) {
	super();
	this.sdg = sdg;
	this.cfg = cfg;
	this.Vc = Vc;
	this.P = P;
	this.F = F;
	this.C = C;
	this.m = m;
	this.defers = defers;
	this.s = s;
    }

    public DefaultDirectedGraph<Vertex, Edge> clonedCfg() {
	final DefaultDirectedGraph<Vertex, Edge> result = new DefaultDirectedGraph<>(Edge.class);
	for (final Vertex v : cfg.vertexSet()) {
	    result.addVertex(v);
	}
	for (final Edge e : cfg.edgeSet()) {
	    result.addEdge(e.getSource(), e.getTarget(), new Edge(e.getSource(), e.getTarget(), e.getType()));
	}
	return result;
    }

	public SysDepGraph getSdg() {
		return sdg;
	}

}
