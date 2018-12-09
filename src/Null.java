public class Null extends AST
{
	public Null()
	{
		super();
	}

	@Override
	public String toString(int level)
	{
		String treeString = printDot(level) + "<null> @\n";
		return treeString;
	}
}
