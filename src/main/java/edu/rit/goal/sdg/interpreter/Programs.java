package edu.rit.goal.sdg.interpreter;

import edu.rit.goal.sdg.interpreter.params.NoParam;
import edu.rit.goal.sdg.interpreter.params.Param;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.statement.Stmt;

public class Programs {

    public static Stmt simpleDef() {
	final Param args = new Params("p1", new Params("p2", new NoParam()));
	final Assign assign = new Assign("res", new Call("m1", new Params("1", new Params("2", new NoParam()))));
	final Def main = new Def(false, "main", assign);
	final Def m1 = new Def(true, "m1", args, new Skip());
	final Stmt result = new Seq(main, m1);
	return result;
    }

}
