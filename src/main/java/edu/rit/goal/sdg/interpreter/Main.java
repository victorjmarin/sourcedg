package edu.rit.goal.sdg.interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import org.jgrapht.DirectedGraph;
import edu.rit.goal.sdg.Utils;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;

public class Main {

  private static final File FILE = new File("/Users/goal/git/sdg/era_bcb_sample/7/selected/1829265.java");

  public static void main(final String[] args) throws IOException {

    final Interpreter intrprtr = new Interpreter(FILE);
    final Program p = intrprtr.interpret();
    final SysDepGraph sdg = p.sdg;
    final Map<String, DirectedGraph<Vertex, Edge>> methodSubgraphs = sdg.getMethodSubgraphs();
    final DirectedGraph<Vertex, Edge> und = p.F.get("t0p");
    Utils.exportAsDot(sdg, "/Users/goal/Desktop", "und");
    // debug();
  }

  private static void debug() throws IOException {
    final FileReader fileReader = new FileReader("programs/failed.txt");

    final BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line = null;

    while ((line = bufferedReader.readLine()) != null) {
      try {
        final Interpreter intrprtr = new Interpreter(new File(line));
        final Program p = intrprtr.interpret();
      } catch (final Exception e) {
        System.out.println(line);
      }
    }
    bufferedReader.close();
  }
}
