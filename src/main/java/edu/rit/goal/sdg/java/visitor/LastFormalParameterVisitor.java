package edu.rit.goal.sdg.java.visitor;

import edu.rit.goal.sdg.java.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java.antlr.Java8Parser;
import edu.rit.goal.sdg.java.statement.FormalParameter;

public class LastFormalParameterVisitor extends Java8BaseVisitor<FormalParameter> {

    // ... not supported currently.
    @Override
    public FormalParameter visitLastFormalParameter(final Java8Parser.LastFormalParameterContext ctx) {
	return new FormalParameterVisitor().visit(ctx);
    }

}
