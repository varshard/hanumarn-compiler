public class Output extends SequencialAST
{
	Terminal out;

	public Output(Terminal outTerminal)
	{
		super();
		out = outTerminal;
	}

	@Override
	public String toString(int level)
	{
		token = out.getToken();
		String treeString = printDot(level) + " output @ L" + token.getLine()
				+ "/T" + token.getTokenNum() + "\n";

		for (AST x : astList)
		{
			treeString += x.toString(level + 1);
		}

		return treeString;
	}
}
