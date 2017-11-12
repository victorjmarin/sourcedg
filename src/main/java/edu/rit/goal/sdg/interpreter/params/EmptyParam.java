package edu.rit.goal.sdg.interpreter.params;

import edu.rit.goal.sdg.interpreter.stmt.BaseStmt;

public class EmptyParam extends BaseStmt implements Param {

  @Override
  public String toString() {
    final int emptySetAscii = 248;
    final Character emptySetChar = new Character((char) emptySetAscii);
    return emptySetChar.toString();
  }

}
