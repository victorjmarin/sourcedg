package edu.rit.goal.sdg;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaParser;

public class DefUsesUtils {

  // Type is checked to leave out parameters such as numbers
  public static void strDefUses(final Str str, final boolean isFormal) {
    if (isFormal) {
      final int type = ((TerminalNode) str.pt).getSymbol().getType();
      if (type == JavaParser.IDENTIFIER)
        str.setDef(str.value);
    } else {
      final List<TerminalNode> terminalNodes = getTerminalNodes(str.pt);
      final Set<String> uses = new HashSet<>();
      for (final TerminalNode pt : terminalNodes) {
        final int type = pt.getSymbol().getType();
        if (type == JavaParser.IDENTIFIER)
          uses.add(pt.getText());
      }
      str.setUses(uses);
    }
  }

  private static List<TerminalNode> getTerminalNodes(final ParseTree ctx) {
    final List<TerminalNode> result = new LinkedList<>();
    if (ctx == null)
      return result;
    if (ctx.getChildCount() == 0 && ctx instanceof TerminalNode)
      result.add((TerminalNode) ctx);
    for (int i = 0; i < ctx.getChildCount(); i++) {
      result.addAll(getTerminalNodes(ctx.getChild(i)));
    }
    return result;
  }

  // Type is checked to leave out parameters such as numbers
  public static void paramInDefUses(final Params p, final Str str, final boolean isFormal) {
    final List<TerminalNode> terminalNodes = getTerminalNodes(str.pt);
    if (isFormal) {
      final int type = ((TerminalNode) terminalNodes.get(0)).getSymbol().getType();
      if (type == JavaParser.IDENTIFIER)
        p.setDef(p.x);
    } else {
      final Set<String> uses = new HashSet<>();
      for (final TerminalNode pt : terminalNodes) {
        final int type = pt.getSymbol().getType();
        if (type == JavaParser.IDENTIFIER)
          uses.add(pt.getText());
      }
      p.setUses(uses);
    }
  }

  public static void paramsDefUses(final Vertex v, final String value) {
    final VertexType t = v.getType();
    if (VertexType.ACTUAL_OUT.equals(t))
      v.setAssignedVariable(value);
  }

}
