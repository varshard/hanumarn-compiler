public class Identifier extends Terminal
{
	public Identifier(Token token)
	{
		super(token);
	}

	@Override
	public String toString(int level)
	{
		String treeString = printDot(level);
		treeString += "<identifier> @ L" + token.getLine() + "/T"
				+ token.getTokenNum() + "\n";
		treeString += super.toString(level + 1);
		return treeString;
	}

	@Override
	public Token getToken()
	{
		return token;
	}
}
