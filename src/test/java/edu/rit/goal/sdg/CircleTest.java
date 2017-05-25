package edu.rit.goal.sdg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.rit.goal.sdg.java.Java8SdgBuilder;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;

public class CircleTest {

    private SysDepGraph sdg;

    @Before
    public void parseTestProgram() throws IOException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/Circle.java")));
	final Java8SdgBuilder javaParser = new Java8SdgBuilder();
	sdg = javaParser.buildSdg(program);
    }

    @Test
    public void enterShouldBeParsed() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final List<String> params = vertices.stream().map(v -> v.getAst()).collect(Collectors.toList());
	Assert.assertTrue(params.contains("program"));
    }

//    @Test
//    public void formalInShouldBeParsed() {
//	final Set<Vertex> vertices = sdg.vertexSet();
//	final List<String> params = vertices.stream().map(v -> v.getAst()).collect(Collectors.toList());
//	Assert.assertTrue(params.contains("rad"));
//    }

}
