package edu.rit.goal.sdg.java.statement;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotImplementedStmnt implements Statement {

    private final String msg;

    public NotImplementedStmnt(final Object cls, final ParserRuleContext ctx) {
	msg = cls.getClass().getSimpleName() + "[" + ctx.getClass().getSimpleName() + "]";
    }

    public String getMsg() {
	return msg;
    }

    @Override
    public String toString() {
	return msg;
    }

}
