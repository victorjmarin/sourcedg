package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.SwitchBlockStatementGroupContext;
import edu.rit.goal.sdg.java.statement.Statement;
import edu.rit.goal.sdg.java.statement.control.SwitchBlockStmntGroup;

public class SwitchBlockVisitor {

    public List<SwitchBlockStmntGroup> visitSwitchBlock(final Java8Parser.SwitchBlockContext ctx) {
	final List<SwitchBlockStmntGroup> result = new ArrayList<>();
	SwitchLabelsVisitor switchLblsVisitor;
	List<String> switchLabels;
	BlockStatementsVisitor blockStmntsVisitor;
	List<Statement> blockStmnts;
	for (final SwitchBlockStatementGroupContext blockCtx : ctx.switchBlockStatementGroup()) {
	    switchLblsVisitor = new SwitchLabelsVisitor();
	    switchLabels = switchLblsVisitor.visitSwitchLabels(blockCtx.switchLabels());
	    blockStmntsVisitor = new BlockStatementsVisitor();
	    blockStmnts = blockStmntsVisitor.visit(blockCtx.blockStatements());
	    final SwitchBlockStmntGroup switchBlockStmntGroup = new SwitchBlockStmntGroup(switchLabels, blockStmnts);
	    result.add(switchBlockStmntGroup);
	}
	return result;
    }

}
