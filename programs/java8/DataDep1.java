public class DataDep1 {

    int foo(int p) {
	int[] a = new int[2];
	int i = 0;
	a[i] = 2;
	int y = a[0];
    }

}