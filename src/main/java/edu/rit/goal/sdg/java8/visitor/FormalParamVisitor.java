package edu.rit.goal.sdg.java8.visitor;

import edu.rit.goal.sdg.java8.antlr.Java8BaseVisitor;
import edu.rit.goal.sdg.java8.antlr.Java8Parser;
import edu.rit.goal.sdg.statement.FormalParameter;

public class FormalParamVisitor extends Java8BaseVisitor<FormalParameter> {

    @Override
    public FormalParameter visitFormalParameter(final Java8Parser.FormalParameterContext ctx) {
	final String variableDeclaratorId = ctx.variableDeclaratorId().Identifier().getText();
	final FormalParameter result = new FormalParameter(variableDeclaratorId);
	return result;
    }

}
