package edu.rit.goal.sourcedg.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryEdge;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryNode;

public class SubgraphMatching {
	
	public Set<Map<SubgraphQueryNode, Vertex>> subgraphMatching(CFG g, SubgraphQuery q) {
		Set<Map<SubgraphQueryNode, Vertex>> sol = new HashSet<>();
		
		// Compute search space.
		Map<SubgraphQueryNode, List<Vertex>> ss = new HashMap<>();
		boolean hasEmptySS = false;
		SubgraphQueryNode firstNode = null;
		
		for (Iterator<SubgraphQueryNode> it = q.vertexSet().iterator(); !hasEmptySS && it.hasNext(); ) {
			SubgraphQueryNode u = it.next();
			List<Vertex> l = new ArrayList<>();
			ss.put(u, l);
			
			for (Vertex v : g.vertexSet())
				// Ensure that the type of the query node and whatever is coming from the CFG matches at the AST level.
				if (((g.getEdge(v, v) != null && q.getEdge(u, u) != null) || (g.getEdge(v, v) == null && q.getEdge(u, u) == null)) &&
						(u.type == null || v.getType().equals(u.type)) && (u.astClass == null || v.getAst().getClass().equals(u.astClass)))
					l.add(v);
			hasEmptySS = l.isEmpty();
			
			if (firstNode == null || ss.get(firstNode).size() > l.size())
				firstNode = u;
		}
		
		if (!hasEmptySS)
			backtrackSearch(0, g, q, ss, computeOrder(q, ss, firstNode), new HashMap<>(), sol);
		
		return sol;
	}
	
	private List<SubgraphQueryNode> computeOrder(SubgraphQuery query, Map<SubgraphQueryNode, List<Vertex>> searchSpace, SubgraphQueryNode first) {
		List<SubgraphQueryNode> order = new ArrayList<>();
		order.add(first);
		
		Set<SubgraphQueryNode> toTreat = new HashSet<>(query.getUndirectedNeighbors(first)), treated = new HashSet<>();
		treated.add(first);
		
		while (toTreat.size() > 0) {
			SubgraphQueryNode next = null;
			double minCost = Double.MAX_VALUE;
			
			for (SubgraphQueryNode u : toTreat) {
				Set<SubgraphQueryNode> intersection = new HashSet<>(query.getUndirectedNeighbors(u));
				intersection.retainAll(treated);
				
				double cost = searchSpace.get(u).size() * Math.pow(0.5, intersection.size());
				if (cost < minCost) {
					next = u;
					minCost = cost;
				}
			}
			
			order.add(next);
			treated.add(next);
			
			toTreat.addAll(query.getUndirectedNeighbors(next));
			toTreat.removeAll(order);
		}
		
		return order;
	}
	
	private void backtrackSearch(int i, CFG g, SubgraphQuery query, Map<SubgraphQueryNode, List<Vertex>> searchSpace, 
			List<SubgraphQueryNode> order, Map<SubgraphQueryNode, Vertex> currentSolution, Set<Map<SubgraphQueryNode, Vertex>> solutions) {
		if (i == order.size())
			solutions.add(new HashMap<>(currentSolution));
		else {
			SubgraphQueryNode u = order.get(i);
			for (Vertex v : searchSpace.get(u))
				if (canMap(u, query, v, g, currentSolution)) {
					currentSolution.put(u, v);
					backtrackSearch(i + 1, g, query, searchSpace, order, currentSolution, solutions);
					currentSolution.remove(u);
				}
		}
	}
	
	private boolean canMap(SubgraphQueryNode u, SubgraphQuery query, Vertex v, CFG g, Map<SubgraphQueryNode, Vertex> currentSolution) {
		boolean canMap = true;
		
		Set<SubgraphQueryNode> neigh = query.getUndirectedNeighbors(u);
		neigh.retainAll(currentSolution.keySet());
		
		for (Iterator<SubgraphQueryNode> nIt = neigh.iterator(); canMap && nIt.hasNext(); ) {
			SubgraphQueryNode n = nIt.next();
			Vertex o = currentSolution.get(n);
			
			SubgraphQueryEdge queryEdge = null;
			GraphPath<Vertex, Edge> dataPath = null;
			
			// From u to n.
			if (query.containsEdge(u, n)) {
				queryEdge = query.getEdge(u, n);
				dataPath = DijkstraShortestPath.findPathBetween(g, v, o);
			} else {
				queryEdge = query.getEdge(n, u);
				dataPath = DijkstraShortestPath.findPathBetween(g, o, v);
			}
			
			canMap = canMap && dataPath != null && (!queryEdge.isPath ? dataPath.getLength() == 1 : dataPath.getLength() > 1);
		}
		
		// TODO 0: Induced?
//		Set<SubgraphQueryNode> rest = new HashSet<>(currentSolution.keySet());
//		rest.removeAll(neigh);
//		
//		for (Iterator<SubgraphQueryNode> rIt = rest.iterator(); canMap && rIt.hasNext(); ) {
//			SubgraphQueryNode r = rIt.next();
//			Vertex o = currentSolution.get(r);
//			
//			// TODO 0: Are these paths of just simple edges.
//			GraphPath<Vertex, Edge> dataPath = DijkstraShortestPath.findPathBetween(g, v, o);
//			canMap = canMap && dataPath == null;
//			
//			dataPath = DijkstraShortestPath.findPathBetween(g, o, v);
//			canMap = canMap && dataPath == null;
//		}
		
		return canMap;
	}
	
}
