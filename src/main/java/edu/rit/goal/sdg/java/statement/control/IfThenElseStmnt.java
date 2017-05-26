package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class IfThenElseStmnt implements Statement {

    private Expression condition;
    private List<Statement> thenBranch;
    private List<Statement> elseBranch;

    public IfThenElseStmnt(final Expression condition, final List<Statement> thenBranch,
	    final List<Statement> elseBranch) {
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
    public String toString() {
	return "if " + condition + " then " + thenBranch.toString() + " else " + elseBranch.toString();
    }
}
