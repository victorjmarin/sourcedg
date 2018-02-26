public class Example {
	
	void test() {
		int x = 0;
		if (true) {
			;
			x = 7;
		}
	}
	
	void empties() {
		if (true);
		;
		while (false);
		;
		while (false) {
			;
			;
		}
		;
		do {
		} while (false);
		;
		do {
			;
		} while (false);
		;
		for (; true; );
	}
	
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
				while (true);
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
	
	void b() {
		int x = 0;
		for (; true; )
			x = 7;
	}
	
	void b(int h) {
		int x = 0;
		for (; true; )
			x = 7;
		;
		;
		for (int m = 0; true; ) {
			x = 7;
			;
		}
		;
		int a = 0;
		double d = 0.0;
		for (a = 1, d = 1.34; a < 10; ) {
			// something
		}
		;
		for (int m = 0, n = 0; true; ) {
			x = 7;
			;
		}
		;
		for (int m = 0, n = 0, o = 0; true; ) {
			x = 7;
			x = 8;
			x = 9;
			if (m < 10)
				x = 10;
			;
		}
		;
		for (int a = 0, b[] = { 1 }, c[][] = { { 1 }, { 2 } }; a < 10; ) {
			// something
		}
		;
		;
		for (int m = 0, n = 0; true; n++, m--, m+=2, n--) {
			x = 7;
			;
		}
		;
	}
	
}
