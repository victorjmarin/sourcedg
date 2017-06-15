package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class DoStmnt implements Statement {

    private List<Statement> body;
    private Expression condition;

    public DoStmnt(final List<Statement> body, final Expression condition) {
	super();
	this.body = body;
	this.condition = condition;
    }

    public List<Statement> getBody() {
	return body;
    }

    public void setBody(final List<Statement> body) {
	this.body = body;
    }

    public Expression getCondition() {
	return condition;
    }

    public void setCondition(final Expression condition) {
	this.condition = condition;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("do {\n");
	body.forEach(b -> {
	    sb.append(b);
	    sb.append("\n");
	});
	sb.append("} while ");
	sb.append(condition);
	return sb.toString();
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
