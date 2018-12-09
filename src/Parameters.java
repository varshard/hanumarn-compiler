public class Parameters extends SequencialAST
{
	// private final ArrayList<Declare> dclnList;
	private final Token token;

	public Parameters(Token token) // token for ( the first token of params
	{
		super();
		this.token = token;
	}

	@Override
	public String toString(int level)
	{

		String treeString = printDot(level) + "params @ L" + token.getLine()
				+ "/T" + token.getTokenNum() + "\n";
		treeString += super.toString(level + 1);

		return treeString;
	}
}
