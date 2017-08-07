package edu.rit.goal.sdg.java8.exception;

import org.antlr.v4.runtime.ParserRuleContext;

public class MultipleExitPointsException extends RuntimeException {

    private static final long serialVersionUID = -6085311677063587121L;

    public static final String MSG = "Only one exit point per method is supported currently.\n\tAssign possible results to a variable and return it.";

    public MultipleExitPointsException(final ParserRuleContext prc) {
	super(MSG);
	System.err.println("Exception at line " + prc.start.getLine() + ":\n\t" + MultipleExitPointsException.MSG);
    }

}
