package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;

public class DoStmt implements Stmt {

    private List<Stmt> body;
    private Expr condition;

    public DoStmt(final List<Stmt> body, final Expr condition) {
	super();
	this.body = body;
	this.condition = condition;
    }

    public List<Stmt> getBody() {
	return body;
    }

    public void setBody(final List<Stmt> body) {
	this.body = body;
    }

    public Expr getCondition() {
	return condition;
    }

    public void setCondition(final Expr condition) {
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

}
