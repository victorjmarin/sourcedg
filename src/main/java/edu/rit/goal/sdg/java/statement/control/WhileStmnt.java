package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class WhileStmnt implements Statement {

    private final Expression condition;
    private final List<Statement> body;

    public WhileStmnt(final Expression condition, final List<Statement> body) {
	super();
	this.condition = condition;
	this.body = body;
    }

    public Expression getCondition() {
	return condition;
    }

    public List<Statement> getBody() {
	return body;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("while ");
	sb.append(condition);
	sb.append(" {\n");
	body.forEach(b -> {
	    sb.append(b);
	    sb.append("\n");
	});
	sb.append("}");
	return sb.toString();
    }

}
