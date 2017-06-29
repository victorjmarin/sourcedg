package edu.rit.goal.sdg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jgrapht.ext.ExportException;
import org.junit.Test;

import edu.rit.goal.sdg.java.SysDepGraphBuilder;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.java.exception.MultipleExitPointsException;

public class UnsupportedMultipleExitPointsTest {

    public static String PREFIX = "programs/unsupported";

    @Test(expected = MultipleExitPointsException.class)
    public void functionInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedMultipleExitPoints.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

}
