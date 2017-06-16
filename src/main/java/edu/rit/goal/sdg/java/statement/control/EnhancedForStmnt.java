package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class EnhancedForStmnt implements Statement {

    private final String var;
    private final Expression iterable;
    private final List<Statement> body;

    public EnhancedForStmnt(final String var, final Expression iterable, final List<Statement> body) {
	super();
	this.var = var;
	this.iterable = iterable;
	this.body = body;
    }

    public String getVar() {
	return var;
    }

    public Expression getIterable() {
	return iterable;
    }

    public List<Statement> getBody() {
	return body;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("for (");
	sb.append(var);
	sb.append(": ");
	sb.append(iterable);
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
