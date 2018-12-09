import java.util.ArrayList;

public class SequencialAST extends AST
{
	public ArrayList<AST> astList;
	public Token token;

	/*
	 * public SequencialAST(Token token)
	 * {
	 * astList = new ArrayList<AST>();
	 * this.token = token; // use for L and T for declarations
	 * }
	 */

	public SequencialAST()
	{
		astList = new ArrayList<AST>();
	}

	public void add(AST ast)
	{
		astList.add(ast);
	}

	public Token getFirstToken()
	{
		return token;
	}

	@Override
	public String toString(int level)
	{
		String treeString = "";

		for (AST x : astList)
		{
			treeString += x.toString(level);
		}

		return treeString;
	}
}
