package edu.rit.goal.sdg.java8.visitor;

import java.util.LinkedList;
import java.util.List;
import edu.rit.goal.sdg.interpreter.Translator;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.java8.antlr.JavaParser;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ClassBodyDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.ConstructorDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.GenericMethodDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MemberDeclarationContext;
import edu.rit.goal.sdg.java8.antlr.JavaParser.MethodDeclarationContext;

public class ClassBodyVisitor {

  private final String className;

  public ClassBodyVisitor(final String className) {
    this.className = className;
  }

  public Stmt visit(final JavaParser.ClassBodyContext ctx) {
    final List<Stmt> defStmts = new LinkedList<>();
    for (final ClassBodyDeclarationContext clsBodyDeclCtx : ctx.classBodyDeclaration()) {
      final MemberDeclarationContext memberDeclCtx = clsBodyDeclCtx.memberDeclaration();
      if (memberDeclCtx != null) {
        MethodDeclarationContext methodDeclCtx = memberDeclCtx.methodDeclaration();
        final GenericMethodDeclarationContext genMethodDecl =
            memberDeclCtx.genericMethodDeclaration();
        final ConstructorDeclarationContext constDeclCtx = memberDeclCtx.constructorDeclaration();
        // Attempt to retrieve generic method if regular method declaration is
        // null
        if (methodDeclCtx == null && genMethodDecl != null)
          methodDeclCtx = genMethodDecl.methodDeclaration();
        // We are only interested in methods currently
        if (methodDeclCtx != null) {
          final MethodDeclarationVisitor visitor = new MethodDeclarationVisitor(className);
          final Stmt def = visitor.visit(methodDeclCtx);
          defStmts.add(def);
        } else if (constDeclCtx != null) {
          final ConstructorDeclarationVisitor visitor =
              new ConstructorDeclarationVisitor(className);
          final Stmt def = visitor.visit(constDeclCtx);
          defStmts.add(def);
        } else {
          Translator.unsupported(memberDeclCtx);
        }
      } else {
        Translator.unsupported(clsBodyDeclCtx);
      }
    }
    final Stmt result = Translator.seq(defStmts);
    return result;
  }

}
