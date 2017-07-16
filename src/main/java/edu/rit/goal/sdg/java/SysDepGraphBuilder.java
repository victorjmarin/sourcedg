package edu.rit.goal.sdg.java;

import java.util.List;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.statement.ArrayAccessAssignment;
import edu.rit.goal.sdg.java.statement.Assignment;
import edu.rit.goal.sdg.java.statement.BreakStmnt;
import edu.rit.goal.sdg.java.statement.ContinueStmnt;
import edu.rit.goal.sdg.java.statement.MethodInvocation;
import edu.rit.goal.sdg.java.statement.MethodInvocationAssignment;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.PostDecrementExpr;
import edu.rit.goal.sdg.java.statement.PostIncrementExpr;
import edu.rit.goal.sdg.java.statement.PreDecrementExpr;
import edu.rit.goal.sdg.java.statement.PreIncrementExpr;
import edu.rit.goal.sdg.java.statement.ReturnStmnt;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.VariableDecl;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.DoStmnt;
import edu.rit.goal.sdg.java.statement.control.EnhancedForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.SwitchStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;

/**
 * The list of vertices returned by each method represent the statements nested
 * immediately.
 */

public interface SysDepGraphBuilder {

    SysDepGraph fromSource(final String program);

    void methodSignature(MethodSignature methodSignature, SysDepGraph sdg);

    List<Vertex> assignment(Assignment assignment, SysDepGraph sdg, boolean isNested, List<Statement> scope,
	    boolean isForStmntHeader, boolean isLoopBody);

    List<Vertex> arrayAccessAssignment(ArrayAccessAssignment arrayAccessAssignment, SysDepGraph sdg, boolean isNested,
	    List<Statement> scope, boolean isForStmntHeader);

    List<Vertex> basicForStmnt(BasicForStmnt basicForStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> breakStmnt(BreakStmnt breakStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> continueStmnt(ContinueStmnt continueStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> variableDeclaration(VariableDecl variableDecl, SysDepGraph sdg, boolean isNested,
	    List<Statement> scope, boolean isForStmntHeader);

    List<Vertex> enhancedForStmnt(EnhancedForStmnt enhancedForStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> ifThenElseStmnt(IfThenElseStmnt ifThenElseStmnt, SysDepGraph sdg, boolean isNested,
	    boolean isLoopBody);

    List<Vertex> ifThenStmnt(IfThenStmnt ifThenStmnt, SysDepGraph sdg, boolean isNested, boolean isLoopBody);

    List<Vertex> whileStmnt(WhileStmnt whileStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> doStmnt(DoStmnt doStmnt, SysDepGraph sdg, boolean isNested);

    List<Vertex> switchStmnt(SwitchStmnt switchStmnt, SysDepGraph sdg, boolean isNested, boolean isLoopBody);

    List<Vertex> methodInvocation(MethodInvocation methodInvocation, SysDepGraph sdg, boolean isNested);

    List<Vertex> methodInvocationAssignment(MethodInvocationAssignment methodInvocationAssignment, SysDepGraph sdg,
	    boolean isNested);

    List<Vertex> postIncrementExpr(PostIncrementExpr postIncrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmntHeader, boolean isLoopBody);

    List<Vertex> postDecrementExpr(PostDecrementExpr postDecrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmntHeader, boolean isLoopBody);

    List<Vertex> preIncrementExpr(PreIncrementExpr preIncrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmntHeader, boolean isLoopBody);

    List<Vertex> preDecrementExpr(PreDecrementExpr preDecrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmntHeader, boolean isLoopBody);

    List<Vertex> returnStmnt(ReturnStmnt returnStmnt, SysDepGraph sdg, boolean isNested);

}
