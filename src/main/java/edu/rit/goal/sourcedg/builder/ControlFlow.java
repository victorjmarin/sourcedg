package edu.rit.goal.sourcedg.builder;

import java.util.HashSet;
import java.util.Set;
import edu.rit.goal.sourcedg.graph.Vertex;

/*
 * Model control flow between statements
 */
public class ControlFlow {

  private final Set<Vertex> in;
  private final Set<Vertex> out;

  public ControlFlow() {
    in = new HashSet<>();
    out = new HashSet<>();
  }

  public ControlFlow(final Vertex in, final Vertex out) {
    this.in = new HashSet<>();
    this.out = new HashSet<>();
    this.in.add(in);
    this.out.add(out);
  }

  public ControlFlow(final Vertex in, final Set<Vertex> out) {
    this.in = new HashSet<>();
    this.in.add(in);
    this.out = new HashSet<>(out);
  }

  public ControlFlow(final Set<Vertex> in, final Set<Vertex> out) {
    this.in = new HashSet<>(in);
    this.out = new HashSet<>(out);
  }

  public Set<Vertex> getIn() {
    return in;
  }

  public Set<Vertex> getOut() {
    return out;
  }

}
