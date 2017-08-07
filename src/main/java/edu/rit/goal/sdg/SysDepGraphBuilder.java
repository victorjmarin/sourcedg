package edu.rit.goal.sdg;

import java.util.List;

import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.statement.ArrayAccessAssignment;
import edu.rit.goal.sdg.statement.Assignment;
import edu.rit.goal.sdg.statement.BreakStmt;
import edu.rit.goal.sdg.statement.ContinueStmt;
import edu.rit.goal.sdg.statement.MethodInvocation;
import edu.rit.goal.sdg.statement.MethodInvocationAssignment;
import edu.rit.goal.sdg.statement.MethodSignature;
import edu.rit.goal.sdg.statement.PostDecrementExpr;
import edu.rit.goal.sdg.statement.PostIncrementExpr;
import edu.rit.goal.sdg.statement.PreDecrementExpr;
import edu.rit.goal.sdg.statement.PreIncrementExpr;
import edu.rit.goal.sdg.statement.ReturnStmt;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.VariableDecl;
import edu.rit.goal.sdg.statement.control.BasicForStmt;
import edu.rit.goal.sdg.statement.control.DoStmt;
import edu.rit.goal.sdg.statement.control.EnhancedForStmt;
import edu.rit.goal.sdg.statement.control.IfThenElseStmt;
import edu.rit.goal.sdg.statement.control.IfThenStmt;
import edu.rit.goal.sdg.statement.control.SwitchStmt;
import edu.rit.goal.sdg.statement.control.WhileStmt;

/**
 * The list of vertices returned by each method represent the statements nested
 * immediately.
 */

public interface SysDepGraphBuilder {

    SysDepGraph from(final String program);

    void methodSignature(MethodSignature methodSignature, SysDepGraph sdg);

    List<Vertex> assignment(Assignment assignment, SysDepGraph sdg, boolean isNested, List<Stmt> scope,
	    boolean isForStmtHeader, boolean isLoopBody);

    List<Vertex> arrayAccessAssignment(ArrayAccessAssignment arrayAccessAssignment, SysDepGraph sdg, boolean isNested,
	    List<Stmt> scope, boolean isForStmtHeader);

    List<Vertex> basicForStmt(BasicForStmt basicForStmt, SysDepGraph sdg, boolean isNested);

    List<Vertex> breakStmt(BreakStmt breakStmt, SysDepGraph sdg, boolean isNested);

    List<Vertex> continueStmt(ContinueStmt continueStmt, SysDepGraph sdg, boolean isNested);

    List<Vertex> variableDeclaration(VariableDecl variableDecl, SysDepGraph sdg, boolean isNested, List<Stmt> scope,
	    boolean isForStmtHeader);

    List<Vertex> enhancedForStmt(EnhancedForStmt enhancedForStmt, SysDepGraph sdg, boolean isNested);

    List<Vertex> ifThenElseStmt(IfThenElseStmt ifThenElseStmt, SysDepGraph sdg, boolean isNested, boolean isLoopBody);

    List<Vertex> ifThenStmt(IfThenStmt ifThenStmt, SysDepGraph sdg, boolean isNested, boolean isLoopBody);

    List<Vertex> whileStmt(WhileStmt whileStmt, SysDepGraph sdg, boolean isNested);

    List<Vertex> doStmt(DoStmt doStmt, SysDepGraph sdg, boolean isNested);

    List<Vertex> switchStmt(SwitchStmt switchStmt, SysDepGraph sdg, boolean isNested, boolean isLoopBody);

    List<Vertex> methodInvocation(MethodInvocation methodInvocation, SysDepGraph sdg, boolean isNested);

    List<Vertex> methodInvocationAssignment(MethodInvocationAssignment methodInvocationAssignment, SysDepGraph sdg,
	    boolean isNested);

    List<Vertex> returnStmt(ReturnStmt returnStmt, SysDepGraph sdg, boolean isNested);

    List<Vertex> postIncrementExpr(PostIncrementExpr postIncrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmtHeader, boolean isLoopBody);

    List<Vertex> postDecrementExpr(PostDecrementExpr postDecrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmtHeader, boolean isLoopBody);

    List<Vertex> preIncrementExpr(PreIncrementExpr preIncrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmtHeader, boolean isLoopBody);

    List<Vertex> preDecrementExpr(PreDecrementExpr preDecrementExpr, SysDepGraph sdg, boolean isNested,
	    boolean isForStmtHeader, boolean isLoopBody);

}
