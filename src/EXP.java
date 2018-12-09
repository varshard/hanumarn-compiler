public class EXP extends Factor
{
	private AST rHS = null;
	private EXP lHS = null;
	private Token operator = null;

	public EXP()
	{

	}

	public EXP(AST primary)
	{
		rHS = primary;
	}

	public EXP(Token operator, EXP lHS, AST rHS)
	{
		this.lHS = lHS;
		this.rHS = rHS;
		this.operator = operator;
	}

	@Override
	public String toString(int level)
	{

		String treeString = "";

		if (rHS != null && lHS == null) // if just a single Primary
		{
			treeString += rHS.toString(level);
		}
		else
		{
			treeString += printDot(level) + "^^ @ L" + operator.getLine()
					+ "/T" + operator.getTokenNum() + "\n";
			treeString += lHS.toString(level + 1);
			treeString += rHS.toString(level + 1);

		}

		return treeString;

	}
}
