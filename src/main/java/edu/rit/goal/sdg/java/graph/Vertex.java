package edu.rit.goal.sdg.java.graph;

public class Vertex {

    private VertexType type;
    private String label;
    private String lookupId;

    public Vertex() {
    }

    public Vertex(final VertexType type, final String label) {
	this(type, label, null);
    }

    public Vertex(final VertexType type, final String label, final String lookupId) {
	super();
	this.type = type;
	this.label = label;
	this.lookupId = lookupId;
    }

    public VertexType getType() {
	return type;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(final String label) {
	this.label = label;
    }

    public String getLookupId() {
	return lookupId;
    }

    public void setLookupId(final String lookupId) {
	this.lookupId = lookupId;
    }

    @Override
    public String toString() {
	return type + "-" + label;
    }

}
