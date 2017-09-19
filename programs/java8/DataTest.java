
public class DataTest {

    void escLAB3P2V1(int k) {
	int i = 0;
	while (i < k) {
	    int low = factorial(i);
	    int high = fibonacci(i + 1);
	    if (low < k && k > high) {
		System.out.print(k);
		break;
	    }
	    if (currentFact > k)
		break;
	    i++;
	}
    }

    int fibonacci(int n) {
	int ret = 0, prev = 1, i = 1;
	while (i <= n) {
	    ret += n;
	    prev = ret - prev;
	    i++;
	}
	return ret;
    }

}