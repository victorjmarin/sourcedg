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

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

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
			Set<IfStmt> ifs = getIfs(g);
			
			for (IfStmt i : ifs) {
				SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
				SubgraphQueryNode main = q.addVertex(VertexType.CTRL, i);
				
				// This needs to have two or three child nodes, the first one if the condition itself.
				if (i.getChildNodes().size() > 3)
					throw new Exception("An if statement with more than three children!");
				else {
					// Is it always a block what we are going to find here?
					SubgraphQueryNode ifStmt = q.addVertex(null, getFirstNode(i.getThenStmt()));
					q.addEdge(main, ifStmt, false);
					
					SubgraphQueryNode elseStmt = null;
					Optional<Statement> els = i.getElseStmt();
					if (els.isPresent()) {
						elseStmt = q.addVertex(null, getFirstNode(els.get()));
						q.addEdge(main, elseStmt, false);
					}
					
					Optional<Node> parentOptional = i.getParentNode();
					if (parentOptional.isPresent()) {
						Node parent = parentOptional.get();
						int pos = parent.getChildNodes().indexOf(i);
						
						if (pos >= 0) {
							if (pos + 1 < parent.getChildNodes().size()) {
								Node nextNode = getFirstNode(parent.getChildNodes().get(pos + 1));
								
								SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
								q.addEdge(ifStmt, nextStmt, true);
								if (elseStmt != null)
									q.addEdge(elseStmt, nextStmt, true);
							}
						} else
							throw new Exception("Position not found!");
					} else
						throw new Exception("An if statement without a parent!");
				}
				
				SubgraphMatching match = new SubgraphMatching();
				Set<Map<SubgraphQueryNode, Vertex>> solutions = match.subgraphMatching(g, q);

				if (solutions.size() != 1)
					throw new Exception("Zero or more than one solution found!");
				else
					System.out.println("We are good!");
			}
		}
	}
	
	private static Set<IfStmt> getIfs(CFG g) {
		Node entryNode = null;
		for (Iterator<Vertex> it = g.vertexSet().iterator(); entryNode == null && it.hasNext(); ) {
			Vertex v = it.next();
			if (v.getType().equals(VertexType.ENTRY))
				entryNode = v.getAst();
		}
		
		Set<IfStmt> ret = new HashSet<>();
		getIfs(entryNode, ret);
		return ret;
	}
	
	private static void getIfs(Node n, Set<IfStmt> ifs) {
		if (n.getClass().equals(IfStmt.class))
			ifs.add((IfStmt) n);
		
		for (Node o : n.getChildNodes())
			getIfs(o, ifs);
	}
	
	private static Node getFirstNode(Node n) {
		if (n.getClass().equals(ExpressionStmt.class))
			return getFirstNode(((ExpressionStmt) n).getExpression());
		else if (n.getClass().equals(BlockStmt.class))
			return getFirstNode(((BlockStmt) n).getStatement(0));
		else
			return n;
	}

}
