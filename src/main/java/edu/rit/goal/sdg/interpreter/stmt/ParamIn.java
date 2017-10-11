package edu.rit.goal.sdg.interpreter.stmt;

import java.util.LinkedHashSet;
import edu.rit.goal.sdg.graph.Vertex;

public class ParamIn extends Stmt {

  public String x;
  public LinkedHashSet<Vertex> V;

  public ParamIn(final String x, final LinkedHashSet<Vertex> v) {
    super();
    this.x = x;
    V = v;
  }

  @Override
  public String toString() {
    return "paramin " + x + " " + V;
  }

}
