public class Assign extends AST
{
	private final Identifier name;

	private Expression rHs = null;

	public Assign(Identifier name, Expression expression)
	{
		this.name = name;
		rHs = expression;
	}

	@Override
	public String toString(int level)
	{
		String treeString = printDot(level) + "assign @ L";
		treeString += name.getToken().getLine() + "/T"
				+ name.getToken().getTokenNum() + "\n";
		treeString += name.toString(level + 1);

		treeString += rHs.toString(level + 1);

		return treeString;
	}
}
