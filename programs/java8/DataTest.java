import java.util.Scanner;

public class Circle {

    void countGoldMedals(int year) {
	       int i = 1, medals = 0, p = 0, y = 0;
	       String ln = "";
	       Scanner s = new Scanner(
	                     new File("summer_olympics.txt"));
	       while (s.hasNext()) {
	              if (i % 5 == 0)                   ln = s.next();
	              if (i % 5 == 1)                   y = s.nextInt();
	              if (i % 5 == 2)                   p = s.nextInt();
	              if (i % 5 == 2 && y == year
	                                  && p == 1)                 medals++;
	              i++;
	       }
	       s.close();
	       System.out.println(medals);
	}


}