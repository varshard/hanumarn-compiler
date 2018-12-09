public class Body extends SequencialAST
{

	public Body(Token beginToken)
	{
		super();
		token = beginToken;
	}

	@Override
	public String toString(int level)
	{
		String treeString = printDot(level) + "bodyblock @ L" + token.getLine()
				+ "/T" + token.getTokenNum() + "\n";
		treeString += super.toString(level + 1);
		// System.out.println("boyd size " + astList.size());

		return treeString;
	}
}
