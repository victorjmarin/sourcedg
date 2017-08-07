package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;

public class IfThenStmnt implements Stmt {

    private final Expr condition;
    private final List<Stmt> thenBranch;

    public IfThenStmnt(final Expr condition, final List<Stmt> thenBranch) {
	super();
	this.condition = condition;
	this.thenBranch = thenBranch;
    }

    public Expr getCondition() {
	return condition;
    }

    public List<Stmt> getThenBranch() {
	return thenBranch;
    }

    public List<Stmt> expandScope() {
	return thenBranch;
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
