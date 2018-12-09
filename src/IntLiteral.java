public class IntLiteral extends Terminal
{
	public IntLiteral(Token token)
	{
		super(token);
	}

	@Override
	public String toString(int level)
	{
		String treeString = printDot(level);
		treeString += "<integer> @ L" + token.getLine() + "/T"
				+ token.getTokenNum() + "\n";
		treeString += super.toString(level + 1);
		return treeString;
	}
}
