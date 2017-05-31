package edu.rit.goal.sdg.java.statement;

import java.util.List;

public interface Statement {

    List<Statement> expandScope();

}
