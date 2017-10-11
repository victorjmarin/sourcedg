package edu.rit.goal.sdg.java8.visitor;

import java.util.List;
import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaParser.BlockContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.FormalParametersContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MethodBodyContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MethodDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeTypeContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.TypeTypeOrVoidContext;

public class MethodDeclarationVisitor {

  private final String className;

  public MethodDeclarationVisitor(final String className) {
    this.className = className;
  }

  public Stmt visit(final MethodDeclarationContext ctx) {
    Stmt result = null;
    final TypeTypeOrVoidContext typeVoidCtx = ctx.typeTypeOrVoid();
    final TypeTypeContext typeCtx = typeVoidCtx.typeType();
    // Method has return type
    final Boolean b = typeCtx != null ? true : false;
    // Method name with format className.methodName
    final String methodName = ctx.IDENTIFIER().getText();
    final String x = Translator.fullMethodName(methodName, className);
    final MethodBodyContext methodBodyCtx = ctx.methodBody();
    // Formal parameters
    final FormalParametersContext formalParamsCtx = ctx.formalParameters();
    final List<Str> params = Translator.formalParams(formalParamsCtx);
    // Method body
    final BlockContext blockCtx = methodBodyCtx.block();
    // Not abstract method
    if (blockCtx != null) {
      Stmt s = null;
      final BlockVisitor visitor = new BlockVisitor(className);
      s = visitor.visit(blockCtx);
      final Def def = new Def(b, x, Translator.param(params, true), s);
      // Start and end lines of the method
      def.startLine = ctx.getStart().getLine();
      def.endLine = ctx.getStop().getLine();
      result = def;
    }
    return result;
  }

}
