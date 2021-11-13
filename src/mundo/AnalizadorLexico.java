/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindï¿½o (Armenia - Colombia)
 * Programa de Ingenierï¿½a de Sistemas y Computaciï¿½n
 *
 * Asignatura: Teorï¿½a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Diseï¿½o original por: Leonardo A. Hernï¿½ndez R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

/**
 * Clase que modela un analizador lï¿½xico
 */

public class AnalizadorLexico {

	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un código fuente dado
	 *
	 * @param cod - código al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList extraerTokens(String cod) {

		Token token;
		ArrayList vectorTokens = new ArrayList();

		// El primer token se extrae a partir de posición cero
		int i = 0;

		// Ciclo para extraer todos los tokens

		while (i < cod.length()) {

			// Extrae el token de la posición i
			token = extraerSiguienteToken(cod, i);
			vectorTokens.add(token);

			i = token.darIndiceSiguiente();
		}
		return vectorTokens;
	}

	/*
	 * Extrae el token de la cadena cod a partir de la posición i, basándose en el
	 * Autómata
	 *
	 * @param cod - código al cual se le va a extraer un token - codigo!=null
	 * @param i   - posición a partir de la cual se va a extraer el token - i>=0
	 * @return token que se extrajo de la cadena
	 */
	public Token extraerSiguienteToken(String cod, int i) {
		Token token;

		//			METODOS PALABRAS RESERVADAS-----------------------------------
		// Intenta extraer la palabra reservada String
		token = extraerTipoCadena(cod, i);
		if (token != null)
			return token;

		// Intenta extraer la palabra reservada char
		token = extraerTipoCaracter(cod, i);
		if (token != null)
			return token;

		// Intenta extraer la palabra reservada char
		token = extraerTipoEntero(cod, i);
		if (token != null)
			return token;

		// Intenta extraer la palabra reservada Double
		token = extraerTipoDecimal(cod, i);
		if (token != null)
			return token;

		// Intenta extraer la palabra reservada Double
		token = extraerTipoBooleano(cod, i);
		if (token != null)
			return token;
		//			METODOS PALABRAS RESERVADAS-----------------------------------

		// Intenta extraer un entero
		token = extraerEnteroReal(cod, i);
		if (token != null)
			return token;


		//			METODOS OPERADORES-----------------------------------
		// Intenta extraer un operador relacional
		token = extraerOperadorRelacional(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un entero o un real
		token = extraerEnteroReal(cod, i);
		if (token != null)
			return token;

		// Intenta extraer una cadena
		token = extraerCadena(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un char
		token = extraerChar(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador logico
		token = extraerLogico(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer un Separador de Sentencia
		token = extraerSeparadorDeSentencia(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer un operador Incial o Terminal
		token = extraerOperadorInicialTerminal(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer un operador aritmetico
		token = extraerOperadorAritmetico(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer un operador Logico
		token = extraerOperadorLogico(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer un operador de Asignacion
		token = extraerOperadorAsignacion(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer un operador de abrir
		token = extraerOperadorDeAbrir(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer un operador de cerrar
		token = extraerOperadorDeCerrar(cod, i);
		if (token != null)
			return token;
		//			METODOS OPERADORES-----------------------------------

		//			METODOS CICLOS---------------------------------------
		// Intenta Extraer la palabra while /FASES
		token = extraePalabraWhile(cod, i);
		if (token != null)
			return token;
		// Intenta Extraer la palabra for -fr
		token = extraePalabraFor(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer la palabra if (Si)
		token = extraePalabraIf(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer la palabra Switch (CASE)
		token = extraePalabraSwitch(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer la palabra Clase (KLSS)
		token = extraePalabraClase(cod, i);
		if (token != null)
			return token;

		// Intenta Extraer la palabra Do While(Du)
		token = extraePalabraDoWhile(cod, i);
		if (token != null)
			return token;
		//			METODOS CICLOS---------------------------------------

		// Extrae un token no reconocido
		token = extraerNoReconocido(cod, i);
		return token;
	}



	/**
	 * Este metodo extraera un flotante con su inicial flotante o si es real lo identificara.
	 * @param codigo
	 * @param indice
	 * @return
	 */

	public Token extraerEnteroReal ( String codigo, int indice)
	{
		int indiceInicial=0;
		String lexema;
		if(indiceInicial<codigo.length() && codigo.charAt(indice)=='#')
		{
			indiceInicial=indice+1;
			if(indiceInicial<codigo.length() && esDigito(codigo.charAt(indiceInicial)))
			{
				do
					indiceInicial++;
				while (indiceInicial<codigo.length( ) && esDigito(codigo.charAt(indiceInicial)) );

				if(indiceInicial<codigo.length() && codigo.charAt(indiceInicial)==',')
				{
					indiceInicial++;
					if(indiceInicial<codigo.length() && esDigito(codigo.charAt(indiceInicial)))
					{
						do
							indiceInicial++;
						while (indiceInicial<codigo.length( ) && esDigito(codigo.charAt(indiceInicial)) );
						lexema =  codigo.substring( indice, indiceInicial);
						Token token = new Token( lexema, Token.REAL, indiceInicial );
						return token;
					}
					else
					{
						lexema =  codigo.substring( indice, indiceInicial-1);
						Token token = new Token( lexema, Token.ENTERO, indiceInicial-1 );
						return token;

					}
				}else
				{
					lexema =  codigo.substring( indice, indiceInicial);
					Token token = new Token( lexema, Token.ENTERO, indiceInicial );
					return token;
				}
			}
		}
		return null;
	}


	//	METODOS PALABRAS RESERVADAS-----------------------------------
	/**
	 *
	 * extrae la palabra reservada SS para el tipo de dato String en nuestro
	 * lenguaje
	 *
	 * @param cadena
	 * @param i
	 * @return
	 */
	public Token extraerTipoCadena(String cadena, int i) {
		if (cadena.charAt(i) == 'S' && esMayuscula(cadena.charAt(i))) {
			int nextChar = i + 1;
			if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'S' && esMayuscula(cadena.charAt(nextChar))) {

				nextChar++;
				String lexema = cadena.substring(i, nextChar);
				Token token = new Token(lexema, Token.STRINGTYPE, nextChar);
				return token;
			}

		}
		return null;
	}

	/**
	 * Este metodo intenta extraer un cadena de texto segun el contrato
	 * @param codigo
	 * @param indice
	 * @return
	 */
	public Token extraerCadena (String codigo, int indice)
	{
		int indiceInicial=0;
		String lexema;
		if(indiceInicial < codigo.length() && codigo.charAt(indice) == '-')
		{
			indiceInicial = indice + 1;
			if (indiceInicial < codigo.length() && codigo.charAt(indiceInicial) == '-')
			{
				indiceInicial++;
				if (indiceInicial < codigo.length() && esLetra(codigo.charAt(indiceInicial)))
				{
					do
						indiceInicial++;
					while(indiceInicial < codigo.length() && esLetra(codigo.charAt(indiceInicial)));
					if(indiceInicial < codigo.length() &&codigo.charAt(indiceInicial) == '.')
					{
						indiceInicial++;
						if(indiceInicial < codigo.length() && codigo.charAt(indiceInicial) == 'L' || indiceInicial < codigo.length() && codigo.charAt(indiceInicial) == 'T' || indiceInicial < codigo.length() && codigo.charAt(indiceInicial) == 'N')
						{
							do
								indiceInicial++;
							while(indiceInicial < codigo.length() && esLetra(codigo.charAt(indiceInicial)));
						}else
						{
							if(indiceInicial < codigo.length() &&codigo.charAt(indiceInicial) == '-')
							{
								indiceInicial++;
								if(indiceInicial < codigo.length() && codigo.charAt(indiceInicial) == '-')
								{
									indiceInicial++;
									lexema = codigo.substring(indice, indiceInicial);
									Token token = new Token (lexema, Token.OPERADORCADENA, indiceInicial);
									return token;
								}
							}
						}
					}else
					{
						if(indiceInicial < codigo.length() &&codigo.charAt(indiceInicial) == '-')
						{
							indiceInicial++;
							if(indiceInicial < codigo.length() && codigo.charAt(indiceInicial) == '-')
							{
								indiceInicial++;
								lexema = codigo.substring(indice, indiceInicial);
								Token token = new Token (lexema, Token.OPERADORCADENA, indiceInicial);
								return token;
							}
						}

					}
					if(indiceInicial < codigo.length() &&codigo.charAt(indiceInicial) == '-')
					{
						indiceInicial++;
						if(indiceInicial < codigo.length() && codigo.charAt(indiceInicial) == '-')
						{
							indiceInicial++;
							lexema = codigo.substring(indice, indiceInicial);
							Token token = new Token (lexema, Token.OPERADORCADENA, indiceInicial);
							return token;
						}
					}
				}
			}
		}
		return null;
	}
	/*
	 * extrae la palabra reservada char' para el tipo de dato char en nuestro
	 * lenguaje
	 * @param cadena
	 * @param i
	 * @return
	 */
	public Token extraerTipoCaracter(String cadena, int i) {
		if (cadena.charAt(i) == '_') {
			int nextChar = i + 1;
			if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'c') {
				nextChar = nextChar + 1;
				if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'H') {
					nextChar = nextChar + 1;
					if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'a') {
						nextChar = nextChar + 1;
						if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'r') {
							nextChar = nextChar + 1;
							if (nextChar < cadena.length() && cadena.charAt(nextChar) == '\'') {
								nextChar++;
								String lexema = cadena.substring(i, nextChar);
								Token token = new Token(lexema, Token.CHARTYPE, nextChar);
								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Metodo para extraer el tipo de dato char
	 * @param codigo
	 * @param indice
	 * @return
	 */
	public Token extraerChar (String codigo, int indice){
		int indiceInicial;
		String lexema;
		if(codigo.charAt(indice) == '-')
		{
			indiceInicial = indice + 1;
			if (indiceInicial < codigo.length() && esLetra(codigo.charAt(indiceInicial)))
			{
				do
					indiceInicial++;
				while(indiceInicial < codigo.length() && esLetra(codigo.charAt(indiceInicial)));
				if(indiceInicial < codigo.length() &&codigo.charAt(indiceInicial) == '-')
				{
					indiceInicial++;
					lexema = codigo.substring(indice, indiceInicial);
					Token token = new Token (lexema, Token.OPERADORCHAR, indiceInicial);
					return token;
				}
			}
		}
		return null;
	}

	/*
	 * extrae la palabra reservada E#nt para el tipo de dato int en nuestro lenguaje
	 *
	 * @param cadena
	 * @param i
	 * @return
	 */
	public Token extraerTipoEntero(String cadena, int i) {
		if (cadena.charAt(i) == 'E') {
			int nextChar = i + 1;
			if (nextChar < cadena.length() && cadena.charAt(nextChar) == '#') {
				nextChar = nextChar + 1;
				if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'n') {
					nextChar = nextChar + 1;
					if (nextChar < cadena.length() && cadena.charAt(nextChar) == 't') {
						nextChar++;
						String lexema = cadena.substring(i, nextChar);
						Token token = new Token(lexema, Token.ENTEROTYPE, nextChar);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**

	 * La X representa falso
	 * La Ó representa verdadero
	 * @param codigo
	 * @param indice
	 * @return
	 */
	public Token extraerLogico(String codigo, int indice){
		int indiceInicial;
		String lexema;
		if(codigo.charAt(indice) == 'X')
		{
			indiceInicial = indice+1;
			lexema = String.valueOf((codigo.charAt(indice)));
			Token token = new Token (lexema, Token.FALSO, indiceInicial);
			return token;
		}else if(codigo.charAt(indice) == 'Ó')
		{
			indiceInicial = indice+1;
			lexema = String.valueOf((codigo.charAt(indice)));
			Token token = new Token (lexema, Token.VERDADERO, indiceInicial);
			return token;
		}
		return null;
	}
	/*
	 * extrae la palabra reservada _Deci.L para el tipo de dato Double en nuestro
	 * lenguaje
	 *
	 * @param cadena
	 * @param i
	 * @return
	 */
	public Token extraerTipoDecimal(String cadena, int i) {
		if (cadena.charAt(i) == '_') {
			int nextChar = i + 1;
			if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'D') {
				nextChar = nextChar + 1;
				if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'e') {
					nextChar = nextChar + 1;
					if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'c') {
						nextChar = nextChar + 1;
						if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'i') {
							nextChar = nextChar + 1;
							if (nextChar < cadena.length() && cadena.charAt(nextChar) == '.') {
								nextChar = nextChar + 1;
								if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'L') {
									nextChar++;
									String lexema = cadena.substring(i, nextChar);
									Token token = new Token(lexema, Token.DOUBLETYPE, nextChar);
									return token;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * extrae la palabra reservada _Deci.L para el tipo de dato Double en nuestro
	 * lenguaje
	 *
	 * @param cadena
	 * @param i
	 * @return
	 */
	public Token extraerTipoBooleano(String cadena, int i) {
		if (cadena.charAt(i) == '?') {
			int nextChar = i + 1;
			if (nextChar < cadena.length() && cadena.charAt(nextChar) == 'T' || cadena.charAt(nextChar) == 'F') {
				nextChar = nextChar + 1;
				if (nextChar < cadena.length() && cadena.charAt(nextChar) == '_') {
					nextChar = nextChar + 1;
					if (nextChar < cadena.length() && cadena.charAt(nextChar) == '?') {
						nextChar++;
						String lexema = cadena.substring(i, nextChar);
						Token token = new Token(lexema, Token.BOOLEANTYPE, nextChar);
						return token;
					}
				}
			}
		}
		return null;
	}
	//	METODOS PALABRAS RESERVADAS-----------------------------------

	/**
	 * Intenta extraer un operador relacional de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 *
	 * @param cod - código al cual se le va a intentar extraer el operador
	 *            relacional - codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer el
	 *            operador relacional - 0<=i<codigo.length()
	 * @return el token operador relacional o NULL, si el token en la posición dada
	 *         no es un operador relacional. El Token se compone de el lexema, el
	 *         tipo y la posición del siguiente lexema.
	 */
	public Token extraerOperadorRelacional(String cod, int i) {
		Token token1 = null;
		int j = 0;
		if (cod.charAt(i) == '»' || cod.charAt(i) == '«') {
			j = i + 1;
			String lex1 = cod.substring(i, j);
			token1 = new Token(lex1, Token.OPERADORRELACIONAL, j);

		} else if (cod.charAt(i) == '$') {
			j = i + 1;

		} else if (cod.charAt(i) == '-') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '>') {
				j++;
			}
		}
		if (j < cod.length() && cod.charAt(j) == '-') {
			j++;
			if (j < cod.length() && cod.charAt(j) == '>') {
				j++;
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);

				return token;
			}
		}
		return token1;
	}

	/**
	 * Intenta extraer un Separador de sentencia de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 *
	 * @param cod - código al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posición dada no es
	 *         un identificador. El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */
	public Token extraerSeparadorDeSentencia(String cod, int i) {
		if (cod.charAt(i) == ';') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SEPARADORSENTENCIA, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un Operador Inicial o Terminal de la cadena cod a partir de
	 * la posición i, basándose en el Autómata
	 *
	 * @param cod - código al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posición dada no es
	 *         un identificador. El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */
	public Token extraerOperadorInicialTerminal(String cod, int i) {
		if (cod.charAt(i) == '~' || cod.charAt(i) == '¬') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORINICIALTERMINAL, j);
			return token;
		}
		return null;
	}

	/**

	 * Intenta extraer un Operador Aritmetico de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 *
	 * @param cod - código al cual se le va a intentar extraer un operador
	 *            Aritmetico - codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer un
	 *            operador Aritmetico - 0<=indice<codigo.length()
	 * @return el token Aritmetico o NULL, si el token en la posición dada no es un
	 *         operador Aritmetico. El Token se compone de el lexema, el tipo y la
	 *         posición del siguiente lexema.
	 */
	public Token extraerOperadorAritmetico(String cod, int i) {
		if (cod.charAt(i) == 's') {
			int j = i + 1;
			if(j < cod.length() &&  cod.charAt(j) == 'u') {
				j++;
				if(j < cod.length() &&  cod.charAt(j) == 'm') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORARITMETICO, j);
					return token;
				}
			}

		}else if(cod.charAt(i) == 'r') {
			int j = i + 1;
			if(j < cod.length() &&  cod.charAt(j) == 'e') {
				j++;
				if(j < cod.length() &&  cod.charAt(j) == 's') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORARITMETICO, j);
					return token;
				}
			}
		}else if(cod.charAt(i) == 'm') {
			int j = i + 1;
			if(j < cod.length() &&  cod.charAt(j) == 'u') {
				j++;
				if(j < cod.length() &&  cod.charAt(j) == 'l') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORARITMETICO, j);
					return token;
				}
			}
		}else if(cod.charAt(i) == 'd') {
			int j = i + 1;
			if(j < cod.length() &&  cod.charAt(j) == 'i') {
				j++;
				if(j < cod.length() &&  cod.charAt(j) == 'v') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORARITMETICO, j);
					return token;
				}
			}
		}else if(cod.charAt(i) == 'p') {
			int j = i + 1;
			if(j < cod.length() &&  cod.charAt(j) == 'o') {
				j++;
				if(j < cod.length() &&  cod.charAt(j) == 'r') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORARITMETICO, j);
					return token;
				}
			}
		}else if(cod.charAt(i) == 'e') {
			int j = i + 1;
			if(j < cod.length() &&  cod.charAt(j) == 'x') {
				j++;
				if(j < cod.length() &&  cod.charAt(j) == 'p') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORARITMETICO, j);
					return token;
				}
			}
		}
		return null;
	}

	/**

	 * Intenta extraer un Operador Logico de la cadena cod a partir de la posición
	 * i, basándose en el Autómata
	 *
	 * @param cod - código al cual se le va a intentar extraer un Logico -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer un Logico
	 *            - 0<=indice<codigo.length()
	 * @return el token Logico o NULL, si el token en la posición dada no es un
	 *         Logico. El Token se compone de el lexema, el tipo y la posición del
	 *         siguiente lexema.
	 */
	public Token extraerOperadorLogico(String cod, int i) {
		Token token1 = null;
		if (cod.charAt(i) == '¥' || cod.charAt(i) == 'Ø') {
			int j = i + 1;
			String lex1 = cod.substring(i, j);
			token1 = new Token(lex1, Token.OPERADORLOGICO, j);
		} else if (cod.charAt(i) == '¿') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '?') {
				j++;
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORLOGICO, j);
				return token;
			}
		}
		return token1;
	}

	/**
	 * Intenta extraer un Operador de asignacion de la cadena cod a partir de la
	 * posición i, basándose en el Autómata
	 *
	 * @param cod - código al cual se le va a intentar extraer un asignacion -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a intentar extraer un
	 *            asignacion - 0<=indice<codigo.length()
	 * @return el token asignacion o NULL, si el token en la posición dada no es un
	 *         asignacion. El Token se compone de el lexema, el tipo y la posición
	 *         del siguiente lexema.
	 */
	public Token extraerOperadorAsignacion(String cod, int i) {
		if (cod.charAt(i) == '-') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '>') {
				j++;
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORASIGNACION, j);
				return token;
			}

		} else if ((esMinuscula(cod.charAt(i)) && cod.charAt(i) == 's')
				|| (esMinuscula(cod.charAt(i)) && cod.charAt(i) == 'r')
				|| (esMinuscula(cod.charAt(i)) && cod.charAt(i) == 'm')
				|| (esMinuscula(cod.charAt(i)) && cod.charAt(i) == 'p')
				|| (esMinuscula(cod.charAt(i)) && cod.charAt(i) == 'd')) {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '-') {
				j++;
				if (j < cod.length() && cod.charAt(j) == '>') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORASIGNACION, j);
					return token;
				}

			}
		}

		return null;
	}

	/**
	 * Intenta extraer un Operador de de abrir de la cadena cod a partir de la
	 * posiciï¿½n i, basï¿½ndose en el Autï¿½mata
	 *
	 * @param cod - cï¿½digo al cual se le va a intentar extraer un abrir -
	 *            codigo!=null
	 * @param i   - posiciï¿½n a partir de la cual se va a intentar extraer un abrir
	 *            - 0<=indice<codigo.length()
	 * @return el token abrir o NULL, si el token en la posiciï¿½n dada no es un
	 *         abrir. El Token se compone de el lexema, el tipo y la posiciï¿½n del
	 *         siguiente lexema.
	 */
	public Token extraerOperadorDeAbrir(String cod, int i) {
		if (cod.charAt(i) == '>') {
			int j = i + 1;
			if ((j < cod.length() && cod.charAt(j) == '[') || (j < cod.length() && cod.charAt(j) == '(')
					|| (j < cod.length() && cod.charAt(j) == '{')) {
				j++;
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORABRIR, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un Operador de de abrir de la cadena cod a partir de la
	 * posiciï¿½n i, basï¿½ndose en el Autï¿½mata
	 *
	 * @param cod - cï¿½digo al cual se le va a intentar extraer un abrir -
	 *            codigo!=null
	 * @param i   - posiciï¿½n a partir de la cual se va a intentar extraer un abrir
	 *            - 0<=indice<codigo.length()
	 * @return el token abrir o NULL, si el token en la posiciï¿½n dada no es un
	 *         abrir. El Token se compone de el lexema, el tipo y la posiciï¿½n del
	 *         siguiente lexema.
	 */
	public Token extraerOperadorDeCerrar(String cod, int i) {
		if (cod.charAt(i) == ']' || cod.charAt(i) == ')' || cod.charAt(i) == '}') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '<') {
				j++;
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORCERRAR, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Este método valida la palabra /FASES de nuestro lenguage
	 *
	 * @param cod
	 * @param i
	 * @return token
	 */
	public Token extraePalabraWhile(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == '/') {
			j++;
			if (j < cod.length() && cod.charAt(j) == 'F') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'A') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'S') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'E') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'S') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.PALABRAWHILE, j);
								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Este método valida la palabra -fr de nuestro lenguage
	 *
	 * @param cod
	 * @param i
	 * @return token
	 */
	public Token extraePalabraFor(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == '-') {
			j++;
			if (j < cod.length() && cod.charAt(j) == 'f') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'r') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.PALABRAFOR, j);
					return token;
				}
			}
		}

		return null;
	}

	/**
	 * Este método valida la palabra Si de nuestro lenguage
	 *
	 * @param cod
	 * @param i
	 * @return token
	 */
	public Token extraePalabraIf(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == 'S') {
			j++;
			if (j < cod.length() && cod.charAt(j) == 'i') {
				j++;

				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.PALABRAIF, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Este método valida la palabra CASE de nuestro lenguage
	 *
	 * @param cod
	 * @param i
	 * @return token
	 */
	private Token extraePalabraSwitch(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == 'C') {
			j++;
			if (j < cod.length() && cod.charAt(j) == 'A') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'S') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'E') {
						j++;

						String lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRASWITCH, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Este método valida la palabra KLSS de nuestro lenguage
	 *
	 * @param cod
	 * @param i
	 * @return token
	 */
	private Token extraePalabraClase(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == 'K') {
			j++;
			if (j < cod.length() && cod.charAt(j) == 'L') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'S') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'S') {
						j++;

						String lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRACLASE, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Este método valida la palabra Du de nuestro lenguage
	 *
	 * @param cod
	 * @param i
	 * @return token
	 */
	public Token extraePalabraDoWhile(String cod, int i) {
		int j = i;
		if (cod.charAt(i) == 'D') {
			j++;
			if (j < cod.length() && cod.charAt(j) == 'u') {
				j++;

				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.PALABRADOWHILE, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posición i.
	 * Antes de utilizar este método, debe haberse intentado todos los otros métodos
	 * para los otros tipos de token
	 *
	 * @param cod - código al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i   - posición a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y la
	 *         posición del siguiente lexema.
	 *
	 */
	public Token extraerNoReconocido(String cod, int i) {
		String lexema = cod.substring(i, i + 1);
		int j = i + 1;
		Token token = new Token(lexema, Token.NORECONOCIDO, j);
		return token;
	}

	/**
	 * Determina si un carácter es un dígito
	 *
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un dígito o no
	 */
	public boolean esDigito(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un carácter es una letra
	 *
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');

	}

	/**
	 * Determina si un carácter es una letra Mayuscula
	 *
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra Mayuscula o no
	 */
	public boolean esMayuscula(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z');
	}

	/**
	 * Determina si un carácter es una letra Minuscula
	 *
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra Minuscula o no
	 */
	public boolean esMinuscula(char caracter) {
		return (caracter >= 'a' && caracter <= 'z');
	}

}
