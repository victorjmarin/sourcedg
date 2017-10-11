package edu.rit.goal.sdg.interpreter.stmt.sw;

public class MultiSwitch implements ISwitchBody {

  public SingleSwitch ss;
  public MultiSwitch sb;

  public MultiSwitch(final SingleSwitch ss, final MultiSwitch sb) {
    super();
    this.ss = ss;
    this.sb = sb;
  }

  @Override
  public String toString() {
    return "(" + ss + ", " + sb + ")";
  }

}
