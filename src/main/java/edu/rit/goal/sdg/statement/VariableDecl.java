package edu.rit.goal.sdg.statement;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Stmt> expandScope() {
	final List<Stmt> result = new ArrayList<>();
	result.add(this);
	return result;
    }

}
