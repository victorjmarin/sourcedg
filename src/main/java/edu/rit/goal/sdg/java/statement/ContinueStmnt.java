package edu.rit.goal.sdg.java.statement;

public class ContinueStmnt implements Statement {

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

}
