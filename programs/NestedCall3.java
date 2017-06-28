public class NestedCall3 {

    void foo() {
	String s = "str".equals(str());
    }

    String str() {
	return "str";
    }

}