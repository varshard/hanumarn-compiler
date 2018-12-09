public class AST
{
	private AST leftChild = null, rightChild = null;

	public AST(AST leftChild, AST rightChild, int layer)
	{
		this.leftChild = leftChild;
		this.rightChild = rightChild;

	}

	public AST()
	{

	}

	public String toString(int level)
	{
		String tree = "<hanumarn>\n";
		String dotString = "";// . infront of a line

		tree += dotString + leftChild.toString();
		tree += dotString + rightChild.toString();

		return tree;
	}

	public String printDot(int counter)
	{
		String dotString = "";
		for (int i = 0; i < counter; i++)
		{
			dotString += ". ";
		}

		return dotString;
	}
}
