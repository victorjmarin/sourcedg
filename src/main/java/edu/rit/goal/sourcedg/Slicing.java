package edu.rit.goal.sourcedg;

import java.io.FileInputStream;
import java.util.Set;
import java.util.stream.Collectors;
import edu.rit.goal.sourcedg.analysis.PDGSlicer;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.util.GraphExporter;

public class Slicing {

  public static void main(final String[] args) throws Exception {
    final FileInputStream in = new FileInputStream("programs/java8/normalization/8810011.java");
    final PDGBuilder builder = new PDGBuilder();
    builder.build(in, true);
    final PDG pdg = builder.getPDG();

    final Vertex v = pdg.vertexSet().stream().filter(u -> u.getLabel().contains("System.out.print"))
        .findFirst().get();

    System.out.println(v);

    final Set<Vertex> S =
        pdg.outgoingEdgesOf(v).stream().map(e -> e.getTarget()).collect(Collectors.toSet());
    S.add(v);


    final Set<Vertex> slice = PDGSlicer.backward(pdg, S);
    for (final Vertex w : slice) {
      w.setFillColor("yellow");
    }
    GraphExporter.exportAsDot(pdg, "/Users/goal/Desktop", "graph");
    System.out.println(slice);


  }

}
