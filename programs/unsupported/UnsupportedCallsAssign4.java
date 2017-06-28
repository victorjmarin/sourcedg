public class NestedCall4 {

    void foo() {
	String s = "str";
	boolean b = s.equals(str());
    }

    String str() {
	return "str";
    }

}