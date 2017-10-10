package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;

public class OtherwisePattern implements Pattern {
  private final Function<Object, Object> function;

  public OtherwisePattern(final Function<Object, Object> function) {
    this.function = function;
  }

  @Override
  public boolean matches(final Object value) {
    return true;
  }

  @Override
  public Object apply(final Object value) {
    return function.apply(value);
  }

  public static Pattern otherwise(final Function<Object, Object> function) {
    return new OtherwisePattern(function);
  }
}
