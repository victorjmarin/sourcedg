package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;

public class IfThenElseStmt implements Stmt {

    private final Expr condition;
    private final List<Stmt> thenBranch;
    private final List<Stmt> elseBranch;

    public IfThenElseStmt(final Expr condition, final List<Stmt> thenBranch,
	    final List<Stmt> elseBranch) {
	super();
	this.condition = condition;
	this.thenBranch = thenBranch;
	this.elseBranch = elseBranch;
    }

    public Expr getCondition() {
	return condition;
    }

    public List<Stmt> getThenBranch() {
	return thenBranch;
    }

    public List<Stmt> getElseBranch() {
	return elseBranch;
    }

    @Override
    public String toString() {
	return "if " + condition + " then " + thenBranch.toString() + " else " + elseBranch.toString();
    }

    @Override
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }
}
