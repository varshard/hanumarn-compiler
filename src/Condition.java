public class Condition extends SequencialAST
{
	public Condition(Token ifToken)
	{
		super();
	}

	@Override
	public String toString(int level)
	{
		Token ifToken = ((Terminal) astList.get(0)).getToken();

		String treeString = printDot(level) + "Cond @ L" + ifToken.getLine()
				+ "/T" + ifToken.getTokenNum() + "\n";

		super.toString(level + 1);

		return treeString;
	}
}
