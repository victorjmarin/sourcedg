package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class BasicForStmnt implements Statement {

    private final List<Statement> init;
    private final Expression condition;
    private final List<Statement> update;
    private final List<Statement> body;

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

    public Expression getCondition() {
	return condition;
    }

    public List<Statement> getUpdate() {
	return update;
    }

    public List<Statement> getBody() {
	return body;
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

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
