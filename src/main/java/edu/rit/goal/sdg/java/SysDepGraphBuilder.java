package edu.rit.goal.sdg.java;

import java.util.List;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.statement.Assignment;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.NotImplementedStmnt;
import edu.rit.goal.sdg.java.statement.ReturnStmnt;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.VariableDecl;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;

/**
 * The list of vertices returned by each method represent the statements nested
 * immediately.
 */

public interface SysDepGraphBuilder {

    SysDepGraph fromSource(final String program);

    void methodSignature(MethodSignature methodSignature, SysDepGraph sdg);

    List<Vertex> basicForStmnt(BasicForStmnt basicForStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> ifThenElseStmnt(IfThenElseStmnt ifThenElseStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> ifThenStmnt(IfThenStmnt ifThenStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> whileStmnt(WhileStmnt whileStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> variableDeclaration(VariableDecl variableDecl, SysDepGraph sdg, boolean isNested,
	    List<Statement> scope);

    List<Vertex> assignment(Assignment assignment, SysDepGraph sdg, boolean isNested, List<Statement> scope);

    List<Vertex> methodInvocation(MethodInvocation methodInvocation, SysDepGraph sdg, boolean isNested);

    List<Vertex> returnStmnt(ReturnStmnt returnStmnt, SysDepGraph sdg, boolean isNested);

    void notImplementedStmnt(NotImplementedStmnt notImplementedStmnt, SysDepGraph sdg);

}
