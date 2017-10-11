package edu.rit.goal.sdg.java8.exception;

import org.antlr.v4.runtime.ParserRuleContext;

public class InvocationArgException extends RuntimeException {

  private static final long serialVersionUID = -6085311677063587121L;

  public static final String MSG =
      "Method/function calls as arguments are not supported currently.\n\tExtract the invocation to a variable assignment.";

  public InvocationArgException(final ParserRuleContext prc) {
    super(MSG);
    System.err
        .println("Exception at line " + prc.start.getLine() + ":\n\t" + InvocationArgException.MSG);
  }

}
