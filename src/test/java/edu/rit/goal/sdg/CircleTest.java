package edu.rit.goal.sdg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ext.ComponentNameProvider;
import org.jgrapht.ext.ExportException;
import org.jgrapht.ext.GraphMLExporter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.rit.goal.sdg.java.SysDepGraphBuilder;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.java.graph.Edge;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;

public class CircleTest {

    private SysDepGraph sdg;

    @Before
    public void parseTestProgram() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/Circle.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.HORWITZ);
	sdg = builder.fromSource(program);
	final GraphMLExporter<Vertex, Edge> exporter = new GraphMLExporter<Vertex, Edge>();
	exporter.setEdgeLabelAttributeName("label");
	exporter.setVertexLabelAttributeName("label");
	exporter.setEdgeLabelProvider(new ComponentNameProvider<Edge>() {
	    @Override
	    public String getName(final Edge edge) {
		return edge.toString();
	    }
	});
	exporter.setVertexLabelProvider(new ComponentNameProvider<Vertex>() {

	    @Override
	    public String getName(final Vertex vtx) {
		return vtx.toString();
	    }
	});
	final String path = "/Users/goal/Desktop/sdg";
	exporter.exportGraph(sdg, new File(path + ".graphml"));
	System.out.println(sdg);
    }

    @Test
    public void enterShouldBeParsed() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final List<String> params = vertices.stream().map(v -> v.getLabel()).collect(Collectors.toList());
	Assert.assertTrue(params.contains("program"));
    }

    // @Test
    // public void formalInShouldBeParsed() {
    // final Set<Vertex> vertices = sdg.vertexSet();
    // final List<String> params = vertices.stream().map(v ->
    // v.getAst()).collect(Collectors.toList());
    // Assert.assertTrue(params.contains("rad"));
    // }

}
