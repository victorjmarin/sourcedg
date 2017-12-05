package edu.rit.goal.sdg.interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.jgrapht.DirectedGraph;
import edu.rit.goal.sdg.Utils;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;

public class Main {

  private static final File FILE = new File("programs/java8/normalization/2306307.java");

  public static void main(final String[] args) throws IOException {
    final Interpreter intrprtr = new Interpreter(FILE);
    final Program p = intrprtr.interpret();
    final SysDepGraph sdg = p.sdg;
    final Map<String, DirectedGraph<Vertex, Edge>> methodSubgraphs = sdg.getMethodSubgraphs();
    final DirectedGraph<Vertex, Edge> und = p.F.get("RunningExample.f");
    Utils.exportAsDot(sdg, "/Users/goal/Desktop", "und");
    // for (final Entry<String, DirectedGraph<Vertex, Edge>> e :
    // methodSubgraphs.entrySet()) {
    // final DirectedGraph<Vertex, Edge> g = e.getValue();
    // TestUtils.exportAsDot(g, e.getKey() + "Flow");
    // TestUtils.exportAsDot(new FlowGraph(e.getValue()).graph, e.getKey() +
    // "Flow");
    // }
  }

}
