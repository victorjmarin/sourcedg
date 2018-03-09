package edu.rit.goal.sourcedg.builder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.jgrapht.DirectedGraph;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.utils.Pair;
import edu.rit.goal.sourcedg.graph.CFG;
import edu.rit.goal.sourcedg.graph.Edge;
import edu.rit.goal.sourcedg.graph.EdgeType;
import edu.rit.goal.sourcedg.graph.PDG;
import edu.rit.goal.sourcedg.graph.Vertex;
import edu.rit.goal.sourcedg.graph.VertexCreator;
import edu.rit.goal.sourcedg.normalization.Normalizer;

public class PDGBuilder {

  public static final Logger LOGGER = Logger.getLogger("PDG");

  private PDG pdg;
  private Collection<CFG> cfgs;
  private CompilationUnit originalCu;
  private CompilationUnit normalizedCu;
  private CDGBuilder cdgBuilder;

  public PDGBuilder() {
    this(Level.OFF, null);
  }

  public PDGBuilder(final Level logLevel) {
    this(logLevel, null);
  }

  public PDGBuilder(final Level logLevel, final Handler handler) {
    LOGGER.setLevel(logLevel);
    LOGGER.addHandler(new ConsoleHandler());
    if (handler != null)
      LOGGER.addHandler(handler);
  }

  public void build(final InputStream in, final boolean normalizeCode) {
    final CompilationUnit cu = JavaParser.parse(in);
    build(cu, normalizeCode);
  }

  public void build(final Path in, final boolean normalizeCode) {
    try {
      CompilationUnit cu;
      cu = JavaParser.parse(in);
      build(cu, normalizeCode);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public void build(final String in, final boolean normalizeCode) {
    final CompilationUnit cu = JavaParser.parse(in);
    build(cu, normalizeCode);
  }

  private void build(CompilationUnit cu, final boolean normalizeCode) {
    final CombinedTypeSolver typeSolver = new CombinedTypeSolver();
    typeSolver.add(new ReflectionTypeSolver());
    originalCu = JavaParser.parse(cu.toString());
    if (normalizeCode) {
      final Normalizer normalizer = new Normalizer(cu, typeSolver);
      cu = normalizer.normalize();
    }
    normalizedCu = cu;
    cdgBuilder = new CDGBuilder(cu);
    cdgBuilder.build();
    pdg = cdgBuilder.getCDG();
    computeInterProceduralCalls(cdgBuilder.getMethodParams(), cdgBuilder.getCalls(),
        cdgBuilder.getMethodFormalOut());
    cfgs = cdgBuilder.getCfgs();
    computeDataDependencies();
  }

  private void computeInterProceduralCalls(
      final HashMap<String, Pair<Vertex, List<Vertex>>> methodParams,
      final HashMap<String, Set<Pair<Vertex, List<Vertex>>>> calls,
      final HashMap<Vertex, Vertex> methodFormalOut) {
    for (final Entry<String, Set<Pair<Vertex, List<Vertex>>>> e : calls.entrySet()) {
      final String methodName = e.getKey();
      final Pair<Vertex, List<Vertex>> defPair = methodParams.get(methodName);
      if (defPair == null) {
        LOGGER.warning("No definition found for call (" + methodName + ")");
        continue;
      }
      final Vertex callee = defPair.a;
      final Vertex formalOut = methodFormalOut.get(callee);
      final Set<Pair<Vertex, List<Vertex>>> callPairs = e.getValue();
      for (final Pair<Vertex, List<Vertex>> callPair : callPairs) {
        final int callSize = callPair.b.size();
        final int defSize = defPair.b.size();
        if (callSize != defSize) {
          LOGGER.warning("Definition found for call (" + methodName
              + ") but number of parameters do not match (" + callSize + " args. vs " + defSize
              + " params.)");
          continue;
        }
        final Vertex caller = callPair.a;
        VertexCreator.selfCall(caller);
        pdg.addEdge(caller, callee, new Edge(caller, callee, EdgeType.CALL));
        for (int i = 0; i < callPair.b.size(); i++) {
          final Vertex callArg = callPair.b.get(i);
          final Vertex defParam = defPair.b.get(i);
          pdg.addEdge(callArg, defParam, new Edge(callArg, defParam, EdgeType.PARAM_IN));
        }
        final Vertex actualOut = pdg.actualOut(caller);
        if (actualOut == null) {
          if (caller.getType().isAssign())
            LOGGER.warning("Could not find ACTUAL_OUT vertex for " + caller);
          continue;
        }
        pdg.addEdge(formalOut, actualOut, new Edge(formalOut, actualOut, EdgeType.PARAM_OUT));
      }
    }
  }

  // https://dl.acm.org/citation.cfm?id=154268
  private void computeDataDependencies() {
    for (final DirectedGraph<Vertex, Edge> cfg : cfgs)
      reachingDefinitions(cfg);
    for (final Vertex v : pdg.vertexSet()) {
      final Map<String, Set<Vertex>> inDefs = inDefs(v);
      for (final String use : v.getRefs()) {
        boolean noEdgeForUse = true;
        final Set<Vertex> inVtcs = inDefs.get(use);
        if (inVtcs != null) {
          for (final Vertex inVtx : inVtcs)
            pdg.addEdge(inVtx, v, new Edge(inVtx, v, EdgeType.DATA));
          noEdgeForUse = false;
        }
        if (noEdgeForUse) {
          // TODO: Create initial state vertex
        }
      }
    }
  }

  private void reachingDefinitions(final DirectedGraph<Vertex, Edge> cfg) {
    boolean changes = true;
    while (changes) {
      changes = false;
      for (final Vertex n : cfg.vertexSet()) {
        final Set<Vertex> oldOut = n.getOut();
        final Set<Vertex> oldIn = n.getIn();
        final Set<Edge> incomingEdges = cfg.incomingEdgesOf(n);
        final Set<Vertex> pred =
            incomingEdges.stream().map(e -> e.getSource()).collect(Collectors.toSet());
        for (final Vertex p : pred)
          n.getIn().addAll(p.getOut());
        final Set<Vertex> out = new HashSet<>();
        final Set<Vertex> diff = new HashSet<>(n.getIn());
        Set<Vertex> kill = new HashSet<>();
        if (n.getDef() != null) {
          out.add(n);
          kill = n.getIn().stream().filter(v -> v.getDef().equals(n.getDef()))
              .collect(Collectors.toSet());
        }
        diff.removeAll(kill);
        out.addAll(diff);
        n.setOut(out);
        // Check if changes
        changes = changes || !oldIn.equals(n.getIn()) || !oldOut.equals(n.getOut());
      }
    }
  }

  private Map<String, Set<Vertex>> inDefs(final Vertex v) {
    final Set<Vertex> in = v.getIn();
    return in.stream().collect(Collectors.groupingBy(Vertex::getDef, Collectors.toSet()));
  }

  public PDG getPDG() {
    return pdg;
  }

  public Collection<CFG> getCfgs() {
    return cfgs;
  }

  public CompilationUnit getOriginalCU() {
    return originalCu;
  }

  public CompilationUnit getNormalizedCU() {
    return normalizedCu;
  }

  // Flag indicating if the graph built covered all the source code
  public boolean isComplete() {
    return cdgBuilder.getUnmatchedAstNodes().isEmpty();
  }

  public Map<String, Integer> getUnmatchedAstNodes() {
    return cdgBuilder.getUnmatchedAstNodes();
  }

}
