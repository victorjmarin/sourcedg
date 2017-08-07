package edu.rit.goal.sdg.java8.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.java8.antlr.Java8Parser.FormalParametersContext;
import edu.rit.goal.sdg.statement.FormalParameter;

public class FormalParamListVisitor extends Java8BaseVisitor<List<FormalParameter>> {

    @Override
    public List<FormalParameter> visitFormalParameterList(final Java8Parser.FormalParameterListContext ctx) {
	final List<FormalParameter> result = new ArrayList<>();
	final FormalParametersContext params = ctx.formalParameters();
	final boolean multipleParams = ctx.formalParameters() != null;
	if (multipleParams) {
	    final FormalParamVisitor visitor = new FormalParamVisitor();
	    params.formalParameter().forEach(p -> result.add(visitor.visit(p)));
	}
	final LastFormalParamVisitor visitor = new LastFormalParamVisitor();
	final FormalParameter lastParameter = visitor.visit(ctx.lastFormalParameter());
	result.add(lastParameter);
	return result;
    }

}
