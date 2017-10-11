package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;
import edu.rit.goal.sdg.interpreter.Program;

public class PatternMatching {
  private final Pattern[] patterns;

  @SafeVarargs
  public PatternMatching(final Pattern... patterns) {
    this.patterns = patterns;
  }

  public static PatternMatching $(final Pattern... patterns) {
    return new PatternMatching(patterns);
  }

  public Function<Program, Object> matchFor(final Object value) {
    for (final Pattern pattern : patterns)
      if (pattern.matches(value))
        return pattern.apply(value);
    throw new IllegalArgumentException("Cannot match " + value);
  }
}
