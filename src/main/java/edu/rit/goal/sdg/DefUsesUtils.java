package edu.rit.goal.sdg;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.tree.TerminalNode;

import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.java8.antlr.JavaParser;

public class DefUsesUtils {

    // Type is checked to leave out parameters such as numbers
    public static void strDefUses(final Str str, final boolean isFormal) {
	final int type = ((TerminalNode) str.pt).getSymbol().getType();
	if (type == JavaParser.IDENTIFIER) {
	    if (isFormal) {
		str.setDef(str.value);
	    } else {
		final Set<String> uses = new HashSet<>();
		uses.add(str.value);
		str.setUses(uses);
	    }
	}
    }

    // Type is checked to leave out parameters such as numbers
    public static void paramInDefUses(final Params p, final Str str, final boolean isFormal) {
	final int type = ((TerminalNode) str.pt).getSymbol().getType();
	if (type == JavaParser.IDENTIFIER) {
	    if (isFormal) {
		p.setDef(p.x);
	    } else {
		final Set<String> uses = new HashSet<>();
		uses.add(p.x);
		p.setUses(uses);
	    }
	}
    }

    public static void paramsDefUses(final Vertex v, final String value) {
	final VertexType t = v.getType();
	if (VertexType.ACTUAL_OUT.equals(t))
	    v.setAssignedVariable(value);
    }

}
