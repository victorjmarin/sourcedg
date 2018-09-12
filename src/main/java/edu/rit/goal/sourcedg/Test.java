package edu.rit.goal.sourcedg;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.logging.Level;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.builder.PDGBuilderConfig;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.util.GraphExporter;

public class Test {

  public static void main(final String[] args) throws Exception {
    final FileInputStream in = new FileInputStream(
        "/Users/goal/eclipse-workspace/task-segmentation/BUYING2/submissions/correct/11967195.java");
    PDGBuilderConfig config = PDGBuilderConfig.create();
    final PDGBuilder builder = new PDGBuilder(config, Level.WARNING);
    builder.build(in);
    final PDG pdg = builder.getPDG();
    System.out.println(pdg);
    final Iterator<CFG> it = builder.getCfgs().iterator();
    final CFG cfg = it.next();
    GraphExporter.exportAsDot(cfg, "/Users/goal/Desktop", "cfg");
  }

}
