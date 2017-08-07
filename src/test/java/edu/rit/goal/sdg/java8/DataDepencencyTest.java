package edu.rit.goal.sdg.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jgrapht.ext.ExportException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.rit.goal.sdg.SysDepGraphBuilder;
import edu.rit.goal.sdg.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.graph.SysDepGraph;

public class DataDepencencyTest {

    private static SysDepGraph sdg;

    @BeforeClass
    public static void parseTestProgram() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/java8/DataDep1.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	sdg = builder.from(program);
	System.out.println(sdg);
    }

    @Test
    public void enterShouldBeParsed() {

    }

}
