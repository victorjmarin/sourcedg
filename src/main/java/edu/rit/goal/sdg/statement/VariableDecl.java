package edu.rit.goal.sdg.statement;

public class VariableDecl implements Stmt {

    private final String variableDeclaratorId;
    private final Expr variableInitializer;

    public VariableDecl(final String variableDeclaratorId, final Expr variableInitializer) {
	super();
	this.variableDeclaratorId = variableDeclaratorId;
	this.variableInitializer = variableInitializer;
    }

    public String getVariableDeclaratorId() {
	return variableDeclaratorId;
    }

    public Expr getVariableInitializer() {
	return variableInitializer;
    }

    @Override
    public String toString() {
	return variableDeclaratorId + "=" + variableInitializer;
    }

}
