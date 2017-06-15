package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class MethodInvocationAssignment implements Statement {

    private final String name;
    private final String outVar;
    private final List<Expression> inVars;

    public MethodInvocationAssignment(final String name, final String outVar, final List<Expression> inVars) {
	super();
	this.name = name;
	this.outVar = outVar;
	this.inVars = inVars;
    }

    public String getName() {
	return name;
    }

    public String getOutVar() {
	return outVar;
    }

    public List<Expression> getInVars() {
	return inVars;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	if (outVar != null) {
	    sb.append(outVar);
	    sb.append("=");
	}
	sb.append(name);
	sb.append(inVars);
	return sb.toString();
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }
}