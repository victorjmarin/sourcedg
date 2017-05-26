package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class MethodInvocation implements Statement {

    private String name;
    private List<Expression> args;

    public MethodInvocation(final String name, final List<Expression> args) {
	super();
	this.name = name;
	this.args = args;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public List<Expression> getArgs() {
	return args;
    }

    public void setArgs(final List<Expression> args) {
	this.args = args;
    }

    @Override
    public String toString() {
	return name + args.toString();
    }

}
