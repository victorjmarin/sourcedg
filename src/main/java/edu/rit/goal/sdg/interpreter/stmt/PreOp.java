package edu.rit.goal.sdg.interpreter.stmt;

public class PreOp extends IncrDecrOp {

    public PreOp(final String x, final String op) {
	super(x, op);
    }

    @Override
    public String toString() {
	return op + x;
    }

}
