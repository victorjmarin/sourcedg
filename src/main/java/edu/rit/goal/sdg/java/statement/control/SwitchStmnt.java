package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;

public class SwitchStmnt implements Statement {

    private final Expression expr;
    private final List<SwitchBlockStmntGroup> blocks;

    public SwitchStmnt(final Expression expr, final List<SwitchBlockStmntGroup> blocks) {
	super();
	this.expr = expr;
	this.blocks = blocks;
    }

    @Override
    public List<Statement> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

    public Expression getExpr() {
	return expr;
    }

    public List<SwitchBlockStmntGroup> getBlocks() {
	return blocks;
    }

}
