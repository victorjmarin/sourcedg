package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;

public class WhileStmt implements Stmt {

    private final Expr condition;
    private final List<Stmt> body;

    public WhileStmt(final Expr condition, final List<Stmt> body) {
	super();
	this.condition = condition;
	this.body = body;
    }

    public Expr getCondition() {
	return condition;
    }

    public List<Stmt> getBody() {
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

    @Override
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
