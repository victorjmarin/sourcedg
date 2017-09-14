package edu.rit.goal.sdg.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;

import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.interpreter.stmt.CtrlVertex;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class Program {

    SysDepGraph sdg;
    DefaultDirectedGraph<Vertex, Edge> cfg;
    Set<Vertex> Vc;
    Map<String, LinkedHashSet<Vertex>> P;
    Deque<CtrlVertex> C;
    Stmt s;

    public Program(final Stmt s) {
	sdg = new SysDepGraph();
	cfg = new DefaultDirectedGraph<>(Edge.class);
	Vc = new HashSet<>();
	P = new HashMap<>();
	C = new ArrayDeque<CtrlVertex>();
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
	    final Map<String, LinkedHashSet<Vertex>> P, final Deque<CtrlVertex> C, final Stmt s) {
	super();
	this.sdg = sdg;
	this.cfg = cfg;
	this.Vc = Vc;
	this.P = P;
	this.C = C;
	this.s = s;
    }

}
