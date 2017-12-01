package edu.rit.goal.sdg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <dl>
 * <dt>Purpose: GraphViz Java API
 * <dd>
 *
 * <dt>Description:
 * <dd>With this Java class you can simply call dot from your Java programs.
 * <dt>Example usage:
 * <dd>
 * 
 * <pre>
 * GraphViz gv = new GraphViz();
 * gv.addln(gv.start_graph());
 * gv.addln("A -> B;");
 * gv.addln("A -> C;");
 * gv.addln(gv.end_graph());
 * System.out.println(gv.getDotSource());
 *
 * String type = "gif";
 * String representationType = "dot";
 * File out = new File("out." + type); // out.gif in this example
 * gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, representationType), out);
 * </pre>
 * 
 * </dd>
 *
 * </dl>
 *
 * @version v0.6.1, 2016/04/10 (April) -- Patch of Markus Keunecke is added. The eclipse project
 *          configuration was extended with the maven nature.
 * @version v0.6, 2013/11/28 (November) -- Patch of Olivier Duplouy is added. Now you can specify
 *          the representation type of your graph: dot, neato, fdp, sfdp, twopi, circo
 * @version v0.5.1, 2013/03/18 (March) -- Patch of Juan Hoyos (Mac support)
 * @version v0.5, 2012/04/24 (April) -- Patch of Abdur Rahman (OS detection + start subgraph + read
 *          config file)
 * @version v0.4, 2011/02/05 (February) -- Patch of Keheliya Gallaba is added. Now you can specify
 *          the type of the output file: gif, dot, fig, pdf, ps, svg, png, etc.
 * @version v0.3, 2010/11/29 (November) -- Windows support + ability to read the graph from a text
 *          file
 * @version v0.2, 2010/07/22 (July) -- bug fix
 * @version v0.1, 2003/12/04 (December) -- first release
 * @author Laszlo Szathmary (<a href="jabba.laci@gmail.com">jabba.laci@gmail.com</a>)
 */
public class Graphviz {
  /**
   * Detects the client's operating system.
   */
  private final static String osName = System.getProperty("os.name").replaceAll("\\s", "");

  /**
   * The image size in dpi. 96 dpi is normal size. Higher values are 10% higher each. Lower values
   * 10% lower each.
   *
   * dpi patch by Peter Mueller
   */
  private final int[] dpiSizes =
      {46, 51, 57, 63, 70, 78, 86, 96, 106, 116, 128, 141, 155, 170, 187, 206, 226, 249};

  /**
   * Define the index in the image size array.
   */
  private int currentDpiPos = 7;

  /**
   * Increase the image size (dpi).
   */
  public void increaseDpi() {
    if (currentDpiPos < (dpiSizes.length - 1)) {
      ++currentDpiPos;
    }
  }

  /**
   * Decrease the image size (dpi).
   */
  public void decreaseDpi() {
    if (currentDpiPos > 0) {
      --currentDpiPos;
    }
  }

  public int getImageDpi() {
    return dpiSizes[currentDpiPos];
  }

  /**
   * The source of the graph written in dot language.
   */
  private StringBuilder graph = new StringBuilder();

  private String tempDir;

  private String executable;

  /**
   * Convenience Constructor with default OS specific pathes creates a new GraphViz object that will
   * contain a graph. Windows: executable = c:/Program Files (x86)/Graphviz 2.28/bin/dot.exe tempDir
   * = c:/temp MacOs: executable = /usr/local/bin/dot tempDir = /tmp Linux: executable =
   * /usr/bin/dot tempDir = /tmp
   */
  public Graphviz() {
    if (Graphviz.osName.contains("Windows")) {
      tempDir = "c:/temp";
      executable = "c:/Program Files (x86)/Graphviz 2.28/bin/dot.exe";
    } else if (Graphviz.osName.equals("MacOSX")) {
      tempDir = "/tmp";
      executable = "/usr/local/bin/dot";
    } else if (Graphviz.osName.equals("Linux")) {
      tempDir = "/tmp";
      executable = "/usr/bin/dot";
    }
  }

  /**
   * Configurable Constructor with path to executable dot and a temp dir
   *
   * @param executable absolute path to dot executable
   * @param tempDir absolute path to temp directory
   */
  public Graphviz(final String executable, final String tempDir) {
    this.executable = executable;
    this.tempDir = tempDir;
  }

  /**
   * Returns the graph's source description in dot language.
   * 
   * @return Source of the graph in dot language.
   */
  public String getDotSource() {
    return graph.toString();
  }

  /**
   * Adds a string to the graph's source (without newline).
   */
  public void add(final String line) {
    graph.append(line);
  }

  /**
   * Adds a string to the graph's source (with newline).
   */
  public void addln(final String line) {
    graph.append(line + "\n");
  }

  /**
   * Adds a newline to the graph's source.
   */
  public void addln() {
    graph.append('\n');
  }

  public void clearGraph() {
    graph = new StringBuilder();
  }

  /**
   * Returns the graph as an image in binary format.
   * 
   * @param dot_source Source of the graph to be drawn.
   * @param type Type of the output image to be produced, e.g.: gif, dot, fig, pdf, ps, svg, png.
   * @param representationType Type of how you want to represent the graph:
   *        <ul>
   *        <li>dot</li>
   *        <li>neato</li>
   *        <li>fdp</li>
   *        <li>sfdp</li>
   *        <li>twopi</li>
   *        <li>circo</li>
   *        </ul>
   * @see http://www.graphviz.org under the Roadmap title
   * @return A byte array containing the image of the graph.
   */
  public byte[] getGraph(final String dot_source, final String type, final String representationType) {
    File dot;
    byte[] img_stream = null;

    try {
      dot = writeDotSourceToFile(dot_source);
      if (dot != null) {
        img_stream = get_img_stream(dot, type, representationType);
        if (dot.delete() == false) {
          System.err.println("Warning: " + dot.getAbsolutePath() + " could not be deleted!");
        }
        return img_stream;
      }
      return null;
    } catch (final java.io.IOException ioe) {
      return null;
    }
  }

  /**
   * Writes the graph's image in a file.
   * 
   * @param img A byte array containing the image of the graph.
   * @param file Name of the file to where we want to write.
   * @return Success: 1, Failure: -1
   */
  public int writeGraphToFile(final byte[] img, final String file) {
    final File to = new File(file);
    return writeGraphToFile(img, to);
  }

  /**
   * Writes the graph's image in a file.
   * 
   * @param img A byte array containing the image of the graph.
   * @param to A File object to where we want to write.
   * @return Success: 1, Failure: -1
   */
  public int writeGraphToFile(final byte[] img, final File to) {
    try {
      final FileOutputStream fos = new FileOutputStream(to);
      fos.write(img);
      fos.close();
    } catch (final java.io.IOException ioe) {
      return -1;
    }
    return 1;
  }

  /**
   * It will call the external dot program, and return the image in binary format.
   * 
   * @param dot Source of the graph (in dot language).
   * @param type Type of the output image to be produced, e.g.: gif, dot, fig, pdf, ps, svg, png.
   * @param representationType Type of how you want to represent the graph:
   *        <ul>
   *        <li>dot</li>
   *        <li>neato</li>
   *        <li>fdp</li>
   *        <li>sfdp</li>
   *        <li>twopi</li>
   *        <li>circo</li>
   *        </ul>
   * @see http://www.graphviz.org under the Roadmap title
   * @return The image of the graph in .gif format.
   */
  private byte[] get_img_stream(final File dot, final String type, final String representationType) {
    File img;
    byte[] img_stream = null;

    try {
      img = File.createTempFile("graph_", "." + type, new File(tempDir));
      final Runtime rt = Runtime.getRuntime();

      // patch by Mike Chenault
      // representation type with -K argument by Olivier Duplouy
      final String[] args = {executable, "-T" + type, "-K" + representationType,
          "-Gdpi=" + dpiSizes[currentDpiPos], dot.getAbsolutePath(), "-o",
          img.getAbsolutePath()};
      final Process p = rt.exec(args);
      p.waitFor();

      final FileInputStream in = new FileInputStream(img.getAbsolutePath());
      img_stream = new byte[in.available()];
      in.read(img_stream);
      // Close it if we need to
      if (in != null) {
        in.close();
      }

      if (img.delete() == false) {
        System.err.println("Warning: " + img.getAbsolutePath() + " could not be deleted!");
      }
    } catch (final java.io.IOException ioe) {
      System.err.println("Error:    in I/O processing of tempfile in dir " + tempDir + "\n");
      System.err.println("       or in calling external command");
      ioe.printStackTrace();
    } catch (final java.lang.InterruptedException ie) {
      System.err.println("Error: the execution of the external program was interrupted");
      ie.printStackTrace();
    }

    return img_stream;
  }

  /**
   * Writes the source of the graph in a file, and returns the written file as a File object.
   * 
   * @param str Source of the graph (in dot language).
   * @return The file (as a File object) that contains the source of the graph.
   */
  private File writeDotSourceToFile(final String str) throws java.io.IOException {
    File temp;
    try {
      temp = File.createTempFile("graph_", ".dot.tmp", new File(tempDir));
      final FileWriter fout = new FileWriter(temp);
      fout.write(str);
      fout.close();
    } catch (final Exception e) {
      System.err.println("Error: I/O error while writing the dot source to temp file!");
      return null;
    }
    return temp;
  }

  /**
   * Returns a string that is used to start a graph.
   * 
   * @return A string to open a graph.
   */
  public String start_graph() {
    return "digraph G {";
  }

  /**
   * Returns a string that is used to end a graph.
   * 
   * @return A string to close a graph.
   */
  public String end_graph() {
    return "}";
  }

  /**
   * Takes the cluster or subgraph id as input parameter and returns a string that is used to start
   * a subgraph.
   * 
   * @return A string to open a subgraph.
   */
  public String start_subgraph(final int clusterid) {
    return "subgraph cluster_" + clusterid + " {";
  }

  /**
   * Returns a string that is used to end a graph.
   * 
   * @return A string to close a graph.
   */
  public String end_subgraph() {
    return "}";
  }

  /**
   * Read a DOT graph from a text file.
   *
   * @param input Input text file containing the DOT graph source.
   */
  public void readSource(final String input) {
    final StringBuilder sb = new StringBuilder();
    try {
      final String content = new String(Files.readAllBytes(Paths.get(input)));
      sb.append(content);
    } catch (final Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    graph = sb;
  }

}
