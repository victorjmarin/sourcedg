public class UnsupportedMultipleExitPoints {

    int foo1() {
	return 4;
    }

    int foo2(int p) {
	if (p > 2) {
	    return 0;
	}
	return 1;
    }

}