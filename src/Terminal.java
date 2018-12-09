public class Terminal extends AST
{
	public Token token;

	public Terminal(Token token)
	{
		this.token = token;
	}

	public Terminal()
	{

	}

	@Override
	public String toString(int level)
	{
		String treeStr = printDot(level);
		String spelling = token.spelling;

		if (spelling.equals("int"))
		{
			spelling = "integer";
		}
		else if (spelling.equals("bool"))
		{
			spelling = "boolean";
		}
		else if (spelling.equals("read"))
		{
			spelling = "in";
		}

		treeStr += spelling + " @ L" + token.getLine() + "/T"
				+ token.getTokenNum() + "\n";

		return treeStr;
	}

	public Token getToken()
	{
		return token;
	}
}
