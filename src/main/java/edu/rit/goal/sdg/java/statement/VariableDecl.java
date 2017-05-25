package edu.rit.goal.sdg.java.statement;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.graph.VertexType;

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
	return variableDeclaratorId + " = " + variableInitializer;
    }

    @Override
    public void buildSdg(final SysDepGraph sdg) {
	final Vertex v = new Vertex(VertexType.DECL, toString());
	sdg.addVertex(v);
	System.out.println(toString());
    }

}
