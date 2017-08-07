package edu.rit.goal.sdg.python3;

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

public class BasicTest {

    private static SysDepGraph sdg;

    @BeforeClass
    public static void parseTestProgram() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/python3/test.py")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	sdg = builder.from(program);
	System.out.println(sdg);
    }

    @Test
    public void enterShouldBeParsed() {

    }

}
