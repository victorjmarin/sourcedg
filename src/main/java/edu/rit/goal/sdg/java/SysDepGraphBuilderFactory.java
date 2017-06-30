package edu.rit.goal.sdg.java;

public class SysDepGraphBuilderFactory {

    public enum BuildStrategy {
	MARIN
    }

    private SysDepGraphBuilderFactory() {
    }

    public static SysDepGraphBuilder using(final BuildStrategy strategy) {
	SysDepGraphBuilder result = null;
	switch (strategy) {
	case MARIN:
	    result = new MarinSysDepGraphBuilder();
	    break;
	}
	return result;
    }

}
