package edu.rit.goal.sdg.java;

public class SysDepGraphBuilderFactory {

    public enum BuildStrategy {
	HORWITZ
    }

    private SysDepGraphBuilderFactory() {
    }

    public static SysDepGraphBuilder using(final BuildStrategy strategy) {
	SysDepGraphBuilder result = null;
	switch (strategy) {
	case HORWITZ:
	    result = new HorwitzSysDepGraphBuilder();
	}
	return result;
    }

}
