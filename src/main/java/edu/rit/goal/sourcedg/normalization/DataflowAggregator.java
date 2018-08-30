package edu.rit.goal.sourcedg.normalization;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.ModifierVisitor;

public class DataflowAggregator {

  private CompilationUnit cu;
  private final List<ModifierVisitor<Void>> visitors;

  public DataflowAggregator(final CompilationUnit cu) {
    this.cu = cu;
    visitors = new ArrayList<>();
  }

  public void normalize() {
    for (final ModifierVisitor<Void> mv : visitors) {
      cu.accept(mv, null);
      cu = JavaParser.parse(cu.toString());
    }
  }


}
