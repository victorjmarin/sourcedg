package sourcedg.builder;

/**
 * Flags to be considered by the PDG builder.
 * 
 * The 'normalize' flag will result in the builder breaking down expressions
 * into simpler nodes by introducing fresh variables. This is an experimental
 * feature.
 * 
 * The 'keepLines' flag will result in the builder assigning to every node in
 * the PDG the source code line it originated from.
 * 
 * The 'interproceduralCalls' flag will result in the builder including call
 * edges between procedures.
 * 
 * @author victorjmarin
 *
 */
public class PDGBuilderConfig
{

    private boolean normalize, keepLines, interproceduralCalls;

    private long initialVertexId;

    private PDGBuilderConfig()
    {}

    private PDGBuilderConfig(boolean normalize, boolean keepLines, boolean interproceduralCalls)
    {
	this.normalize = normalize;
	this.keepLines = keepLines;
	this.interproceduralCalls = interproceduralCalls;
    }

    public static PDGBuilderConfig create()
    {
	return new PDGBuilderConfig(
		false,
		false,
		false);
    }

    public PDGBuilderConfig initialVertexId(long id)
    {
	initialVertexId = id;
	return this;
    }

    public PDGBuilderConfig normalize()
    {
	normalize = true;
	return this;
    }

    public PDGBuilderConfig keepLines()
    {
	keepLines = true;
	return this;
    }

    public PDGBuilderConfig interproceduralCalls()
    {
	interproceduralCalls = true;
	return this;
    }

    public boolean isNormalize()
    {
	return normalize;
    }

    public boolean isKeepLines()
    {
	return keepLines;
    }

    public boolean isInterproceduralCalls()
    {
	return interproceduralCalls;
    }

    public long getInitialVertexId()
    {
	return initialVertexId;
    }

    public String toString()
    {
	return String
		.format("[normalize=%s, originalLines=%s, interproceduralCalls=%s]", normalize, keepLines, interproceduralCalls);
    }

}
