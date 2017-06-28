public class NestedCall2 {

    void foo() {
	String s = "str";
	s.equals(s.equals("str"));
    }

}