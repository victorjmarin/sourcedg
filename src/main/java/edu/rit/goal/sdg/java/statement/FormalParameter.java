package edu.rit.goal.sdg.java.statement;

public class FormalParameter {

    private final String variableDeclaratorId;

    public FormalParameter(final String variableDeclaratorId) {
	super();
	this.variableDeclaratorId = variableDeclaratorId;
    }

    public String getVariableDeclaratorId() {
	return variableDeclaratorId;
    }

    @Override
    public String toString() {
	return variableDeclaratorId;
    }

}
