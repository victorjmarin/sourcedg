package edu.rit.goal.sdg.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jgrapht.ext.ExportException;
import org.junit.Test;

import edu.rit.goal.sdg.SysDepGraphBuilder;
import edu.rit.goal.sdg.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.java8.exception.MultipleExitPointsException;

public class UnsupportedMultipleExitPointsTest {

    public static String PREFIX = "programs/java8/unsupported";

    @Test(expected = MultipleExitPointsException.class)
    public void functionInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedMultipleExitPoints.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.from(program);
    }

}
