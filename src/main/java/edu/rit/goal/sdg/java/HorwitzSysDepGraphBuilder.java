package edu.rit.goal.sdg.java;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.graph.VertexType;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.NotImplementedStmnt;
import edu.rit.goal.sdg.java.statement.VariableDecl;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;

public class HorwitzSysDepGraphBuilder extends AbstractSysDepGraphBuilder {

    @Override
    public void methodSignature(final MethodSignature methodSignature, final SysDepGraph sdg) {
	final Vertex v1 = new Vertex(VertexType.ENTER, methodSignature.getName());
	sdg.addVertex(v1);
	methodSignature.getParams().forEach(p -> {
	    final Vertex v2 = new Vertex(VertexType.FORMAL_IN, p.getVariableDeclaratorId());
	    sdg.addVertex(v2);
	});
	System.out.println(methodSignature.toString());
    }

    @Override
    public void basicForStmnt(final BasicForStmnt basicForStmnt, final SysDepGraph sdg) {
	System.out.println(basicForStmnt.toString());
    }

    @Override
    public void ifThenElseStmnt(final IfThenElseStmnt ifThenElseStmnt, final SysDepGraph sdg) {
	// TODO Auto-generated method stub

    }

    @Override
    public void ifThenStmnt(final IfThenStmnt ifThenStmnt, final SysDepGraph sdg) {
	// TODO Auto-generated method stub
    }

    @Override
    public void whileStmnt(final WhileStmnt whileStmnt, final SysDepGraph sdg) {
	// TODO Auto-generated method stub

    }

    @Override
    public void variableDeclaration(final VariableDecl variableDecl, final SysDepGraph sdg) {
	final Vertex v = new Vertex(VertexType.DECL, variableDecl.toString());
	sdg.addVertex(v);
	System.out.println(variableDecl.toString());
    }

    @Override
    public void notImplementedStmnt(final NotImplementedStmnt notImplementedStmnt, final SysDepGraph sdg) {
	System.out.println(notImplementedStmnt.toString());
    }

}
