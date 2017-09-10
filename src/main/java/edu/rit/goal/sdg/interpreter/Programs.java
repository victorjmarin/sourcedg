package edu.rit.goal.sdg.interpreter;

import edu.rit.goal.sdg.interpreter.Interpreter.Program;
import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Break;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Continue;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.Return;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.interpreter.stmt.While;

public class Programs {

    public static Program simpleDef() {
	final Param args = new Params("p1", new Params("p2", new EmptyParam()));
	final Assign assign = new Assign("res", new Call("m1", new Params("1", new Str("2"))));
	final Def main = new Def(false, "main", assign);
	final Def m1 = new Def(true, "m1", args, new Skip());
	final Stmt result = new Seq(main, m1);
	return new Program(result);
    }

    public static Program returningMethod() {
	final Def result = new Def(true, "m", new Str("p1"), new Skip());
	return new Program(result);
    }

    public static Program nestedBreak() {
	final IfThenElse ifThenElse = new IfThenElse(new Str("true"), new Break(), new Skip());
	final While whileStmt = new While(new Str("true"), ifThenElse);
	final Def result = new Def(false, "m", whileStmt);
	return new Program(result);
    }

    public static Program nestedContinue() {
	final IfThenElse ifThenElse = new IfThenElse(new Str("true"), new Continue(), new Skip());
	final While whileStmt = new While(new Str("true"), ifThenElse);
	final Def result = new Def(false, "m", whileStmt);
	return new Program(result);
    }

    public static Program horwitz() {
	final Assign l1 = new Assign("P", new Str("3.14"));
	final Assign l2 = new Assign("rad", new Str("3"));
	final IfThenElse l3 = new IfThenElse(new Str("DEBUG"), new Assign("rad", new Str("4")), new Skip());
	final edu.rit.goal.sdg.interpreter.params.Param args1 = new Params("P", new Params("rad", new Str("rad")));
	final edu.rit.goal.sdg.interpreter.params.Param args2 = new Params("2", new Params("P", new Str("rad")));
	final Assign l4 = new Assign("area", new Call("mult3", args1));
	final Assign l5 = new Assign("circ", new Call("mult3", args2));
	final Call l6 = new Call("output", new Str("area"));
	final Call l7 = new Call("output", new Str("circ"));
	final Stmt mainBody = new Seq(l1, new Seq(l2, new Seq(l3, new Seq(l4, new Seq(l5, new Seq(l6, l7))))));
	final Def main = new Def(false, "main", mainBody);
	// Mult3 function
	final Return mult3Body = new Return("op1*op2*op3");
	final Def mult3 = new Def(true, "mult3",
		new Params("op1", new Params("op2", new Params("op3", new EmptyParam()))), mult3Body);
	final Stmt program = new Seq(main, mult3);
	return new Program(program);
    }

}
