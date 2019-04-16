package sourcedg;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.logging.Level;

import sourcedg.builder.PDGBuilder;
import sourcedg.builder.PDGBuilderConfig;
import sourcedg.graph.CFG;
import sourcedg.graph.PDG;
import sourcedg.graph.VertexType;
import sourcedg.util.GraphExporter;

public class Test {

	public static void main(final String[] args) throws Exception {
		final FileInputStream in = new FileInputStream("/Users/goal/git/jplag-filtering/fJOHNY/12481738.java");
		PDGBuilderConfig config = PDGBuilderConfig.create();
		final PDGBuilder builder = new PDGBuilder(config, Level.WARNING);
		builder.build(in);
		final PDG pdg = builder.getPDG();
		pdg.collapseNodes(VertexType.ACTUAL_OUT);
		pdg.collapseNodes(VertexType.ARRAY_IDX);
		System.out.println(pdg);
		final Iterator<CFG> it = builder.getCfgs().iterator();
		final CFG cfg = it.next();
		// System.out.println(cfg.cyclomaticComplexity());
		GraphExporter.exportAsDot(pdg, "/Users/goal/Desktop", "pdg");
	}

}
