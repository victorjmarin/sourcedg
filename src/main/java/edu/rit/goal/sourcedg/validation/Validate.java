package edu.rit.goal.sourcedg.validation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.graph.VertexType;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryEdge;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryNode;

public class Validate {

  public static void main(final String[] args) throws Exception {
    final String filename = "era_bcb_sample";
    final ZipFile zip = new ZipFile(filename + ".zip");

    final String chunkp = "validation_chunks/bcb-chunk$.txt";

    IntStream.rangeClosed(1, BCBChunks.CHUNKS).parallel().forEach(i -> {
      try {
        final String chunk = chunkp.replace("$", String.valueOf(i));
        final String[] programPaths = new String(Files.readAllBytes(Paths.get(chunk))).split("\n");

        final BufferedWriter writer = new BufferedWriter(new FileWriter(
            chunk.replace(".txt", ".out").replace("validation_chunks", "validation_out"), true));

        for (final String p : programPaths) {
          final ZipEntry folder = zip.getEntry(p);
          final InputStream is = zip.getInputStream(folder);
          try (BufferedReader buffer = new BufferedReader(new InputStreamReader(is))) {
            final String s = buffer.lines().collect(Collectors.joining("\n"));
            final byte[] encoded = s.getBytes();
            final String programStr = new String(encoded, Charset.forName("UTF-8"));
            try {
              check(programStr);
            } catch (final Exception e) {
              String msg = "null";
              if (e.getMessage() != null)
                msg = e.getMessage();
              final String str = msg + "," + p;
              writer.append(str + "\n");
            }
          }
        }
        writer.close();
        System.out.println("Finished " + i);
      } catch (final Exception e) {
        e.printStackTrace();
      }
    });

    zip.close();
	  
//	  byte[] encoded = Files.readAllBytes(Paths.get(new File(/*"programs/java8/validation/Example.java"*/
//			  "C:/Users/crr/Desktop/era_bcb_sample/2/selected/1858980.java").toURI()));
//	  String programStr = new String(encoded, Charset.forName("UTF-8"));
//	  check(programStr);
  }
  
  private class DisruptInfo {
	  Node disrupt;
	  Node main;
	  Set<Node> impactThen = new HashSet<>(), impactElse = new HashSet<>();
  }
  
  private static boolean disruptFound(Node toSearch, Node ctrlFlowDisrupt) {
	  boolean ret = toSearch == ctrlFlowDisrupt;
	  if (!ret)
		  for (Iterator<Node> it = toSearch.getChildNodes().iterator(); !ret && it.hasNext(); )
			  ret = disruptFound(it.next(), ctrlFlowDisrupt);
	  return ret;
  }
  
  // Takes a node that disrupts the control flow and returns all bottom-up impacted nodes.
  private static DisruptInfo getImpactedStatements(Node ctrlFlowDisrupt) {
	  DisruptInfo info = new Validate().new DisruptInfo();
	  info.disrupt = ctrlFlowDisrupt;
	  
	  Node current = ctrlFlowDisrupt;
	  boolean goOn = true;
	  
	  do {
		  Node n = current.getParentNode().get();
		  if (n.getClass().equals(IfStmt.class)) {
			  IfStmt stmt = (IfStmt) n;
			  if (disruptFound(stmt.getThenStmt(), ctrlFlowDisrupt))
				  info.impactThen.add(n);
			  else
				  info.impactElse.add(n);
		  }
		  
		  // TODO 0: Throw.
		  // TODO 0: Try, catch, finally?
		  if ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) && n.getClass().equals(SwitchStmt.class)) ||
				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
						 n.getClass().equals(WhileStmt.class)) ||
				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
							 n.getClass().equals(DoStmt.class)) ||
				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
						 n.getClass().equals(ForStmt.class)) || 
				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
						 n.getClass().equals(ForeachStmt.class)) ||
				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
						 n.getClass().equals(SwitchStmt.class)) ||
				  ctrlFlowDisrupt.getClass().equals(ReturnStmt.class) && 
				  		(n.getClass().equals(ConstructorDeclaration.class) || n.getClass().equals(MethodDeclaration.class)))
			  goOn = false;
		  current = n;
	  } while (goOn);
	  
	  info.main = current;
	  
	  return info;
  }

  private static void check(final String programStr) throws Exception {
    final PDGBuilder builder = new PDGBuilder();
    builder.build(programStr);
    for (final CFG g : builder.getCfgs()) {
    	// Get all breaks, continues and returns.
    	List<DisruptInfo> disruptions = new ArrayList<>();
    	for (BreakStmt b : get(g, BreakStmt.class))
    		disruptions.add(getImpactedStatements(b));
    	for (ContinueStmt c : get(g, ContinueStmt.class))
    		disruptions.add(getImpactedStatements(c));
    	for (ReturnStmt r :get(g, ReturnStmt.class))
    		disruptions.add(getImpactedStatements(r));
    	
    	for (DisruptInfo i : disruptions) {
    		final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
    		
    		if (i.disrupt.getClass().equals(BreakStmt.class) && !i.main.getClass().equals(SwitchStmt.class)) {
    			final SubgraphQueryNode main = q.addVertex(VertexType.BREAK, i.disrupt);
    			
    			final Node nextNode = getNext(i.main);
    	        if (nextNode != null) {
    	          SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
    	          q.addEdge(main, nextStmt, false);
    	        }
    		} else if (i.disrupt.getClass().equals(BreakStmt.class) && i.main.getClass().equals(SwitchStmt.class)) {
    			// TODO 0: Switch.
    		} else if (i.disrupt.getClass().equals(ContinueStmt.class) && !i.main.getClass().equals(ForStmt.class)) {
    			final SubgraphQueryNode main = q.addVertex(VertexType.CONTINUE, i.disrupt);
    			SubgraphQueryNode conditionStmt = q.addVertex(null, i.main);
  	          	q.addEdge(main, conditionStmt, false);
    		} else if (i.disrupt.getClass().equals(ContinueStmt.class) && i.main.getClass().equals(ForStmt.class)) {
    			final SubgraphQueryNode main = q.addVertex(VertexType.CONTINUE, i.disrupt);
  	          	ForStmt f = (ForStmt) i.main;
    			
    			Node firstUpdate = null;
    	        if (!f.getUpdate().isEmpty())
    	          firstUpdate = getFirstNode(f.getUpdate().get(0));
    	        
    	        if (firstUpdate == null) {
    	        	SubgraphQueryNode conditionStmt = q.addVertex(null, i.main);
      	          	q.addEdge(main, conditionStmt, false);
    	        } else {
    	        	SubgraphQueryNode updateStmt = q.addVertex(null, firstUpdate);
      	          	q.addEdge(main, updateStmt, false);
    	        }	
    		} else if (i.disrupt.getClass().equals(ReturnStmt.class))
    			// TODO 0: Return is just one vertex?
    			q.addVertex(VertexType.RETURN, i.disrupt);
    		
            match(g, q);
    	}
    	
      // Get all if nodes.
      for (final IfStmt i : get(g, IfStmt.class)) {
        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, i);

        SubgraphQueryNode ifStmt = null;
        final Node thenNode = getFirstNode(i.getThenStmt());
        if (thenNode != null) {
          ifStmt = q.addVertex(null, thenNode);
          q.addEdge(main, ifStmt, false);
        }

        SubgraphQueryNode elseStmt = null;
        final Optional<Statement> els = i.getElseStmt();
        if (els.isPresent()) {
          final Node elseNode = getFirstNode(els.get());
          if (elseNode != null) {
            elseStmt = q.addVertex(null, elseNode);
            q.addEdge(main, elseStmt, false);
          }
        }
        
        boolean thenImpactedByDisruptions = false, elseImpactedByDisruptions = false;
        for (Iterator<DisruptInfo> it = disruptions.iterator(); !thenImpactedByDisruptions && !elseImpactedByDisruptions && it.hasNext(); ) {
        	DisruptInfo info = it.next();
        	thenImpactedByDisruptions = thenImpactedByDisruptions || info.impactThen.contains(i);
        	elseImpactedByDisruptions = elseImpactedByDisruptions || info.impactElse.contains(i);
        }

        final Node nextNode = getNext(i);
        if (nextNode != null && (!thenImpactedByDisruptions || !elseImpactedByDisruptions)) {
          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
          if (ifStmt != null && !thenImpactedByDisruptions)
            q.addEdge(ifStmt, nextStmt, true);
          if (elseStmt != null && !elseImpactedByDisruptions)
            q.addEdge(elseStmt, nextStmt, true);
        }

        match(g, q);
      }

      // Get all while and enhanced for nodes.
      List<Statement> whileAndEnhancedForLoops = new ArrayList<>();
      whileAndEnhancedForLoops.addAll(get(g, WhileStmt.class));
      whileAndEnhancedForLoops.addAll(get(g, ForeachStmt.class));
      
      for (final Statement loop : whileAndEnhancedForLoops) {
        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, loop);
        
        boolean hasDisruptions = false;
        for (Iterator<DisruptInfo> it = disruptions.iterator(); !hasDisruptions && it.hasNext(); ) {
        	DisruptInfo info = it.next();
        	hasDisruptions = hasDisruptions || info.main == loop;
        }
        
        Statement body = null;
        if (loop.getClass().equals(WhileStmt.class))
        	body = ((WhileStmt) loop).getBody();
        else
        	body = ((ForeachStmt) loop).getBody();

        final Node first = getFirstNode(body), last = getLastNode(body);
        if (first != null && last != null && first.equals(last)) {
          final SubgraphQueryNode whileStmt = q.addVertex(null, first);
          q.addEdge(main, whileStmt, false);
          if (!hasDisruptions)
        	  q.addEdge(whileStmt, main, false);  
        } else if (first != null && last != null) {
          final SubgraphQueryNode whileFirstStmt = q.addVertex(null, first);
          final SubgraphQueryNode whileLastStmt = q.addVertex(null, last);

          q.addEdge(main, whileFirstStmt, false);
          if (!hasDisruptions) {
        	  q.addEdge(whileLastStmt, main, false);
        	  q.addEdge(whileFirstStmt, whileLastStmt, true);
          }
        } else
          q.addEdge(main, main, false);

        final Node nextNode = getNext(loop);
        if (nextNode != null && !hasDisruptions) {
          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
          q.addEdge(main, nextStmt, false);
        }

        match(g, q);
      }

      // Get all do-while nodes.
      for (final DoStmt d : get(g, DoStmt.class)) {
        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, d);
        
        boolean hasDisruptions = false;
        for (Iterator<DisruptInfo> it = disruptions.iterator(); !hasDisruptions && it.hasNext(); ) {
        	DisruptInfo info = it.next();
        	hasDisruptions = hasDisruptions || info.main == d;
        }

        final Node first = getFirstNode(d.getBody()), last = getLastNode(d.getBody());
        if (first != null && last != null && first.equals(last)) {
          final SubgraphQueryNode doWhileStmt = q.addVertex(null, first);
          if (!hasDisruptions) {
	          q.addEdge(doWhileStmt, main, false);
	          q.addEdge(main, doWhileStmt, false);
          }
        } else if (first != null && last != null) {
          final SubgraphQueryNode doWhileFirstStmt = q.addVertex(null, first);
          final SubgraphQueryNode doWhileLastStmt = q.addVertex(null, last);

          if (!hasDisruptions) {
	          q.addEdge(doWhileLastStmt, main, false);
	          q.addEdge(main, doWhileFirstStmt, false);
	          q.addEdge(doWhileFirstStmt, doWhileLastStmt, true);
          }
        } else
          q.addEdge(main, main, false);

        final Node nextNode = getNext(d);
        if (nextNode != null && !hasDisruptions) {
          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
          q.addEdge(main, nextStmt, false);
        }

        match(g, q);
      }

      // Get all for nodes.
      for (final ForStmt f : get(g, ForStmt.class)) {
        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, f);

        final NodeList<Expression> init = f.getInitialization();
        final NodeList<Expression> update = f.getUpdate();
        
        boolean hasDisruptions = false;
        for (Iterator<DisruptInfo> it = disruptions.iterator(); !hasDisruptions && it.hasNext(); ) {
        	DisruptInfo info = it.next();
        	hasDisruptions = hasDisruptions || info.main == f;
        }

        final Node first = getFirstNode(f.getBody()), last = getLastNode(f.getBody());
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
          final SubgraphQueryNode initStmt = q.addVertex(null, firstInit);
          q.addEdge(initStmt, main, false);
        } else if (firstInit != null && lastInit != null) {
          final SubgraphQueryNode initStmt = q.addVertex(null, firstInit);
          final SubgraphQueryNode lastStmt = q.addVertex(null, lastInit);
          q.addEdge(initStmt, lastStmt, true);
          q.addEdge(lastStmt, main, false);
        }

        SubgraphQueryNode updateStmt = null;
        if (firstUpdate != null && lastUpdate != null && firstUpdate.equals(lastUpdate)) {
          updateStmt = q.addVertex(null, firstUpdate);
          q.addEdge(updateStmt, main, false);
        } else if (firstUpdate != null && lastInit != null) {
          updateStmt = q.addVertex(null, firstUpdate);
          final SubgraphQueryNode lastStmt = q.addVertex(null, lastUpdate);
          q.addEdge(updateStmt, lastStmt, true);
          q.addEdge(lastStmt, main, false);
        }

        if (first != null && last != null && first.equals(last)) {
          final SubgraphQueryNode forStmt = q.addVertex(null, first);
          q.addEdge(main, forStmt, false);

          if (updateStmt != null && !hasDisruptions)
            q.addEdge(forStmt, updateStmt, false);
          else if (!hasDisruptions)
            q.addEdge(forStmt, main, false);
        } else if (first != null && last != null) {
          final SubgraphQueryNode forFirstStmt = q.addVertex(null, first);
          final SubgraphQueryNode forLastStmt = q.addVertex(null, last);

          q.addEdge(main, forFirstStmt, false);
          if (!hasDisruptions)
        	  q.addEdge(forFirstStmt, forLastStmt, true);

          if (updateStmt != null && !hasDisruptions)
            q.addEdge(forLastStmt, updateStmt, false);
          else if (!hasDisruptions)
            q.addEdge(forLastStmt, main, false);
        } else
          q.addEdge(main, main, false);

        final Node nextNode = getNext(f);
        if (nextNode != null && !hasDisruptions) {
          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
          q.addEdge(main, nextStmt, false);
        }

        match(g, q);
      }

      // TODO 0: Switches, try-catch-finally.
    }
  }

  private static <T> List<T> get(final CFG g, final Class<T> clazz) {
    Node entryNode = null;
    for (final Iterator<Vertex> it = g.vertexSet().iterator(); entryNode == null && it.hasNext();) {
      final Vertex v = it.next();
      // EXIT nodes will have null type
      if (v.getType() != null
          && (v.getType().equals(VertexType.ENTRY) || v.getType().equals(VertexType.INIT)))
        entryNode = v.getAst();
    }

    final List<T> ret = new ArrayList<>();

    // entryNode will be null for empty constructors
    if (entryNode != null)
      get(entryNode, ret, clazz);

    return ret;
  }

  private static <T> void get(final Node n, final List<T> list, final Class<T> clazz) {
    if (n.getClass().equals(clazz))
    	list.add(clazz.cast(getFirstNode(n)));

    for (final Node o : n.getChildNodes())
      get(o, list, clazz);
  }

  private static Node getFirstNode(final Node n) {
    if (n.getClass().equals(EmptyStmt.class))
      return null;
    else if (n.getClass().equals(ExpressionStmt.class))
      return getFirstNode(((ExpressionStmt) n).getExpression());
    else if (n.getClass().equals(VariableDeclarationExpr.class))
      return getFirstNode(((VariableDeclarationExpr) n).getVariable(0));
    else if (n.getClass().equals(BlockStmt.class)) {
      Node ret = null;
      final BlockStmt b = (BlockStmt) n;
      for (int i = 0; ret == null && i < b.getStatements().size(); i++)
        ret = getFirstNode(b.getStatement(i));
      return ret;
    } else
      return n;
  }

  private static Node getLastNode(final Node n) {
    if (n.getClass().equals(EmptyStmt.class))
      return null;
    else if (n.getClass().equals(ExpressionStmt.class))
      return getLastNode(((ExpressionStmt) n).getExpression());
    else if (n.getClass().equals(VariableDeclarationExpr.class)) {
      final VariableDeclarationExpr v = (VariableDeclarationExpr) n;
      return getLastNode(v.getVariable(v.getVariables().size() - 1));
    } else if (n.getClass().equals(BlockStmt.class)) {
      Node ret = null;
      final BlockStmt b = (BlockStmt) n;
      for (int i = b.getStatements().size() - 1; ret == null && i >= 0; i--)
        ret = getLastNode(b.getStatement(i));
      return ret;
    } else
      return n;
  }

  private static void match(final CFG g, final SubgraphQuery q) throws Exception {
    final SubgraphMatching match = new SubgraphMatching();
    if (q.vertexSet().isEmpty())
    	throw new Exception("EQ");
    final Set<Map<SubgraphQueryNode, Vertex>> solutions = match.subgraphMatching(g, q);

    if (solutions.isEmpty())
      // Zero solutions
      throw new Exception("ZS");
    else if (solutions.size() > 1)
      // More than one solutions
      throw new Exception("MTOS");
  }

  private static Node getNext(final Node n) throws Exception {
    Node ret = null;

    final Optional<Node> parentOptional = n.getParentNode();
    if (parentOptional.isPresent()) {
      final Node parent = parentOptional.get();
      final int pos = parent.getChildNodes().indexOf(n);

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
