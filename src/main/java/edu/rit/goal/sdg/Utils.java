package edu.rit.goal.sdg;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.ComponentNameProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.ExportException;
import org.jgrapht.ext.IntegerComponentNameProvider;
import edu.rit.goal.sdg.graph.Edge;
import edu.rit.goal.sdg.graph.Vertex;

public class Utils {

  private static final ComponentNameProvider<Vertex> vertexLabelProvider =
      new ComponentNameProvider<Vertex>() {
        @Override
        public String getName(final Vertex component) {
          return component.getId() + "-" + component.getType() + "\n"
              + component.getLabel().replaceAll("\"", "'");
        }
      };

  private static final ComponentNameProvider<Edge> edgeLabelProvider =
      new ComponentNameProvider<Edge>() {
        @Override
        public String getName(final Edge component) {
          return component.toString().replaceAll("\"", "'");
        }
      };

  private static final ComponentAttributeProvider<Vertex> vertexAttrProvider =
      new ComponentAttributeProvider<Vertex>() {

        @Override
        public Map<String, String> getComponentAttributes(final Vertex component) {
          final Map<String, String> result = new HashMap<>();
          if (component.getType() != null) {
            switch (component.getType()) {
              default:
                result.put("fontname", "helvetica");
                break;
            }
          }
          return result;
        }
      };

  private static final ComponentAttributeProvider<Edge> edgeAttrProvider =
      new ComponentAttributeProvider<Edge>() {

        @Override
        public Map<String, String> getComponentAttributes(final Edge component) {
          final Map<String, String> result = new HashMap<>();
          if (component.getType() != null) {
            switch (component.getType()) {
              case CTRL_TRUE:
                result.put("splines", "true");
                break;
              case DATA:
                result.put("style", "dashed");
                break;
              case CALL:
              case PARAM_IN:
              case PARAM_OUT:
                result.put("style", "dotted");
                // result.put("constraint", "false");
                break;
              default:
                break;
            }
          }
          return result;
        }
      };

  private static final DOTExporter<Vertex, Edge> exporter =
      new DOTExporter<>(new IntegerComponentNameProvider<>(), vertexLabelProvider, null,
          vertexAttrProvider, edgeAttrProvider);

  public static void exportAsDot(final Graph<Vertex, Edge> graph, final String path,
      final String fileName) {
    try {
      final String filePath = path + "/" + fileName + ".dot";
      final File dotFile = new File(filePath);
      exporter.exportGraph(graph, dotFile);

      final Graphviz gv = new Graphviz();
      gv.readSource(filePath);

      final String type = "png";
      final String repesentationType = "dot";
      final File out = new File(path + "/" + fileName + "." + type);
      gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);

      //dotFile.delete();
    } catch (final ExportException e) {
      e.printStackTrace();
    }
  }

}
