package sourcedg.normalization;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;

public class NodeSearchResult implements Comparable<NodeSearchResult> {
  private static int _id;
  Integer id;
  BlockStmt blk;
  int idx;
  public Node node;

  public NodeSearchResult(final BlockStmt blk, final int idx) {
    super();
    this.blk = blk;
    this.idx = idx;
    id = _id++;
  }

  public NodeSearchResult(final BlockStmt blk, final int idx, final Node node) {
    super();
    this.blk = blk;
    this.idx = idx;
    id = _id++;
    this.node = node;
  }

  @Override
  public int compareTo(final NodeSearchResult o) {
    return id.compareTo(o.id);
  }
}
