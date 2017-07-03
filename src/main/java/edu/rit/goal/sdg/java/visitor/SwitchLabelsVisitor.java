package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.SwitchLabelContext;

public class SwitchLabelsVisitor {

    public List<String> visitSwitchLabels(final Java8Parser.SwitchLabelsContext ctx) {
	final List<String> result = new ArrayList<>();
	for (final SwitchLabelContext switchLblCtx : ctx.switchLabel()) {
	    final SwitchLabelVisitor visitor = new SwitchLabelVisitor();
	    final String switchLabel = visitor.visitSwitchLabel(switchLblCtx);
	    result.add(switchLabel);
	}
	return result;
    }

}
