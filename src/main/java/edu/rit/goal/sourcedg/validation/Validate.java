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
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
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
				
				SubgraphQueryNode ifStmt = null;
				Node thenNode = getFirstNode(i.getThenStmt());
				if (thenNode != null) {
					ifStmt = q.addVertex(null, thenNode);
					q.addEdge(main, ifStmt, false);
				}
				
				SubgraphQueryNode elseStmt = null;
				Optional<Statement> els = i.getElseStmt();
				if (els.isPresent()) {
					Node elseNode = getFirstNode(els.get());
					if (elseNode != null) {
						elseStmt = q.addVertex(null, elseNode);
						q.addEdge(main, elseStmt, false);
					}
				}
				
				Node nextNode = getNext(i);
				if (nextNode != null) {
					SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
					if (ifStmt != null)
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
				
				Node first = getFirstNode(w.getBody()), last = getLastNode(w.getBody());
				if (first != null && last != null && first.equals(last)) {
					SubgraphQueryNode whileStmt = q.addVertex(null, first);
					q.addEdge(whileStmt, main, false);
					q.addEdge(main, whileStmt, false);
				} else if (first != null && last != null) {
					SubgraphQueryNode whileFirstStmt = q.addVertex(null, first);
					SubgraphQueryNode whileLastStmt = q.addVertex(null, last);
					
					q.addEdge(main, whileFirstStmt, false);
					q.addEdge(whileLastStmt, main, false);
					q.addEdge(whileFirstStmt, whileLastStmt, true);
				} else
					q.addEdge(main, main, false);
				
				Node nextNode = getNext(w);
				if (nextNode != null) {
					SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
					q.addEdge(main, nextStmt, false);
				}
				
				match(g, q);
			}
			
			// Get all do-while nodes.
			for (DoStmt d : get(g, DoStmt.class)) {
				SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
				SubgraphQueryNode main = q.addVertex(VertexType.CTRL, d);
				
				Node first = getFirstNode(d.getBody()), last = getLastNode(d.getBody());
				if (first != null && last != null && first.equals(last)) {
					SubgraphQueryNode doWhileStmt = q.addVertex(null, first);
					q.addEdge(doWhileStmt, main, false);
					q.addEdge(main, doWhileStmt, false);
				} else if (first != null && last != null) {
					SubgraphQueryNode doWhileFirstStmt = q.addVertex(null, first);
					SubgraphQueryNode doWhileLastStmt = q.addVertex(null, last);
					
					q.addEdge(doWhileLastStmt, main, false);
					q.addEdge(main, doWhileFirstStmt, false);
					q.addEdge(doWhileFirstStmt, doWhileLastStmt, true);
				} else
					q.addEdge(main, main, false);
				
				Node nextNode = getNext(d);
				if (nextNode != null) {
					SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
					q.addEdge(main, nextStmt, false);
				}
				
				match(g, q);
			}
			
			// Get all for nodes.
			for (ForStmt f : get(g, ForStmt.class)) {
				SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
				SubgraphQueryNode main = q.addVertex(VertexType.CTRL, f);
				
				NodeList<Expression> init = f.getInitialization();
				NodeList<Expression> update = f.getUpdate();
				
				Node first = getFirstNode(f.getBody()), last = getLastNode(f.getBody());
				Node firstInit = null, lastInit = null;
				if (init.size() == 1) {
					firstInit = getFirstNode(init.get(0));
					lastInit = getLastNode(init.get(0));
				} else if (init.size() > 1) {
					firstInit = getFirstNode(init.get(0));
					lastInit = getLastNode(init.get(init.size() - 1));
				}
				Node firstUpdate = null, lastUpdate = null;
				if (!update.isEmpty()) {
					firstUpdate = getFirstNode(update.get(0));
					lastUpdate = getLastNode(update.get(update.size() - 1));
				}
				
				if (firstInit != null && lastInit != null && firstInit.equals(lastInit)) {
					SubgraphQueryNode initStmt = q.addVertex(null, firstInit);
					q.addEdge(initStmt, main, false);
				} else if (firstInit != null && lastInit != null) {
					SubgraphQueryNode initStmt = q.addVertex(null, firstInit);
					SubgraphQueryNode lastStmt = q.addVertex(null, lastInit);
					q.addEdge(initStmt, lastStmt, true);
					q.addEdge(lastStmt, main, false);
				}
				
				SubgraphQueryNode updateStmt = null;
				if (firstUpdate != null && lastUpdate != null && firstUpdate.equals(lastUpdate)) {
					updateStmt = q.addVertex(null, firstUpdate);
					q.addEdge(updateStmt, main, false);
				} else if (firstUpdate != null && lastInit != null) {
					updateStmt = q.addVertex(null, firstUpdate);
					SubgraphQueryNode lastStmt = q.addVertex(null, lastUpdate);
					q.addEdge(updateStmt, lastStmt, true);
					q.addEdge(lastStmt, main, false);
				}
				
				if (first != null && last != null && first.equals(last)) {
					SubgraphQueryNode forStmt = q.addVertex(null, first);
					q.addEdge(main, forStmt, false);
					
					if (updateStmt != null)
						q.addEdge(forStmt, updateStmt, false);
					else
						q.addEdge(forStmt, main, false);
				} else if (first != null && last != null) {
					SubgraphQueryNode forFirstStmt = q.addVertex(null, first);
					SubgraphQueryNode forLastStmt = q.addVertex(null, last);
					
					q.addEdge(main, forFirstStmt, false);
					q.addEdge(forFirstStmt, forLastStmt, true);
					
					if (updateStmt != null)
						q.addEdge(forLastStmt, updateStmt, false);
					else
						q.addEdge(forLastStmt, main, false);
				} else
					q.addEdge(main, main, false);
				
				Node nextNode = getNext(f);
				if (nextNode != null) {
					SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
					q.addEdge(main, nextStmt, false);
				}
				
				match(g, q);
			}
			
			// TODO 0: Enhanced fors, switches, try-catch-finally, break, continue.
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
		if (n.getClass().equals(EmptyStmt.class))
			return null;
		else if (n.getClass().equals(ExpressionStmt.class))
			return getFirstNode(((ExpressionStmt) n).getExpression());
		else if (n.getClass().equals(VariableDeclarationExpr.class))
			return getFirstNode(((VariableDeclarationExpr) n).getVariable(0));
		else if (n.getClass().equals(BlockStmt.class)) {
			Node ret = null;
			BlockStmt b = (BlockStmt) n;
			for (int i = 0; ret == null && i < b.getStatements().size(); i++)
				ret = getFirstNode(b.getStatement(i));
			return ret;
		}
		else
			return n;
	}
	
	private static Node getLastNode(Node n) {
		if (n.getClass().equals(EmptyStmt.class))
			return null;
		else if (n.getClass().equals(ExpressionStmt.class))
			return getLastNode(((ExpressionStmt) n).getExpression());
		else if (n.getClass().equals(VariableDeclarationExpr.class)) {
			VariableDeclarationExpr v = (VariableDeclarationExpr) n;
			return getLastNode(v.getVariable(v.getVariables().size() - 1));
		} else if (n.getClass().equals(BlockStmt.class)) {
			Node ret = null;
			BlockStmt b = (BlockStmt) n;
			for (int i = b.getStatements().size() - 1; ret == null && i >= 0; i--)
				ret = getLastNode(b.getStatement(i));
			return ret;
		}
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
