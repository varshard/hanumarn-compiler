public class Declare extends SequencialAST
{

	public Declare()
	{
		super();
	}

	public Token getToken(int index)
	{
		return ((Identifier) astList.get(index)).getToken();
	}

	@Override
	public String toString(int level)
	{
		Token token = ((Identifier) astList.get(0)).getToken(); // L and T from the first token of Declare
		String treeString = printDot(level);
		treeString += "Dcln @ " + "L" + token.getLine() + "/T"
				+ token.getTokenNum() + "\n";

		treeString += super.toString(level + 1);

		return treeString;
	}
}
