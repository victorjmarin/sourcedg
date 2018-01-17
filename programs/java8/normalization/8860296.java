import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Buying {
  
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(input.readLine());
    String op = "";
    for (int i = 0; i < t; i++) {
      StringTokenizer st = new StringTokenizer(input.readLine(), " ");
      int n = Integer.parseInt(st.nextToken());
      int notes[] = new int[n];
      int x = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(input.readLine(), " ");
      int sum = 0, c = 0;
      while (st.hasMoreTokens()) {
        notes[c] = Integer.parseInt(st.nextToken());
        sum += notes[c++];
      }
      int no = sum % x;
      c = 0;
      if (no != 0)
        while (c < n) {
          if (notes[c] < no) {
            sum = 0;
            break;
          }
          c++;
        }
      if (sum == 0)
        op += "-1\n";
      else
        op += sum / x + "\n";
    }
    System.out.println(op);

  }
}

