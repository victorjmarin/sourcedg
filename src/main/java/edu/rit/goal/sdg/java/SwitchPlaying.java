package edu.rit.goal.sdg.java;

public class SwitchPlaying {

    public static void main(final String[] args) {
	final int s = 1;

	if (s == 1) {
	    System.out.println(1);
	    System.out.println(2);
	} else if (s == 2) {
	    System.out.println(2);
	}

	switch (s) {
	case 1:
	    System.out.println(1);
	    break;
	case 2:
	    System.out.println(2);
	}

    }

}
