package edu.rit.goal.sourcedg.validation;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.github.javaparser.ast.stmt.IfStmt;

import edu.rit.goal.sourcedg.graph.VertexType;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryEdge;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryNode;

public class SubgraphQuery extends DefaultDirectedGraph<SubgraphQueryNode, SubgraphQueryEdge> {
	private static final long serialVersionUID = -513017247218635783L;
	
	public SubgraphQuery(Class<? extends SubgraphQueryEdge> edgeClass) {
		super(edgeClass);
	}
	
	public Set<SubgraphQueryNode> getUndirectedNeighbors(SubgraphQueryNode u) {
		Set<SubgraphQueryNode> set = new HashSet<>();
		
		Set<SubgraphQueryEdge> edges = this.incomingEdgesOf(u);
		for (SubgraphQueryEdge e : edges)
			set.add(e.src);
		
		edges = this.outgoingEdgesOf(u);
		for (SubgraphQueryEdge e : edges)
			set.add(e.tgt);
		
		return set;
	}

	public static SubgraphQuery buildIfNoTerminalQuery() {
		SubgraphQuery ret = new SubgraphQuery(SubgraphQueryEdge.class);
		
		SubgraphQueryNode n1 = ret.new SubgraphQueryNode();
		n1.type = VertexType.CTRL;
		n1.astClass = IfStmt.class;
		ret.addVertex(n1);
		
		SubgraphQueryNode n2 = ret.new SubgraphQueryNode();
		ret.addVertex(n2);
		
		ret.addEdge(n1, n2, ret.new SubgraphQueryEdge(n1, n2, false));
		
		return ret;
	}
	
	public class SubgraphQueryNode {
		VertexType type;
		Class<?> astClass;
	}

	public class SubgraphQueryEdge {
		SubgraphQueryNode src, tgt;
		boolean isPath;

		public SubgraphQueryEdge(SubgraphQueryNode src, SubgraphQueryNode tgt, boolean isPath) {
			super();
			this.src = src;
			this.tgt = tgt;
			this.isPath = isPath;
		}
		
	}
	
}