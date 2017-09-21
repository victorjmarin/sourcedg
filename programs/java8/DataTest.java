public class Circle {

    void countGoldMedals(int year) throws Exception {
	int counter = 0, y = 0, p = 0, medals = 0;
	String fn = "", ln = "", end = "";
	java.util.Scanner s = new java.util.Scanner(
		new java.io.File("assignments/rit-all-g-medals/summer_olympics.txt"));
	while (s.hasNext()) {
	    if (counter % 5 == 0)
		fn = s.next();
	    if (counter % 5 == 1)
		ln = s.next();
	    if (counter % 5 == 2)
		y = s.nextInt();
	    if (counter % 5 == 3)
		p = s.nextInt();
	    if (counter % 5 == 4)
		end = s.next();
	    if (counter % 5 == 4 && y == year && p == 1)
		medals += 1;
	    counter++;
	}
	s.close();
	System.out.println(medals);
    }
}