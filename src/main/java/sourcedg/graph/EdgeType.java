package sourcedg.graph;

public enum EdgeType {

	DATA, OUTPUT, CTRL_TRUE, CTRL_FALSE, CALL, PARAM_IN, PARAM_OUT, MEMBER_OF;

	public boolean isControl() {
		return equals(CTRL_TRUE) || equals(CTRL_FALSE);
	}

	@Override
	public String toString() {
		String result = super.toString().toLowerCase();
		result = result.substring(0, 1).toUpperCase() + result.substring(1);
		result = result.replace("_", "-");
		return result;
	}

}
