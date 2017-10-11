package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;
import edu.rit.goal.sdg.interpreter.Program;

public class OtherwisePattern implements Pattern {
  private final Function<Object, Function<Program, Program>> function;

  public OtherwisePattern(final Function<Object, Function<Program, Program>> function) {
    this.function = function;
  }

  @Override
  public boolean matches(final Object value) {
    return true;
  }

  @Override
  public Function<Program, Program> apply(final Object value) {
    return function.apply(value);
  }

  public static Pattern otherwise(final Function<Object, Function<Program, Program>> function) {
    return new OtherwisePattern(function);
  }
}
