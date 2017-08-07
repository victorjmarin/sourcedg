package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Expr;
import edu.rit.goal.sdg.statement.Stmt;

public class SwitchStmnt implements Stmt {

    private final Expr expr;
    private final List<SwitchBlockStmntGroup> blocks;

    public SwitchStmnt(final Expr expr, final List<SwitchBlockStmntGroup> blocks) {
	super();
	this.expr = expr;
	this.blocks = blocks;
    }

    @Override
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

    public Expr getExpr() {
	return expr;
    }

    public List<SwitchBlockStmntGroup> getBlocks() {
	return blocks;
    }

}
