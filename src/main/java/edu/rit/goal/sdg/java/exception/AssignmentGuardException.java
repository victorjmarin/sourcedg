package edu.rit.goal.sdg.java.exception;

import org.antlr.v4.runtime.ParserRuleContext;

public class AssignmentGuardException extends RuntimeException {

    private static final long serialVersionUID = -6085311677063587121L;

    public static final String MSG = "Assignments within guards are not supported currently.\n\tExtract the assignment to a variable.";

    public AssignmentGuardException(final ParserRuleContext prc) {
	super(MSG);
	System.err.println("Exception at line " + prc.start.getLine() + ":\n\t" + AssignmentGuardException.MSG);
    }

}
