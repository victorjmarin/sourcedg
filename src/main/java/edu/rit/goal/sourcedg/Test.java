package edu.rit.goal.sourcedg;

import java.io.FileInputStream;
import java.util.Iterator;
import org.jgrapht.graph.DefaultDirectedGraph;
import edu.rit.goal.sourcedg.builder.CFGDominator;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.util.GraphExporter;

public class Test {

  public static void main(final String[] args) throws Exception {
    final FileInputStream in = new FileInputStream("programs/java8/while.java");
    final PDGBuilder builder = new PDGBuilder();
    builder.build(in, true);
    final PDG pdg = builder.getPDG();
    System.out.println(pdg);
    final Iterator<CFG> it = builder.getCfgs().iterator();
    // it.next();
    final CFG cfg = it.next();
    final Vertex ev = new Vertex("exit");
    cfg.removeVertex(cfg.getVertexWithId(12));
    cfg.removeVertex(cfg.getVertexWithId(13));
    cfg.addVertex(ev);
    cfg.addEdge(cfg.getVertexWithId(6), ev);
    cfg.addEdge(cfg.getVertexWithId(9), cfg.getVertexWithId(6));
    cfg.addEdge(cfg.getVertexWithId(11), cfg.getVertexWithId(6));
    
    cfg.addEdge(cfg.getVertexWithId(9), cfg.getVertexWithId(4));
    cfg.addEdge(cfg.getVertexWithId(11), cfg.getVertexWithId(4));
    cfg.removeEdge(cfg.getVertexWithId(9), cfg.getVertexWithId(6));
    cfg.removeEdge(cfg.getVertexWithId(11), cfg.getVertexWithId(6));
    
    pdg.removeVertex(pdg.getVertexWithId(0));
    GraphExporter.exportAsDot(cfg, "/Users/goal/Desktop", "cfg");
    final CFGDominator dom = new CFGDominator();
    final DefaultDirectedGraph<Vertex, Edge> cdg =
        dom.cdg(cfg, cfg.getVertexWithId(1), cfg.getVertexWithId(0));
    GraphExporter.exportAsDot(cdg, "/Users/goal/Desktop", "cdg");
  }

}
