package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;

public class BooleanPattern implements Pattern {
  private final Boolean pattern;
  private final Function<Boolean, Object> function;

  public BooleanPattern(final boolean pattern, final Function<Boolean, Object> function) {
    this.pattern = pattern;
    this.function = function;
  }

  @Override
  public boolean matches(final Object value) {
    return pattern.equals(value);
  }

  @Override
  public Object apply(final Object value) {
    return function.apply((Boolean) value);
  }

  public static Pattern caseof(final boolean pattern, final Function<Boolean, Object> function) {
    return new BooleanPattern(pattern, function);
  }

}
