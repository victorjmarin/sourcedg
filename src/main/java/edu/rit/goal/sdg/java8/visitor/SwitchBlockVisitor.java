package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.SwitchBlockStatementGroupContext;
import edu.rit.goal.sdg.statement.Stmt;
import edu.rit.goal.sdg.statement.control.SwitchBlockStmtGroup;

public class SwitchBlockVisitor {

    public List<SwitchBlockStmtGroup> visitSwitchBlock(final Java8Parser.SwitchBlockContext ctx) {
	final List<SwitchBlockStmtGroup> result = new ArrayList<>();
	SwitchLabelsVisitor switchLblsVisitor;
	List<String> switchLabels;
	BlockStmtsVisitor blockStmntsVisitor;
	List<Stmt> blockStmnts;
	for (final SwitchBlockStatementGroupContext blockCtx : ctx.switchBlockStatementGroup()) {
	    switchLblsVisitor = new SwitchLabelsVisitor();
	    switchLabels = switchLblsVisitor.visitSwitchLabels(blockCtx.switchLabels());
	    blockStmntsVisitor = new BlockStmtsVisitor();
	    blockStmnts = blockStmntsVisitor.visit(blockCtx.blockStatements());
	    final SwitchBlockStmtGroup switchBlockStmntGroup = new SwitchBlockStmtGroup(switchLabels, blockStmnts);
	    result.add(switchBlockStmntGroup);
	}
	return result;
    }

}
