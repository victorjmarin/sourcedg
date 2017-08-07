package edu.rit.goal.sdg.statement;

import java.util.List;

public interface Stmt {

    List<Stmt> expandScope();

}
