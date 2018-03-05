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
	
	void easyWhileBrk() {
		while (false)
			break;
	}
	
	void easyDoWhileBrk() {
		do {
			break;
		} while (false);
	}
	
	void easyWhileContinue() {
		while (false)
			continue;
	}
	
	void easyDoWhileContinue() {
		do {
			continue;
		} while (false);
	}
	void easyForBrk() {
		for ( ; false; )
			break;
	}

	void easyForContinue() {
		for ( ; false; )
			continue;
	}
	
	void enhancedFor() {
		for (int x : new int[]{})
			break;
		for (int x : new int[]{})
			continue;
		for (int x : new int[]{}) {
			x = 8;
			break;
		}
		for (int x : new int[]{}) {
			x = 9;
			break;
		}
	}
	
	void brk() {
		int x = 0;
		while (true) {
			if (false) {
				if (true)
					break;
				else
					x = 9;
				x = 10;
			} else {
				x = 11;
				if (false)
					break;
				else
					x = 12;
			}
			x = 15;
		}
		x = 16;
		while (true) {
			if (false) {
				if (true)
					break;
				else
					x = 9;
				x = 10;
			} else {
				x = 11;
				if (false)
					x = 12;
				else
					break;
			}
			x = 15;
		}
		for (; true; ) {
			x = 7;
			break;
		}
		;
		;
		for (int m = 0; true; ) {
			x = 7;
			;
			continue;
		}
		;
		int a = 0;
		double d = 0.0;
		for (a = 1, d = 1.34; a < 10; ) {
			// something
			continue;
		}
		;
		for (int m = 0, n = 0; true; ) {
			x = 7;
			;
			break;
		}
		;
		for (int m = 0, n = 0, o = 0; true; ) {
			x = 7;
			x = 8;
			x = 9;
			if (m < 10) {
				x = 10;
				break;
			}
			;
		}
		;
		for (int a = 0, b[] = { 1 }, c[][] = { { 1 }, { 2 } }; a < 10; ) {
			// something
			break;
		}
		;
		;
		for (int m = 0, n = 0; true; n++, m--, m+=2, n--) {
			x = 7;
			;
			break;
		}
		;
		for (int m = 0, n = 0; true; n++, m--, m+=2, n--) {
			x = 7;
			;
			if (true)
				continue;
			else
				x = 8;
			x = 10;
		}
		;
	}
	
	static void staticMethod() {
		int x = 0;
		while (true)
			if (false) {
				x += 9;
				return;
			}
		x++;
	}
	
	void ifWithFors() {
		int x = 0;
		if (true)
			for (; false; )
				x++;
		else
			for (int y = 0; true; )
				x--;
		return;
	}
	
	// TODO 0: Try returns with methods, static methods and constructors.
	
}
