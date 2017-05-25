package edu.rit.goal.sdg.java.statement;

public class FormalParameter {

    private String variableDeclaratorId;

    public FormalParameter(final String variableDeclaratorId) {
	super();
	this.variableDeclaratorId = variableDeclaratorId;
    }

    public String getVariableDeclaratorId() {
	return variableDeclaratorId;
    }

    public void setVariableDeclaratorId(final String variableDeclaratorId) {
	this.variableDeclaratorId = variableDeclaratorId;
    }

    @Override
    public String toString() {
	return variableDeclaratorId;
    }

}
