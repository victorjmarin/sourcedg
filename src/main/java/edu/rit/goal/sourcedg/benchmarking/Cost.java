package edu.rit.goal.sourcedg.benchmarking;

import java.math.BigDecimal;
import java.math.RoundingMode;

// TODO 0: Remove???
public class Cost implements Comparable<Cost> {

  private double beta;
  private double topological;
  private double sequence;

  public Cost() {
    beta = 1;
    topological = Double.MAX_VALUE;
    sequence = Double.MAX_VALUE;
  }

  public Cost(double beta, double topological, double sequence) {
    this.beta = beta;
    this.topological = topological;
    this.sequence = sequence;
  }

  public double getBeta() {
    return beta;
  }

  public void setBeta(double beta) {
    this.beta = beta;
  }

  public double getTopological() {
    return topological;
  }

  public void setTopological(double topological) {
    this.topological = topological;
  }

  public double getSequence() {
    return sequence;
  }

  public void setSequence(double sequence) {
    this.sequence = sequence;
  }

  public Double getCombined() {
    return beta * topological + (1 - beta) * sequence;
  }

  @Override
  public String toString() {
    return round(getCombined(), 2) + " (T: " + round(getTopological(), 2) + ", S: "
        + round(getSequence(), 2) + ")";
  }

  @Override
  public int compareTo(Cost c) {
    return getCombined().compareTo(c.getCombined());
  }

  public static float round(double d, int decimalPlace) {
    BigDecimal bd = new BigDecimal(Double.toString(d));
    bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
    return bd.floatValue();
  }

}
