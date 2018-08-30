package edu.rit.goal.sourcedg.benchmarking;

import java.util.ArrayList;
import java.util.List;
import edu.rit.goal.sourcedg.graph.Vertex;

public class BasicBlock {

  private List<Vertex> vertices;

  public BasicBlock() {
    this.vertices = new ArrayList<>();
  }

  public void add(Vertex v) {
    vertices.add(v);
  }

  public String toString() {
    return vertices.toString();
  }
  
  public List<Vertex> getVertices() {
    return vertices;
  }

}
