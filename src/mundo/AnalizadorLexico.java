/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Diseño original por: Leonardo A. Hernández R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.util.ArrayList;


/**
 * Clase que modela un analizador léxico
 */

public class AnalizadorLexico {
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Extrae los tokens de un código fuente dado
     * @param cod - código al cual se le van a extraer los tokens - !=null
     * @return vector con los tokens
     */
    public ArrayList extraerTokens( String cod )
    {
    	Token token;
    	ArrayList vectorTokens = new ArrayList();

	    // El primer token se extrae a partir de posición cero
    	int i = 0;

    	// Ciclo para extraer todos los tokens
    	while( i < cod.length() )
		{
	        // Extrae el token de la posición i
			token = extraerSiguienteToken( cod, i);
	        vectorTokens.add( token );
	        i = token.darIndiceSiguiente();
    	}
		return vectorTokens;
    }

    /**
     * Extrae el token de la cadena cod a partir de la posición i, basándose en el Autómata
     * @param cod - código al cual se le va a extraer un token - codigo!=null
     * @param i - posición a partir de la cual se va a extraer el token  - i>=0
     * @return token que se extrajo de la cadena
     */
    public Token extraerSiguienteToken( String cod, int i )
    {
    	Token token;

    	// Intenta extraer un entero
    			token = extraerEntero( cod, i);
    			if ( token != null )
    				return token;
    	    	
    	    	// Intenta extraer un operador aditivo
    	/*		token = extraerOperadorAditivo( cod, i);
    			if ( token != null )
    				return token;
    	*/
    			// Intenta extraer un operador relacional
    			token = extraerOperadorRelacional( cod, i);
    			if ( token != null )
    				return token;

    			// Intenta extraer un identificador
    			token = extraerIdentificador( cod, i);
    			if ( token != null )
    				return token;
    			
    			// Intenta Extraer un Separador de Sentencia
    			token = extraerSeparadorDeSentencia( cod, i);
    			if ( token != null )
    				return token;
    			
    			// Intenta Extraer un operador Incial o Terminal
    			token = extraerOperadorInicialTerminal( cod, i);
    				if ( token != null )
    				return token;
    				
    			// Intenta Extraer un operador aritmetico
    			token = extraerOperadorAritmetico( cod, i);
    				if ( token != null )
    				return token;
    				
    			// Intenta Extraer un operador Logico
    			token = extraerOperadorLogico( cod, i);
    				if ( token != null )
    				return token;
    				
    			// Intenta Extraer un operador de Asignacion
    			token = extraerOperadorAsignacion( cod, i);
    				if ( token != null )
    				return token;
    				
    			// Intenta Extraer un operador de abrir
    			token = extraerOperadorDeAbrir( cod, i);
    				if ( token != null )
    				return token;
    				
    			// Intenta Extraer un operador de cerrar
    			token = extraerOperadorDeCerrar( cod, i);
    				if ( token != null )
    				return token;	
    				
    			// Extrae un token no reconocido
    			token = extraerNoReconocido( cod, i);
    			return token;
		
		
    }

    /**
     * Intenta extraer un entero de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un entero - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un entero  - 0<=indice<codigo.length()
     * @return el token entero o NULL, si el token en la posición dada no es un entero. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	
    // Este método usa el método substring(), que se explica a continuación:
    // x.substring( i, j ) retorna una nueva cadena que es una subcadena de la cadena x.
    // La subcadena comienza en la posición i y
    // se extiende hasta el carácter en la posición j-1.
    // Ejemplo: "universidad".substring(3,6) retorna "ver",
	
	
	public Token extraerEntero ( String cod, int i)
	{
		
		int j;
		String lex;
		if( cod.charAt(i)=='#' ){
			j=i+1;
			if( j<cod.length() && esDigito(cod.charAt(j)) ){		
			    do
			    	j++;
			    while (  j<cod.length( ) && esDigito(cod.charAt(j)) );
		        lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.ENTERO, j );
				return token;			
			}
		}
		
		return null;
	}

    /**
     * Intenta extraer un operador aditivo de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador aditivo  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador aditivo  - 0<=i<codigo.length()
     * @return el token operador aditivo o NULL, si el token en la posición dada no es un operador aditivo.El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	/*public Token extraerOperadorAditivo ( String cod, int i )
	{
		if( cod.charAt(i) =='+' || cod.charAt(i) =='-'){
			int j=i+1;
	        String lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.OPERADORADITIVO, j );
			return token;
		}
		return null;
	}*/

    /**
     * Intenta extraer un operador relacional de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador relacional  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador relacional  - 0<=i<codigo.length()
     * @return el token operador relacional o NULL, si el token en la posición dada no es un operador relacional. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorRelacional ( String cod, int i )
	{
		Token token1 = null;
		int j = 0;
		if( cod.charAt(i) =='»' || cod.charAt(i) =='«'){
			j=i+1;
			String lex1 =  cod.substring( i, j);			    
			token1 = new Token( lex1, Token.OPERADORRELACIONAL, j );
			
		}else if (cod.charAt(i) =='$') {
			j=i+1;
			
		}else if (cod.charAt(i) =='-') {
			j=i+1;
			if( j<cod.length() && cod.charAt(j) =='>' ) {		
				j++;
			}
		}
		if( j<cod.length() && cod.charAt(j) =='-' ) {		
			j++;
			if( j<cod.length() && cod.charAt(j) =='>' ){		
				j++;
		        String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
				return token;
			}
		}
		return token1;
	}
	
    /**
     * Intenta extraer un identificador de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posición dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerIdentificador ( String cod, int i)
	{
		if( cod.charAt(i)=='_' ){
			int j=i+1;
			while( j<cod.length() && esLetra(cod.charAt(j)) )		
			    	j++;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.IDENTIFICADOR, j );
			return token;			
		}	
		return null;
	}
	
	/**
     * Intenta extraer un Separador de sentencia de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posición dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerSeparadorDeSentencia ( String cod, int i)
	{
		if( cod.charAt(i)==';' ){
			int j=i+1;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.SEPARADORSENTENCIA, j );
			return token;			
		}	
		return null;
		
		
	}
	
	/**
     * Intenta extraer un Operador Inicial o Terminal de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posición dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorInicialTerminal ( String cod, int i)
	{
		if( cod.charAt(i)=='~' || cod.charAt(i)=='¬' ){
			int j=i+1;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.OPERADORINICIALTERMINAL, j );		
		    return token;
		}	
		return null;
	}
	
	/**
     * Intenta extraer un Operador Aritmetico de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un operador Aritmetico - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un operador Aritmetico  - 0<=indice<codigo.length()
     * @return el token Aritmetico o NULL, si el token en la posición dada no es un operador Aritmetico. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorAritmetico ( String cod, int i)
	{
		if( cod.charAt(i)=='s' || cod.charAt(i)=='r' || cod.charAt(i)=='m' || cod.charAt(i)=='d' || cod.charAt(i)=='p' || cod.charAt(i)=='c'){
			int j=i+1;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.OPERADORARITMETICO, j );
			return token;			
		}	
		return null;
		/* Propuesta Profe - interviene con los operadores de asignacion
		 * if( (esMayuscula(cod.charAt(i)) && cod.charAt(i)=='S') || (esMayuscula(cod.charAt(i)) && cod.charAt(i)=='R') || (esMayuscula(cod.charAt(i)) && cod.charAt(i)=='M') 
		 *  || (esMayuscula(cod.charAt(i)) && cod.charAt(i)=='D') || (esMayuscula(cod.charAt(i)) && cod.charAt(i)=='P') || (esMayuscula(cod.charAt(i)) && cod.charAt(i)=='C')){
			int j=i+1;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.OPERADORARITMETICO, j );
			return token;			
		}	
		return null;
		 */
	}
	
	/**
     * Intenta extraer un Operador Logico de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un Logico - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un Logico  - 0<=indice<codigo.length()
     * @return el token Logico o NULL, si el token en la posición dada no es un Logico. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */	
	public Token extraerOperadorLogico ( String cod, int i)
	{
		Token token1 = null;
		if( cod.charAt(i)=='¥' || cod.charAt(i)=='Ø'){
			int j=i+1;
		    String lex1 =  cod.substring( i, j);			    
		    token1 = new Token( lex1, Token.OPERADORLOGICO, j );			
		}else if (cod.charAt(i)=='¿') {
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='?' ){		
				j++;
		        String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.OPERADORLOGICO, j );
				return token;
			}
		}
		return token1;
	}

	/**
     * Intenta extraer un Operador de asignacion de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un asignacion - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un asignacion  - 0<=indice<codigo.length()
     * @return el token asignacion o NULL, si el token en la posición dada no es un asignacion. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */	
	public Token extraerOperadorAsignacion ( String cod, int i)
	{			
		if (cod.charAt(i)=='-') {
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='>' ){		
				j++;
		        String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.OPERADORASIGNACION, j );
				return token;
			}
			
		}else if ((esMinuscula(cod.charAt(i)) && cod.charAt(i)=='s') || (esMinuscula(cod.charAt(i)) && cod.charAt(i)=='r') || (esMinuscula(cod.charAt(i)) && cod.charAt(i)=='m') 
				|| (esMinuscula(cod.charAt(i)) && cod.charAt(i)=='p') || (esMinuscula(cod.charAt(i)) && cod.charAt(i)=='d') ) {
			if (cod.charAt(i)=='-') {
				int j=i+1;
				if( j<cod.length() && cod.charAt(j) =='>' ){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORASIGNACION, j );
					return token;
				}
				
			}
		}
		
		return null;
	}
	
	/**
     * Intenta extraer un Operador de de abrir de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un abrir - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un abrir  - 0<=indice<codigo.length()
     * @return el token abrir o NULL, si el token en la posición dada no es un abrir. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */	
	public Token extraerOperadorDeAbrir ( String cod, int i)
	{
		if( cod.charAt(i)=='>' ){
			int j=i+1;
			if( (j<cod.length() && cod.charAt(j) =='[') || (j<cod.length() && cod.charAt(j) =='(') 
					|| (j<cod.length() && cod.charAt(j) =='{')){		
				j++;
		        String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.OPERADORABRIR, j );
				return token;
			}			
		}	
		return null;
	}
	
	/**
     * Intenta extraer un Operador de de abrir de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un abrir - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un abrir  - 0<=indice<codigo.length()
     * @return el token abrir o NULL, si el token en la posición dada no es un abrir. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */	
	public Token extraerOperadorDeCerrar ( String cod, int i)
	{
		if( cod.charAt(i) ==']' ||cod.charAt(i) ==')' || cod.charAt(i) =='}' ){
			int j=i+1;
			if( j<cod.length() &&  cod.charAt(j) =='<'){		
				j++;
		        String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.OPERADORCERRAR, j );
				return token;
			}			
		}	
		return null;
	}
	
    /**
     * Extraer un lexema no reconocido de la cadena cod a partir de la posición i.
     * Antes de utilizar este método, debe haberse intentado todos los otros métodos para los otros tipos de token
     * @param cod - código al cual se le va a extraer el token no reconocido - codigo!=null
     * @param i - posición a partir de la cual se va a extraer el token no reconocido  - 0<=indice<codigo.length()
     * @return el token no reconocido. El Token se compone de lexema, el tipo y la posición del siguiente lexema.

     */
	public Token extraerNoReconocido ( String cod, int i)
	{
		String lexema =  cod.substring( i, i + 1);
		int j=i+1;
		Token token = new Token( lexema, Token.NORECONOCIDO, j );
		return token;
	}
	
	/**
     * Determina si un carácter es un dígito
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea un dígito o no
     */
	public boolean esDigito (char caracter )
	{
		return  caracter >= '0' && caracter <= '9';
	}

	/**
     * Determina si un carácter es una letra
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea una letra o no
     */
	public boolean esLetra (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}
	
	/**
     * Determina si un carácter es una letra Mayuscula
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea una letra Mayuscula o no
     */
	public boolean esMayuscula (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z');
	}
	
	/**
     * Determina si un carácter es una letra Minuscula
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea una letra Minuscula o no
     */
	public boolean esMinuscula (char caracter )
	{
		return (caracter >= 'a' && caracter <= 'z');
	}

}
