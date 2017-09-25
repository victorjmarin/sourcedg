public class DataTest {

    public void m() {
	int a = 1;
	try {
	    System.out.println("Error");
	} catch (Exception e) {
	    System.out.println(e);
	} catch (Exception e) {
	    System.out.println(2);
	} finally {
	    System.out.println(1);
	}
    }

}