package edu.rit.goal.sourcedg;

import java.io.FileInputStream;
import java.util.Iterator;
import org.jgrapht.graph.DefaultDirectedGraph;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.util.GraphExporter;

public class TestLines {

  public static void main(final String[] args) throws Exception {
    final FileInputStream in = new FileInputStream("programs/java8/normalization/8860296.java");
    final PDGBuilder builder = new PDGBuilder();
    builder.build(in);
    final PDG pdg = builder.getPDG();
    System.out.println(pdg);
    final Iterator<CFG> it = builder.getCfgs().iterator();
    final DefaultDirectedGraph<Vertex, Edge> cfg = it.next();
    GraphExporter.exportAsDot(pdg, "/Users/goal/Desktop", "und2");
  }

}
