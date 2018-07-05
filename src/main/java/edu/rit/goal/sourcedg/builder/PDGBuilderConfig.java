package edu.rit.goal.sourcedg.builder;

public class PDGBuilderConfig {

  private boolean normalize, originalLines, interproceduralCalls;

  private PDGBuilderConfig() {}

  private PDGBuilderConfig(boolean normalize, boolean originalLines, boolean interproceduralCalls) {
    super();
    this.normalize = normalize;
    this.originalLines = originalLines;
    this.interproceduralCalls = interproceduralCalls;
  }

  public static PDGBuilderConfig create() {
    return new PDGBuilderConfig(false, false, false);
  }

  public PDGBuilderConfig normalize() {
    normalize = true;
    return this;
  }

  public PDGBuilderConfig originalLines() {
    originalLines = true;
    return this;
  }
  
  public PDGBuilderConfig interproceduralCalls() {
	  interproceduralCalls = true;
	  return this;
  }

  public boolean isNormalize() {
    return normalize;
  }

  public boolean isOriginalLines() {
    return originalLines;
  }
  
  public boolean isInterproceduralCalls() {
	return interproceduralCalls;
  }

  public String toString() {
    return String.format("[normalize=%s, originalLines=%s, interproceduralCalls=%s]", normalize, originalLines, interproceduralCalls);
  }

}
