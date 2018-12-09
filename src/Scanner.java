import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanner
{
	private final BufferedReader reader;
	private String currentWord, currentSpelling;
	private Pattern pattern = null;
	private final static String identifierPattern = "[a-zA-Z]([a-zA-Z]|[0-9]|_)*",
			numeralPattern = "[0-9]+",
			let = "let",
			out = "out",
			prg = "prg",
			type = "(int|bool)",
			sub = "sub",
			begin = "begin",
			ifPattern = "if",
			then = "then",
			elsePattern = "else",
			forPattern = "for",
			to = "to",
			doPattern = "do",
			whilePattern = "while",
			expressionOps = "(==|!=|<<|>>|<=|>=|<>)",
			primarySign = "(\\+|-|not)",
			primaryWord = "(read|true|false|eof)",
			comment = "@.*", pow = "\\^\\^";

	private Matcher matcher;
	private StringTokenizer tokenizer;

	private int lineNum = 1;
	private int tokenNum = 1;
	private String line;

	public Scanner(BufferedReader reader) throws IOException
	{
		this.reader = reader;
		if ((line = reader.readLine()) != null)
		{
			tokenizer = new StringTokenizer(line);
			currentWord = tokenizer.nextToken();
		}
		else
		{
			System.out.println("the source file is empty");

		}

	}

	private void takeIt() throws IOException
	{
		currentSpelling = currentWord;
		if (tokenizer.hasMoreTokens())
		{
			currentWord = tokenizer.nextToken();

		}

	}

	public Token scan() throws IOException
	{
		int kind;
		pattern = Pattern.compile(comment);
		matcher = pattern.matcher(currentWord);

		while (matcher.matches() || !tokenizer.hasMoreTokens()) // comment or an empty line
		{
			// String line;

			// scanSeparator()
			if ((line = reader.readLine()) != null) // begin new line
			{
				tokenNum = 1;
				lineNum++;
				tokenizer = new StringTokenizer(line);
				matcher = pattern.matcher(currentWord);
				if (tokenizer.hasMoreTokens() && matcher.matches()) // if new line isn't empty or not comment
				{
					currentWord = tokenizer.nextToken();
				}

			}
			else
			// no more new line
			{
				break;
			}
			matcher = pattern.matcher(currentWord);
		}

		kind = scanToken();

		return new Token(kind, currentSpelling, lineNum, tokenNum++);
	}

	private int scanToken() throws IOException
	{
		// System.out.println("currentWOrd: " + currentWord);

		if (currentWord.equals("end"))
		{
			// System.out.println("take : " + currentWord);
			takeIt();
			return Token.END;
		}

		// if
		else if (currentWord.equals(ifPattern))
		{
			takeIt();
			return Token.IF;
		}

		// out
		else if (currentWord.equals(out))
		{
			takeIt();
			return Token.OUT;
		}

		// then
		else if (currentWord.equals(then))
		{
			takeIt();
			return Token.THEN;
		}

		// else
		else if (currentWord.equals(elsePattern))
		{
			takeIt();
			return Token.ELSE;
		}

		// for
		else if (currentWord.equals(forPattern))
		{
			takeIt();
			return Token.FOR;
		}

		// to
		else if (currentWord.equals(to))
		{
			takeIt();
			return Token.TO;
		}

		// do
		else if (currentWord.equals(doPattern))
		{
			takeIt();
			return Token.DO;
		}

		// while
		else if (currentWord.equals(whilePattern))
		{
			takeIt();
			return Token.WHILE;
		}

		// prg
		else if (currentWord.equals(prg))
		{
			takeIt();
			return Token.PRG;
		}

		// let
		else if (currentWord.equals(let))
		{
			takeIt();
			return Token.LET;
		}

		// begin
		else if (currentWord.equals(begin))
		{
			takeIt();
			return Token.BEGIN;
		}

		// sub
		else if (currentWord.equals(sub))
		{
			takeIt();
			return Token.SUB;
		}

		// ;
		else if (currentWord.equals(";"))
		{

			takeIt();
			return Token.SEMICOLON;
		}
		// :
		else if (currentWord.equals(":"))
		{
			takeIt();
			return Token.COLON;
		}
		// (
		else if (currentWord.equals("("))
		{
			takeIt();
			return Token.LPAREN;
		}
		// )
		else if (currentWord.equals(")"))
		{
			takeIt();
			return Token.RPAREN;
		}
		// ,
		else if (currentWord.equals(","))
		{
			takeIt();
			return Token.COMMA;
		}

		// .
		else if (currentWord.equals("."))
		{

			takeIt();
			return Token.DOT;
		}

		// become :=
		else if (currentWord.equals(":="))
		{

			takeIt();
			return Token.BECOME;
		}

		else if (currentWord.equals("++"))
		{
			takeIt();
			return Token.PLUS;
		}
		else if (currentWord.equals("--"))
		{
			takeIt();
			return Token.MINUS;
		}
		else if (currentWord.equals("**"))
		{
			takeIt();
			return Token.MUL;
		}
		else if (currentWord.equals("%%"))
		{
			takeIt();
			return Token.MODULO;
		}
		else if (currentWord.equals("//"))
		{
			takeIt();
			return Token.DIV;
		}
		else if (currentWord.equals("&&"))
		{
			takeIt();
			return Token.AND;
		}
		else if (currentWord.equals("||"))
		{
			takeIt();
			return Token.OR;
		}
		else
		{

			// intliteral
			pattern = Pattern.compile(numeralPattern);
			matcher = pattern.matcher(currentWord);
			if (matcher.matches())
			{
				takeIt();
				return Token.INTLITERAL;
			}

			// ==, !=, <<, >>
			pattern = Pattern.compile(expressionOps);
			matcher = pattern.matcher(currentWord);
			if (matcher.matches())
			{
				takeIt();
				return Token.EXPRESSIONSIGN;
			}

			// ^^
			pattern = Pattern.compile(pow);
			matcher = pattern.matcher(currentWord);
			if (matcher.matches())
			{
				takeIt();
				return Token.POWER;
			}

			// + -
			pattern = Pattern.compile(primarySign);
			matcher = pattern.matcher(currentWord);
			if (matcher.matches())
			{
				takeIt();
				return Token.PRIMARYSIGN;
			}

			// read true false eof
			pattern = Pattern.compile(primaryWord);
			matcher = pattern.matcher(currentWord);
			if (matcher.matches())
			{
				takeIt();
				return Token.PRIMARYWORD;
			}

			// type int|bool
			pattern = Pattern.compile(type);
			matcher = pattern.matcher(currentWord);
			if (matcher.matches())
			{

				takeIt();
				return Token.TYPE;
			}

			// Identifier
			pattern = Pattern.compile(identifierPattern);
			matcher = pattern.matcher(currentWord);
			if (matcher.matches())
			{
				takeIt();
				return Token.IDENTIFIER;
			}

			System.out.println("Unknown symbol");
			return -1;
		}

	}
}
