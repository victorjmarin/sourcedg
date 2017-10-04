package edu.rit.goal.sdg.java8.visitor;

import java.util.List;
import java.util.Set;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;

public class SwitchBlockStatementGroup {

    public Set<Str> labels;
    public List<Stmt> stmts;

    public SwitchBlockStatementGroup(final Set<Str> labels, final List<Stmt> stmts) {
	super();
	this.labels = labels;
	this.stmts = stmts;
    }

    @Override
    public String toString() {
	return labels + " -> " + stmts;
    }

}
