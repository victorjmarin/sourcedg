package edu.rit.goal.sdg.java.statement;

public class BreakStmnt implements Statement {

    private String gotoLabel;

    public BreakStmnt(final String gotoLabel) {
	super();
	this.gotoLabel = gotoLabel;
    }

    public String getGotoLabel() {
	return gotoLabel;
    }

    public void setGotoLabel(final String gotoLabel) {
	this.gotoLabel = gotoLabel;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("break");
	if (gotoLabel != null) {
	    sb.append(" ");
	    sb.append(gotoLabel);
	}
	return sb.toString();
    }

}
