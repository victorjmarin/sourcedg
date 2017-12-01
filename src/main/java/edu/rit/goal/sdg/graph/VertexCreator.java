package edu.rit.goal.sdg.graph;

import edu.rit.goal.sdg.interpreter.stmt.Assign;
import edu.rit.goal.sdg.interpreter.stmt.Break;
import edu.rit.goal.sdg.interpreter.stmt.CUnit;
import edu.rit.goal.sdg.interpreter.stmt.Call;
import edu.rit.goal.sdg.interpreter.stmt.Cls;
import edu.rit.goal.sdg.interpreter.stmt.Continue;
import edu.rit.goal.sdg.interpreter.stmt.Decl;
import edu.rit.goal.sdg.interpreter.stmt.Def;
import edu.rit.goal.sdg.interpreter.stmt.DoWhile;
import edu.rit.goal.sdg.interpreter.stmt.For;
import edu.rit.goal.sdg.interpreter.stmt.IfThenElse;
import edu.rit.goal.sdg.interpreter.stmt.Import;
import edu.rit.goal.sdg.interpreter.stmt.Pkg;
import edu.rit.goal.sdg.interpreter.stmt.PostOp;
import edu.rit.goal.sdg.interpreter.stmt.PreOp;
import edu.rit.goal.sdg.interpreter.stmt.Return;
import edu.rit.goal.sdg.interpreter.stmt.While;

public interface VertexCreator {

  int getId();

  void resetId();

  void setId(Vertex v);
  
  void selfCall(Vertex v);

  Vertex compilationUnitVertex(CUnit cunit);

  Vertex packageVertex(Pkg pkg);

  Vertex importVertex(Import imp);

  Vertex classVertex(Cls cls);

  Vertex defVertex(Def def);

  Vertex callVertex(Call call);

  Vertex assignCallVertex(Assign assign);

  Vertex declVertex(Decl decl);

  Vertex assignVertex(Assign assign);

  Vertex preOpVertex(PreOp preOp);

  Vertex postOpVertex(PostOp postOp);

  Vertex returnVertex(Return ret);

  Vertex ifThenElseVertex(IfThenElse ifThenElse);

  Vertex whileVertex(While _while);

  Vertex doWhileVertex(DoWhile doWhile);

  Vertex forVertex(For _for);

  Vertex breakVertex(Break _break);

  Vertex continueVertex(Continue _continue);

}
