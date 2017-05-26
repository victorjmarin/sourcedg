package edu.rit.goal.sdg.java.statement;

import java.util.List;

public class MethodInvocation implements Statement {

    private final String name;
    private final List<Expression> args;

    public MethodInvocation(final String name, final List<Expression> args) {
	super();
	this.name = name;
	this.args = args;
    }

    public String getName() {
	return name;
    }

    public List<Expression> getArgs() {
	return args;
    }

    @Override
    public String toString() {
	return name + args.toString();
    }

}
