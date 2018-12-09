import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Hanumarn1
{
	public static void main(String args[]) throws IOException
	{
		String srcPath = "./hanumarn/" + args[0] + ".han", destPath = "./tree/"
				+ args[0] + ".tree";

		String astString;

		File file = new File(srcPath);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);

		Parser parser = new Parser(reader);

		try
		{
			astString = parser.parseHanumarn().toString(0);
		}
		catch (NullPointerException e)
		{
			astString = "!ERROR: " + parser.currentToken.spelling + " @ L"
					+ parser.currentToken.getLine() + "/T"
					+ parser.currentToken.getTokenNum();
		}

		System.out.println(astString);

		FileWriter writer = new FileWriter(destPath);
		writer.write(astString);
		writer.close();
	}
}
