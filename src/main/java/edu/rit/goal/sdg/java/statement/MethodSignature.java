package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class MethodSignature implements Statement {

    private final String name;
    private final List<FormalParameter> params;

    public MethodSignature(final String name, final List<FormalParameter> params) {
	super();
	this.name = name;
	this.params = params;
    }

    public String getName() {
	return name;
    }

    public List<FormalParameter> getParams() {
	return params;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append(name);
	if (params != null)
	    sb.append(params);
	return sb.toString();
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
