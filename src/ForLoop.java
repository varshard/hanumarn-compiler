public class ForLoop extends AST
{
	private Token forToken = null;
	private Expression expression1 = null, expression2 = null;
	private AST statement = null;
	private Identifier ident = null;

	public ForLoop(Token forToken, Identifier ident, Expression expression1,
			Expression expression2, AST statement)
	{
		this.forToken = forToken;
		this.ident = ident;
		this.expression1 = expression1;
		this.expression2 = expression2;
		this.statement = statement;
	}

	@Override
	public String toString(int level)
	{

		String treeString = printDot(level) + "forloop @ L"
				+ forToken.getLine() + "/T" + forToken.getTokenNum() + "\n";
		treeString += ident.toString(level + 1);
		treeString += expression1.toString(level + 1);
		treeString += expression2.toString(level + 1);
		treeString += statement.toString(level + 1);

		return treeString;
	}
}
