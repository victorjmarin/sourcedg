package sourcedg.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.io.Attribute;
import org.jgrapht.io.ComponentAttributeProvider;
import org.jgrapht.io.ComponentNameProvider;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.DefaultAttribute;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.IntegerComponentNameProvider;

import sourcedg.graph.Edge;
import sourcedg.graph.Vertex;

public class GraphExporter {

	private static final ComponentNameProvider<Vertex> vertexLabelProvider = new ComponentNameProvider<Vertex>() {
		@Override
		public String getName(final Vertex component) {
			String result = component.getId() + "-" + component.getType() + "\n";
			final Set<String> subtypes = component.getSubtypes();
			// if (!subtypes.isEmpty())
			// result += subtypes + "\n";
			result += component.getLabel().replaceAll("\"", "'");
			return result;
		}
	};

	private static final ComponentNameProvider<Edge> edgeLabelProvider = new ComponentNameProvider<Edge>() {
		@Override
		public String getName(final Edge component) {
			return component.toString().replaceAll("\"", "'").toLowerCase();
		}
	};

	private static final ComponentAttributeProvider<Vertex> vertexAttrProvider = new ComponentAttributeProvider<Vertex>() {

		@Override
		public Map<String, Attribute> getComponentAttributes(final Vertex component) {
			final Map<String, Attribute> result = new HashMap<>();
			final Attribute fillColor = DefaultAttribute.createAttribute(component.getFillColor());
//			if (fillColor != null) {
//				result.put("style", DefaultAttribute.createAttribute("filled"));
//				result.put("fillcolor", fillColor);
//			}
			if (component.getType() != null) {
				switch (component.getType()) {
				default:
					result.put("fontname", DefaultAttribute.createAttribute("helvetica"));
					result.put("shape", DefaultAttribute.createAttribute("oval"));
					break;
				}
			}
			return result;
		}
	};

	private static final ComponentAttributeProvider<Edge> edgeAttrProvider = new ComponentAttributeProvider<Edge>() {

		@Override
		public Map<String, Attribute> getComponentAttributes(final Edge component) {
			final Map<String, Attribute> result = new HashMap<>();
			if (component.getType() != null) {
				switch (component.getType()) {
				case CTRL_TRUE:
					result.put("splines", DefaultAttribute.createAttribute(true));
					break;
				case DATA:
					result.put("style", DefaultAttribute.createAttribute("dashed"));
					break;
				case OUTPUT:
					result.put("style", DefaultAttribute.createAttribute("bold"));
					break;
				case CALL:
				case PARAM_IN:
				case PARAM_OUT:
					result.put("style", DefaultAttribute.createAttribute("dotted"));
					result.put("constraint", DefaultAttribute.createAttribute(false));
					break;
				default:
					break;
				}
			}
			return result;
		}
	};

	private static final DOTExporter<Vertex, Edge> exporter = new DOTExporter<>(new IntegerComponentNameProvider<>(),
			vertexLabelProvider, null, vertexAttrProvider, edgeAttrProvider);

	public static void exportAsDot(final Graph<Vertex, Edge> graph, final String path, final String fileName) {
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

			dotFile.delete();
		} catch (final ExportException e) {
			e.printStackTrace();
		}
	}

	public static <T> void exportAsDot2(final Graph<T, DefaultWeightedEdge> graph, final String path,
			final String fileName) {
		try {
			final String filePath = path + "/" + fileName + ".dot";
			final File dotFile = new File(filePath);

			DOTExporter<T, DefaultWeightedEdge> exporter = new DOTExporter<>(new IntegerComponentNameProvider<>(),
					new ComponentNameProvider<T>() {

						@Override
						public String getName(T component) {
							return component.toString();
						}
					}, new ComponentNameProvider<DefaultWeightedEdge>() {

						@Override
						public String getName(DefaultWeightedEdge e) {
							double roundedWeight = Math.round(graph.getEdgeWeight(e) * 100.0) / 100.0;
							;
							return String.valueOf(roundedWeight);
						}
					}, null, null);
			exporter.exportGraph(graph, dotFile);

			final Graphviz gv = new Graphviz();
			gv.readSource(filePath);

			final String type = "png";
			final String repesentationType = "dot";
			final File out = new File(path + "/" + fileName + "." + type);
			gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);

			dotFile.delete();
		} catch (final ExportException e) {
			e.printStackTrace();
		}
	}

	public static <T> void exportAsDot3(final Graph<T, DefaultEdge> graph, final String path, final String fileName) {
		try {
			final String filePath = path + "/" + fileName + ".dot";
			final File dotFile = new File(filePath);

			DOTExporter<T, DefaultEdge> exporter = new DOTExporter<>(new IntegerComponentNameProvider<>(),
					new ComponentNameProvider<T>() {

						@Override
						public String getName(T component) {
							return component.toString();
						}
					}, null, null, null);
			exporter.exportGraph(graph, dotFile);

			final Graphviz gv = new Graphviz();
			gv.readSource(filePath);

			final String type = "png";
			final String repesentationType = "dot";
			final File out = new File(path + "/" + fileName + "." + type);
			gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);

			dotFile.delete();
		} catch (final ExportException e) {
			e.printStackTrace();
		}
	}

	public static void exportDefaultAsDot(final Graph<Vertex, DefaultEdge> graph, final String path,
			final String fileName) {
		try {
			final DOTExporter<Vertex, DefaultEdge> exporter = new DOTExporter<>(new IntegerComponentNameProvider<>(),
					vertexLabelProvider, null, vertexAttrProvider, null);
			final String filePath = path + "/" + fileName + ".dot";
			final File dotFile = new File(filePath);
			exporter.exportGraph(graph, dotFile);

			final Graphviz gv = new Graphviz();
			gv.readSource(filePath);

			final String type = "png";
			final String repesentationType = "dot";
			final File out = new File(path + "/" + fileName + "." + type);
			gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);

			// dotFile.delete();
		} catch (final ExportException e) {
			e.printStackTrace();
		}
	}

}
