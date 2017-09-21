package edu.rit.goal.sdg.interpreter.stmt.sw;

public class EmptySwitch implements ISwitchBody {

    @Override
    public String toString() {
	final int emptySetAscii = 248;
	final Character emptySetChar = new Character((char) emptySetAscii);
	return emptySetChar.toString();
    }

}
