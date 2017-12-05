public class Triangle1 {

  public static void main(String[] args) {
    int h = 0;
    Scanner scan = new Scanner(System.in);
    do {
      System.out.println("Enter height: ");
      h = scan.nextInt();
    } while (h < 0 || h > 23);
    scan.close();
    for (int i = 0; i < h; i += 1) {
      int j = 0;
      for (; j < h - i - 1; j += 1)
        System.out.print("-");
      for (; j < h; j += 1)
        System.out.print(j % 10);
      System.out.println();
    }
  }
}
