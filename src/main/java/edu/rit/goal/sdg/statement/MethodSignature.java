package edu.rit.goal.sdg.statement;

import java.util.List;

import edu.rit.goal.sdg.graph.PrimitiveType;

public class MethodSignature implements Stmt {

    private PrimitiveType returnType;
    private final String name;
    private final List<FormalParameter> params;

    public MethodSignature(final String name, final List<FormalParameter> params) {
	super();
	this.name = name;
	this.params = params;
    }

    public PrimitiveType getReturnType() {
	return returnType;
    }

    public void setReturnType(final PrimitiveType returnType) {
	this.returnType = returnType;
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

}
