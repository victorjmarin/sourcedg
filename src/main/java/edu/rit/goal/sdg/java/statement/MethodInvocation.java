package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class MethodInvocation implements Statement {

    // Variable name of the object to which the invoked method belongs to.
    private final String refVar;
    private final String methodName;
    private final List<Expression> inVars;

    public MethodInvocation(final String refVar, final String methodName, final List<Expression> inVars) {
	super();
	this.refVar = refVar;
	this.methodName = methodName;
	this.inVars = inVars;
    }

    public String getRefVar() {
	return refVar;
    }

    public String getMethodName() {
	return methodName;
    }

    public List<Expression> getInVars() {
	return inVars;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	if (refVar != null) {
	    sb.append(refVar);
	    sb.append(".");
	}
	sb.append(methodName);
	if (inVars != null && !inVars.isEmpty())
	    sb.append(inVars);
	return sb.toString();
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
