package edu.rit.goal.sourcedg.validation;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hamcrest.core.IsEqual;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;

import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.graph.VertexType;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryEdge;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryNode;

public class Validate {

	public static void main(String[] args) throws Exception {
		byte[] encoded = Files.readAllBytes(Paths.get(new File("programs/java8/validation/Example.java").toURI()));
		String programStr = new String(encoded, Charset.forName("UTF-8"));

		PDGBuilder builder = new PDGBuilder();
		builder.build(programStr);
		for (CFG g : builder.getCfgs()) {
			// Get all if nodes.
			for (IfStmt i : get(g, IfStmt.class)) {
				SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
				SubgraphQueryNode main = q.addVertex(VertexType.CTRL, i);
				
				SubgraphQueryNode ifStmt = q.addVertex(null, getFirstNode(i.getThenStmt()));
				q.addEdge(main, ifStmt, false);
				
				SubgraphQueryNode elseStmt = null;
				Optional<Statement> els = i.getElseStmt();
				if (els.isPresent()) {
					elseStmt = q.addVertex(null, getFirstNode(els.get()));
					q.addEdge(main, elseStmt, false);
				}
				
				Node nextNode = getNext(i);
				if (nextNode != null) {
					SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
					q.addEdge(ifStmt, nextStmt, true);
					if (elseStmt != null)
						q.addEdge(elseStmt, nextStmt, true);
				}
				
				match(g, q);
			}
			
			// Get all while nodes.
			for (WhileStmt w : get(g, WhileStmt.class)) {
				SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
				SubgraphQueryNode main = q.addVertex(VertexType.CTRL, w);
				q.addEdge(main, main, false);
				
				SubgraphQueryNode whileStmt = q.addVertex(null, getFirstNode(w.getBody()));
				q.addEdge(main, whileStmt, false);
				q.addEdge(whileStmt, main, true);
				
				Node nextNode = getNext(w);
				if (nextNode != null) {
					SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
					q.addEdge(main, nextStmt, false);
				}
				
				// TODO 0: Why is this retrieving no solutions???
				match(g, q);
			}
		}
	}
	
	private static <T> Set<T> get(CFG g, Class<T> clazz) {
		Node entryNode = null;
		for (Iterator<Vertex> it = g.vertexSet().iterator(); entryNode == null && it.hasNext(); ) {
			Vertex v = it.next();
			if (v.getType().equals(VertexType.ENTRY))
				entryNode = v.getAst();
		}
		
		Set<T> ret = new HashSet<>();
		get(entryNode, ret, clazz);
		return ret;
	}
	
	private static <T> void get(Node n, Set<T> set, Class<T> clazz) {
		if (n.getClass().equals(clazz))
			set.add(clazz.cast(getFirstNode(n)));
		
		for (Node o : n.getChildNodes())
			get(o, set, clazz);
	}
	
	private static Node getFirstNode(Node n) {
		if (n.getClass().equals(ExpressionStmt.class))
			return getFirstNode(((ExpressionStmt) n).getExpression());
		else if (n.getClass().equals(BlockStmt.class))
			return getFirstNode(((BlockStmt) n).getStatement(0));
		else
			return n;
	}
	
	private static void match(CFG g, SubgraphQuery q) throws Exception {
		SubgraphMatching match = new SubgraphMatching();
		Set<Map<SubgraphQueryNode, Vertex>> solutions = match.subgraphMatching(g, q);

		if (solutions.isEmpty())
			throw new Exception("Zero solutions found!");
		else if (solutions.size() > 1)
			throw new Exception("More than one solutions found!");
		else
			System.out.println("We are good!");
	}
	
	private static Node getNext(Node n) throws Exception {
		Node ret = null;
		
		Optional<Node> parentOptional = n.getParentNode();
		if (parentOptional.isPresent()) {
			Node parent = parentOptional.get();
			int pos = parent.getChildNodes().indexOf(n);
			
			if (pos >= 0) {
				if (pos + 1 < parent.getChildNodes().size())
					ret = getFirstNode(parent.getChildNodes().get(pos + 1));
			} else
				throw new Exception("Position not found!");
		} else
			throw new Exception("A statement without a parent!");
		
		return ret;
	}

}
