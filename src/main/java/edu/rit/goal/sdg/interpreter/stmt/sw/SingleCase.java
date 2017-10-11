package edu.rit.goal.sdg.interpreter.stmt.sw;

public class SingleCase implements ICase {

  public String x;

  public SingleCase(final String x) {
    super();
    this.x = x;
  }

  @Override
  public String toString() {
    return "case " + x;
  }

}
