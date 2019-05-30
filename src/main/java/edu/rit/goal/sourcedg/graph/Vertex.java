package edu.rit.goal.sourcedg.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.javaparser.ast.Node;

public class Vertex implements Serializable {
  private static final long serialVersionUID = -8461510336107098420L;

  private int id;
  private VertexType type;
  private String label;
  private String def;
  private Set<String> refs;
  private List<String> subtypes;
  private Integer startLine;
  private Integer endLine;
  private final Set<Vertex> in;
  private Set<Vertex> out;
  private Node ast;
  private Integer originalLine;
  private PDG pdg;

  // Graphviz attributes
  private String fillColor;

  private boolean visited;

  public Vertex(final String label) {
    this.label = label;
    refs = new HashSet<>();
    subtypes = new ArrayList<>();
    in = new HashSet<>();
    out = new HashSet<>();
  }

  public Vertex(final int id) {
    this.id = id;
    refs = new HashSet<>();
    subtypes = new ArrayList<>();
    in = new HashSet<>();
    out = new HashSet<>();
  }

  public Vertex(final VertexType type, final String label, final Node ast) {
    id = -1;
    this.type = type;
    this.label = label;
    refs = new HashSet<>();
    subtypes = new ArrayList<>();
    in = new HashSet<>();
    out = new HashSet<>();
    this.ast = ast;
  }

  public Vertex(final VertexType type, final String label) {
    this(-1, type, label);
  }

  public Vertex(final int id, final VertexType type, final String label) {
    this.id = id;
    this.type = type;
    this.label = label;
    refs = new HashSet<>();
    subtypes = new ArrayList<>();
    in = new HashSet<>();
    out = new HashSet<>();
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

  public String getDef() {
    return def;
  }

  public void setDef(final String assignedVariable) {
    def = assignedVariable;
  }

  public Set<String> getRefs() {
    if (refs == null)
      refs = new HashSet<>();
    return refs;
  }

  public void setRefs(final Set<String> readingVariables) {
    refs = readingVariables;
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

  public Set<Vertex> getIn() {
    return in;
  }

  public Set<Vertex> getOut() {
    return out;
  }

  public void setOut(final Set<Vertex> out) {
    this.out = out;
  }

  public List<String> getSubtypes() {
    return subtypes;
  }

  public void setSubtypes(final List<String> subtypes) {
    this.subtypes = subtypes;
  }

  public Node getAst() {
    return ast;
  }

  public String getFillColor() {
    return fillColor;
  }

  public void setFillColor(final String fillColor) {
    this.fillColor = fillColor;
  }

  public void resetDefUses() {
    def = null;
    refs = new HashSet<>();
  }

  public Integer getOriginalLine() {
    return originalLine;
  }

  public void setOriginalLine(final Integer originalLine) {
    this.originalLine = originalLine;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public PDG getPDG() {
    return pdg;
  }

  public void setPDG(PDG pdg) {
    this.pdg = pdg;
  }

  @Override
  public String toString() {
    String result = id + "-" + type;
    if (label != null && label != "")
      result += "-" + label;
    return result;
  }

}
