import java.io.BufferedReader;
import java.io.IOException;

public class Parser
{
	public Token currentToken;
	private final Scanner scanner;

	public Parser(BufferedReader reader) throws IOException
	{
		scanner = new Scanner(reader);
		currentToken = scanner.scan();
	}

	public void acceptIt() throws IOException
	{
		currentToken = scanner.scan();
	}

	public void accept(int expectedKind) throws IOException
	{
		if (currentToken.kind == expectedKind)
		{
			currentToken = scanner.scan();
		}
		else
		{
			System.out.println("not accept " + currentToken.spelling);

		}

	}

	public void accept(int expectedKind, String error) throws IOException
	{
		if (currentToken.kind == expectedKind)
		{
			currentToken = scanner.scan();
		}
		else
		{
			System.out.println("incorrect " + currentToken.spelling
					+ " error: " + error);

		}

	}

	public Program parseHanumarn() throws IOException
	{
		Token prgToken = currentToken;
		Identifier ident;
		Declarations dclns = null;
		SubProgram subProg = null;
		Body body;

		accept(Token.PRG);
		ident = parseName();
		accept(Token.COLON);
		dclns = parseDeclarations();
		subProg = parseSubProgram();
		body = parseBody();

		accept(Token.DOT, "dot " + currentToken.kind);

		return new Program(prgToken, ident, dclns, subProg, body);
	}

	public Identifier parseName() throws IOException
	{
		if (currentToken.kind == Token.IDENTIFIER)
		{
			Identifier ident = new Identifier(currentToken);
			acceptIt();
			return ident;
		}
		else
		{
			reportError("identifier error");
			return null;
		}
	}

	public Declare parseDeclare() throws IOException // name (, name)* : type
	{
		Declare dcln = new Declare();
		dcln.add(parseName()); // name
		while (currentToken.kind == Token.COMMA) // (, name)*
		{
			acceptIt(); // ,

			dcln.add(parseName()); // name
		}
		accept(Token.COLON); // :
		dcln.add(parseType()); // type

		return dcln;
	}

	public Body parseBody() throws IOException // begin statement (; statement) end
	{
		Body bodyBlock = null;
		if (currentToken.kind == Token.BEGIN)
		{
			bodyBlock = new Body(currentToken);
			acceptIt();

			bodyBlock.add(parseStatement());
			while (currentToken.kind == Token.SEMICOLON)
			{
				acceptIt();
				bodyBlock.add(parseStatement());
			}
			accept(Token.END);
		}

		return bodyBlock;
	}

	public Procedure parseProcedure() throws IOException // sub name parameters ; dclns body ;
	{
		Token subToken = currentToken;
		Identifier ident;
		Parameters param;
		Declarations dclns;
		Body body;

		accept(Token.SUB);
		ident = parseName();
		param = parseParameters();
		accept(Token.SEMICOLON);
		dclns = parseDeclarations();
		body = parseBody();
		accept(Token.SEMICOLON);

		return new Procedure(subToken, ident, param, dclns, body);
	}

	public AST parseStatement() throws IOException
	{
		switch (currentToken.kind)
		{

			case Token.IDENTIFIER: // name ; assigncommand
			{

				Identifier name;
				name = parseName();
				if (currentToken.kind == Token.BECOME) // name := expression
				{

					accept(Token.BECOME, "become " + currentToken.spelling);

					Assign assign;

					assign = new Assign(name, parseExpression()); // expression

					return assign;
				}

				else if (currentToken.kind == Token.LPAREN) // callCommand
				{
					acceptIt();
					Call call = new Call();
					if (currentToken.kind == Token.PRIMARYSIGN
							|| currentToken.kind == Token.PRIMARYWORD
							|| currentToken.kind == Token.INTLITERAL
							|| currentToken.kind == Token.IDENTIFIER
							|| currentToken.kind == Token.LPAREN) // for (expression)
					// first symbol of expression is primary : for ? symbol
					{
						call.add(name);

						call.add(parseExpression()); // expression

						while (currentToken.kind == Token.COMMA) // ,
						{
							acceptIt();
							call.add(parseExpression()); // expression
						}
					}

					accept(Token.RPAREN); // )

					return call;
				}

				else
				{
					reportError("statement error :" + currentToken.spelling);
				}

				break;
			}

			case Token.OUT: // output
			{
				Output output = new Output(new Terminal(currentToken));
				acceptIt(); // out
				accept(Token.LPAREN); // (
				output.add(parseExpression()); // expression
				while (currentToken.kind == Token.COMMA) // *
				{
					acceptIt(); // ,
					output.add(parseExpression()); // expression
				}
				accept(Token.RPAREN); // )

				return output;

			}
				// wait for Condition
			case Token.IF: // ifcommand
			{
				Condition cond = new Condition(currentToken);
				acceptIt(); // if
				cond.add(parseExpression()); // expression

				accept(Token.THEN); // then
				cond.add(parseStatement()); // statement
				if (currentToken.kind == Token.ELSE) // ?
				{
					acceptIt(); // else
					cond.add(parseStatement()); // statement
				}
				return cond;

			}

			case Token.FOR: // forloop
			{
				ForLoop forLoop;
				Identifier ident;
				Token forToken = currentToken;
				Expression expression1, expression2;
				AST statement;

				acceptIt(); // for
				ident = parseName(); // name
				accept(Token.BECOME); // :=
				expression1 = parseExpression(); // expresion
				accept(Token.TO); // to
				expression2 = parseExpression(); // expression
				accept(Token.DO); // do
				statement = parseStatement(); // statement

				forLoop = new ForLoop(forToken, ident, expression1,
						expression2, statement);

				return forLoop;
			}

			case Token.WHILE: // while command
			{
				Token whileToken = currentToken;
				acceptIt(); // while

				Expression expression = parseExpression(); // expression
				accept(Token.DO); // do

				AST/* Statement */statement = parseStatement(); // statement
				return new WhileLoop(whileToken, expression, statement);
			}

			case Token.BEGIN: // bodyblock: first symbol of body is begin
			{
				Body bodyBlock = parseBody();
				return bodyBlock;
			}

				// default:
				// { // empty set
				// return new Null();
				// }
		}

		return new Null();
	}

	public Expression parseExpression() throws IOException
	{
		Expression expression;
		Term lHS = parseTerm(); // term
		Term rHS = null;
		Token operator = null;
		if (currentToken.kind == Token.EXPRESSIONSIGN) // ==, !=, <<, >>
		{
			operator = currentToken;
			acceptIt();
			rHS = parseTerm(); // term
		}
		expression = new Expression(operator, lHS, rHS);
		return expression;
	}

	public Declarations parseDeclarations() throws IOException
	{
		Declarations dclns = new Declarations();
		if (currentToken.kind == Token.LET)
		{
			dclns = new Declarations(currentToken);
			acceptIt(); // let

			dclns.add(parseDeclare()); // declare
			accept(Token.SEMICOLON); // ;
			while (currentToken.kind == Token.IDENTIFIER) // * declare begin with name -> identifier (
			{
				dclns.add(parseDeclare()); // declare
				accept(Token.SEMICOLON); // ;)*
			}

		}
		return dclns;

	}

	public Terminal parseType() throws IOException
	{
		Terminal type = new Terminal(currentToken);
		accept(Token.TYPE); // bool | int
		return type;
	}

	public AST parsePrimary() throws IOException
	{
		Primary primary = null;
		switch (currentToken.kind)
		{
			case Token.PRIMARYSIGN: // +,-, not primary
			{
				Terminal terminal = new Terminal(currentToken);
				acceptIt();
				primary = new Primary(terminal, parsePrimary());
				// parsePrimary();
				break;
			}

			case Token.PRIMARYWORD: // read, true, false, eof

			{
				Terminal terminal = new Terminal(currentToken);
				acceptIt();
				primary = new Primary(terminal);
				break;
			}
			case Token.IDENTIFIER: // name
			{
				Identifier ident = new Identifier(currentToken);
				primary = new Primary(ident);
				acceptIt();
				break;
			}
			case Token.LPAREN: // (expression)
			{
				Expression expression;

				acceptIt();
				expression = parseExpression(); // incomplete
				accept(Token.RPAREN);

				return expression;
			}

			case Token.INTLITERAL: // int
			{
				IntLiteral integer = new IntLiteral(currentToken);
				acceptIt();
				return integer;
			}

			default:
				reportError("error at primary");
		}
		return primary;
	}

	public Term parseTerm() throws IOException // Factor(term's sign Factor)*
	{
		// Factor
		Term term = parseFactor();

		while (currentToken.kind == Token.PLUS
				|| currentToken.kind == Token.MINUS
				|| currentToken.kind == Token.OR) // *
		{

			Token operatorToken = currentToken; // token for operator
			acceptIt();
			term = new Term(operatorToken, term, parseFactor());

		}
		return term;

	}

	public Factor parseFactor() throws IOException // exp(%%exp)*
	{
		Factor factor;
		factor = parseExp(); // exp

		while (currentToken.kind == Token.MUL || currentToken.kind == Token.DIV
				|| currentToken.kind == Token.AND
				|| currentToken.kind == Token.MODULO) // ** // && %% ; *
		{

			Token operator = currentToken;
			acceptIt();
			factor = new Factor(operator, factor, parseExp()); // exp
		}
		return factor;
	}

	public EXP parseExp() throws IOException // primary(^^primary)*
	{
		EXP exp;
		exp = new EXP(parsePrimary()); // primary

		while (currentToken.kind == Token.POWER) // ^^ ; *
		{
			// System.out.println("exp " + currentToken.spelling);
			Token operator = currentToken;
			acceptIt();
			exp = new EXP(operator, exp, parsePrimary()); // primary
		}
		return exp;
	}

	public SubProgram parseSubProgram() throws IOException
	{
		SubProgram subProg = new SubProgram();
		while (currentToken.kind == Token.SUB) // sub, beginning of a procedure
		{
			subProg.add(parseProcedure());
		}
		return subProg;
	}

	public Parameters parseParameters() throws IOException
	{
		Parameters params = new Parameters(currentToken);
		accept(Token.LPAREN); // (

		if (currentToken.kind == Token.IDENTIFIER) // first symbol of declare is Identifier : if for 0 or 1
		{
			params.add(parseDeclare()); // declare

			while (currentToken.kind == Token.SEMICOLON) // *
			{
				// ;
				acceptIt();
				// declare
				params.add(parseDeclare());
			}
		}

		accept(Token.RPAREN);
		return params;
	}

	private void reportError(String error)
	{
		System.out.println(error + " " + currentToken.spelling);

	}
}
