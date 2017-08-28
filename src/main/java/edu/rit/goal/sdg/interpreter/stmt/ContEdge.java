package edu.rit.goal.sdg.interpreter.stmt;

import java.util.Deque;

import edu.rit.goal.sdg.statement.Stmt;

public class ContEdge implements Stmt {

    public CtrlVertex cv;
    public Deque<CtrlVertex> S;

    public ContEdge(final CtrlVertex cv, final Deque<CtrlVertex> S) {
	super();
	this.cv = cv;
	this.S = S;
    }

    @Override
    public String toString() {
	return "contedge " + cv + " " + S;
    }

}
