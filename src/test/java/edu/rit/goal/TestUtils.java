package edu.rit.goal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.ComponentNameProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.ExportException;
import org.jgrapht.ext.IntegerComponentNameProvider;

import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.Vertex;

public class TestUtils {

    public static void exportAsDot(final Graph<Vertex, Edge> graph, final String fileName) {
	final ComponentNameProvider<Vertex> vertexLabelProvider = new ComponentNameProvider<Vertex>() {
	    @Override
	    public String getName(final Vertex component) {
		return component.getId() + "-" + component.getType() + "\n"
			+ component.getLabel().replaceAll("\"", "'");
	    }
	};
	final ComponentNameProvider<Edge> edgeLabelProvider = new ComponentNameProvider<Edge>() {
	    @Override
	    public String getName(final Edge component) {
		return component.toString().replaceAll("\"", "'");
	    }
	};
	final ComponentAttributeProvider<Edge> edgeAttrProvider = new ComponentAttributeProvider<Edge>() {

	    @Override
	    public Map<String, String> getComponentAttributes(final Edge component) {
		final Map<String, String> result = new HashMap<>();
		if (component.getType() != null) {
		    switch (component.getType()) {
		    case CTRL_TRUE:
			result.put("splines", "compound");
			break;
		    case CALL:
			result.put("style", "dashed");
			break;
		    default:
			result.put("splines", "true");
			break;
		    }
		}
		return result;
	    }
	};
	final DOTExporter<Vertex, Edge> exporter = new DOTExporter<>(new IntegerComponentNameProvider<>(),
		vertexLabelProvider, null, null, edgeAttrProvider);
	final String path = "/Users/goal/Desktop/" + fileName;
	try {
	    exporter.exportGraph(graph, new File(path + ".dot"));
	} catch (final ExportException e) {
	    e.printStackTrace();
	}
    }

}
