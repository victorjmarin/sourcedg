package edu.rit.goal.sdg.java.statement;

import java.util.List;

import edu.rit.goal.sdg.java.graph.SysDepGraph;

public class IfThenElse implements Statement {

    private Expression condition;
    private List<Statement> thenBranch;
    private List<Statement> elseBranch;

    public IfThenElse(final Expression condition, final List<Statement> thenBranch, final List<Statement> elseBranch) {
	super();
	this.condition = condition;
	this.thenBranch = thenBranch;
	this.elseBranch = elseBranch;
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

    public List<Statement> getElseBranch() {
	return elseBranch;
    }

    public void setElseBranch(final List<Statement> elseBranch) {
	this.elseBranch = elseBranch;
    }

    @Override
    public void buildSdg(final SysDepGraph sdg) {
	// TODO Auto-generated method stub

    }

}
