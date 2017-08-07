package edu.rit.goal.sdg.statement;

import java.util.List;

public class ContinueStmnt implements Stmt {

    private final String gotoLabel;

    public ContinueStmnt(final String gotoLabel) {
	super();
	this.gotoLabel = gotoLabel;
    }

    public String getGotoLabel() {
	return gotoLabel;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("continue");
	if (gotoLabel != null) {
	    sb.append(" ");
	    sb.append(gotoLabel);
	}
	return sb.toString();
    }

    @Override
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
