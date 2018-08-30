package edu.rit.goal.sourcedg.benchmarking;

import java.util.Arrays;
import java.util.Scanner;

class BUYING2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    while (t-- != 0) {
      int n = sc.nextInt();
      int x = sc.nextInt();
      int[] M = new int[n];
      int sum = 0;
      for (int i = 0; i < n; i++) {
        M[i] = sc.nextInt();
        sum += M[i];
      }
      Arrays.sort(M);
      int temp = 0;
      temp = sum / x;
      if ((sum - M[0]) / x == temp) {
        temp = -1;
      }
      System.out.println(temp);
    }
  }
}
