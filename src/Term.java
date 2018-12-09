public class Term extends AST
{
	private Token operator = null;
	private Term lHS = null;
	private AST rHS = null;

	public Term()
	{

	}

	public Term(Token operator, Term lHS, Factor rHS)
	{
		this.operator = operator;
		this.lHS = lHS; // lHS = left hand side
		this.rHS = rHS; // rHS = right hand side
	}

	public Term(Factor factor)
	{
		rHS = factor;
	}

	@Override
	public String toString(int level)
	{

		String treeString = "";
		if (operator != null) // if term != factor
		{
			treeString = printDot(level) + operator.spelling + " @ L"
					+ operator.getLine() + "/T" + operator.getTokenNum() + "\n";
		}
		if (lHS != null) // if term != factor
		{
			treeString += lHS.toString(level + 1);
		}

		treeString += rHS.toString(level + 1);

		return treeString;

	}
}
