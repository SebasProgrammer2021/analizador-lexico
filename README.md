# PROYECTO ANALIZADOR LÉXICO

## Universidad del Quindío · Ingeniería de Sistemas y Computación

### Equipo 2021 · Séptimo semestre
- Juan Sebastian Tobon  
- Rodrico Acosta  
- Sandra Milena Quintero  
- Sebastián Londoño Valencia  

---

## Descripción general
Aplicación de escritorio escrita en Java SE que permite tokenizar código en un lenguaje académico simplificado. Los estudiantes ingresan una cadena y reciben una lista clasificada de tokens (tipos de datos, operadores, estructuras de control, literales, etc.). La interfaz está construida con Swing y delega la lógica al paquete `mundo`, donde se implementa un autómata determinista que recorre carácter por carácter el código fuente.

## Componentes principales
- `mundo.AnalizadorLexico`: expone `extraerTokens` y una colección de métodos especializados para reconocer lexemas (tipos de dato `SS`, `_cHar'`, `E#nt`, operadores `sum/res/mul/div`, palabras clave `/FASES`, `-fr`, `Si`, etc.).
- `mundo.Token`: representa cada lexema, su tipo semántico y la posición desde la que continúa el análisis.
- `interfaz.InterfazAnalizadorLexico`: ventana principal, orquesta la captura del código y muestra los resultados.
- `interfaz.PanelEntradaCodigo`: panel con campo de texto y el botón “Ver tokens”.
- `interfaz.DialogoTokens`: diálogo modal con una `JList` que despliega las descripciones devueltas por el analizador.
- `src/resources/tokensLenguaje.txt`: cadena de ejemplo que cubre la mayoría de los símbolos del lenguaje diseñado para la asignatura.

## Lenguaje soportado y tipos de token
- **Tipos primitivos**: `SS` (String), `_cHar'` (char), `E#nt` (entero), `_Deci.L` (double) y `?T_?` / `?F_?` para booleanos.
- **Literales**: números con prefijo `#`, reales con coma, operadores especiales para cadenas y caracteres (`--id--`), booleanos `Ó`/`X`.
- **Operadores**: relacionales `»`, `«`, `$- ->`, aritméticos (`sum`, `res`, `mul`, `div`, `por`, `exp`), lógicos (`¥`, `Ø`, `¿?`), de asignación (`->`, `sum->`, `res->`, etc.), agrupadores `>[`, `{`, `(` y sus cierres.
- **Estructuras de control**: `/FASES` (while), `-fr` (for), `Si` (if), `CASE` (switch), `KLSS` (clase) y `Du` (do-while).
- **Otros**: separador de sentencia `;`, operadores inicial/terminal `~`/`¬` y marcador de lexemas no reconocidos.

## Flujo de uso
1. Ejecutar `InterfazAnalizadorLexico`.
2. Escribir o pegar el código en el campo “Código fuente que se va a analizar”.
3. Presionar **Ver tokens**.
4. El diálogo listado mostrará cada token con su tipo y el índice desde el que continúa el análisis (`Token: ... Tipo: ... Indice del siguiente: ...`).

## Requisitos y ejecución
- **Dependencias**: JDK 8 o superior, sin librerías externas.
- **Compilación**:  
  `javac -d bin $(find src -name "*.java")`
- **Ejecución**:  
  `java -cp bin interfaz.InterfazAnalizadorLexico`
- Desde un entorno como Eclipse basta con importar el proyecto existente (`.project`, `.classpath` incluidos).

## Recursos adicionales
- Carpeta `docs/`: contiene diagramas (interfaz, modelo conceptual, pruebas) y el documento de requisitos funcionales en formato Word.
- Archivo de prueba `src/resources/tokensLenguaje.txt`: útil para validar rápidamente todos los caminos del autómata (copiar su contenido en la interfaz y analizar).

## Próximos pasos sugeridos
- Añadir pruebas automatizadas para los métodos de extracción de tokens.
- Internacionalizar la interfaz (actualmente solo en español).
- Mostrar columnas separadas (lexema, tipo, posición) en lugar de una cadena formateada.
- Exportar los resultados a archivo `.csv` o `.json` para facilitar su revisión.