package edu.rit.goal.sourcedg.validation;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import edu.rit.goal.sourcedg.builder.PDGBuilder;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.validation.SubgraphQuery.SubgraphQueryNode;

public class Validate {

	public static void main(String[] args) throws Exception {
		byte[] encoded = Files.readAllBytes(Paths.get(new File("programs/java8/validation/IfNoFinalStmt.java").toURI()));
		String programStr = new String(encoded, Charset.forName("UTF-8"));

		PDGBuilder builder = new PDGBuilder();
		builder.build(programStr);
		for (CFG g : builder.getCfgs()) {
			// TODO 0: Get all if nodes.
			
			SubgraphQuery q = SubgraphQuery.buildIfNoTerminalQuery();
			SubgraphMatching match = new SubgraphMatching();
			Set<Map<SubgraphQueryNode, Vertex>> solutions = match.subgraphMatching(g, q);
			// TODO 0: Check solutions!!
			System.out.println("Solutions: " + solutions);
		}
	}

}
