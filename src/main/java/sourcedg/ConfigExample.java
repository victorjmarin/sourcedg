package sourcedg;

import sourcedg.builder.PDGBuilder;
import sourcedg.builder.PDGBuilderConfig;

public class ConfigExample {

  public static void main(String[] args) {

    // Default configuration. Keeps original expressions and does not include interprocedural calls or lines.
    PDGBuilderConfig config1 = PDGBuilderConfig.create();
    final PDGBuilder builder1 = new PDGBuilder(config1);

    // Consider interprocedural calls.
    PDGBuilderConfig config2 = PDGBuilderConfig.create().interproceduralCalls();
    final PDGBuilder builder2 = new PDGBuilder(config2);

    // Normalize code and keep line numbers.
    PDGBuilderConfig config3 = PDGBuilderConfig.create().normalize().keepLines();
    final PDGBuilder builder3 = new PDGBuilder(config3);
    
    // Slicing example
    
    // Pattern matching example?


  }

}
