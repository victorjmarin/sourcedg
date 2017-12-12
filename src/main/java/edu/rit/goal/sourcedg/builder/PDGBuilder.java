package edu.rit.goal.sourcedg.builder;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.DirectedGraph;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.EdgeType;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.graph.VertexType;
import edu.rit.goal.sourcedg.normalization.Normalizer;

public class PDGBuilder {

  private PDG pdg;
  private Collection<CFG> cfgs;

  public void build(final FileInputStream in) {
    CompilationUnit cu = JavaParser.parse(in);
    System.out.println("Normalizing...");
    final Normalizer normalizer = new Normalizer(cu);
    cu = normalizer.normalize();
    System.out.println("Building CDG...");
    final CDGBuilder cdgBuilder = new CDGBuilder(cu);
    cdgBuilder.build();
    pdg = cdgBuilder.getCDG();
    System.out.println("Computing inter-procedural calls...");
    computeInterProceduralCalls();
    cfgs = cdgBuilder.getCfgs();
    System.out.println("Computing data dependencies...");
    computeDataDependencies();
  }

  private void computeInterProceduralCalls() {

  }

  private void computeDataDependencies() {
    for (final DirectedGraph<Vertex, Edge> cfg : cfgs)
      reachingDefinitions(cfg);
    for (final Vertex useVtx : pdg.vertexSet()) {
      for (final String use : useVtx.getUses()) {
        boolean noEdgeForUse = true;
        for (final Vertex inVtx : useVtx.getIn()) {
          if (use.equals(inVtx.getDef())) {
            pdg.addEdge(inVtx, useVtx, new Edge(inVtx, useVtx, EdgeType.DATA));
            noEdgeForUse = false;
          }
        }
        if (noEdgeForUse) {
          // TODO: Create initial state vertex
        }
      }
    }
  }

  private void reachingDefinitions(final DirectedGraph<Vertex, Edge> cfg) {
    boolean changes = true;
    while (changes) {
      changes = false;
      for (final Vertex n : cfg.vertexSet()) {
        final Set<Vertex> oldOut = n.getOut();
        final Set<Vertex> oldIn = n.getIn();
        final Set<Edge> incomingEdges = cfg.incomingEdgesOf(n);
        final Set<Vertex> pred =
            incomingEdges.stream().map(e -> e.getSource()).collect(Collectors.toSet());
        if (VertexType.CTRL.equals(n.getType())) {
          for (final Vertex p : pred)
            n.getOut().addAll(p.getOut());
        } else {
          for (final Vertex p : pred)
            n.getIn().addAll(p.getOut());
          final Set<Vertex> out = new HashSet<>();
          final Set<Vertex> diff = new HashSet<>(n.getIn());
          Set<Vertex> kill = new HashSet<>();
          if (n.getDef() != null) {
            out.add(n);
            kill = n.getIn().stream().filter(v -> v.getDef().equals(n.getDef()))
                .collect(Collectors.toSet());
          }
          diff.removeAll(kill);
          out.addAll(diff);
          n.setOut(out);

        }
        // Check if changes
        changes = changes || !oldIn.equals(n.getIn()) || !oldOut.equals(n.getOut());
      }
    }
  }

  public PDG getPDG() {
    return pdg;
  }

  public Collection<CFG> getCfgs() {
    return cfgs;
  }

}
