package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

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
	final StringBuilder sb = new StringBuilder();
	sb.append("if ");
	sb.append(condition);
	sb.append(" {");
	thenBranch.forEach(b -> {
	    sb.append(b);
	    sb.append(";");
	});
	sb.append("}");
	return sb.toString();
    }

}
