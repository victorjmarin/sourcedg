package edu.rit.goal.sdg.interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.rit.goal.sdg.interpreter.stmt.Stmt;

public class Main {

    public static void main(final String[] args) throws IOException {
	final String program = new String(Files.readAllBytes(Paths.get("programs/java8/Circle.java")));
	final Translator translator = new Translator();
	final Stmt stmt = translator.from(program);
	System.out.println(stmt);
    }

}
