package edu.rit.goal.sdg.interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import org.jgrapht.DirectedGraph;

import edu.rit.goal.TestUtils;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class Main {

    public static void main(final String[] args) throws IOException {
	final long t = System.currentTimeMillis();
	final String program = new String(Files.readAllBytes(Paths.get("programs/java8/ref1.java")));
	final Translator translator = new Translator();
	final Stmt stmt = translator.from(program);
	System.out.println(stmt);
	final Program p = InterpreterExt.interpret(new Program(stmt));
	final SysDepGraph sdg = p.sdg;
	final Map<String, DirectedGraph<Vertex, Edge>> methodSubgraphs = sdg.getMethodSubgraphs();
	System.out.println(System.currentTimeMillis() - t + " ms. to build the PDG");
	System.out.println(sdg);
	sdg.computeDataFlow();
	final DirectedGraph<Vertex, Edge> und = p.F.get("escLAB3P1V1");
	TestUtils.exportAsDot(sdg, "und");
	for (final Entry<String, DirectedGraph<Vertex, Edge>> e : methodSubgraphs.entrySet()) {
	    final DirectedGraph<Vertex, Edge> g = e.getValue();
	    TestUtils.exportAsDot(g, e.getKey() + "Flow");
	    // TestUtils.exportAsDot(new FlowGraph(e.getValue()).graph, e.getKey() +
	    // "Flow");
	}
    }

}
