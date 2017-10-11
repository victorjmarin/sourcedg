package edu.rit.goal.sdg.interpreter.stmt.sw;

public class MultiCase implements ICase {

  public SingleCase x;
  public ICase cs;

  public MultiCase(final SingleCase x, final ICase cs) {
    super();
    this.x = x;
    this.cs = cs;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + cs + ")";
  }

}
