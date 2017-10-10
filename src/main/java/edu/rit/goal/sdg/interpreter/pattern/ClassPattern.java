package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;

public class ClassPattern<T> implements Pattern {

  private final Class<T> clazz;

  private final Function<T, Object> function;

  public ClassPattern(final Class<T> clazz, final Function<T, Object> function) {
    this.clazz = clazz;
    this.function = function;
  }

  @Override
  public boolean matches(final Object value) {
    return clazz.isInstance(value);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object apply(final Object value) {
    return function.apply((T) value);
  }

  public static <T> Pattern caseof(final Class<T> clazz, final Function<T, Object> function) {
    return new ClassPattern<T>(clazz, function);
  }
}
