package edu.rit.goal.sdg.java8.antlr4;

import java.io.File;
import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import edu.rit.goal.sdg.interpreter.stmt.CUnit;


public class VisitorTest {

  private static final File FILE = new File("programs/java8/normalization/Assignment.java");

  public static void main(final String[] args) throws IOException {
    final Lexer lexer;
    CommonTokenStream tokens;
    ParseTree tree;
    Parser parser;
    final CharStream chrStream = CharStreams.fromFileName(FILE.getPath());
    lexer = new JavaLexer(chrStream);
    tokens = new CommonTokenStream(lexer);
    parser = new JavaParser(tokens);
    tree = ((JavaParser) parser).compilationUnit();
    final AbstractParseTreeVisitor<ParseResult> visitor = new SourceDGJavaVisitor();
    final CUnit cunit = (CUnit) visitor.visit(tree).getStmt();
    cunit.x = FILE.getName();
    System.out.println(cunit);
  }

}
