package edu.rit.goal.sourcedg.normalization;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;

public class SearchResult implements Comparable<SearchResult> {
  private static int _id;
  Integer id;
  BlockStmt blk;
  int idx;
  public Node node;

  public SearchResult(final BlockStmt blk, final int idx) {
    super();
    this.blk = blk;
    this.idx = idx;
    id = _id++;
  }

  public SearchResult(final BlockStmt blk, final int idx, final Node node) {
    super();
    this.blk = blk;
    this.idx = idx;
    id = _id++;
    this.node = node;
  }

  @Override
  public int compareTo(final SearchResult o) {
    return id.compareTo(o.id);
  }
}
