package sourcedg.builder;

import static com.github.javaparser.utils.CodeGenerationUtils.f;
import java.util.HashSet;
import java.util.Set;

import sourcedg.graph.Vertex;


/*
 * Model control flow between statements
 */
public class ControlFlow {

  private final Vertex in;
  private final Set<Vertex> out;
  private final Set<Vertex> breaks;

  public ControlFlow() {
    in = null;
    out = new HashSet<>();
    breaks = new HashSet<>();
  }

  public ControlFlow(final Vertex in, final Vertex out) {
    this.in = in;
    this.out = new HashSet<>();
    breaks = new HashSet<>();
    this.out.add(out);
  }

  public ControlFlow(final Vertex in, final Set<Vertex> out) {
    this.in = in;
    this.out = new HashSet<>(out);
    breaks = new HashSet<>();
  }

  public Vertex getIn() {
    return in;
  }

  public Set<Vertex> getOut() {
    return out;
  }

  public Set<Vertex> getBreaks() {
    return breaks;
  }

  @Override
  public String toString() {
    return f("<%s, %s>", in, out);
  }

}
