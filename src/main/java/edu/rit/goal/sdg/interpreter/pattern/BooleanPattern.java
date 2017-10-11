package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;
import edu.rit.goal.sdg.interpreter.Program;

public class BooleanPattern implements Pattern {
  private final Boolean pattern;
  private final Function<Boolean, Function<Program, Program>> function;

  public BooleanPattern(final boolean pattern,
      final Function<Boolean, Function<Program, Program>> function) {
    this.pattern = pattern;
    this.function = function;
  }

  @Override
  public boolean matches(final Object value) {
    return pattern.equals(value);
  }

  @Override
  public Function<Program, Program> apply(final Object value) {
    return function.apply((Boolean) value);
  }

  public static Pattern caseof(final boolean pattern,
      final Function<Boolean, Function<Program, Program>> function) {
    return new BooleanPattern(pattern, function);
  }

}
