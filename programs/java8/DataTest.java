
public class DataTest {

    void assignment1(int[] a) {
	int o = 1, e = 0, i = 0;

	while (i < a.length)
	    ;
	{
	    if (i % 2 == 1)
		o *= a[i];
	    if (i % 2 == 0)
		e += a[i];
	    i++;
	}

	System.out.println("Even: " + e + ", Odd: " + o);
    }

}