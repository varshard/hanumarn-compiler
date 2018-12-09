public class Call extends SequencialAST
{
	public Call()
	{
		super();
	}

	@Override
	public String toString(int level)
	{
		token = ((Identifier) astList.get(0)).getToken(); // token of name

		String treeString = printDot(level) + "call @ L" + token.getLine()
				+ "/T" + token.getTokenNum() + "\n";

		treeString += super.toString(level + 1);

		return treeString;
	}
}
