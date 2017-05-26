package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class BasicForStmnt implements Statement {

    private List<Statement> init;
    private Expression condition;
    private List<Statement> update;
    private List<Statement> body;

    public BasicForStmnt(final List<Statement> init, final Expression condition, final List<Statement> update,
	    final List<Statement> body) {
	super();
	this.init = init;
	this.condition = condition;
	this.update = update;
	this.body = body;
    }

    public List<Statement> getInit() {
	return init;
    }

    public void setInit(final List<Statement> init) {
	this.init = init;
    }

    public Expression getCondition() {
	return condition;
    }

    public void setCondition(final Expression condition) {
	this.condition = condition;
    }

    public List<Statement> getUpdate() {
	return update;
    }

    public void setUpdate(final List<Statement> update) {
	this.update = update;
    }

    public List<Statement> getBody() {
	return body;
    }

    public void setBody(final List<Statement> body) {
	this.body = body;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("for (");
	sb.append(init);
	sb.append("; ");
	sb.append(condition);
	sb.append("; ");
	sb.append(update);
	sb.append(") {");
	sb.append("\n");
	body.forEach(b -> {
	    sb.append(b);
	    sb.append("\n");
	});
	sb.append("}");
	return sb.toString();
    }

}
