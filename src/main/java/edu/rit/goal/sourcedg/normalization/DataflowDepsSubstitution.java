package edu.rit.goal.sourcedg.normalization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import edu.rit.goal.FileUtils;
import edu.rit.goal.sourcedg.builder.PDGBuilderConfig;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.EdgeType;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.graph.VertexType;
import edu.rit.goal.sourcedg.util.GraphExporter;

public class DataflowDepsSubstitution {

  private PDG pdg;

  public DataflowDepsSubstitution(PDG pdg) {
    this.pdg = pdg;
  }

  public void propagateSubstitutions() {
    Set<Edge> dataEdges = pdg.edgeSet().stream().filter(e -> EdgeType.DATA.equals(e.getType()))
        .collect(Collectors.toSet());

    for (Edge e : dataEdges) {
      Vertex source = e.getSource();
      Vertex target = e.getTarget();
      if (VertexType.ACTUAL_OUT.equals(source.getType())) {
        Edge assign = pdg.incomingEdgesOf(source).stream().findFirst().get();
        source = assign.getSource();
      }

      Node sourceAst = source.getAst();
      Node targetAst = target.getAst();

      if (sourceAst instanceof AssignExpr)
        replace(target, (AssignExpr) sourceAst, targetAst);
      else if (sourceAst instanceof VariableDeclarator) {
        replace(target, (VariableDeclarator) sourceAst, targetAst);
      }
    }
  }

  private void replace(Vertex v, AssignExpr assignExpr, Node expr) {
    Expression target = assignExpr.getTarget();
    Expression value = assignExpr.getValue();
    List<Expression> lst = expr.findAll(Expression.class, s -> s.equals(target));
    // TODO: Deal with VariableInitializer and such
    Node n = expr;
    if (!lst.isEmpty())
      n = lst.get(0);
    n.replace(value);
    System.out.println(v.getLabel());
    if (n == expr)
      v.setLabel(value.toString());
    else
      v.setLabel(expr.toString());
    System.out.println(v.getLabel());
    System.out.println();
  }

  private void replace(Vertex v, VariableDeclarator varDeclExpr, Node expr) {
    SimpleName name = varDeclExpr.getName();
    NameExpr nameExpr = new NameExpr(name);
    Expression init = varDeclExpr.getInitializer().get();

    List<NameExpr> lst = expr.findAll(NameExpr.class, s -> s.equals(nameExpr));
    Node n = expr;
    if (!lst.isEmpty())
      n = lst.get(0);
    n.replace(init);

    v.setLabel(expr.toString());
  }

  public static void main(String[] args) throws FileNotFoundException, Exception {
    PDGBuilderConfig config = PDGBuilderConfig.create();
    String path =
        "/Users/goal/eclipse-workspace/KDD2018/KDD18/Assignments/MUFFINS3/submissions/correct/15026981.java";
    PDG pdg = FileUtils.buildPDG(new FileInputStream(path), config);
    GraphExporter.exportAsDot(pdg, "./", "10842912");
    DataflowDepsSubstitution dataflowSubs = new DataflowDepsSubstitution(pdg);
    dataflowSubs.propagateSubstitutions();
  }

}
