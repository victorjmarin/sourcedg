package edu.rit.goal.sdg.translation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import edu.rit.goal.sdg.java8.antlr4.JavaLexer;
import edu.rit.goal.sdg.java8.antlr4.JavaParser;

public class SpeedTest {

  public static void main(final String[] args) throws IOException {
    final ArrayList<File> files = new ArrayList<>();
    listf("era_bcb_sample/", files);
    final List<Long> antlrTimes = new LinkedList<>();
    final List<Long> javaParserTimes = new LinkedList<>();
    int c = 0;
    for (final File f : files) {
      final long antlrTime = antlrTimeToParse(f.getPath());
      antlrTimes.add(antlrTime);
      try {
        final long javaParserTime = javaParserTimeToParse(f.getPath());
        javaParserTimes.add(javaParserTime);
      } catch (final Exception e) {
        e.printStackTrace();
        System.out.println(f.getPath());
      }
      if (++c % 100 == 0) {
        System.out.println(c);
        final OptionalDouble antlrAvg = antlrTimes.stream().mapToDouble(a -> a).average();
        final OptionalDouble javaParserAvg = javaParserTimes.stream().mapToDouble(a -> a).average();
        System.out.println("ANTLR avg. time -> " + antlrAvg.getAsDouble());
        System.out.println("JavaParser avg. time -> " + javaParserAvg.getAsDouble());
      }
    }
  }

  private static long antlrTimeToParse(final String file) throws IOException {
    final long tic = System.currentTimeMillis();
    final Lexer lexer = new JavaLexer(CharStreams.fromFileName(file));
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final Parser parser = new JavaParser(tokens);
    ((JavaParser) parser).compilationUnit();
    final long toc = System.currentTimeMillis();
    return toc - tic;
  }

  private static long javaParserTimeToParse(final String file) throws FileNotFoundException {
    final long tic = System.currentTimeMillis();
    final FileInputStream in = new FileInputStream(file);
    com.github.javaparser.JavaParser.parse(in);
    final long toc = System.currentTimeMillis();
    return toc - tic;
  }

  public static void listf(final String directoryName, final ArrayList<File> files) {
    final File directory = new File(directoryName);
    final File[] fList = directory.listFiles();
    for (final File file : fList) {
      if (file.isFile() && file.getName().endsWith(".java")) {
        files.add(file);
      } else if (file.isDirectory()) {
        listf(file.getAbsolutePath(), files);
      }
    }
  }

}
