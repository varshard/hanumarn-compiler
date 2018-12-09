public class Procedure extends AST
{
	private final Token subToken;
	private final Identifier ident;
	private final Parameters param;
	private final Declarations dclns;
	private final Body body;

	public Procedure(Token subToken, Identifier ident, Parameters param,
			Declarations dclns, Body body)
	{
		this.subToken = subToken;
		this.ident = ident;
		this.param = param;
		this.dclns = dclns;
		this.body = body;
	}

	@Override
	public String toString(int level)
	{
		String treeString = printDot(level) + "procedure @ L"
				+ subToken.getLine() + "/T" + subToken.getTokenNum() + "\n";
		treeString += ident.toString(level + 1);
		treeString += param.toString(level + 1);
		treeString += dclns.toString(level + 1);
		treeString += body.toString(level + 1);

		return treeString;
	}
}
