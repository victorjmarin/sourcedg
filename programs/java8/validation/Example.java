public class Example {
	void x() {
		int x = 0;
		if (true)
			x = 7;
	}
	
	void x(int z) {
		int x = 0;
		if (true) {
			x = 7;
			x = 9;
		}
	}
	
	void y() {
		int x = 0;
		if (true)
			x = 7;
		x = 8;
		while (false)
			x = 9;
	}
	
	void y() {
		int x = 0;
		if (true) {
			x = 7;
			x = 9;
		} else
			x = 9;
		x = 8;
		while (false)
			x = 10;
		x = 11;
	}
}
