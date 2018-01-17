package edu.rit.goal.sourcedg.graph;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.github.javaparser.ast.Node;

public class Vertex implements Serializable {
  private static final long serialVersionUID = -8461510336107098420L;

  private int id;
  private VertexType type;
  private String label;
  private String def;
  private Set<String> uses;
  private Set<VertexSubtype> subtypes;
  private Integer startLine;
  private Integer endLine;
  private final Set<Vertex> in;
  private Set<Vertex> out;
  private Node ast;
  private Integer originalLine;

  // Graphviz attributes
  private String fillColor;

  public Vertex(final String label) {
    this.label = label;
    uses = new HashSet<>();
    subtypes = new HashSet<>();
    in = new HashSet<>();
    out = new HashSet<>();
  }

  public Vertex(final int id) {
    this.id = id;
    uses = new HashSet<>();
    subtypes = new HashSet<>();
    in = new HashSet<>();
    out = new HashSet<>();
  }

  public Vertex(final VertexType type, final String label, final Node ast) {
    id = -1;
    this.type = type;
    this.label = label;
    uses = new HashSet<>();
    subtypes = new HashSet<>();
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
    uses = new HashSet<>();
    subtypes = new HashSet<>();
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

  public Set<String> getUses() {
    if (uses == null)
      uses = new HashSet<>();
    return uses;
  }

  public void setUses(final Set<String> readingVariables) {
    uses = readingVariables;
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

  public Set<VertexSubtype> getSubtypes() {
    return subtypes;
  }

  public void setSubtypes(final Set<VertexSubtype> subtypes) {
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
    uses = new HashSet<>();
  }

  public Integer getOriginalLine() {
    return originalLine;
  }

  public void setOriginalLine(final Integer originalLine) {
    this.originalLine = originalLine;
  }

  @Override
  public String toString() {
    String result = id + "-" + type;
    if (label != null && label != "")
      result += "-" + label;
    return result;
  }

}
