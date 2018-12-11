package sourcedg;

import java.io.FileInputStream;
import java.util.Set;
import java.util.stream.Collectors;

import sourcedg.analysis.PDGSlicer;
import sourcedg.builder.PDGBuilder;
import sourcedg.builder.PDGBuilderConfig;
import sourcedg.graph.PDG;
import sourcedg.graph.Vertex;
import sourcedg.util.GraphExporter;

public class Slicing {

  public static void main(final String[] args) throws Exception {
    final FileInputStream in = new FileInputStream("programs/java8/normalization/8810011.java");
    PDGBuilderConfig config = PDGBuilderConfig.create().normalize();
    final PDGBuilder builder = new PDGBuilder(config);
    builder.build(in);
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
