package edu.rit.goal.sdg.java.statement;

import java.util.List;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.graph.VertexType;

public class MethodSignature implements Statement {

    private final String name;
    private final List<FormalParameter> params;

    public MethodSignature(final String name, final List<FormalParameter> params) {
	super();
	this.name = name;
	this.params = params;
    }

    public String getName() {
	return name;
    }

    public List<FormalParameter> getParams() {
	return params;
    }

    @Override
    public String toString() {
	return name + params.toString();
    }

    @Override
    public void buildSdg(final SysDepGraph sdg) {
	final Vertex v1 = new Vertex(VertexType.ENTER, name);
	sdg.addVertex(v1);
	params.forEach(p -> {
	    final Vertex v2 = new Vertex(VertexType.FORMAL_IN, p.getVariableDeclaratorId());
	    sdg.addVertex(v2);
	});
	System.out.println(toString());
    }

}
