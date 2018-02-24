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
			if (false)
				x = 6;
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
	
	void y(int z) {
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
	
	void z() {
		int x = 0;
		while (true) {
			x = 7;
			while (false) {
				x = 9;
				while (false)
					x = 5;
			}
		}
		x = 8;
	}
	
	void z(int a) {
		int x = 0;
		do {
			x = 7;
		} while (true);
	}
	
	void a() {
		int x = 0;
		do {
			x = 7;
		} while (true);
		x = 8;
		do {
			x = 7;
			x = 9;
		} while (true);
	}
	
	void a(int z) {
		int x = 0;
		do {
			x = 7;
		} while (true);
		x = 8;
		do {
			x = 1;
			do {
				x = 2;
				do {
					x = 3;
					x = 4;
					x = 5;
				} while (true);
				x = 6;
			} while (true);
			x = 7;
		} while (true);
	}
	
}
