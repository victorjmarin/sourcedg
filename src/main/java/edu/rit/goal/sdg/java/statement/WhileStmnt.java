package edu.rit.goal.sdg.java.statement;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;

public class WhileStmnt implements Statement {

    private Vertex guard;
    private Statement body;

    public Vertex getGuard() {
	return guard;
    }

    public void setGuard(final Vertex guard) {
	this.guard = guard;
    }

    public Statement getBody() {
	return body;
    }

    public void setBody(final Statement body) {
	this.body = body;
    }

    @Override
    public void buildSdg(final SysDepGraph sdg) {
	// TODO Auto-generated method stub

    }

}
