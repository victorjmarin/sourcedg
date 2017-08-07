package edu.rit.goal.sdg.python3.walker;

import java.util.LinkedList;
import java.util.List;

import edu.rit.goal.sdg.python3.antlr.Python3Parser;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.ParametersContext;
import edu.rit.goal.sdg.python3.antlr.Python3Parser.SuiteContext;
import edu.rit.goal.sdg.statement.FormalParameter;
import edu.rit.goal.sdg.statement.MethodSignature;
import edu.rit.goal.sdg.statement.Stmt;

public class FuncDefWalker {

    public List<Stmt> walk(final Python3Parser.FuncdefContext ctx) {
	final List<Stmt> result = new LinkedList<>();
	// Function signature
	final String methodName = ctx.NAME().getText();
	final ParametersContext paramsCtx = ctx.parameters();
	final ParametersWalker paramsWalker = new ParametersWalker();
	final List<FormalParameter> params = paramsWalker.walk(paramsCtx);
	final MethodSignature funcSignature = new MethodSignature(methodName, params);
	result.add(funcSignature);
	// Function body
	final SuiteContext suiteCtx = ctx.suite();
	final SuiteWalker suiteWalker = new SuiteWalker();
	final List<Stmt> suiteStmts = suiteWalker.walk(suiteCtx);
	result.addAll(suiteStmts);
	return result;
    }

}
