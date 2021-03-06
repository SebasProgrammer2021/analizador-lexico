/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Dise�o original por: Leonardo A. Hern�ndez R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

/**
 * Clase que modela un token
 */

public class Token {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constantes para modelar los posibles tipos de token que se van a analizar
     */

	//	Palabras reservadas
	final public static String STRINGTYPE = "Palabra reservada String";
    final public static String CHARTYPE = "Palabra reservada char";
    final public static String ENTEROTYPE = "Palabra reservada int";
    final public static String DOUBLETYPE = "Palabra reservada Double";
    final public static String BOOLEANTYPE = "Palabra reservada Boolean";

    final public static String OPERADORADITIVO = "Operador aditivo";
	final public static String IDENTIFICADOR = "Idcador";
    final public static String NORECONOCIDO = "No reconocido";

    //	Operadores
    final public static String SEPARADORSENTENCIA = "Operador Separado de sentencia";
    final public static String OPERADORINICIALTERMINAL = "Operador Inicial o Terminal";
    final public static String OPERADORARITMETICO = "Operador Aritmetico";
    final public static String OPERADORRELACIONAL = "Operador Relacional";
    final public static String OPERADORLOGICO = "Operador Logico";
    final public static String OPERADORASIGNACION = "Operador de asignacion";
    final public static String OPERADORABRIR = "Operador de Abrir";
    final public static String OPERADORCERRAR = "Operador de Cerrar";

    //Operadores de asignacion
    final public static String OPERADORCADENA = "Operador de Cadena";
    final public static String OPERADORCHAR = "Operador de char";
    final public static String ENTERO = "Operador de numero entero";
    final public static String REAL = "Operador de numero real";
    final public static String FALSO = "Operador de valor falso";
    final public static String VERDADERO = "Operador valor verdadero";


    // Ciclos y condicionales . Palabras reservadas
    final public static String PALABRAWHILE = "Palabra de Ciclo While (/FASES)";
    final public static String PALABRAFOR = "Palabra de Ciclo For (-fr)";
    final public static String PALABRAIF = "Palabra de Decision If (Si)";
    final public static String PALABRASWITCH = "Palabra de Decision Switch(CASE)";
    final public static String PALABRACLASE= "Identificador de Clase(KLSS)";
    final public static String PALABRADOWHILE= "Palabra de Ciclo Do While(Du)";
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Lexema
     */
    private String lexema;

    /**
     * tipo
     */
    private String tipo;

    /**
     * posicion del siguiente lexema
     */
    private int indiceSiguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor de un token
     * @param elLexema - cadena - laCadena != null
     * @param elTipo - tipo del token - elTipo != null
     * @param elIndiceSiguiente - posicion del siguiente token - laPosicionSiguiente > 0
     */
    public Token( String elLexema, String elTipo, int elIndiceSiguiente )
    {
        lexema = elLexema;
        tipo = elTipo;
        indiceSiguiente = elIndiceSiguiente;
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Entrega la informacion del token
     * @return Descripcion del token
     */
    public String darDescripcion( )
    {
        return "Token: " + lexema + "     Tipo: " + tipo + "     Indice del siguiente: " + indiceSiguiente;
    }

    /**
     * Metodo que retorna el lexema del token
     * @return el lexema del token
     */
    public String darLexema( )
    {
        return lexema;
    }

    /**
     * Metodo que retorna la posicion del siguiente lexema
     * @return posicion del siguiente token
     */
    public int darIndiceSiguiente( )
    {
        return indiceSiguiente;
    }

    /**
     * Metodo que retorna el tipo del token
     * @return el tipo del token
     */
    public String darTipo( )
    {
        return tipo;
    }
}
