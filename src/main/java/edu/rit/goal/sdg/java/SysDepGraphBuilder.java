package edu.rit.goal.sdg.java;

import edu.rit.goal.sdg.java.graph.SysDepGraph;
import edu.rit.goal.sdg.java.statement.MethodSignature;
import edu.rit.goal.sdg.java.statement.NotImplementedStmnt;
import edu.rit.goal.sdg.java.statement.VariableDecl;
import edu.rit.goal.sdg.java.statement.control.BasicForStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenElseStmnt;
import edu.rit.goal.sdg.java.statement.control.IfThenStmnt;
import edu.rit.goal.sdg.java.statement.control.WhileStmnt;

public interface SysDepGraphBuilder {

    SysDepGraph fromSource(final String program);

    void methodSignature(MethodSignature methodSignature, SysDepGraph sdg);

    void basicForStmnt(BasicForStmnt basicForStmnt, SysDepGraph sdg);

    void ifThenElseStmnt(IfThenElseStmnt ifThenElseStmnt, SysDepGraph sdg);

    void ifThenStmnt(IfThenStmnt ifThenStmnt, SysDepGraph sdg);

    void whileStmnt(WhileStmnt whileStmnt, SysDepGraph sdg);
    
    void variableDeclaration(VariableDecl variableDecl, SysDepGraph sdg);

    void notImplementedStmnt(NotImplementedStmnt notImplementedStmnt, SysDepGraph sdg);

}
