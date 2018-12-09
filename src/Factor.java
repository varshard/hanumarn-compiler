public class Factor extends Term
{
	private AST rHS = null;
	private Factor lHS = null;
	private Token operator = null;

	public Factor()
	{

	}

	public Factor(AST primary)
	{
		rHS = primary;
	}

	public Factor(Token operator, Factor lHS, AST rHS)
	{
		this.lHS = lHS;
		this.rHS = rHS;
		this.operator = operator;
	}

	@Override
	public String toString(int level)
	{
		System.out.println("factor level " + level);
		String treeString = "";

		if (rHS != null && lHS == null)
		{
			treeString += rHS.toString(level + 1);
		}
		else
		{
			treeString += printDot(level) + operator.spelling + " @ L"
					+ operator.getLine() + "/T" + operator.getTokenNum() + "\n";
			treeString += lHS.toString(level + 1);
			treeString += rHS.toString(level + 1);

		}

		return treeString;

	}
}
