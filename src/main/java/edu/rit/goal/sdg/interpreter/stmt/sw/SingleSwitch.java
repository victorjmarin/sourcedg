package edu.rit.goal.sdg.interpreter.stmt.sw;

import edu.rit.goal.sdg.interpreter.stmt.BaseStmt;

public class SingleSwitch implements ISwitchBody {

  public ICase cs;
  public BaseStmt s;

  public SingleSwitch(final ICase cs, final BaseStmt s) {
    super();
    this.cs = cs;
    this.s = s;
  }

  @Override
  public String toString() {
    return cs + ": " + s;
  }

}
