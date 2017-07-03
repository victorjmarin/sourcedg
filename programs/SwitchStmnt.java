public class SwitchStmnt {

    void foo(int s) {
	switch (s) {
	case 0:
	case 1:
	    System.out.println(1);
	case 2:
	    System.out.println(2);
	default:
	    System.out.println(3);
	}

	// if (s == 0 || s == 1) {
	// System.out.println(1);
	// }
	// if (s == 0 || s == 1 || s == 2) {
	// System.out.println(2);
	// }
    }

}