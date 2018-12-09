public class Error extends AST
{
	private final Token token;

	public Error(Token token)
	{
		this.token = token;
	}

	@Override
	public String toString(int level)
	{
		String treeString = "!ERROR: " + token.spelling + " @ L"
				+ token.getLine() + "/T" + token.getTokenNum() + "\n";
		return treeString;
	}
}
