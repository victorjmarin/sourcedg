package edu.rit.goal.sdg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ext.ExportException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.rit.goal.sdg.java.SysDepGraphBuilder;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.java.graph.Edge;
import edu.rit.goal.sdg.java.graph.EdgeType;
import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.graph.Vertex;
import edu.rit.goal.sdg.java.graph.VertexType;

public class FactorialTest {

    private static SysDepGraph sdg;

    @BeforeClass
    public static void parseTestProgram() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/Factorial.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	sdg = builder.fromSource(program);
	System.out.println(sdg);
    }

    @Test
    public void enterShouldBeParsed() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final List<Vertex> enterVtcs = vertices.stream().filter(v -> v.getType().equals(VertexType.ENTER))
		.collect(Collectors.toList());
	System.out.println("Enter vertices");
	System.out.println("  " + enterVtcs);
	Assert.assertTrue(enterVtcs.size() == 2);
    }

    @Test
    public void getFactorialFirstLevelControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex enterVtx = vertices.stream().filter(v -> v.getLabel().equals("getFactorial")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(enterVtx);
	System.out.println("getFactorial first level control edges");
	outgoingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(outgoingEdges.size() == 5);
    }

    @Test
    public void escLAB3P1V1FirstLevelControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex enterVtx = vertices.stream().filter(v -> v.getLabel().equals("escLAB3P1V1")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(enterVtx);
	System.out.println("escLAB3P1V1 first level control edges");
	outgoingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(outgoingEdges.size() == 3);
    }

    @Test
    public void getFactorialLoopControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex enterVtx = vertices.stream().filter(v -> v.getLabel().equals("getFactorial")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(enterVtx);
	final Vertex cond = outgoingEdges.stream().filter(e -> e.getTarget().getType().equals(VertexType.COND))
		.findFirst().get().getTarget();
	final Set<Edge> edges = sdg.outgoingEdgesOf(cond);
	System.out.println("getFactorial loop control edges");
	edges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(edges.size() == 3);
    }

    @Test
    public void escLAB3P1V1LoopControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex enterVtx = vertices.stream().filter(v -> v.getLabel().equals("escLAB3P1V1")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(enterVtx);
	final Vertex cond = outgoingEdges.stream().filter(e -> e.getTarget().getType().equals(VertexType.COND))
		.findFirst().get().getTarget();
	final Set<Edge> edges = sdg.outgoingEdgesOf(cond);
	System.out.println("escLAB3P1V1 loop control edges");
	edges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(edges.size() == 6);
    }

    @Test
    public void escLAB3P1V1FirstIfControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex cond = vertices.stream().filter(v -> v.getLabel().equals("currentFact<=k&&k<nextFact")).findFirst()
		.get();
	final Set<Edge> edges = sdg.outgoingEdgesOf(cond);
	System.out.println("escLAB3P1V1 first if control edges");
	edges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(edges.size() == 2);
    }

    @Test
    public void escLAB3P1V1SecondIfControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex cond = vertices.stream().filter(v -> v.getLabel().equals("currentFact>k")).findFirst().get();
	final Set<Edge> edges = sdg.outgoingEdgesOf(cond);
	System.out.println("escLAB3P1V1 second if control edges");
	edges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(edges.size() == 1);
    }

    @Test
    public void getFactorialLoopDataEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex enterVtx = vertices.stream().filter(v -> v.getLabel().equals("getFactorial")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(enterVtx);
	final Vertex cond = outgoingEdges.stream().filter(e -> e.getTarget().getType().equals(VertexType.COND))
		.findFirst().get().getTarget();
	final Set<Edge> edges = sdg.incomingEdgesOf(cond).stream().filter(e -> e.getType().equals(EdgeType.FLOW))
		.collect(Collectors.toSet());
	System.out.println("getFactorial loop data edges");
	edges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(edges.size() == 3);
    }
    
    @Test
    public void escLAB3P1V1LoopDataEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex enterVtx = vertices.stream().filter(v -> v.getLabel().equals("escLAB3P1V1")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(enterVtx);
	final Vertex cond = outgoingEdges.stream().filter(e -> e.getTarget().getType().equals(VertexType.COND))
		.findFirst().get().getTarget();
	final Set<Edge> edges = sdg.incomingEdgesOf(cond).stream().filter(e -> e.getType().equals(EdgeType.FLOW))
		.collect(Collectors.toSet());
	System.out.println("escLAB3P1V1 loop data edges");
	edges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(edges.size() == 3);
    }

}
