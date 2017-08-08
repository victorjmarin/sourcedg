package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;

public class BasicForStmt implements Stmt {

    private final List<Stmt> init;
    private final Expr condition;
    private final List<Stmt> update;
    private final List<Stmt> body;

    public BasicForStmt(final List<Stmt> init, final Expr condition, final List<Stmt> update,
	    final List<Stmt> body) {
	super();
	this.init = init;
	this.condition = condition;
	this.update = update;
	this.body = body;
    }

    public List<Stmt> getInit() {
	return init;
    }

    public Expr getCondition() {
	return condition;
    }

    public List<Stmt> getUpdate() {
	return update;
    }

    public List<Stmt> getBody() {
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

}
