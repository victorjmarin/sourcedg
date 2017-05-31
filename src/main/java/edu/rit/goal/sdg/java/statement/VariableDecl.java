package edu.rit.goal.sdg.java.statement;

import java.util.ArrayList;
import java.util.List;

public class VariableDecl implements Statement {

    private final String variableDeclaratorId;
    private final Expression variableInitializer;

    public VariableDecl(final String variableDeclaratorId, final Expression variableInitializer) {
	super();
	this.variableDeclaratorId = variableDeclaratorId;
	this.variableInitializer = variableInitializer;
    }

    public String getVariableDeclaratorId() {
	return variableDeclaratorId;
    }

    public Expression getVariableInitializer() {
	return variableInitializer;
    }

    @Override
    public String toString() {
	return variableDeclaratorId + "=" + variableInitializer;
    }

    @Override
    public List<Statement> expandScope() {
	final List<Statement> result = new ArrayList<>();
	result.add(this);
	return result;
    }

}
