package edu.rit.goal.sdg.java.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.antlr.Java8Parser.FormalParametersContext;
import edu.rit.goal.sdg.java.statement.FormalParameter;

public class FormalParameterListVisitor extends Java8BaseVisitor<List<FormalParameter>> {

    @Override
    public List<FormalParameter> visitFormalParameterList(final Java8Parser.FormalParameterListContext ctx) {
	final List<FormalParameter> result = new ArrayList<>();
	final FormalParametersContext params = ctx.formalParameters();
	final boolean multipleParams = ctx.formalParameters() != null;
	if (multipleParams) {
	    final FormalParameterVisitor visitor = new FormalParameterVisitor();
	    params.formalParameter().forEach(p -> result.add(visitor.visit(p)));
	}
	final LastFormalParameterVisitor visitor = new LastFormalParameterVisitor();
	final FormalParameter lastParameter = visitor.visit(ctx.lastFormalParameter());
	result.add(lastParameter);

	return result;
    }

}
