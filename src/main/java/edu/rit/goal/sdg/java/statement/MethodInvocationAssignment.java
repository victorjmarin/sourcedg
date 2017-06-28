package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class MethodInvocationAssignment extends MethodInvocation implements Statement {

    private final String outVar;

    public MethodInvocationAssignment(final String refVar, final String methodName, final String outVar,
	    final List<Expression> inVars) {
	super(refVar, methodName, inVars);
	this.outVar = outVar;
    }

    public String getOutVar() {
	return outVar;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	if (outVar != null) {
	    sb.append(outVar);
	    sb.append("=");
	}
	sb.append(super.toString());
	return sb.toString();
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }
}