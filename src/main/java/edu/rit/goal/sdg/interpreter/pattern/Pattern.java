package edu.rit.goal.sdg.interpreter.pattern;

import java.util.function.Function;
import edu.rit.goal.sdg.interpreter.Program;

public interface Pattern {
  boolean matches(Object value);

  Function<Program, Object> apply(Object value);

}
