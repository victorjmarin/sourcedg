package sourcedg.graph;

import java.util.Optional;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;

import sourcedg.analysis.PDGSlicer;

public class PDG extends DefaultDirectedGraph<Vertex, Edge> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4208713030783810103L;

	private String pathToProgram;

	public PDG() {
		super(Edge.class);
	}

	public Vertex actualOut(final Vertex v) {
		final Set<Edge> edges = outgoingEdgesOf(v);
		Vertex successor = null;
		for (final Edge e : edges) {
			successor = e.getTarget();
			if (VertexType.ACTUAL_OUT.equals(successor.getType())) {
				return successor;
			}
		}
		return null;
	}

	public Vertex getVertexWithId(final int id) {
		final Optional<Vertex> v = vertexSet().stream().filter(u -> u.getId() == id).findFirst();
		if (v.isPresent())
			return v.get();
		return null;
	}

	public Vertex getEntry() {
		return vertexSet().stream().filter(n -> VertexType.ENTRY.equals(n.getType())).findFirst().get();
	}

	public Set<Vertex> backwardSlice(final Set<Vertex> S) {
		return PDGSlicer.backward(this, S);
	}

	public Set<Vertex> forwardSlice(final Set<Vertex> S) {
		return PDGSlicer.forward(this, S);
	}

	public String getPathToProgram() {
		return pathToProgram;
	}

	public void setPathToProgram(String pathToProgram) {
		this.pathToProgram = pathToProgram;
	}

}
