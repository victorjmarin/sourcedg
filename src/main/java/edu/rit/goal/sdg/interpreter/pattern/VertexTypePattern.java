package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;
import edu.rit.goal.sdg.graph.VertexType;

public class VertexTypePattern implements Pattern {
  private final VertexType pattern;
  private final Function<VertexType, Object> function;

  public VertexTypePattern(final VertexType pattern, final Function<VertexType, Object> function) {
    this.pattern = pattern;
    this.function = function;
  }

  @Override
  public boolean matches(final Object value) {
    return pattern.equals(value);
  }

  @Override
  public Object apply(final Object value) {
    return function.apply((VertexType) value);
  }

  public static Pattern caseof(final VertexType pattern, final Function<VertexType, Object> function) {
    return new VertexTypePattern(pattern, function);
  }

}
