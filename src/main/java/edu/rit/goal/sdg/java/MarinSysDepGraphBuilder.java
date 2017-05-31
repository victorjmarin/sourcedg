package edu.rit.goal.sdg.java;

import java.util.List;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.statement.Expression;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;

/**
 * Implementation of features not described in the paper by Horwitz and Reps.
 */
public class MarinSysDepGraphBuilder extends HorwitzRepsSysDepGraphBuilder {

    @Override
    public List<Vertex> basicForStmnt(final BasicForStmnt basicForStmnt, final SysDepGraph sdg, final boolean isNested) {
	final List<Statement> init = basicForStmnt.getInit();
	final Expression condition = basicForStmnt.getCondition();
	final List<Statement> update = basicForStmnt.getUpdate();
	final List<Statement> body = basicForStmnt.getBody();
	return null;
    }

}