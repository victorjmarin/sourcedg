package edu.rit.goal.sourcedg.validation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.SynchronizedSummaryStatistics;

import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.builder.PDGBuilderConfig;

public class Validate {

  public static void main(final String[] args) throws Exception {
    final String filename = "era_bcb_sample";
    final ZipFile zip = new ZipFile(filename + ".zip");

    final String chunkp = "validation_chunks/bcb-chunk$.txt";

    SynchronizedSummaryStatistics globalLocStats = new SynchronizedSummaryStatistics(), globalTimeStats = new SynchronizedSummaryStatistics();
    SynchronizedSummaryStatistics globalNodesStats = new SynchronizedSummaryStatistics(), globalEdgesStats = new SynchronizedSummaryStatistics();
    IntStream.rangeClosed(1, BCBChunks.CHUNKS).parallel().forEach(i -> {
      try {
        final String chunk = chunkp.replace("$", String.valueOf(i));
        final String[] programPaths = new String(Files.readAllBytes(Paths.get(chunk))).split("\n");

        final BufferedWriter writer = new BufferedWriter(new FileWriter(
            chunk.replace(".txt", ".out").replace("validation_chunks", "validation_out"), true));

        SummaryStatistics locStats = new SummaryStatistics(), timeStats = new SummaryStatistics();
        for (final String p : programPaths) {
          final ZipEntry folder = zip.getEntry(p);
          final InputStream is = zip.getInputStream(folder);
          try (BufferedReader buffer = new BufferedReader(new InputStreamReader(is))) {
            final String s = buffer.lines().collect(Collectors.joining("\n"));
            final byte[] encoded = s.getBytes();
            final String programStr = new String(encoded, Charset.forName("UTF-8"));
            try {
            	long tic = System.nanoTime();
              check(programStr, globalNodesStats, globalEdgesStats);
              long toc = System.nanoTime();
              
              locStats.addValue(programStr.split("\n").length);
              globalLocStats.addValue(programStr.split("\n").length);
              timeStats.addValue(toc - tic);
              globalTimeStats.addValue(toc - tic);
            } catch (final Exception e) {
              writer.append("Error in: " + p + "\n");
            }
          }
        }
        writer.append("LOC: " + locStats.getMean() + " +/- " + locStats.getStandardDeviation() + " (" + locStats.getMax() + ")\n");
        writer.append("Time: " + timeStats.getMean() + " +/- " + timeStats.getStandardDeviation() + " (" + timeStats.getMax() + ")\n");
        writer.close();
        System.out.println("Finished " + i);
      } catch (final Exception e) {
        e.printStackTrace();
      }
    });
    zip.close();
    
    System.out.println("Total: " + globalLocStats.getN());
    System.out.println("Nodes: " + globalNodesStats.getMean() + " +/- " + globalNodesStats.getStandardDeviation() + " (" + globalNodesStats.getMax() + ")");
    System.out.println("Edges: " + globalEdgesStats.getMean() + " +/- " + globalEdgesStats.getStandardDeviation() + " (" + globalEdgesStats.getMax() + ")");
    System.out.println("LOC: " + globalLocStats.getMean() + " +/- " + globalLocStats.getStandardDeviation() + " (" + globalLocStats.getMax() + ")");
    System.out.println("Time: " + globalTimeStats.getMean() + " +/- " + globalTimeStats.getStandardDeviation() + " (" + globalTimeStats.getMax() + ")");
	  
//	  byte[] encoded = Files.readAllBytes(Paths.get(new File(
////			  "programs/java8/validation/Example.java"
//			  "C:/Users/crr/Desktop/era_bcb_sample/4/selected/692406.java"
//			  ).toURI()));
//	  String programStr = new String(encoded, Charset.forName("UTF-8"));
//	  check(programStr);
  }
  
//  private class DisruptInfo {
//	  Node disrupt;
//	  Node main;
//	  Set<Node> impactThen = new HashSet<>(), impactElse = new HashSet<>();
//  }
//  
//  private static boolean disruptFound(final Node toSearch, final Node ctrlFlowDisrupt) {
//	  boolean ret = toSearch == ctrlFlowDisrupt;
//	  if (!ret)
//		  for (final Iterator<Node> it = toSearch.getChildNodes().iterator(); !ret && it.hasNext(); )
//			  ret = disruptFound(it.next(), ctrlFlowDisrupt);
//	  return ret;
//  }
  
  // Takes a node that disrupts the control flow and returns all bottom-up impacted nodes.
//  private static DisruptInfo getImpactedStatements(final Node ctrlFlowDisrupt) {
//	  final DisruptInfo info = new Validate().new DisruptInfo();
//	  info.disrupt = ctrlFlowDisrupt;
//	  
//	  Node current = ctrlFlowDisrupt;
//	  boolean goOn = true;
//	  
//	  do {
//		  final Node n = current.getParentNode().get();
//		  if (n.getClass().equals(IfStmt.class)) {
//			  final IfStmt stmt = (IfStmt) n;
//			  if (disruptFound(stmt.getThenStmt(), ctrlFlowDisrupt))
//				  info.impactThen.add(n);
//			  else
//				  info.impactElse.add(n);
//		  }
//		  
//		  // TODO 0: Throw.
//		  // TODO 0: Try, catch, finally?
//		  if ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) && n.getClass().equals(SwitchStmt.class)) ||
//				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
//						 n.getClass().equals(WhileStmt.class)) ||
//				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
//							 n.getClass().equals(DoStmt.class)) ||
//				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
//						 n.getClass().equals(ForStmt.class)) || 
//				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
//						 n.getClass().equals(ForeachStmt.class)) ||
//				  ((ctrlFlowDisrupt.getClass().equals(BreakStmt.class) || ctrlFlowDisrupt.getClass().equals(ContinueStmt.class)) && 
//						 n.getClass().equals(SwitchStmt.class)) ||
//				  ctrlFlowDisrupt.getClass().equals(ReturnStmt.class) && 
//				  		(n.getClass().equals(ConstructorDeclaration.class) || n.getClass().equals(MethodDeclaration.class)))
//			  goOn = false;
//		  current = n;
//	  } while (goOn);
//	  
//	  info.main = current;
//	  
//	  return info;
//  }

  private static void check(final String programStr, SynchronizedSummaryStatistics globalNodesStats, SynchronizedSummaryStatistics globalEdgesStats) throws Exception {
    PDGBuilderConfig config = PDGBuilderConfig.create();
    final PDGBuilder builder = new PDGBuilder(config);
    builder.build(programStr);
    globalNodesStats.addValue(builder.getPDG().vertexSet().size());
    globalEdgesStats.addValue(builder.getPDG().edgeSet().size());
//    for (final CFG g : builder.getCfgs()) {
//    	// TODO 0: Remove!!!!
//    	if (!get(g, SynchronizedStmt.class).isEmpty())
//    		continue;
//    	if (!get(g, ThrowStmt.class).isEmpty())
//    		continue;
//    	if (!get(g, SwitchStmt.class).isEmpty())
//    		continue;
//    	if (!get(g, LocalClassDeclarationStmt.class).isEmpty())
//    		continue;
//    	if (!get(g, ObjectCreationExpr.class).isEmpty())
//    		continue;
//    	if (!get(g, TryStmt.class).isEmpty())
//    		continue;
//    	if (!get(g, AssertStmt.class).isEmpty())
//    		continue;
//    	if (!get(g, LabeledStmt.class).isEmpty())
//    		continue;
//    	boolean withLabels = false;
//    	
//    	// Get all breaks, continues and returns.
//    	final List<DisruptInfo> disruptions = new ArrayList<>();
//    	for (final BreakStmt b : get(g, BreakStmt.class)) {
//    		withLabels = b.getLabel().isPresent();
//    		disruptions.add(getImpactedStatements(b));
//    	}
//    	for (final ContinueStmt c : get(g, ContinueStmt.class)) {
//    		withLabels = c.getLabel().isPresent();
//    		disruptions.add(getImpactedStatements(c));
//    	}
//    	for (final ReturnStmt r :get(g, ReturnStmt.class))
//    		disruptions.add(getImpactedStatements(r));
//    	
//    	if (withLabels)
//    		continue;
//    	
//    	for (final DisruptInfo i : disruptions) {
//    		final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
//    		
//    		if (i.disrupt.getClass().equals(BreakStmt.class) && !i.main.getClass().equals(SwitchStmt.class)) {
//    			final SubgraphQueryNode main = q.addVertex(VertexType.BREAK, i.disrupt);
//    			
//    			final Node nextNode = getNext(i.main);
//    	        if (nextNode != null) {
//    	          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
//    	          q.addPath(main, nextStmt);
//    	        }
//    		} else if (i.disrupt.getClass().equals(BreakStmt.class) && i.main.getClass().equals(SwitchStmt.class)) {
//    			// TODO 0: Switch.
//    		} else if (i.disrupt.getClass().equals(ContinueStmt.class) && !i.main.getClass().equals(ForStmt.class)) {
//    			final SubgraphQueryNode main = q.addVertex(VertexType.CONTINUE, i.disrupt);
//    			final SubgraphQueryNode conditionStmt = q.addVertex(null, i.main);
//  	          	q.addPath(main, conditionStmt);
//    		} else if (i.disrupt.getClass().equals(ContinueStmt.class) && i.main.getClass().equals(ForStmt.class)) {
//    			final SubgraphQueryNode main = q.addVertex(VertexType.CONTINUE, i.disrupt);
//  	          	final ForStmt f = (ForStmt) i.main;
//    			
//    			Node firstUpdate = null;
//    	        if (!f.getUpdate().isEmpty())
//    	          firstUpdate = getFirstNode(f.getUpdate().get(0));
//    	        
//    	        if (firstUpdate == null) {
//    	        	final SubgraphQueryNode conditionStmt = q.addVertex(null, i.main);
//      	          	q.addPath(main, conditionStmt);
//    	        } else {
//    	        	final SubgraphQueryNode updateStmt = q.addVertex(null, firstUpdate);
//      	          	q.addPath(main, updateStmt);
//    	        }	
//    		} else if (i.disrupt.getClass().equals(ReturnStmt.class))
//    			q.addVertex(VertexType.RETURN, i.disrupt);
//    		
//    		final Set<Map<SubgraphQueryNode, Vertex>> solutions = match(g, q);
//    		
//    		// Only for returns.
//    		if (!solutions.isEmpty() && i.disrupt.getClass().equals(ReturnStmt.class))
//    			for (final Map<SubgraphQueryNode, Vertex> sol : solutions)
//    				if (!g.outgoingEdgesOf(sol.values().iterator().next()).isEmpty())
//    					// Return with outgoing edges.
//    					throw new Exception("RWOE");
//    					
//    	}
//    	
//      // Get all if nodes.
//      for (final IfStmt i : get(g, IfStmt.class)) {
//        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
//        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, i);
//
//        SubgraphQueryNode ifStmt = null;
//        final Node thenNode = getFirstNode(i.getThenStmt());
//        if (thenNode != null) {
//          ifStmt = q.addVertex(null, thenNode);
//          q.addPath(main, ifStmt);
//        }
//
//        SubgraphQueryNode elseStmt = null;
//        final Optional<Statement> els = i.getElseStmt();
//        if (els.isPresent()) {
//          final Node elseNode = getFirstNode(els.get());
//          if (elseNode != null) {
//            elseStmt = q.addVertex(null, elseNode);
//            q.addPath(main, elseStmt);
//          }
//        }
//        
//        boolean thenImpactedByDisruptions = false, elseImpactedByDisruptions = false;
//        for (final Iterator<DisruptInfo> it = disruptions.iterator(); (!thenImpactedByDisruptions || !elseImpactedByDisruptions) && it.hasNext(); ) {
//        	final DisruptInfo info = it.next();
//        	thenImpactedByDisruptions = thenImpactedByDisruptions || info.impactThen.contains(i);
//        	elseImpactedByDisruptions = elseImpactedByDisruptions || info.impactElse.contains(i);
//        }
//
//        final Node nextNode = getNext(i);
//        SubgraphQueryNode nextStmt = null;
//        if (nextNode != null)
//        	nextStmt = q.addVertex(null, nextNode);
//        
//        boolean removeNode = true;
//        if (nextStmt != null && ifStmt != null && !thenImpactedByDisruptions) {
//          q.addPath(ifStmt, nextStmt);
//          removeNode = false;
//        }
//        
//        if (nextStmt != null && elseStmt != null && !elseImpactedByDisruptions) {
//            q.addPath(elseStmt, nextStmt);
//            removeNode = false;
//        } else if (nextStmt != null && elseStmt == null && !thenImpactedByDisruptions) {
//        	q.addPath(main, nextStmt);
//        	removeNode = false;
//        }
//        
//        if (nextStmt != null && removeNode)
//        	q.removeVertex(nextStmt);
//
//        match(g, q);
//      }
//
//      // Get all while and enhanced for nodes.
//      final List<Statement> whileAndEnhancedForLoops = new ArrayList<>();
//      whileAndEnhancedForLoops.addAll(get(g, WhileStmt.class));
//      whileAndEnhancedForLoops.addAll(get(g, ForeachStmt.class));
//      
//      for (final Statement loop : whileAndEnhancedForLoops) {
//        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
//        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, loop);
//        
//        boolean hasDisruptions = false;
//        for (final Iterator<DisruptInfo> it = disruptions.iterator(); !hasDisruptions && it.hasNext(); ) {
//        	final DisruptInfo info = it.next();
//        	hasDisruptions = hasDisruptions || info.main == loop;
//        }
//        
//        Statement body = null;
//        if (loop.getClass().equals(WhileStmt.class))
//        	body = ((WhileStmt) loop).getBody();
//        else
//        	body = ((ForeachStmt) loop).getBody();
//
//        final Node first = getFirstNode(body), last = getLastNode(body);
//        if (first != null && last != null && first.equals(last)) {
//          final SubgraphQueryNode whileStmt = q.addVertex(null, first);
//          q.addPath(main, whileStmt);
//          // Note that, even without disruptions, q.addPath(whileStmt, main) may not be true when the last node is an if or switch statement.
//        } else if (first != null && last != null) {
//          final SubgraphQueryNode whileFirstStmt = q.addVertex(null, first);
//          final SubgraphQueryNode whileLastStmt = q.addVertex(null, last);
//
//          q.addPath(main, whileFirstStmt);
//          if (!hasDisruptions) {
//        	  // Not true in all cases: q.addPath(whileLastStmt, main);
//        	  q.addPath(whileFirstStmt, whileLastStmt);
//          }
//        } else
//          q.addPath(main, main);
//
//        final Node nextNode = getNext(loop);
//        if (nextNode != null && !hasDisruptions) {
//          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
//          q.addPath(main, nextStmt);
//        }
//
//        match(g, q);
//      }
//
//      // Get all do-while nodes.
//      for (final DoStmt d : get(g, DoStmt.class)) {
//        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
//        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, d);
//        
//        boolean hasDisruptions = false;
//        for (final Iterator<DisruptInfo> it = disruptions.iterator(); !hasDisruptions && it.hasNext(); ) {
//        	final DisruptInfo info = it.next();
//        	hasDisruptions = hasDisruptions || info.main == d;
//        }
//
//        final Node first = getFirstNode(d.getBody()), last = getLastNode(d.getBody());
//        if (first != null && last != null && first.equals(last)) {
//          final SubgraphQueryNode doWhileStmt = q.addVertex(null, first);
//          if (!hasDisruptions) {
//	          // Not true in all cases: q.addPath(doWhileStmt, main);
//	          q.addPath(main, doWhileStmt);
//          }
//        } else if (first != null && last != null) {
//          final SubgraphQueryNode doWhileFirstStmt = q.addVertex(null, first);
//          final SubgraphQueryNode doWhileLastStmt = q.addVertex(null, last);
//
//          if (!hasDisruptions) {
//	          // Not true in all cases: q.addPath(doWhileLastStmt, main);
//	          q.addPath(main, doWhileFirstStmt);
//	          q.addPath(doWhileFirstStmt, doWhileLastStmt);
//          }
//        } else
//          q.addPath(main, main);
//
//        final Node nextNode = getNext(d);
//        if (nextNode != null && !hasDisruptions) {
//          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
//          q.addPath(main, nextStmt);
//        }
//
//        match(g, q);
//      }
//
//      // Get all for nodes.
//      for (final ForStmt f : get(g, ForStmt.class)) {
//        final SubgraphQuery q = new SubgraphQuery(SubgraphQueryEdge.class);
//        final SubgraphQueryNode main = q.addVertex(VertexType.CTRL, f);
//
//        final NodeList<Expression> init = f.getInitialization();
//        final NodeList<Expression> update = f.getUpdate();
//        
//        boolean hasDisruptions = false;
//        for (final Iterator<DisruptInfo> it = disruptions.iterator(); !hasDisruptions && it.hasNext(); ) {
//        	final DisruptInfo info = it.next();
//        	hasDisruptions = hasDisruptions || info.main == f;
//        }
//
//        final Node first = getFirstNode(f.getBody()), last = getLastNode(f.getBody());
//        Node firstInit = null, lastInit = null;
//        if (init.size() == 1) {
//          firstInit = getFirstNode(init.get(0));
//          lastInit = getLastNode(init.get(0));
//        } else if (init.size() > 1) {
//          firstInit = getFirstNode(init.get(0));
//          lastInit = getLastNode(init.get(init.size() - 1));
//        }
//        
//        Node firstUpdate = null, lastUpdate = null;
//        if (!update.isEmpty()) {
//          firstUpdate = getFirstNode(update.get(0));
//          lastUpdate = getLastNode(update.get(update.size() - 1));
//        }
//
//        if (firstInit != null && lastInit != null && firstInit.equals(lastInit)) {
//          final SubgraphQueryNode initStmt = q.addVertex(null, firstInit);
//          q.addPath(initStmt, main);
//        } else if (firstInit != null && lastInit != null) {
//          final SubgraphQueryNode initStmt = q.addVertex(null, firstInit);
//          final SubgraphQueryNode lastStmt = q.addVertex(null, lastInit);
//          q.addPath(initStmt, lastStmt);
//          q.addPath(lastStmt, main);
//        }
//
//        SubgraphQueryNode updateStmt = null;
//        if (firstUpdate != null && lastUpdate != null && firstUpdate.equals(lastUpdate)) {
//          updateStmt = q.addVertex(null, firstUpdate);
//          q.addPath(updateStmt, main);
//        } else if (firstUpdate != null && lastInit != null) {
//          updateStmt = q.addVertex(null, firstUpdate);
//          final SubgraphQueryNode lastStmt = q.addVertex(null, lastUpdate);
//          q.addPath(updateStmt, lastStmt);
//          q.addPath(lastStmt, main);
//        }
//
//        if (first != null && last != null && first.equals(last)) {
//          final SubgraphQueryNode forStmt = q.addVertex(null, first);
//          q.addPath(main, forStmt);
//
//          // Not true in all cases.
////          if (updateStmt != null && !hasDisruptions)
////            q.addPath(forStmt, updateStmt);
////          else if (!hasDisruptions)
////            q.addPath(forStmt, main);
//        } else if (first != null && last != null) {
//          final SubgraphQueryNode forFirstStmt = q.addVertex(null, first);
//          final SubgraphQueryNode forLastStmt = q.addVertex(null, last);
//
//          q.addPath(main, forFirstStmt);
//          if (!hasDisruptions)
//        	  q.addPath(forFirstStmt, forLastStmt);
//
//          // Not true in all cases.
////          if (updateStmt != null && !hasDisruptions)
////            q.addPath(forLastStmt, updateStmt);
////          else if (!hasDisruptions)
////            q.addPath(forLastStmt, main);
//        } else if (firstUpdate == null)
//          q.addPath(main, main);
//        else
//        	q.addPath(main, updateStmt);
//
//        final Node nextNode = getNext(f);
//        if (nextNode != null && !hasDisruptions) {
//          final SubgraphQueryNode nextStmt = q.addVertex(null, nextNode);
//          q.addPath(main, nextStmt);
//        }
//
//        match(g, q);
//      }
//
//      // TODO 0: Switches, try-catch-finally.
//    }
  }

//  private static <T> List<T> get(final CFG g, final Class<T> clazz) {
//    Node entryNode = null;
//    for (final Iterator<Vertex> it = g.vertexSet().iterator(); entryNode == null && it.hasNext();) {
//      final Vertex v = it.next();
//      // EXIT nodes will have null type
//      if (v.getType() != null
//          && (v.getType().equals(VertexType.ENTRY) || v.getType().equals(VertexType.INIT)))
//        entryNode = v.getAst();
//    }
//
//    final List<T> ret = new ArrayList<>();
//
//    // entryNode will be null for empty constructors
//    if (entryNode != null)
//      get(entryNode, ret, clazz);
//
//    return ret;
//  }
//
//  private static <T> void get(final Node n, final List<T> list, final Class<T> clazz) {
//    if (n.getClass().equals(clazz))
//    	list.add(clazz.cast(n));
//
//    for (final Node o : n.getChildNodes())
//      get(o, list, clazz);
//  }
//
//  private static Node getFirstNode(final Node n) {
//    if (n.getClass().equals(EmptyStmt.class))
//      return null;
//    else if (n.getClass().equals(ForStmt.class)) {
//    	final ForStmt forLoop = (ForStmt) n;
//    	
//    	Node ret = null;
//    	if (!forLoop.getInitialization().isEmpty())
//    		for (int i = 0; ret == null && i < forLoop.getInitialization().size(); i++)
//    	        ret = getFirstNode(forLoop.getInitialization().get(i));
//    	else
//    		ret = forLoop;
//    	return ret;
//    	
//    } else if (n.getClass().equals(ExpressionStmt.class))
//      return getFirstNode(((ExpressionStmt) n).getExpression());
//    else if (n.getClass().equals(VariableDeclarationExpr.class))
//      return getFirstNode(((VariableDeclarationExpr) n).getVariable(0));
//    else if (n.getClass().equals(BlockStmt.class)) {
//      Node ret = null;
//      final BlockStmt b = (BlockStmt) n;
//      for (int i = 0; ret == null && i < b.getStatements().size(); i++)
//        ret = getFirstNode(b.getStatement(i));
//      return ret;
//    } else
//      return n;
//  }
//
//  private static Node getLastNode(final Node n) {
//    if (n.getClass().equals(EmptyStmt.class))
//      return null;
//    else if (n.getClass().equals(ExpressionStmt.class))
//      return getLastNode(((ExpressionStmt) n).getExpression());
//    else if (n.getClass().equals(VariableDeclarationExpr.class)) {
//      final VariableDeclarationExpr v = (VariableDeclarationExpr) n;
//      return getLastNode(v.getVariable(v.getVariables().size() - 1));
//    } else if (n.getClass().equals(BlockStmt.class)) {
//      Node ret = null;
//      final BlockStmt b = (BlockStmt) n;
//      for (int i = b.getStatements().size() - 1; ret == null && i >= 0; i--)
//        ret = getLastNode(b.getStatement(i));
//      return ret;
//    } else
//      return n;
//  }
//
//  private static Set<Map<SubgraphQueryNode, Vertex>> match(final CFG g, final SubgraphQuery q) throws Exception {
//    final SubgraphMatching match = new SubgraphMatching();
//    if (q.vertexSet().isEmpty())
//    	throw new Exception("EQ");
//    final Set<Map<SubgraphQueryNode, Vertex>> solutions = match.subgraphMatching(g, q);
//
//    if (solutions.isEmpty())
//      // Zero solutions
//      throw new Exception("ZS");
//    else if (solutions.size() > 1)
//      // More than one solutions
//      throw new Exception("MTOS");
//    return solutions;
//  }
//
//  private static Node getNext(final Node n) throws Exception {
//    Node ret = null;
//
//    final Optional<Node> parentOptional = n.getParentNode();
//    if (parentOptional.isPresent()) {
//      final Node parent = parentOptional.get();
//      
//      if (parent.getClass().equals(IfStmt.class) || parent.getClass().equals(SwitchStmt.class) || 
//    		  parent.getClass().equals(TryStmt.class))
//    	  ret = getNext(parent);
//      else {
//    	  int pos = -1;
//    	  // We cannot use indexOf because the AST library uses equals, which can affect when there is copied and pasted code.
//    	  // For some reason, orphan comments may be added as child nodes, let's not take them into account.
//    	  final List<Node> children = new ArrayList<>(parent.getChildNodes());
//    	  children.removeAll(parent.getOrphanComments());
//    	  for (int i = 0; pos == -1 && i < children.size(); i++)
//    		  if (children.get(i) == n)
//    			  pos = i;
//
//          if (pos >= 0) {
//            if (pos + 1 < children.size())
//              ret = getFirstNode(children.get(pos + 1));
//          } else
//            throw new Exception("Position not found!"); 
//      }
//    } else
//      throw new Exception("A statement without a parent!");
//
//    return ret;
//  }

}
