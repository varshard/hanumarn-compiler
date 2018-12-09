public class Declarations extends SequencialAST
{
	public Declarations()
	{
		super();
	}

	public Declarations(Token letToken)
	{
		super();
		token = letToken;
	}

	@Override
	public String toString(int level)
	{
		String treeString = printDot(level) + "Dclns @";
		// Token token = ((Declare) astList.get(0)).getToken(0); // L and T from the first Token of Declare of Declarations

		if (token != null)
		{
			treeString += " @ L" + token.getLine() + "/T" + token.getTokenNum();
		}
		treeString += "\n";
		treeString += super.toString(level + 1);

		return treeString;
	}
}
