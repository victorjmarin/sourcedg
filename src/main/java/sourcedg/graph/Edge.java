package sourcedg.graph;

import java.io.Serializable;

public class Edge implements Serializable {
	private static final long serialVersionUID = 5811583473376562089L;

	private Vertex source;
	private Vertex target;
	private EdgeType type;

	public Edge() {
	}

	public Edge(final Vertex source, final Vertex target) {
		this.source = source;
		this.target = target;
	}

	public Edge(final Vertex source, final Vertex target, final EdgeType type) {
		this.source = source;
		this.target = target;
		this.type = type;
	}

	public Vertex getSource() {
		return source;
	}

	public Vertex getTarget() {
		return target;
	}

	public EdgeType getType() {
		return type;
	}

	public boolean isControl() {
		return type.isControl();
	}

	@Override
	public String toString() {
		final String result = type != null ? type.toString() : "";
		return result;
	}

}
