package edu.rit.goal.sdg.statement;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotImplementedStmnt implements Stmt {

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

    @Override
    public List<Stmt> expandScope() {
	// TODO Auto-generated method stub
	return null;
    }

}
