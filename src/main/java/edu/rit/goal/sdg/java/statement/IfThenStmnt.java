package edu.rit.goal.sdg.java.statement;

import java.util.List;

import edu.rit.goal.sdg.java.graph.SysDepGraph;

public class IfThenStmnt implements Statement {

    private Expression condition;
    private List<Statement> thenBranch;

    public IfThenStmnt(final Expression condition, final List<Statement> thenBranch) {
	super();
	this.condition = condition;
	this.thenBranch = thenBranch;
    }

    public Expression getCondition() {
	return condition;
    }

    public void setCondition(final Expression condition) {
	this.condition = condition;
    }

    public List<Statement> getThenBranch() {
	return thenBranch;
    }

    public void setThenBranch(final List<Statement> thenBranch) {
	this.thenBranch = thenBranch;
    }

    @Override
    public String toString() {
	return "if " + condition + " then " + thenBranch.toString();
    }

    @Override
    public void buildSdg(final SysDepGraph sdg) {
	System.out.println(toString());
    }

}
