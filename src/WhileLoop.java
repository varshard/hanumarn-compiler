public class WhileLoop extends AST
{
	private Token whileToken = null;
	private Expression expression = null;
	private AST statement = null;

	public WhileLoop(Token whileToken, Expression expression, AST statement)
	{
		this.whileToken = whileToken;
		this.expression = expression;
		this.statement = statement;
	}

	@Override
	public String toString(int level)
	{

		String treeString = printDot(level) + "whileloop @ L"
				+ whileToken.getLine() + "/T" + whileToken.getTokenNum() + "\n";

		treeString += expression.toString(level + 1);
		treeString += statement.toString(level + 1);

		return treeString;
	}
}
