package edu.rit.goal.sdg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jgrapht.ext.ExportException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.rit.goal.sdg.java.SysDepGraphBuilder;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.java.graph.SysDepGraph;

public class Triangle1Test {

    private static SysDepGraph sdg;

    @BeforeClass
    public static void parseTestProgram() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/Triangle1.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	sdg = builder.fromSource(program);
	System.out.println(sdg);
    }

    @Test
    public void enterShouldBeParsed() {

    }

}
