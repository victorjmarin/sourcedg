package edu.rit.goal;

import java.io.File;

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
		return component.toString();
	    }
	};
	final ComponentNameProvider<Edge> edgeLabelProvider = new ComponentNameProvider<Edge>() {
	    @Override
	    public String getName(final Edge component) {
		return component.toString();
	    }
	};
	final DOTExporter<Vertex, Edge> exporter = new DOTExporter<>(new IntegerComponentNameProvider<>(),
		vertexLabelProvider, edgeLabelProvider);
	final String path = "/Users/goal/Desktop/" + fileName;
	try {
	    exporter.exportGraph(sdg, new File(path + ".dot"));
	} catch (final ExportException e) {
	    e.printStackTrace();
	}
    }

}
