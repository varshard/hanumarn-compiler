public class Token
{
	public static final int IDENTIFIER = 0, INTLITERAL = 1, OPERATOR = 2,
			BEGIN = 3, CONST = 4, DO = 5, ELSE = 6, END = 7, IF = 8, IN = 9,
			LET = 10, THEN = 11, VAR = 12, WHILE = 13, LPAREN = 18 /* ( */,
			RPAREN = 19 /* ) */, BECOME = 16 /* := */, COLON = 15,
			SEMICOLON = 14, EOT = 20, IS = 17 /* ~ */,
			TYPE = 37 /* bool, int */, EOF = 21,
			PRIMARYWORD = 22 /* read, true, false, eof */, SUB = 23, PRG = 24,
			PROCEDURE = 26, OUT = 25, FOR = 27, TO = 28, PRIMARYSIGN = 30,
			COMMA = 29, QUESTION = 31/* ? */, DOT = 32/* . */, MUL = 35 /* ** */,
			DIV = 36 /* // */, AND = 37 /* && */, OR = 39 /* || */,
			MODULO = 38 /* %% */, PLUS = 40 /* ++ */, MINUS = 41 /* -- */
			, POWER = 33 /* ^^ */, EXPRESSIONSIGN = 34 /* !=,==, <<, >> */;

	/*
	 * sign : value
	 * identifier : 0
	 * intliteral : 1
	 * operator : 2
	 * begin : 3
	 * const : 4
	 * do : 5
	 * else : 6
	 * end : 7
	 * if : 8
	 * in : 9
	 * let : 10
	 * then : 11
	 * var : 12
	 * while : 13
	 * ; : 14
	 * : : 15
	 * := : 16
	 * ~ : 17
	 * ( : 18
	 * ) : 19
	 * EOT : 20
	 * EOF (ascii \000) : 21
	 * read : 22
	 * sub : 23
	 * prg : 24
	 * procedure : 26
	 * out : 25
	 * for : 27
	 * to : 28
	 * , : 29
	 * +,- : 30
	 * * // && %% : 31
	 * ? : 32
	 * . : 33
	 * ++ -- || : 34
	 * != == << >> : 35
	 * ^^ : 36
	 */

	public int kind;
	public String spelling;
	private final static String[] spellings = { "<identifier>",
			"<integer-literal>", "<operator>", "begin", "const", "do", "else",
			"end", "if", "in", "let", "then", "var", "while", ";", ":", ":=",
			"`", "(", ")", "<eot>" };
	private final int line, tokenNum;

	public Token(int kind, String spelling, int line, int tokenNum)
	{
		this.line = line;
		this.tokenNum = tokenNum;

		this.kind = kind;
		this.spelling = spelling.toString();

		if (kind == IDENTIFIER)
		{
			for (int i = BEGIN; i <= WHILE; i++)
			{
				if (this.spelling.equals(spellings[i]))
				{
					kind = i;
					break;
				}

			}
		}
	}

	public int getLine()
	{
		return line;
	}

	public int getTokenNum()
	{
		return tokenNum;
	}

}
