public class Expression extends AST
{

	private Term lHS = null, rHS = null;
	private Token operator = null;

	public Expression()
	{

	}

	public Expression(Token operator, Term lHS, Term rHS)
	{
		this.operator = operator;
		this.lHS = lHS;
		this.rHS = rHS;
	}

	@Override
	public String toString(int level)
	{
		String treeString = "";
		if (operator != null)
		{
			treeString = printDot(level) + operator.spelling + " @ L"
					+ operator.getLine() + "/T" + operator.getTokenNum() + "\n";

		}

		treeString += lHS.toString(level);
		if (rHS != null)
		{
			treeString += rHS.toString(level);
		}

		return treeString;

	}
}