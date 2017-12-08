package edu.rit.goal.sdg.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Stats {

  public static void main(final String[] args) throws IOException {
    final BufferedReader reader = new BufferedReader(new FileReader("programs/failed.txt"));
    int min = Integer.MAX_VALUE;
    String line = null;
    String result = null;

    while ((line = reader.readLine()) != null) {
      final BufferedReader r = new BufferedReader(new FileReader(line));
      final int l = len(r);
      if (l < min) {
        result = line;
        min = l;
      }
    }
    System.out.println(result);
    System.out.println(min);
  }

  private static int len(final BufferedReader br) throws IOException {
    int result = 0;
    while (br.readLine() != null)
      result++;
    br.close();
    return result;
  }

}
