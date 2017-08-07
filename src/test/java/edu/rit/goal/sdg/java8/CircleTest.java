package edu.rit.goal.sdg.java8;

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

import edu.rit.goal.TestUtils;
import edu.rit.goal.sdg.SysDepGraphBuilder;
import edu.rit.goal.sdg.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;

public class CircleTest {

    private static SysDepGraph sdg;

    @BeforeClass
    public static void parseTestProgram() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/java8/Circle.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	sdg = builder.from(program);
	TestUtils.exportAsDot(sdg, "circle");
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
    public void mainFirstLevelControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex enterVtx = vertices.stream().filter(v -> v.getLabel().equals("main")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(enterVtx);
	System.out.println("main first level control edges");
	outgoingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(outgoingEdges.size() == 8);
    }

    @Test
    public void mult3FirstLevelControlEdges() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex mult3Vtx = vertices.stream().filter(v -> v.getLabel().equals("mult3")).findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(mult3Vtx);
	System.out.println("mult3 first level control edges");
	outgoingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(outgoingEdges.size() == 6);
    }

    @Test
    public void callMult3AreaNodes() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex mult3Vtx = vertices.stream().filter(v -> v.getLabel().equals("area=mult3[p, rad, rad]"))
		.findFirst().get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(mult3Vtx);
	System.out.println("Outgoing edges of area=mult3[p, rad, rad]");
	outgoingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(outgoingEdges.size() == 6);
    }

    @Test
    public void callMult3CircNodes() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex mult3Vtx = vertices.stream().filter(v -> v.getLabel().equals("circ=mult3[2, p, rad]")).findFirst()
		.get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(mult3Vtx);
	System.out.println("Outgoing edges of circ=mult3[2, p, rad]");
	outgoingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(outgoingEdges.size() == 6);
    }

    @Test
    public void globalVariable() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex globalVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.INITIAL_STATE) && v.getLabel().equals("debug")).findFirst()
		.get();
	final Set<Edge> outgoingEdges = sdg.outgoingEdgesOf(globalVtx);
	System.out.println("Outgoing edges of global var. debug");
	outgoingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(outgoingEdges.size() == 1);
    }

    @Test
    public void mult3FormalParam1() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.FORMAL_IN) && v.getLabel().equals("op1")).findFirst().get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("Incoming edges of formal param. 1");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 3);
    }

    @Test
    public void mult3FormalParam2() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.FORMAL_IN) && v.getLabel().equals("op2")).findFirst().get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("Incoming edges of formal param. 2");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 3);
    }

    @Test
    public void mult3FormalParam3() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.FORMAL_IN) && v.getLabel().equals("op3")).findFirst().get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("Incoming edges of formal param. 3");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 3);
    }

    @Test
    public void inResultParam() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.FORMAL_IN) && v.getLabel().equals("mult3ResultIn"))
		.findFirst().get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("ResultIn");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 3);
    }

    @Test
    public void outResultParam() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.FORMAL_OUT) && v.getLabel().equals("mult3ResultOut"))
		.findFirst().get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("ResultOut");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 2);
    }

    @Test
    public void areaResultParam() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.ACTUAL_OUT) && v.getLabel().equals("area")).findFirst()
		.get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("Area result");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 2);
    }

    @Test
    public void circResultParam() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream()
		.filter(v -> v.getType().equals(VertexType.ACTUAL_OUT) && v.getLabel().equals("circ")).findFirst()
		.get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("Circ result");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 2);
    }

    @Test
    public void areaOutput() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream().filter(v -> v.toString().equals("CALL-System.out.println[area]"))
		.findFirst().get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("Area output");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 2);
    }

    @Test
    public void circOutput() {
	final Set<Vertex> vertices = sdg.vertexSet();
	final Vertex paramVtx = vertices.stream().filter(v -> v.toString().equals("CALL-System.out.println[circ]"))
		.findFirst().get();
	final Set<Edge> incomingEdges = sdg.incomingEdgesOf(paramVtx);
	System.out.println("Circ output");
	incomingEdges.forEach(e -> {
	    System.out.println("  " + e + "=(" + e.getSource() + "," + e.getTarget() + ")");
	});
	Assert.assertTrue(incomingEdges.size() == 2);
    }

}
