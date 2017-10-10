package edu.rit.goal.sdg.interpreter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;

import edu.rit.goal.sdg.DefUsesUtils;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.EdgeType;
import edu.rit.goal.sdg.graph.SysDepGraph;
import edu.rit.goal.sdg.graph.Vertex;
import edu.rit.goal.sdg.graph.VertexType;
import edu.rit.goal.sdg.interpreter.params.EmptyParam;
import edu.rit.goal.sdg.interpreter.params.Params;
import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Break;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.CallEdge;
import edu.rit.goal.sdg.interpreter.stmt.CfgEdge;
import edu.rit.goal.sdg.interpreter.stmt.Continue;
import edu.rit.goal.sdg.interpreter.stmt.CtrlEdge;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.DoWhile;
import edu.rit.goal.sdg.interpreter.stmt.Expr;
import edu.rit.goal.sdg.interpreter.stmt.Fed;
import edu.rit.goal.sdg.interpreter.stmt.For;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.Io;
import edu.rit.goal.sdg.interpreter.stmt.IoUnion;
import edu.rit.goal.sdg.interpreter.stmt.Param;
import edu.rit.goal.sdg.interpreter.stmt.ParamIn;
import edu.rit.goal.sdg.interpreter.stmt.ParamOut;
import edu.rit.goal.sdg.interpreter.stmt.PostOp;
import edu.rit.goal.sdg.interpreter.stmt.PreOp;
import edu.rit.goal.sdg.interpreter.stmt.Return;
import edu.rit.goal.sdg.interpreter.stmt.Seq;
import edu.rit.goal.sdg.interpreter.stmt.Skip;
import edu.rit.goal.sdg.interpreter.stmt.Stmt;
import edu.rit.goal.sdg.interpreter.stmt.Str;
import edu.rit.goal.sdg.interpreter.stmt.Vc;
import edu.rit.goal.sdg.interpreter.stmt.While;
import edu.rit.goal.sdg.interpreter.stmt.sw.DefaultCase;
import edu.rit.goal.sdg.interpreter.stmt.sw.EmptySwitch;
import edu.rit.goal.sdg.interpreter.stmt.sw.ICase;
import edu.rit.goal.sdg.interpreter.stmt.sw.ISwitchBody;
import edu.rit.goal.sdg.interpreter.stmt.sw.MultiCase;
import edu.rit.goal.sdg.interpreter.stmt.sw.MultiSwitch;
import edu.rit.goal.sdg.interpreter.stmt.sw.SingleCase;
import edu.rit.goal.sdg.interpreter.stmt.sw.SingleSwitch;
import edu.rit.goal.sdg.interpreter.stmt.sw.Switch;

public class Interpreter {

    public int vtxId;

    public final boolean PRINT = false;
    public final boolean PRINT_RULES = true;

    private final boolean simpleControlLabels;

    public Interpreter() {
	simpleControlLabels = false;
    }

    public Interpreter(final boolean simpleControlLabels) {
	this.simpleControlLabels = simpleControlLabels;
    }

    public Program interpret(final Program program) {
	vtxId = 0;
	final Program result = _interpret(program);
	result.sdg.computeDataFlow(vtxId);
	return result;
    }

    private Program _interpret(final Program program) {
	Program result = program;
	while (!(result.s instanceof Skip)) {
	    if (PRINT)
		System.out.println(result.s);
	    result = small(result);
	    if (PRINT) {
		// System.out.println(result.sdg);
		System.out.println();
	    }
	}
	if (!result.sd.isEmpty()) {
	    final Stmt deferSeq = Translator.seq(result.sd);
	    result.s = deferSeq;
	    result = interpret(result);
	}
	result.sdg.setCfgs(result.F);
	return result;
    }

    public Program small(final Program program) {
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
	    if (s1 instanceof Io) {
		printRule("seqIoRule");
		result = seqIoRule(program);
	    } else if (s1 instanceof Skip) {
		printRule("seqSkipRule");
		result = seqSkipRule(program);
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
	    if (s2 instanceof Seq) {
		printRule("ctrlEdgeSeqRule");
		result = ctrlEdgeSeqRule(program);
	    } else if (s2 instanceof Skip) {
		printRule("ctrlEdgeSkipRule");
		result = ctrlEdgeSkipRule(program);
	    } else if (s2 instanceof Io) {
		printRule("ctrlEdgeIoRule");
		result = ctrlEdgeIoRule(program);
	    } else if (s2 instanceof DoWhile) {
		printRule("ctrlEdgeDoWhileRule");
		result = ctrlEdgeDoWhileRule(program);
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
	} else if (s instanceof DoWhile) {
	    printRule("doWhileRule");
	    result = doWhileRule(program);
	} else if (s instanceof For) {
	    printRule("forRule");
	    result = forRule(program);
	} else if (s instanceof Switch) {
	    final ISwitchBody sb = ((Switch) s).sb;
	    if (sb instanceof EmptySwitch) {
		printRule("switchEmptyRule");
		result = switchEmptyRule(program);
	    } else if (sb instanceof SingleSwitch) {
		printRule("switchCaseRule");
		result = switchCaseRule(program);
	    } else if (sb instanceof MultiSwitch) {
		printRule("switchCasesRule");
		result = switchCasesRule(program);
	    }
	} else if (s instanceof Break) {
	    printRule("breakRule");
	    result = breakRule(program);
	} else if (s instanceof Continue) {
	    printRule("continueRule");
	    result = continueRule(program);
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
	    } else if (VertexType.FORMAL_IN.equals(vtxType)) {
		printRule("paramFormalInRule");
		result = paramFormalInRule(program);
	    } else if (VertexType.FORMAL_OUT.equals(vtxType)) {
		printRule("paramFormalOutRule");
		result = paramFormalOutRule(program);
	    }
	} else if (s instanceof Vc) {
	    printRule("vcRule");
	    result = vcRule(program);
	} else if (s instanceof CallEdge) {
	    printRule("callEdgeRule");
	    result = callEdgeRule(program);
	} else if (s instanceof ParamIn) {
	    final ParamIn paramIn = (ParamIn) s;
	    final LinkedHashSet<Vertex> Px = program.P.get(paramIn.x);
	    if (Px == null || paramIn.i >= paramIn.V.size() || paramIn.V.size() > Px.size()) {
		printRule("paramInOobRule");
		result = paramInOobRule(program);
	    } else if (paramIn.i >= 1 && Px.size() > paramIn.i) {
		printRule("paramInRule");
		result = paramInRule(program);
	    }
	} else if (s instanceof ParamOut) {
	    printRule("paramOutRule");
	    result = paramOutRule(program);
	} else if (s instanceof Return) {
	    printRule("returnRule");
	    result = returnRule(program);
	} else if (s instanceof PreOp) {
	    printRule("preOpRule");
	    result = preOpRule(program);
	} else if (s instanceof PostOp) {
	    printRule("postOpRule");
	    result = postOpRule(program);
	} else if (s instanceof Io) {
	    printRule("ioRule");
	    result = ioRule(program);
	} else if (s instanceof CfgEdge) {
	    final Stmt s1 = ((CfgEdge) s).s1;
	    final Stmt s2 = ((CfgEdge) s).s2;
	    if (s1 instanceof Skip) {
		printRule("cfgEdgeSkipFirstRule");
		result = cfgEdgeSkipFirstRule(program);
	    } else if (s2 instanceof Skip) {
		printRule("cfgEdgeSkipSecondRule");
		result = cfgEdgeSkipSecondRule(program);
	    } else if (s1 instanceof Io) {
		if (s2 instanceof Io) {
		    final Set<Vertex> ioS1 = ((Io) s2).I;
		    final Set<Vertex> ioS2 = ((Io) s2).O;
		    if (ioS1 == null) {
			printRule("cfgEdgeIoCopyInRule");
			result = cfgEdgeIoCopyInRule(program);
		    } else if (ioS2 == null) {
			printRule("cfgEdgeIoCopyOutRule");
			result = cfgEdgeIoCopyOutRule(program);
		    } else {
			printRule("cfgEdgeIoIoRule");
			result = cfgEdgeIoIoRule(program);
		    }
		} else if (s2 instanceof Fed) {
		    printRule("cfgEdgeIoEndDefRule");
		    result = cfgEdgeIoEndDefRule(program);
		} else {
		    printRule("cfgEdgeIoRule");
		    result = cfgEdgeIoRule(program);
		}

	    } else {
		printRule("cfgEdgeRule");
		result = cfgEdgeRule(program);
	    }
	} else if (s instanceof IoUnion) {
	    final Stmt s1 = ((IoUnion) s).s1;
	    if (s1 instanceof Io) {
		final Stmt s2 = ((IoUnion) s).s2;
		if (s2 instanceof Io) {
		    printRule("ioUnionIoIoRule");
		    result = ioUnionIoIoRule(program);
		} else {
		    printRule("ioUnionIoRule");
		    result = ioUnionIoRule(program);
		}
	    } else {
		printRule("outUnionRule");
		result = ioUnionRule(program);
	    }
	} else if (s instanceof Fed) {
	    printRule("endDefRule");
	    result = endDefRule(program);
	}
	return result;
    }

    private Program defRule(final Program program) {
	final Def s = (Def) program.s;
	final String methodName = Translator.removeClassName(s.x);
	final Vertex v = new Vertex(vtxId++, VertexType.ENTRY, methodName);
	v.setStartLine(s.startLine);
	v.setEndLine(s.endLine);
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	final Param param1 = new Param(methodName, VertexType.FORMAL_OUT, new Str(methodName + "ResultOut"));
	final Param param2 = new Param(methodName, VertexType.FORMAL_IN, new Params(methodName + "ResultIn", s.p));
	final Seq seq1 = new Seq(param1, new Seq(param2, s.s));
	final CtrlEdge ctrlEdge = new CtrlEdge(true, v, seq1);
	final Io io = new Io(set(v), set(v));
	program.s = new Seq(new CfgEdge(io, ctrlEdge), new Fed(s.x));
	return program;
    }

    private Program voidDefRule(final Program program) {
	final Def s = (Def) program.s;
	final String methodName = Translator.removeClassName(s.x);
	final Vertex v = new Vertex(vtxId++, VertexType.ENTRY, methodName);
	v.setStartLine(s.startLine);
	v.setEndLine(s.endLine);
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	final Param param = new Param(methodName, VertexType.FORMAL_IN, s.p);
	final Seq seq1 = new Seq(param, s.s);
	final CtrlEdge ctrlEdge = new CtrlEdge(true, v, seq1);
	final Io io = new Io(set(v), set(v));
	program.s = new Seq(new CfgEdge(io, ctrlEdge), new Fed(s.x));
	return program;
    }

    private Program seqRule(final Program program) {
	final Seq seq = (Seq) program.s;
	final Program p = small(program.cloneWithStmt(seq.s1));
	p.s = new Seq(p.s, seq.s2);
	return p;
    }

    private Program seqSkipRule(final Program program) {
	final Seq s = (Seq) program.s;
	program.s = s.s2;
	return program;
    }

    private Program assignRule(final Program program) {
	final Assign assign = (Assign) program.s;
	final Vertex va = new Vertex(vtxId++, VertexType.ASSIGN, assign.x + assign.op + assign.e);
	va.setAssignedVariable(assign.getDef());
	va.setReadingVariables(assign.getUses());
	program.sdg.addVertex(va);
	program.cfg.addVertex(va);
	program.Vc.add(va);
	program.s = new Io(set(va), set(va));
	return program;
    }

    private Program preOpRule(final Program program) {
	final PreOp preOp = (PreOp) program.s;
	final Vertex v = new Vertex(vtxId++, VertexType.ASSIGN, preOp.op + preOp.x);
	v.setAssignedVariable(preOp.getDef());
	v.setReadingVariables(preOp.getUses());
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	program.Vc.add(v);
	program.s = new Io(set(v), set(v));
	return program;
    }

    private Program postOpRule(final Program program) {
	final PostOp postOp = (PostOp) program.s;
	final Vertex v = new Vertex(vtxId++, VertexType.ASSIGN, postOp.x + postOp.op);
	v.setAssignedVariable(postOp.getDef());
	v.setReadingVariables(postOp.getUses());
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	program.Vc.add(v);
	program.s = new Io(set(v), set(v));
	return program;
    }

    private Program returnRule(final Program program) {
	final Return ret = (Return) program.s;
	final Vertex v = new Vertex(vtxId++, VertexType.RETURN, ret.e);
	v.setReadingVariables(ret.getUses());
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	program.Vc.add(v);
	program.s = new Io(set(v), new HashSet<>());
	return program;
    }

    private Program vcRule(final Program program) {
	final Vc vc = (Vc) program.s;
	program.Vc.add(vc.v);
	program.s = new Skip();
	return program;
    }

    private Program ctrlEdgeRule(final Program program) {
	final CtrlEdge ctrledge = (CtrlEdge) program.s;
	final Stmt s = ctrledge.s;
	final Program p = small(program.cloneWithStmt(s));
	p.s = new CtrlEdge(ctrledge.B, ctrledge.N, p.s);
	return p;
    }

    private Program ctrlEdgeSkipRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	createEdges(program.sdg, s.B, s.N, program.Vc);
	program.Vc = new HashSet<>();
	program.s = new Skip();
	return program;
    }

    private Program ctrlEdgeSeqRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	final Seq seq = (Seq) s.s;
	program.s = new Seq(new CtrlEdge(s.B, s.N, seq.s1), new CtrlEdge(s.B, s.N, seq.s2));
	return program;
    }

    private Program ifThenElseRule(final Program program) {
	final IfThenElse s = (IfThenElse) program.s;
	final Vertex v = new Vertex(vtxId++, (simpleControlLabels ? VertexType.CTRL : VertexType.CTRL_IF),
		s.e.toString());
	v.setAssignedVariable(s.getDef());
	v.setReadingVariables(s.getUses());
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	program.Vc = new HashSet<>();
	final CtrlEdge ctrl1 = new CtrlEdge(true, v, s.s1);
	final CtrlEdge ctrl2 = new CtrlEdge(false, v, s.s2);
	final Io io = new Io(set(v), set(v));
	final CfgEdge cfgEdge1 = new CfgEdge(io, ctrl1);
	final CfgEdge cfgEdge2 = new CfgEdge(io, ctrl2);
	final IoUnion outUnion = new IoUnion(cfgEdge1, cfgEdge2);
	program.s = new Seq(outUnion, new Vc(v));
	return program;
    }

    private Program whileRule(final Program program) {
	final While s = (While) program.s;
	final Vertex v = new Vertex(vtxId++, (simpleControlLabels ? VertexType.CTRL : VertexType.CTRL_WHILE),
		s.e.toString());
	v.setAssignedVariable(s.getDef());
	v.setReadingVariables(s.getUses());
	program.sdg.addVertex(v);
	program.sdg.addEdge(v, v, EdgeType.CTRL_TRUE);
	program.cfg.addVertex(v);
	program.Vc = new HashSet<>();
	final CtrlEdge ctrl = new CtrlEdge(true, v, s.s);
	final Io io = new Io(set(v), set(v));
	final CfgEdge cfgEdge1 = new CfgEdge(io, ctrl);
	final CfgEdge cfgEdge2 = new CfgEdge(cfgEdge1, io);
	program.s = new Seq(cfgEdge2, new Vc(v));
	return program;
    }

    private Program doWhileRule(final Program program) {
	final DoWhile s = (DoWhile) program.s;
	final Vertex v = new Vertex(vtxId++, (simpleControlLabels ? VertexType.CTRL : VertexType.CTRL_DO),
		s.e.toString());
	v.setAssignedVariable(s.getDef());
	v.setReadingVariables(s.getUses());
	program.sdg.addVertex(v);
	program.sdg.addEdge(v, v, EdgeType.CTRL_TRUE);
	program.cfg.addVertex(v);
	program.Vc = new HashSet<>();
	final CtrlEdge ctrl = new CtrlEdge(true, v, s.s);
	final Io io1 = new Io(set(v), set(v));
	final Io io2 = new Io(null, set(v));
	final CfgEdge cfgEdge1 = new CfgEdge(ctrl, io1);
	final CfgEdge cfgEdge2 = new CfgEdge(cfgEdge1, io2);
	program.s = new Seq(cfgEdge2, new Vc(v));
	return program;
    }

    private Program ctrlEdgeDoWhileRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	final DoWhile doWhile = (DoWhile) s.s;
	final Vertex v = new Vertex(vtxId++, (simpleControlLabels ? VertexType.CTRL : VertexType.CTRL_DO),
		doWhile.e.toString());
	v.setAssignedVariable(doWhile.getDef());
	v.setReadingVariables(doWhile.getUses());
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	program.Vc = new HashSet<>();
	final List<Boolean> B = list(s.B, true);
	final List<Vertex> N = list(s.N, v);
	final CtrlEdge ctrl = new CtrlEdge(B, N, new Seq(doWhile.s, new Vc(v)));
	final Io io1 = new Io(set(v), set(v));
	final Io io2 = new Io(null, set(v));
	final CfgEdge cfgEdge1 = new CfgEdge(ctrl, io1);
	program.s = new CfgEdge(cfgEdge1, io2);
	return program;
    }

    private Program forRule(final Program program) {
	final For s = (For) program.s;
	final Vertex v = new Vertex(vtxId++, (simpleControlLabels ? VertexType.CTRL : VertexType.CTRL_FOR),
		s.sc.toString());
	v.setAssignedVariable(s.sc.getDef());
	v.setReadingVariables(s.sc.getUses());
	program.sdg.addVertex(v);
	program.cfg.addVertex(v);
	program.sdg.addEdge(v, v, EdgeType.CTRL_TRUE);
	program.Vc = new HashSet<>();
	final CtrlEdge ctrlEdge = new CtrlEdge(true, v, new Seq(s.s, s.su));
	final Io io = new Io(set(v), set(v));
	final CfgEdge cfgEdge2 = new CfgEdge(io, ctrlEdge);
	final Io io2 = new Io(set(v), null);
	final CfgEdge cfgEdge1 = new CfgEdge(cfgEdge2, io2);
	final Io io1 = new Io(new HashSet<>(), set(v));
	final IoUnion ioUnion = new IoUnion(io1, cfgEdge1);
	program.s = new Seq(s.si, new Seq(ioUnion, new Vc(v)));
	return program;
    }

    private Program switchEmptyRule(final Program program) {
	final Switch s = (Switch) program.s;
	final Vertex v = new Vertex(vtxId++, (simpleControlLabels ? VertexType.CTRL : VertexType.CTRL_IF),
		s.e.toString());
	program.sdg.addVertex(v);
	program.Vc.add(v);
	program.s = new Skip();
	return program;
    }

    private Program switchCaseRule(final Program program) {
	final Switch s = (Switch) program.s;
	final SingleSwitch ss = (SingleSwitch) s.sb;
	final String x = largeCsCond(s.e, ss.cs);
	final IfThenElse ifThenElse = new IfThenElse(new Str(x), ss.s, new Skip());
	ifThenElse.setUses(s.getUses());
	program.s = ifThenElse;
	return program;
    }

    private Program switchCasesRule(final Program program) {
	final Switch s = (Switch) program.s;
	final MultiSwitch sb = (MultiSwitch) s.sb;
	final Switch sw = new Switch(s.e, sb.ss);
	final Program p = small(program.cloneWithStmt(sw));
	p.s = new Switch(s.e, sb.sb);
	return p;
    }

    private Program breakRule(final Program program) {
	final Vertex v = new Vertex(vtxId++, VertexType.BREAK, "break");
	program.sdg.addVertex(v);
	program.Vc.add(v);
	program.s = new Skip();
	return program;
    }

    private Program continueRule(final Program program) {
	final Vertex v = new Vertex(vtxId++, VertexType.CONTINUE, "continue");
	program.sdg.addVertex(v);
	program.Vc.add(v);
	program.s = new Skip();
	return program;
    }

    private Program paramActualInRule(final Program program) {
	final Param param = (Param) program.s;
	final LinkedHashSet<Vertex> Vp = largeParam(param);
	for (final Vertex v : Vp) {
	    program.sdg.addVertex(v);
	    program.Vc.add(v);
	}
	program.sd.add(new ParamIn(param.x, 1, Vp));
	program.s = new Skip();
	return program;
    }

    private Program paramActualOutRule(final Program program) {
	final Param param = (Param) program.s;
	final Set<Vertex> Vp = largeParam(param);
	for (final Vertex v : Vp) {
	    program.sdg.addVertex(v);
	    program.Vc.add(v);
	}
	final Vertex Vp0 = Vp.iterator().next();
	program.sd.add(new ParamOut(param.x, Vp0));
	program.s = new Skip();
	return program;
    }

    private Program paramFormalInRule(final Program program) {
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
	final int n = Vp.size();
	final List<Stmt> s = new LinkedList<>();
	final Iterator<Vertex> it = Vp.iterator();
	for (int i = 0; i < n; i++) {
	    final Vertex v = it.next();
	    s.add(new Io(set(v), set(v)));
	    program.cfg.addVertex(v);
	}
	program.s = Translator.seq(s);
	return program;
    }

    private Program paramFormalOutRule(final Program program) {
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
	program.s = new Skip();
	return program;
    }

    private Program paramInRule(final Program program) {
	final ParamIn paramIn = (ParamIn) program.s;
	final Vertex v = vtxAtIdx(program.P.get(paramIn.x), paramIn.i);
	final Vertex Vi = vtxAtIdx(paramIn.V, paramIn.i - 1);
	program.sdg.addEdge(Vi, v, EdgeType.PARAM_IN);
	program.s = new ParamIn(paramIn.x, paramIn.i + 1, paramIn.V);
	return program;
    }

    private Program paramInOobRule(final Program program) {
	program.s = new Skip();
	return program;
    }

    private Program paramOutRule(final Program program) {
	final ParamOut paramOut = (ParamOut) program.s;
	final LinkedHashSet<Vertex> Px = program.P.get(paramOut.x);
	if (Px != null && !Px.isEmpty()) {
	    final Vertex Px0 = vtxAtIdx(program.P.get(paramOut.x), 0);
	    if (VertexType.FORMAL_OUT.equals(Px0.getType())) {
		program.sdg.addEdge(Px0, paramOut.v, EdgeType.PARAM_OUT);
	    } else if (PRINT)
		System.out.println("[WARN] " + Px0 + " type is '" + Px0.getType() + "'. Expected was 'FORMAL_OUT'.");
	} else if (PRINT)
	    System.out.println("[WARN] Cannot find parameter vertices for method '" + paramOut.x + "'.");
	program.s = new Skip();
	return program;
    }

    private Program callRule(final Program program) {
	final Call s = (Call) program.s;
	final String methodName = Translator.removeClassName(s.x);
	final Vertex vc = new Vertex(vtxId++, VertexType.CALL, s.toString());
	vc.setAssignedVariable(s.getDef());
	vc.setReadingVariables(s.getUses());
	program.sdg.addVertex(vc);
	program.cfg.addVertex(vc);
	program.sd.add(new CallEdge(vc, methodName));
	final Io io = new Io(set(vc), set(vc));
	final Seq seq2 = new Seq(new Vc(vc), io);
	final Param param = new Param(methodName, VertexType.ACTUAL_IN, s.p);
	program.s = new Seq(new CtrlEdge(true, vc, param), seq2);
	return program;
    }

    private Program assignCallRule(final Program program) {
	final Assign assign = (Assign) program.s;
	final Call call = (Call) assign.e;
	final String methodName = Translator.removeClassName(call.x);
	final Vertex va = new Vertex(vtxId++, VertexType.CALL, assign.x + assign.op + call.toString());
	va.setAssignedVariable(assign.getDef());
	va.setReadingVariables(call.getUses());
	program.sdg.addVertex(va);
	program.cfg.addVertex(va);
	program.sd.add(new CallEdge(va, methodName));
	final Param param1 = new Param(methodName, VertexType.ACTUAL_OUT, new Str(assign.x));
	final Param param2 = new Param(methodName, VertexType.ACTUAL_IN, new Params(assign.x, call.p));
	final Seq seq2 = new Seq(param1, param2);
	final Io io = new Io(set(va), set(va));
	final Seq seq3 = new Seq(new Vc(va), io);
	program.s = new Seq(new CtrlEdge(true, va, seq2), seq3);
	return program;
    }

    private Program callEdgeRule(final Program program) {
	final CallEdge callEdge = (CallEdge) program.s;
	final Optional<Vertex> ve = program.sdg.vertexSet().stream()
		.filter(v -> v.getType().equals(VertexType.ENTRY) && v.getLabel().equals(callEdge.x)).findFirst();
	if (!ve.isPresent()) {
	    if (PRINT)
		System.out.println("[WARN] Cannot find ENTER vertex for method '" + callEdge.x + "'");
	} else {
	    program.sdg.addEdge(callEdge.v, ve.get(), EdgeType.CALL);
	}
	program.s = new Skip();
	return program;
    }

    // Control-flow graph

    private Program ioRule(final Program program) {
	program.s = new Skip();
	return program;
    }

    private Program seqIoRule(final Program program) {
	final Stmt s1 = ((Seq) program.s).s1;
	final Stmt s2 = ((Seq) program.s).s2;
	program.s = new CfgEdge(s1, s2);
	return program;
    }

    private Program ctrlEdgeIoRule(final Program program) {
	final CtrlEdge s = (CtrlEdge) program.s;
	createEdges(program.sdg, s.B, s.N, program.Vc);
	program.Vc = new HashSet<>();
	program.s = s.s;
	return program;
    }

    private Program cfgEdgeRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	final Stmt s = cfgEdge.s1;
	final Program p = small(program.cloneWithStmt(s));
	p.s = new CfgEdge(p.s, cfgEdge.s2);
	return p;
    }

    private Program cfgEdgeIoRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	final Stmt s = cfgEdge.s2;
	final Program p = small(program.cloneWithStmt(s));
	p.s = new CfgEdge(cfgEdge.s1, p.s);
	return p;
    }

    private Program cfgEdgeSkipFirstRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	program.s = cfgEdge.s2;
	return program;
    }

    private Program cfgEdgeSkipSecondRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	program.s = cfgEdge.s1;
	return program;
    }

    private Program cfgEdgeIoEndDefRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	program.s = cfgEdge.s2;
	return program;
    }

    private Program cfgEdgeIoCopyInRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	final Io io1 = (Io) cfgEdge.s1;
	final Io io2 = (Io) cfgEdge.s2;
	io2.I = new HashSet<>(io1.I);
	program.s = new CfgEdge(io1, io2);
	return program;
    }

    private Program cfgEdgeIoCopyOutRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	final Io io1 = (Io) cfgEdge.s1;
	final Io io2 = (Io) cfgEdge.s2;
	io2.O = new HashSet<>(io1.O);
	program.s = new CfgEdge(io1, io2);
	return program;
    }

    private Program cfgEdgeIoIoRule(final Program program) {
	final CfgEdge cfgEdge = (CfgEdge) program.s;
	final Io io1 = (Io) cfgEdge.s1;
	final Io io2 = (Io) cfgEdge.s2;
	for (final Vertex o : io1.O) {
	    for (final Vertex i : io2.I) {
		program.cfg.addEdge(o, i, new Edge(o, i));
	    }
	}
	program.s = new Io(io1.I, io2.O);
	return program;
    }

    private Program ioUnionRule(final Program program) {
	final IoUnion outUnion = (IoUnion) program.s;
	final Stmt s1 = outUnion.s1;
	final Stmt s2 = outUnion.s2;
	final Program p = small(program.cloneWithStmt(s1));
	p.s = new IoUnion(p.s, s2);
	return p;
    }

    private Program ioUnionIoRule(final Program program) {
	final IoUnion outUnion = (IoUnion) program.s;
	final Io io = (Io) outUnion.s1;
	final Stmt s = outUnion.s2;
	final Program p = small(program.cloneWithStmt(s));
	p.s = new IoUnion(io, p.s);
	return p;
    }

    private Program ioUnionIoIoRule(final Program program) {
	final IoUnion outUnion = (IoUnion) program.s;
	final Io s1 = (Io) outUnion.s1;
	final Io s2 = (Io) outUnion.s2;
	final Set<Vertex> I = union(s1.I, s2.I);
	final Set<Vertex> O = union(s1.O, s2.O);
	program.s = new Io(I, O);
	return program;
    }

    private Program endDefRule(final Program program) {
	final Fed fed = (Fed) program.s;
	program.F.put(fed.x, program.clonedCfg());
	program.cfg = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
	program.s = new Skip();
	return program;
    }

    // Large-step

    private LinkedHashSet<Vertex> largeParam(final Param param) {
	LinkedHashSet<Vertex> result = null;
	final edu.rit.goal.sdg.interpreter.params.Param p = (edu.rit.goal.sdg.interpreter.params.Param) param.p;
	if (p instanceof EmptyParam) {
	    result = largeNoParamRule(param);
	} else if (p instanceof Str) {
	    result = largeParamRule(param);
	} else if (p instanceof Params) {
	    result = largeParamsRule(param);
	}
	return result;
    }

    private LinkedHashSet<Vertex> largeNoParamRule(final Param param) {
	return new LinkedHashSet<>();
    }

    private LinkedHashSet<Vertex> largeParamRule(final Param param) {
	final Str str = (Str) param.p;
	final LinkedHashSet<Vertex> V = new LinkedHashSet<>();
	final Vertex v = new Vertex(vtxId++, param.t, str.value);
	// Def and uses for data dependences
	v.setAssignedVariable(str.getDef());
	v.setReadingVariables(str.getUses());
	DefUsesUtils.paramsDefUses(v, str.value);
	V.add(v);
	return V;
    }

    private LinkedHashSet<Vertex> largeParamsRule(final Param param) {
	final Params params = (Params) param.p;
	final LinkedHashSet<Vertex> V = largeParam(new Param(param.t, params.p));
	final Vertex v = new Vertex(vtxId++, param.t, params.x);
	// Def and uses for data dependences
	v.setAssignedVariable(params.getDef());
	v.setReadingVariables(params.getUses());
	DefUsesUtils.paramsDefUses(v, params.x);
	V.add(v);
	return V;
    }

    private String largeCsCond(final Expr e, final ICase cs) {
	String result = null;
	if (cs instanceof DefaultCase) {
	    result = "default";
	} else if (cs instanceof SingleCase) {
	    result = csCondCaseRule(e, cs);
	} else if (cs instanceof MultiCase) {
	    result = csCondCasesRule(e, cs);
	}
	return result;
    }

    private String csCondCaseRule(final Expr e, final ICase cs) {
	final SingleCase s = (SingleCase) cs;
	return e + "==" + s.x;
    }

    private String csCondCasesRule(final Expr e, final ICase cs) {
	final MultiCase s = (MultiCase) cs;
	final String x1 = largeCsCond(e, s.x);
	final String x2 = largeCsCond(e, s.cs);
	return x1 + "||" + x2;
    }

    // Helper methods

    private void createEdges(final SysDepGraph sdg, final List<Boolean> B, final List<Vertex> sources,
	    final Set<Vertex> targets) {
	if (targets.isEmpty())
	    return;
	final int bSize = B.size();
	if (bSize != sources.size())
	    throw new IllegalArgumentException("B and sources should be the same size.");
	for (int i = 0; i < bSize; i++) {
	    final Boolean b = B.get(i);
	    final EdgeType type = b ? EdgeType.CTRL_TRUE : EdgeType.CTRL_FALSE;
	    final Vertex s = sources.get(i);
	    for (final Vertex t : targets) {
		sdg.addEdge(s, t, type);
	    }
	}
    }

    private Vertex vtxAtIdx(final LinkedHashSet<Vertex> V, int idx) {
	final Iterator<Vertex> it = V.iterator();
	while (idx > 0) {
	    it.next();
	    idx--;
	}
	return it.next();
    }

    private void printRule(final String rule) {
	if (PRINT && PRINT_RULES)
	    System.out.println(rule);
    }

    private Set<Vertex> set(final Vertex v) {
	final Set<Vertex> result = new HashSet<>();
	result.add(v);
	return result;
    }

    private Set<Vertex> union(final Set<Vertex> s1, final Set<Vertex> s2) {
	final Set<Vertex> result = new HashSet<>(s1);
	result.addAll(s2);
	return result;
    }

    private <T> List<T> list(final List<T> l, final T element) {
	final List<T> result = new LinkedList<>(l);
	result.add(element);
	return result;
    }

}
