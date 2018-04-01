package edu.rit.goal.sourcedg;

import java.io.FileInputStream;
import java.util.Iterator;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.builder.PDGBuilderConfig;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.util.GraphExporter;

public class Test {

  public static void main(final String[] args) throws Exception {
    final FileInputStream in = new FileInputStream(
        "/Users/goal/eclipse-workspace/task-segmentation/BUYING2/submissions/correct/10570402.java");
    PDGBuilderConfig config = PDGBuilderConfig.create().normalize();
    final PDGBuilder builder = new PDGBuilder(config);
    builder.build(in);
    final PDG pdg = builder.getPDG();
    System.out.println(pdg);
    final Iterator<CFG> it = builder.getCfgs().iterator();
    final CFG cfg = it.next();
    GraphExporter.exportAsDot(pdg, "/Users/goal/Desktop", "pdg");
  }

}
