public class NestedCall7 {

    void foo() {
	for (int i = 0; check(i); i++) {}
    }

    boolean check(int i) {
	return i < 10;
    }

}