package edu.rit.goal.sdg.graph;

import java.util.HashSet;
import java.util.Set;

public class Vertex {

    private int id;
    private VertexType type;
    private String label;
    private String lookupId;
    private String assignedVariable;
    private Set<String> readingVariables;
    private Integer startLine;
    private Integer endLine;

    public Vertex() {
    }

    public Vertex(final int id) {
	this.id = id;
	readingVariables = new HashSet<>();
    }

    public Vertex(final int id, final VertexType type, final String label) {
	this(id, type, label, null);
    }

    public Vertex(final int id, final VertexType type, final String label, final String lookupId) {
	super();
	this.id = id;
	this.type = type;
	this.label = label;
	this.lookupId = lookupId;
	readingVariables = new HashSet<>();
    }

    public int getId() {
	return id;
    }

    public void setId(final int id) {
	this.id = id;
    }

    public void setType(final VertexType type) {
	this.type = type;
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

    public String getAssignedVariable() {
	return assignedVariable;
    }

    public void setAssignedVariable(final String assignedVariable) {
	this.assignedVariable = assignedVariable;
    }

    public Set<String> getReadingVariables() {
	if (readingVariables == null)
	    readingVariables = new HashSet<>();
	return readingVariables;
    }

    public void setReadingVariables(final Set<String> readingVariables) {
	this.readingVariables = readingVariables;
    }

    public Integer getStartLine() {
	return startLine;
    }

    public void setStartLine(final Integer startLine) {
	this.startLine = startLine;
    }

    public Integer getEndLine() {
	return endLine;
    }

    public void setEndLine(final Integer endLine) {
	this.endLine = endLine;
    }

    @Override
    public String toString() {
	String result = id + "-" + type;
	if (label != null && label != "")
	    result += "-" + label;
	return result;
    }

}
