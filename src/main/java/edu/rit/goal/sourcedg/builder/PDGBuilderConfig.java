package edu.rit.goal.sourcedg.builder;

public class PDGBuilderConfig {

  private boolean normalize;
  private boolean originalLines;

  private PDGBuilderConfig() {}

  private PDGBuilderConfig(boolean normalize, boolean originalLines) {
    super();
    this.normalize = normalize;
    this.originalLines = originalLines;
  }

  public static PDGBuilderConfig create() {
    return new PDGBuilderConfig(false, false);
  }

  public PDGBuilderConfig normalize() {
    normalize = true;
    return this;
  }

  public PDGBuilderConfig originalLines() {
    originalLines = true;
    return this;
  }

  public boolean isNormalize() {
    return normalize;
  }

  public boolean isOriginalLines() {
    return originalLines;
  }

  public String toString() {
    return String.format("[normalize=%s, originalLines=%s]", normalize, originalLines);
  }

}
