public class BreakContinue {

    void foo() {
	int r = 2;
	int[] arr1 = new int[] { 1 };
	int[] arr2 = new int[] { 2 };
	int[] arr3 = new int[] { 3 };
	for (int i : arr1) {
	    for (int j : arr2) {
		continue;
	    }
	}
	for (int k = 0; k < 10; k++) {
	    r = 2;
	    r = 4;
	    arr1 = null;
	    break;
	}
    }

}