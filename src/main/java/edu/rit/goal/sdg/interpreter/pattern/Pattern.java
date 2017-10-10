package edu.rit.goal.sdg.interpreter.pattern;

public interface Pattern {
  boolean matches(Object value);

  Object apply(Object value);
}
