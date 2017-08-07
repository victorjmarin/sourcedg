package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.statement.FormalParameter;

public class LastFormalParamVisitor extends Java8BaseVisitor<FormalParameter> {

    // TODO: varargs (...) not supported currently, so we treat last parameter as a regular parameter
    @Override
    public FormalParameter visitLastFormalParameter(final Java8Parser.LastFormalParameterContext ctx) {
	return new FormalParamVisitor().visit(ctx);
    }

}
