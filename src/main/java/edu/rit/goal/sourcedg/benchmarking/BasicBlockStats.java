package edu.rit.goal.sourcedg.benchmarking;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.builder.PDGBuilderConfig;
import edu.rit.goal.sourcedg.graph.CFG;

public class BasicBlockStats {

  public static void main(final String[] args) throws Exception {
    final File folder =
        new File("/Users/goal/eclipse-workspace/task-segmentation/BUYING2/submissions/correct/");

    List<String> paths = new ArrayList<>();
    listFilesForFolder(folder, paths);

    PDGBuilderConfig config = PDGBuilderConfig.create();

    Set<BasicBlockCFG> s = new HashSet<>();


    for (String p1 : paths) {
      final FileInputStream in1 = new FileInputStream(p1);
      final PDGBuilder builder1 = new PDGBuilder(config);
      builder1.build(in1);
      final Iterator<CFG> it1 = builder1.getCfgs().iterator();
      final CFG cfg1 = it1.next();
      BasicBlockCFG bbcfg1 = new BasicBlockCFG(cfg1, builder1.getPDG());
      s.add(bbcfg1);
    }


    IntSummaryStatistics basicBlocks = new IntSummaryStatistics();
    IntSummaryStatistics verticesPerBasicBlock = new IntSummaryStatistics();

    for (BasicBlockCFG cfg : s) {
      basicBlocks.accept(cfg.vertexSet().size());
      for (BasicBlock bb : cfg.vertexSet()) {
        verticesPerBasicBlock.accept(bb.getVertices().size());
      }
    }

    System.out.println("Avg. basic blocks per program: " + basicBlocks.getAverage());
    System.out.println("Max. basic blocks per program: " + basicBlocks.getMax());
    System.out.println("Avg. vertices per block: " + verticesPerBasicBlock.getAverage());
    System.out.println("Max. vertices per block: " + verticesPerBasicBlock.getMax());

  }


  public static void listFilesForFolder(final File folder, List<String> paths) {
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        listFilesForFolder(fileEntry, paths);
      } else if (fileEntry.getName().endsWith(".java")) {
        paths.add(fileEntry.getAbsolutePath());
      }
    }
  }

}
