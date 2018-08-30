package edu.rit.goal.sourcedg.benchmarking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.graph.VertexType;

public class BasicBlockCFG extends DefaultDirectedGraph<BasicBlock, DefaultEdge> {

  private static final long serialVersionUID = -1767874066180345983L;

  private PDG pdg;

  public BasicBlockCFG(CFG cfg, PDG pdg) {
    super(DefaultEdge.class);

    this.pdg = pdg;

    Map<Vertex, BasicBlock> m = new HashMap<>();

    BasicBlock currBlk = new BasicBlock();
    addVertex(currBlk);

    GraphIterator<Vertex, Edge> iterator = new DepthFirstIterator<>(cfg, cfg.getEntry());

    while (iterator.hasNext()) {
      Vertex v = iterator.next();

      if (v.getType().equals(VertexType.EXIT))
        continue;

      currBlk = m.getOrDefault(v, currBlk);

      List<Vertex> pred =
          cfg.incomingEdgesOf(v).stream().map(e -> e.getSource()).collect(Collectors.toList());

      // We want freedom when aligning ctrl.
      if (v.getType().equals(VertexType.CTRL))
        currBlk = newBlk(v);
      else if ((pred.size() == 1 && cfg.outDegreeOf(pred.get(0)) > 1) || cfg.inDegreeOf(v) > 1)
        currBlk = newBlk(v);

      currBlk.add(v);
      m.put(v, currBlk);
    }

  }

  public void buildBlockCFG(CFG cfg, PDG pdg) {

    Vertex entry = cfg.getEntry();

  }

  private void dfs(CFG cfg, Vertex start, Set<Vertex> visited) {
    visited.add(start);
    
    Set<Vertex> outgoingNeighbors =
        cfg.outgoingEdgesOf(start).stream().map(e -> e.getTarget()).collect(Collectors.toSet());
    
    for (Vertex v : outgoingNeighbors) {
      if (visited.contains(v))
        continue;
      dfs(cfg, v, visited);
    }
    
  }

  public PDG getPDG() {
    return pdg;
  }

  private BasicBlock newBlk(Vertex v) {
    BasicBlock newBlk = new BasicBlock();
    addVertex(newBlk);
    return newBlk;
  }

}
