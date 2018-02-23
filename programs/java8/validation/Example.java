public class Example {
	void x() {
		int x = 0;
		if (true)
			x = 7;
	}
	
	void y() {
		int x = 0;
		if (true)
			x = 7;
		x = 8;
	}
	
	void y() {
		int x = 0;
		if (true) {
			x = 7;
			x = 9;
		} else
			x = 9;
		x = 8;
	}
}
