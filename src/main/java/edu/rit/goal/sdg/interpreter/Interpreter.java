package edu.rit.goal.sdg.interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import edu.rit.goal.sdg.graph.EdgeType;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;
import edu.rit.goal.sdg.interpreter.params.NoParam;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.CallEdge;
import edu.rit.goal.sdg.interpreter.stmt.CtrlEdge;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.Defer;
import edu.rit.goal.sdg.interpreter.stmt.Expr;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.Param;
import edu.rit.goal.sdg.interpreter.stmt.ParamIn;
import edu.rit.goal.sdg.interpreter.stmt.ParamOut;
import edu.rit.goal.sdg.interpreter.stmt.Ret;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.interpreter.stmt.Vc;
import edu.rit.goal.sdg.interpreter.stmt.While;
import edu.rit.goal.sdg.statement.Stmt;

public class Interpreter {

    public static final boolean PRINT_RULES = true;
    public static final String[] RULES = { "defRule", "voidDefRule", "seqSkipRule", "seqDeferRule", "seqSeqDeferRule",
	    "seqRule", "assignCallRule", "assignRule", "ctrlEdgeCtrlEdgeTrueRule", "ctrlEdgeCtrlEdgeFalseRule",
	    "ctrlEdgeSeqRule", "ctrlEdgeSkipRule", "ctrlEdgeDeferRule", "ctrlEdgeRule", "ifThenElseRule", "whileRule",
	    "callRule", "paramActualInRule", "paramActualOutRule", "paramFormalRule", "vcRule", "deferRule",
	    "callEdgeRule", "paramInOobRule", "paramInRule", "paramOutRule" };

    public static Stmt horwitzProgram() {
	// final Assign l1 = new Assign("P", "3.14");
	// final Assign l2 = new Assign("rad", "3");
	// final IfThenElse l3 = new IfThenElse("DEBUG", new Assign("rad", "4"), new
	// Skip());
	// final Args args1 = new Args("P", new Args("rad", new Args("rad", new
	// NoArg())));
	// final Args args2 = new Args("2", new Args("P", new Args("rad", new NoArg())));
	// final Call l4 = new Call("Mult3", args1, "area");
	// final Call l5 = new Call("Mult3", args2, "circ");
	// final Stmt mainBody = new Seq(l1, new Seq(l2, new Seq(l3, new Seq(l4, l5))));
	// final Def main = new Def("main", mainBody);

	// Mult3 function
	final Ret mult3Body = new Ret("op1*op2*op3");
	final Def mult3 = new Def(true, "Mult3", mult3Body);
	return mult3;
    }

    static class Program {

	SysDepGraph sdg;
	Set<Vertex> Vc;
	Map<String, LinkedHashSet<Vertex>> P;
	Stmt s;

	public Program(final Stmt s) {
	    sdg = new SysDepGraph();
	    Vc = new HashSet<>();
	    P = new HashMap<>();
	    this.s = s;
	}

	public Program(final SysDepGraph sdg, final Set<Vertex> Vc, final Map<String, LinkedHashSet<Vertex>> P,
		final Stmt s) {
	    super();
	    this.sdg = sdg;
	    this.Vc = Vc;
	    this.P = P;
	    this.s = s;
	}

    }

    public static void main(final String[] args) {
	final Assign s1 = new Assign("x", new Str("2"));
	final Assign s2 = new Assign("y", new Str("4"));
	final Assign s3 = new Assign("p", new Str("-1"));
	final IfThenElse ifThenElse = new IfThenElse("x>3", s2, new While("true", s3));
	final Assign s4 = new Assign("z", new Str("0"));
	final Seq seq = new Seq(s1, new Seq(ifThenElse, s4));
	final Def hard = new Def(false, "m1", seq);
	final Def easy = new Def(false, "m2", ifThenElse);
	final Program program = new Program(Programs.simpleDef());
	final Program result = interpret(program);
	System.out.println(result.s + "\n");
	final Set<String> allRules = new HashSet<String>(Arrays.asList(RULES));
	System.out.println(execRules.size() + "/" + allRules.size() + " rules were executed to interpret the program.");
	allRules.removeAll(execRules);
	System.out.println("The following rules were not executed:");
	System.out.println(allRules);
    }

    public static Program interpret(final Program program) {
	Program result = program;
	while (!(result.s instanceof Skip)) {
	    System.out.println(result.s);
	    result = small(result);
	    System.out.println(result.sdg);
	    System.out.println();
	}
	return result;
    }

    public static Program small(final Program program) {
	Program result = null;
	final Stmt s = program.s;
	if (s instanceof Def) {
	    final boolean hasReturnType = ((Def) s).b;
	    if (hasReturnType) {
		printRule("defRule");
		result = defRule(program);
	    } else {
		printRule("voidDefRule");
		result = voidDefRule(program);
	    }
	} else if (s instanceof Seq) {
	    final Stmt s1 = ((Seq) s).s1;
	    final Stmt s2 = ((Seq) s).s2;
	    if (s1 instanceof Skip) {
		printRule("seqSkipRule");
		result = seqSkipRule(program);
	    } else if (s1 instanceof Defer && !(s2 instanceof Defer)) {
		printRule("seqDeferRule");
		result = seqDeferRule(program);
	    } else if (s1 instanceof Seq && ((Seq) s1).s2 instanceof Defer && !(s2 instanceof Defer)) {
		printRule("seqSeqDeferRule");
		result = seqSeqDeferRule(program);
	    } else {
		printRule("seqRule");
		result = seqRule(program);
	    }
	} else if (s instanceof Assign) {
	    final Expr expr = ((Assign) s).e;
	    if (expr instanceof Call) {
		printRule("assignCallRule");
		result = assignCallRule(program);
	    } else {
		printRule("assignRule");
		result = assignRule(program);
	    }
	} else if (s instanceof CtrlEdge) {
	    final Stmt s2 = ((CtrlEdge) s).s;
	    if (s2 instanceof CtrlEdge) {
		if (((CtrlEdge) s2).b) {
		    printRule("ctrlEdgeCtrlEdgeTrueRule");
		    result = ctrlEdgeCtrlEdgeTrueRule(program);
		} else {
		    printRule("ctrlEdgeCtrlEdgeFalseRule");
		    result = ctrlEdgeCtrlEdgeFalseRule(program);
		}
	    } else if (s2 instanceof Seq) {
		printRule("ctrlEdgeSeqRule");
		result = ctrlEdgeSeqRule(program);
	    } else if (s2 instanceof Skip) {
		printRule("ctrlEdgeSkipRule");
		result = ctrlEdgeSkipRule(program);
	    } else if (s2 instanceof Defer) {
		printRule("ctrlEdgeDeferRule");
		result = ctrlEdgeDeferRule(program);
	    } else {
		printRule("ctrlEdgeRule");
		result = ctrlEdgeRule(program);
	    }
	} else if (s instanceof IfThenElse) {
	    printRule("ifThenElseRule");
	    result = ifThenElseRule(program);
	} else if (s instanceof While) {
	    printRule("whileRule");
	    result = whileRule(program);
	} else if (s instanceof Call) {
	    printRule("callRule");
	    result = callRule(program);
	} else if (s instanceof Param) {
	    final Param param = (Param) s;
	    final VertexType vtxType = param.t;
	    if (VertexType.ACTUAL_IN.equals(vtxType)) {
		printRule("paramActualInRule");
		result = paramActualInRule(program);
	    } else if (VertexType.ACTUAL_OUT.equals(vtxType)) {
		printRule("paramActualOutRule");
		result = paramActualOutRule(program);
	    } else if (VertexType.FORMAL_IN.equals(vtxType) || VertexType.FORMAL_OUT.equals(vtxType)) {
		printRule("paramFormalRule");
		result = paramFormalRule(program);
	    }
	} else if (s instanceof Vc) {
	    printRule("vcRule");
	    result = vcRule(program);
	} else if (s instanceof Defer) {
	    printRule("deferRule");
	    result = deferRule(program);
	} else if (s instanceof CallEdge) {
	    printRule("callEdgeRule");
	    result = callEdgeRule(program);
	} else if (s instanceof ParamIn) {
	    final ParamIn paramIn = (ParamIn) s;
	    final int size = program.P.get(paramIn.x).size();
	    if (paramIn.i > paramIn.V.size()) {
		printRule("paramInOobRule");
		result = paramInOobRule(program);
	    } else if (paramIn.i >= 1 && size > paramIn.i) {
		printRule("paramInRule");
		result = paramInRule(program);
	    }
	} else if (s instanceof ParamOut) {
	    printRule("paramOutRule");
	    result = paramOutRule(program);
	}
	return result;
    }

    static Set<String> execRules = new HashSet<>();

    private static void printRule(final String rule) {
	execRules.add(rule);
	if (PRINT_RULES)
	    System.out.println(rule);
    }

    private static void createEdges(final SysDepGraph sdg, final boolean b, final Vertex source, final Vertex target) {
	final Set<Vertex> targets = new HashSet<>();
	targets.add(target);
	createEdges(sdg, b, source, targets);
    }

    private static void createEdges(final SysDepGraph sdg, final boolean b, final Vertex source,
	    final Set<Vertex> targets) {
	for (final Vertex vc : targets) {
	    final EdgeType type = b ? EdgeType.CTRL_TRUE : EdgeType.CTRL_FALSE;
	    sdg.addEdge(source, vc, type);
	}
    }

    private static Program ctrlEdgeCtrlEdgeFalseRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	return new Program(program.sdg, program.Vc, program.P, s.s);
    }

    private static Program ctrlEdgeCtrlEdgeTrueRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	final CtrlEdge s2 = (CtrlEdge) s.s;
	createEdges(program.sdg, s.b, s.v, s2.v);
	return new Program(program.sdg, program.Vc, program.P, s2);
    }

    private static Program defRule(final Program program) {
	final Def s = (Def) program.s;
	final Vertex ve = new Vertex(VertexType.ENTER, s.x);
	program.sdg.addVertex(ve);
	final Param param1 = new Param(s.x, VertexType.FORMAL_OUT, new Params(s.x + "ResultOut", new NoParam()));
	final Param param2 = new Param(s.x, VertexType.FORMAL_IN, new Params(s.x + "ResultIn", s.p));
	final Seq seq = new Seq(param1, new Seq(param2, s.s));
	return new Program(program.sdg, program.Vc, program.P, new CtrlEdge(true, ve, seq));
    }

    private static Program voidDefRule(final Program program) {
	final Def s = (Def) program.s;
	final Vertex ve = new Vertex(VertexType.ENTER, s.x);
	program.sdg.addVertex(ve);
	final Param param = new Param(s.x, VertexType.FORMAL_IN, s.p);
	final Seq seq = new Seq(param, s.s);
	return new Program(program.sdg, program.Vc, program.P, new CtrlEdge(true, ve, seq));
    }

    private static Program ctrlEdgeRule(final Program program) {
	final CtrlEdge ctrledge = (CtrlEdge) program.s;
	final Stmt s = ctrledge.s;
	final Program p = small(new Program(program.sdg, program.Vc, program.P, s));
	return new Program(p.sdg, p.Vc, program.P, new CtrlEdge(ctrledge.b, ctrledge.v, p.s));
    }

    private static Program ctrlEdgeSkipRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	createEdges(program.sdg, s.b, s.v, program.Vc);
	return new Program(program.sdg, new HashSet<>(), program.P, new Skip());
    }

    private static Program ctrlEdgeDeferRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	return new Program(program.sdg, program.Vc, program.P, s.s);
    }

    private static Program ctrlEdgeSeqRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	final Seq seq = (Seq) s.s;
	return new Program(program.sdg, program.Vc, program.P,
		new Seq(new CtrlEdge(s.b, s.v, seq.s1), new CtrlEdge(s.b, s.v, seq.s2)));
    }

    private static Program seqRule(final Program program) {
	final Seq seq = (Seq) program.s;
	final Program p = small(new Program(program.sdg, program.Vc, program.P, seq.s1));
	return new Program(p.sdg, p.Vc, program.P, new Seq(p.s, seq.s2));
    }

    private static Program seqSkipRule(final Program program) {
	return new Program(program.sdg, program.Vc, program.P, ((Seq) program.s).s2);
    }

    private static Program seqDeferRule(final Program program) {
	final Seq seq = (Seq) program.s;
	return new Program(program.sdg, program.Vc, program.P, new Seq(seq.s2, seq.s1));
    }

    private static Program seqSeqDeferRule(final Program program) {
	final Seq seq = (Seq) program.s;
	final Stmt s1 = ((Seq) seq.s1).s1;
	final Defer s2 = (Defer) ((Seq) seq.s1).s2;
	final Stmt s3 = seq.s2;
	return new Program(program.sdg, program.Vc, program.P, new Seq(s1, new Seq(s3, s2)));
    }

    private static Program assignCallRule(final Program program) {
	final Assign assign = (Assign) program.s;
	final Call call = (Call) assign.e;
	final Vertex va = new Vertex(VertexType.ASSIGN, assign.x + "=" + call.x + call.p);
	program.sdg.addVertex(va);
	final Param param1 = new Param(call.x, VertexType.ACTUAL_OUT, new Params(assign.x, new NoParam()));
	final Param param2 = new Param(call.x, VertexType.ACTUAL_IN, new Params(assign.x, call.p));
	final Seq seq2 = new Seq(param1, param2);
	final Seq seq3 = new Seq(new Vc(va), new Defer(new CallEdge(va, call.x)));
	final Seq seq = new Seq(new CtrlEdge(true, va, seq2), seq3);
	return new Program(program.sdg, program.Vc, program.P, seq);
    }

    private static Program assignRule(final Program program) {
	final Assign assign = (Assign) program.s;
	final Vertex va = new Vertex(VertexType.ASSIGN, assign.x + "=" + assign.e);
	program.sdg.addVertex(va);
	program.Vc.add(va);
	return new Program(program.sdg, program.Vc, program.P, new Skip());
    }

    private static Program ifThenElseRule(final Program program) {
	final IfThenElse s = (IfThenElse) program.s;
	final Vertex vc = new Vertex(VertexType.COND, s.e);
	program.sdg.addVertex(vc);
	final CtrlEdge ctrl1 = new CtrlEdge(true, vc, s.s1);
	final CtrlEdge ctrl2 = new CtrlEdge(false, vc, s.s2);
	final Seq seq = new Seq(ctrl1, ctrl2);
	program.Vc.clear();
	return new Program(program.sdg, program.Vc, program.P, seq);
    }

    private static Program whileRule(final Program program) {
	final While s = (While) program.s;
	final Vertex vw = new Vertex(VertexType.COND, s.e);
	program.sdg.addVertex(vw);
	program.sdg.addEdge(vw, vw, EdgeType.CTRL_TRUE);
	final CtrlEdge ctrl = new CtrlEdge(true, vw, s.s);
	program.Vc.clear();
	return new Program(program.sdg, program.Vc, program.P, ctrl);
    }

    private static Program callRule(final Program program) {
	final Call s = (Call) program.s;
	final Vertex vc = new Vertex(VertexType.CALL, s.toString());
	program.sdg.addVertex(vc);
	final Seq seq2 = new Seq(new Vc(vc), new Defer(new CallEdge(vc, s.x)));
	final Param param = new Param(s.x, VertexType.ACTUAL_IN, s.p);
	final Seq seq = new Seq(new CtrlEdge(true, vc, param), seq2);
	return new Program(program.sdg, program.Vc, program.P, seq);
    }

    private static Program paramActualInRule(final Program program) {
	final Param param = (Param) program.s;
	final LinkedHashSet<Vertex> Vp = largeParam(param);
	for (final Vertex v : Vp) {
	    program.sdg.addVertex(v);
	    program.Vc.add(v);
	}
	return new Program(program.sdg, program.Vc, program.P, new Defer(new ParamIn(param.x, 1, Vp)));
    }

    private static Program paramActualOutRule(final Program program) {
	final Param param = (Param) program.s;
	final Set<Vertex> Vp = largeParam(param);
	for (final Vertex v : Vp) {
	    program.sdg.addVertex(v);
	    program.Vc.add(v);
	}
	final Vertex Vp0 = Vp.iterator().next();
	return new Program(program.sdg, program.Vc, program.P, new Defer(new ParamOut(Vp0, param.x)));
    }

    private static Program paramFormalRule(final Program program) {
	final Param param = (Param) program.s;
	final Set<Vertex> Vp = largeParam(param);
	LinkedHashSet<Vertex> paramVtcs = program.P.get(param.x);
	if (paramVtcs == null) {
	    paramVtcs = new LinkedHashSet<>();
	}
	for (final Vertex v : Vp) {
	    program.sdg.addVertex(v);
	    program.Vc.add(v);
	    paramVtcs.add(v);
	}
	program.P.put(param.x, paramVtcs);
	return new Program(program.sdg, program.Vc, program.P, new Skip());
    }

    private static Program vcRule(final Program program) {
	final Vc vc = (Vc) program.s;
	program.Vc.add(vc.v);
	return new Program(program.sdg, program.Vc, program.P, new Skip());
    }

    private static Program deferRule(final Program program) {
	final Defer defer = (Defer) program.s;
	return new Program(program.sdg, program.Vc, program.P, defer.s);
    }

    private static Program callEdgeRule(final Program program) {
	final CallEdge callEdge = (CallEdge) program.s;
	final Optional<Vertex> ve = program.sdg.vertexSet().stream()
		.filter(v -> v.getType().equals(VertexType.ENTER) && v.getLabel().equals(callEdge.x)).findFirst();
	if (!ve.isPresent())
	    throw new RuntimeException("Cannot find ENTER vertex for method " + callEdge.x);
	program.sdg.addEdge(callEdge.v, ve.get(), EdgeType.CALL);
	return new Program(program.sdg, program.Vc, program.P, new Skip());
    }

    private static Program paramInRule(final Program program) {
	final ParamIn paramIn = (ParamIn) program.s;
	final Vertex v = vtxAtIdx(program.P.get(paramIn.x), paramIn.i);
	final Vertex Vi = vtxAtIdx(paramIn.V, paramIn.i - 1);
	program.sdg.addEdge(Vi, v, EdgeType.PARAM_IN);
	return new Program(program.sdg, program.Vc, program.P, new ParamIn(paramIn.x, paramIn.i + 1, paramIn.V));
    }

    private static Program paramInOobRule(final Program program) {
	return new Program(program.sdg, program.Vc, program.P, new Skip());
    }

    private static Program paramOutRule(final Program program) {
	final ParamOut paramOut = (ParamOut) program.s;
	final Vertex Px0 = vtxAtIdx(program.P.get(paramOut.x), 0);
	program.sdg.addEdge(Px0, paramOut.v, EdgeType.PARAM_OUT);
	return new Program(program.sdg, program.Vc, program.P, new Skip());
    }

    private static LinkedHashSet<Vertex> largeNoParamRule(final Param param) {
	return new LinkedHashSet<>();
    }

    private static LinkedHashSet<Vertex> largeParamRule(final Param param) {
	final Params params = (Params) param.p;
	final LinkedHashSet<Vertex> V = largeParam(new Param(param.t, params.p));
	V.add(new Vertex(param.t, params.x));
	return V;
    }

    private static LinkedHashSet<Vertex> largeParam(final Param param) {
	LinkedHashSet<Vertex> result = null;
	final edu.rit.goal.sdg.interpreter.params.Param p = (edu.rit.goal.sdg.interpreter.params.Param) param.p;
	if (p instanceof NoParam) {
	    result = largeNoParamRule(param);
	} else {
	    result = largeParamRule(param);
	}
	return result;
    }

    private static Vertex vtxAtIdx(final LinkedHashSet<Vertex> V, int idx) {
	final Iterator<Vertex> it = V.iterator();
	while (idx > 0) {
	    it.next();
	    idx--;
	}
	return it.next();
    }

}
