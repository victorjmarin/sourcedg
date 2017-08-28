package edu.rit.goal.sdg.interpreter.stmt;

public class PostOp extends IncrDecrOp {

    public PostOp(final String x, final String op) {
	super(x, op);
    }

    @Override
    public String toString() {
	return x + op;
    }

}
