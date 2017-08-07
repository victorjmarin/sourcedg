package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;

public class EnhancedForStmt implements Stmt {

    private final String var;
    private final Expr iterable;
    private final List<Stmt> body;

    public EnhancedForStmt(final String var, final Expr iterable, final List<Stmt> body) {
	super();
	this.var = var;
	this.iterable = iterable;
	this.body = body;
    }

    public String getVar() {
	return var;
    }

    public Expr getIterable() {
	return iterable;
    }

    public List<Stmt> getBody() {
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
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
