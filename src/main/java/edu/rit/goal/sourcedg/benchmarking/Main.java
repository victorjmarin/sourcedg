package edu.rit.goal.sourcedg.benchmarking;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import edu.rit.goal.graal.Alignment;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.builder.PDGBuilderConfig;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Vertex;

public class Main {

  public static void main(final String[] args) throws Exception {
    final File folder = new File("/Users/goal/eclipse-workspace/task-miner-fast/Fibonacci-GCD/a");

    List<String> paths = new ArrayList<>();
    listFilesForFolder(folder, paths);

    PDGBuilderConfig config = PDGBuilderConfig.create();

    Set<BasicBlockCFG> s = new HashSet<>();

    long tic = System.currentTimeMillis();

    for (String p1 : paths) {
      final FileInputStream in1 = new FileInputStream(p1);
      final PDGBuilder builder1 = new PDGBuilder(config);
      builder1.build(in1);
      final Iterator<CFG> it1 = builder1.getCfgs().iterator();
      final CFG cfg1 = it1.next();
      BasicBlockCFG bbcfg1 = new BasicBlockCFG(cfg1, builder1.getPDG());
      s.add(bbcfg1);
    }

    long toc = System.currentTimeMillis();

    System.out.println("Done in " + (toc - tic) / 1000 + " s.");

    tic = System.currentTimeMillis();

    int count = 0;
    for (BasicBlockCFG p1 : s) {
      for (BasicBlockCFG p2 : s) {
        if (p1 != p2) {
          Set<Alignment<Vertex>> sa = new BBAlign(p1, p2).align();
          sa.forEach(System.out::println);
          count++;
          if (count % 1000 == 0)
            System.out.println(count);
        }
      }
    }

    toc = System.currentTimeMillis();

    System.out.println("Done in " + (toc - tic) / 1000 + " s.");

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
