package edu.rit.goal.sdg.interpreter.params;

public class EmptyParam implements Param {

    @Override
    public String toString() {
	final int emptySetAscii = 248;
	final Character emptySetChar = new Character((char) emptySetAscii);
	return emptySetChar.toString();
    }

}
