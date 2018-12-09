public class SubProgram extends SequencialAST
{
	public SubProgram()
	{
		super();
	}

	@Override
	public String toString(int level)
	{
		String treeString = "";
		treeString = printDot(level);
		treeString += "subprogs @\n";

		treeString += super.toString(level + 1);
		// System.out.println("subprog " + treeString);
		return treeString;
	}
}
