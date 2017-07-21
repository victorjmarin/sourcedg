package edu.rit.goal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.ComponentNameProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.ExportException;
import org.jgrapht.ext.IntegerComponentNameProvider;

import edu.rit.goal.sdg.java.graph.Edge;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;

public class TestUtils {

    public static void exportAsDot(final SysDepGraph sdg, final String fileName) {
	final ComponentNameProvider<Vertex> vertexLabelProvider = new ComponentNameProvider<Vertex>() {
	    @Override
	    public String getName(final Vertex component) {
		return component.getType() + "\n" + component.getLabel().replaceAll("\"", "'");
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
		switch (component.getType()) {
		case CTRL_TRUE:
		    result.put("splines", "line");
		    break;
		default:
		    result.put("splines", "curved");
		    break;
		}
		return result;
	    }
	};
	final DOTExporter<Vertex, Edge> exporter = new DOTExporter<>(new IntegerComponentNameProvider<>(),
		vertexLabelProvider, edgeLabelProvider, null, edgeAttrProvider);
	final String path = "/Users/goal/Desktop/" + fileName;
	try {
	    exporter.exportGraph(sdg, new File(path + ".dot"));
	} catch (final ExportException e) {
	    e.printStackTrace();
	}
    }

}
