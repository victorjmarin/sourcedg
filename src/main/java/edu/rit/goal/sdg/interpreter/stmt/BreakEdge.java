package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Deque;

import edu.rit.goal.sdg.statement.Stmt;

public class BreakEdge implements Stmt {

    public CtrlVertex cv;
    public Deque<CtrlVertex> S;

    public BreakEdge(final CtrlVertex cv, final Deque<CtrlVertex> S) {
	super();
	this.cv = cv;
	this.S = S;
    }

    @Override
    public String toString() {
	return "breakedge " + cv + " " + S;
    }

}
