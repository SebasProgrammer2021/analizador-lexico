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
//	agregar las demas constantes para los otros tipos
    final public static String ENTERO = "Entero";
    final public static String OPERADORADITIVO = "Operador aditivo";
    final public static String IDENTIFICADOR = "Idcador";
    final public static String NORECONOCIDO = "No reconocido";
    
 
    final public static String SEPARADORSENTENCIA = "Operador Separado de sentencia";
    final public static String OPERADORINICIALTERMINAL = "Operador Inicial o Terminal";
    final public static String OPERADORARITMETICO = "Operador Aritmetico";
    final public static String OPERADORRELACIONAL = "Operador Relacional";
    final public static String OPERADORLOGICO = "Operador Logico";
    final public static String OPERADORASIGNACION = "Operador de asignaci�n";
    final public static String OPERADORABRIR = "Operador de Abrir";
    final public static String OPERADORCERRAR = "Operador de Cerrar";
    
    
    // Ciclos y condicionales . Palabras reservadas
    final public static String PALABRAWHILE = "Palabra de Ciclo While (/FASES)";
    final public static String PALABRAFOR = "Palabra de Ciclo For (-fr)";
    final public static String PALABRAIF = "Palabra de Decisión If (Si)";
    final public static String PALABRASWITCH = "Palabra de Decisión Switch(CASE)";
    final public static String PALABRACLASE= "Identificador de Clase(KLSS)";
    
    
    
    
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
     * posici�n del siguiente lexema
     */
    private int indiceSiguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor de un token
     * @param elLexema - cadena - laCadena != null
     * @param elTipo - tipo del token - elTipo != null
     * @param elIndiceSiguiente - posici�n del siguiente token - laPosicionSiguiente > 0
     */
    public Token( String elLexema, String elTipo, int elIndiceSiguiente )
    {
        lexema = elLexema;
        tipo = elTipo;
        indiceSiguiente = elIndiceSiguiente;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Entrega la informaci�n del token
     * @return Descripci�n del token
     */
    public String darDescripcion( )
    {
        return "Token: " + lexema + "     Tipo: " + tipo + "     �ndice del siguiente: " + indiceSiguiente;
    }

    /**
     * M�todo que retorna el lexema del token
     * @return el lexema del token
     */
    public String darLexema( )
    {
        return lexema;
    }

    /**
     * M�todo que retorna la posici�n del siguiente lexema
     * @return posici�n del siguiente token
     */
    public int darIndiceSiguiente( )
    {
        return indiceSiguiente;
    }

    /**
     * M�todo que retorna el tipo del token
     * @return el tipo del token
     */
    public String darTipo( )
    {
        return tipo;
    }




}
