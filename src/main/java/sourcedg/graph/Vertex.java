package sourcedg.graph;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.github.javaparser.ast.Node;

public class Vertex implements Serializable {
	private static final long serialVersionUID = -8461510336107098420L;

	private long id;
	private String label;
	private String assignment;
	private String submission;
	private VertexType type;
	private Set<String> subtypes;
	private Integer startLine;
	private Integer endLine;
	private Integer line;
	private transient PDG pdg;
	private transient String def;
	private transient Set<String> uses;
	// Used for output dependence edges.
	private transient String pseudoUse;
	private final transient Set<Vertex> in;
	private transient Set<Vertex> out;
	private transient Node ast;

	// Graphviz attributes
	private String fillColor;

	private boolean visited;

	public Vertex(final String label) {
		this.label = label.replaceAll("\n", " ");
		uses = new HashSet<>();
		subtypes = new HashSet<>();
		in = new HashSet<>();
		out = new HashSet<>();
	}

	public Vertex(final long id) {
		this.id = id;
		uses = new HashSet<>();
		subtypes = new HashSet<>();
		in = new HashSet<>();
		out = new HashSet<>();
	}

	public Vertex(final VertexType type, final String label, final Node ast) {
		id = -1;
		this.type = type;
		this.label = label.replaceAll("\n", " ");
		uses = new HashSet<>();
		subtypes = new HashSet<>();
		in = new HashSet<>();
		out = new HashSet<>();
		this.ast = ast;
	}

	public Vertex(final VertexType type, final String label) {
		this(-1, type, label);
	}

	public Vertex(final long id, final VertexType type, final String label) {
		this.id = id;
		this.type = type;
		this.label = label.replaceAll("\n", " ");
		uses = new HashSet<>();
		subtypes = new HashSet<>();
		in = new HashSet<>();
		out = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label.replaceAll("\n", " ");
	}

	public String getDef() {
		return def;
	}

	public void setDef(final String def) {
		this.def = def;
	}

	public Set<String> getUses() {
		if (uses == null)
			uses = new HashSet<>();
		return uses;
	}

	public String getPseudoUse() {
		return pseudoUse;
	}

	public void setPseudoUse(String pseudoUse) {
		this.pseudoUse = pseudoUse;
	}

	public void setUses(final Set<String> uses) {
		this.uses = uses;
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

	public VertexType getType() {
		return type;
	}

	public void setType(final VertexType type) {
		this.type = type;
	}

	public Set<String> getSubtypes() {
		return subtypes;
	}

	public void setSubtypes(final Set<String> subtypes) {
		this.subtypes = subtypes;
	}

	public Set<String> getTypeAndSubtypes() {
		Set<String> result = new HashSet<>(subtypes);
		result.add(type.name());
		return result;
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

	public void clearDefUses() {
		def = null;
		uses = new HashSet<>();
	}

	public void clearUses() {
		uses = new HashSet<>();
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(final Integer line) {
		this.line = line;
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

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getSubmission() {
		return submission;
	}

	public void setSubmission(String submission) {
		this.submission = submission;
	}

	@Override
	public String toString() {
		String result = id + "-" + type;
		if (label != null && label != "")
			result += "-" + label;
		return result;
	}

//	public int hashCode() {
//		int res = Objects.hash(id, submission);
//		return res;
//	}
//
//	public boolean equals(Object o) {
//		if (!(o instanceof Vertex))
//			return false;
//		Vertex v = (Vertex) o;
//		return v.getId() == id && v.getSubmission().equals(submission);
//	}

}
