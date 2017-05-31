public class Circle {

    void main() {
	double p = 3.14;
	double rad = 3.0;
	if (debug)
	    rad = 4.0;
	double area = mult3(p, rad, rad);
	double circ = mult3(2, p, rad);
	System.out.println(area);
	System.out.println(circ);
    }

    double mult3(double op1, double op2, double op3) {
	return op1 * op2 * op3;
    }

}