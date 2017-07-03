package edu.rit.goal.sdg.java.statement.control;

import java.util.List;

import edu.rit.goal.sdg.java.statement.Statement;

public class SwitchBlockStmntGroup {

    private final List<String> switchLabels;
    private final List<Statement> blockStmnts;

    public SwitchBlockStmntGroup(final List<String> switchLabels, final List<Statement> blockStmnts) {
	super();
	this.switchLabels = switchLabels;
	this.blockStmnts = blockStmnts;
    }

    public List<String> getSwitchLabels() {
	return switchLabels;
    }

    public List<Statement> getBlockStmnts() {
	return blockStmnts;
    }

}
