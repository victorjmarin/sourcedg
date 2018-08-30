package edu.rit.goal.sourcedg.benchmarking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.google.common.collect.Lists;
import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.builder.PDGBuilderConfig;
import edu.rit.goal.sourcedg.graph.CFG;

public class Benchmark {

  public static void main(String[] args) throws FileNotFoundException {

    Set<BasicBlockCFG> graphs =
        getGraphs("/Users/goal/eclipse-workspace/task-segmentation/BUYING2/submissions/correct/");

    List<BasicBlockCFG> graphlist = Lists.newArrayList(graphs);

    // Basic block alignment is ~10x faster for this submissions but should produce worse alignments
    // in general. The
    // question to answer is how much worse the alignments are.
    // We are trying to match control flows but allow for flexibility and resiliency against
    // semantics-preserving statement re-ordering.

    // Basic blocks produce less edges in A since we are only aligning within blocks.
    
    // Ideas:
    // 1. All ctrl nodes are basic blocks so that they can be aligned more freely. If we have a
    // chain of if-else, the first if would be linked to the preceding block however we may want to
    // align it with the if in an else.
    // 2. Include edges in BasicBlockCFG.

    long tic = System.currentTimeMillis();
    // int counter = 0;
    // for (int i = 0; i < graphlist.size() - 1; i++) {
    // BasicBlockCFG g1 = graphlist.get(i);
    // for (int j = i + 1; j < graphlist.size(); j++) {
    // BasicBlockCFG g2 = graphlist.get(j);
    // new BBAlign(g1, g2);
    // counter++;
    // if (counter % 1000 == 0)
    // System.out
    // .println(counter + " time: " + (System.currentTimeMillis() - tic) / 1000 + " s.");
    // }
    // }

    int counter = 0;
    for (int i = 0; i < graphlist.size() - 1; i++) {
      BasicBlockCFG g1 = graphlist.get(i);
      for (int j = i + 1; j < graphlist.size(); j++) {
        BasicBlockCFG g2 = graphlist.get(j);
        edu.rit.goal.alignment.FastCost c =
            new edu.rit.goal.alignment.FastCost(g1.getPDG(), g2.getPDG(), 0.75, 2);
        // new BipartiteMatchingAlignment(c, null).align();
        counter++;
        if (counter % 1000 == 0)
          System.out
              .println(counter + " time: " + (System.currentTimeMillis() - tic) / 1000 + " s.");
      }
    }

  }

  private static Set<BasicBlockCFG> getGraphs(String path) throws FileNotFoundException {
    final File folder = new File(path);

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

    return s;
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
