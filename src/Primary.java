public class Primary extends AST
{
	private Terminal terminal = null;
	private AST rHS = null;
	private Identifier ident = null;

	public Primary(Terminal terminal)
	{
		this.terminal = terminal;
	}

	public Primary(Identifier ident)
	{
		this.ident = ident;
	}

	public Primary(Terminal lHS, AST rHS)
	{
		this.terminal = lHS;
		this.rHS = rHS;
	}

	public Token getToken()
	{
		if (terminal != null)
		{
			return terminal.getToken();
		}
		else
		// primary = identifier
		{
			return ident.getToken();
		}

	}

	@Override
	public String toString(int level)
	{
		String treeString = "";

		if (terminal != null) // primary = +,- read, true, false, eof
		{
			treeString = terminal.toString(level);
			if (rHS != null) // +,- primary
			{
				treeString += rHS.toString(level + 1);
			}
		}

		else
		{
			treeString += ident.toString(level); // identifier
		}

		return treeString;
	}
}
