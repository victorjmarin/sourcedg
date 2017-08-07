public class NestedCall1 {

    static void foo() {
	f(f(4));
    }

    static int f(final int a) {
	return a;
    }

}