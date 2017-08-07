package edu.rit.goal.sdg.python3.walker;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.TfpdefContext;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.TypedargslistContext;
import edu.rit.goal.sdg.statement.FormalParameter;

public class ParametersWalker {

    public List<FormalParameter> walk(final Python3Parser.ParametersContext ctx) {
	final List<FormalParameter> result = new LinkedList<>();
	final TypedargslistContext argsCtx = ctx.typedargslist();
	if (argsCtx != null) {
	    FormalParameter formalParam;
	    for (final TfpdefContext tfpDefCtx : argsCtx.tfpdef()) {
		formalParam = new FormalParameter(tfpDefCtx.getText());
		result.add(formalParam);
	    }
	}
	return result;
    }

}
