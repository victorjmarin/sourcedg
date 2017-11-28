package edu.rit.goal.sdg.java8.antlr4;

import java.util.Scanner;

public class Triangle4 {

  int attr;

  void triangle4() {
    int he = 0;
    Scanner in = new Scanner(System.in);
    do {
      String textToOutput = "Enter height: ";
      System.out.println(textToOutput);
      he = in.nextInt();
    } while (he < 0 || he > 23);
    in.close();
    int row = 0;
    while (row < he) {
      int column = 0;
      while (column < he - row - 1) {
        System.out.print("-");
        column += 1;
      }
      while (column < he) {
        int numberToPrint = compute(column);
        System.out.print(numberToPrint);
        column += 1;
      }
      System.out.println();
      row += 1;
    }
  }

  int compute(int k) {
    return k % 10;
  }
}

