package edu.rit.goal.sdg.java.statement;

import java.util.List;

import edu.rit.goal.sdg.java.graph.SysDepGraph;

public class MethodInvocation implements Statement {

    private String name;
    private List<String> args;

    public MethodInvocation(final String name, final List<String> args) {
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

    public List<String> getArgs() {
	return args;
    }

    public void setArgs(final List<String> args) {
	this.args = args;
    }

    @Override
    public String toString() {
	return name + args.toString();
    }

    @Override
    public void buildSdg(final SysDepGraph sdg) {
	System.out.println(toString());
    }

}
