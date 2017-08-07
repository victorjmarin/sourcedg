package edu.rit.goal.sdg.statement.control;

import java.util.List;

import edu.rit.goal.sdg.statement.Stmt;

public class SwitchBlockStmntGroup {

    private final List<String> switchLabels;
    private final List<Stmt> blockStmnts;

    public SwitchBlockStmntGroup(final List<String> switchLabels, final List<Stmt> blockStmnts) {
	super();
	this.switchLabels = switchLabels;
	this.blockStmnts = blockStmnts;
    }

    public List<String> getSwitchLabels() {
	return switchLabels;
    }

    public List<Stmt> getBlockStmnts() {
	return blockStmnts;
    }

}
