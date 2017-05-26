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
	return name + params.toString();
    }

}
