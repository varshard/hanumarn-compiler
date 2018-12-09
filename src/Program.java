public class Program extends AST
{
	private Token prg = null;
	private Identifier ident = null;
	private Declarations dclns = null;
	private SubProgram subProg = null;
	private Body body = null;

	public Program(Token prg, Identifier ident, Declarations dclns,
			SubProgram subProg, Body body)
	{
		this.prg = prg;
		this.ident = ident;
		this.dclns = dclns;
		this.subProg = subProg;
		this.body = body;
	}

	@Override
	public String toString(int level)
	{
		String treeString = "Program @ L" + prg.getLine() + "/T"
				+ prg.getTokenNum() + "\n";

		treeString += ident.toString(level + 1);

		treeString += dclns.toString(level + 1);

		treeString += subProg.toString(level + 1);

		treeString += body.toString(level + 1);

		return treeString;
	}
}
