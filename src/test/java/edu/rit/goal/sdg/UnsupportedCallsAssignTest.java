package edu.rit.goal.sdg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jgrapht.ext.ExportException;
import org.junit.Test;

import edu.rit.goal.sdg.java.SysDepGraphBuilder;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory;
import edu.rit.goal.sdg.java.SysDepGraphBuilderFactory.BuildStrategy;
import edu.rit.goal.sdg.java.exception.AssignmentGuardException;
import edu.rit.goal.sdg.java.exception.InvocationArgException;

public class UnsupportedCallsAssignTest {

    public static String PREFIX = "programs/unsupported";

    @Test(expected = InvocationArgException.class)
    public void functionInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign1.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

    @Test(expected = InvocationArgException.class)
    public void methodInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign2.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

    @Test(expected = InvocationArgException.class)
    public void declarationInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign3.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

    @Test(expected = InvocationArgException.class)
    public void assignmentInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign4.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

    @Test(expected = InvocationArgException.class)
    public void whileGuardInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign5.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

    @Test(expected = AssignmentGuardException.class)
    public void whileGuardAssignmentShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign6.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

    @Test(expected = InvocationArgException.class)
    public void forGuardInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign7.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

    @Test(expected = InvocationArgException.class)
    public void returnInvocationShouldThrowException() throws IOException, ExportException {
	final String program = new String(Files.readAllBytes(Paths.get(PREFIX + "/UnsupportedCallsAssign8.java")));
	final SysDepGraphBuilder builder = SysDepGraphBuilderFactory.using(BuildStrategy.MARIN);
	builder.fromSource(program);
    }

}
