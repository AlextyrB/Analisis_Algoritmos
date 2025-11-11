import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

class MazeSolver {
    private char[][] laberinto;
    private int filas;
    private int columnas;
    private Posicion entrada; // 'E'
    private Posicion salida;  // 'S'
    private final int[] DIR_FILA = {0, 0, 1, -1}; // Direcciones: Derecha, Izquierda, Abajo, Arriba
    private final int[] DIR_COL = {1, -1, 0, 0};

    /**
     * Carga el laberinto desde el archivo, manejando el formato CSV (separado por comas).
     */
    public boolean cargarLaberinto(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // 1. Leer dimensiones
            filas = Integer.parseInt(br.readLine().trim());
            columnas = Integer.parseInt(br.readLine().trim());
            laberinto = new char[filas][columnas];

            // 2. Leer la matriz del laberinto
            for (int i = 0; i < filas; i++) {
                String linea = br.readLine();
                if (linea != null) {
                    // **CORRECCIÓN CLAVE: Dividir la línea usando la coma como separador**
                    String[] elementos = linea.split(",");

                    if (elementos.length < columnas) {
                        System.err.println("Error: La fila " + i + " tiene menos de " + columnas + " elementos. Encontrados: " + elementos.length);
                        return false;
                    }

                    for (int j = 0; j < columnas; j++) {
                        // Limpiar y tomar el primer carácter del elemento
                        String elemento = elementos[j].trim();

                        if (!elemento.isEmpty()) {
                            laberinto[i][j] = elemento.charAt(0);

                            if (laberinto[i][j] == 'E') {
                                entrada = new Posicion(i, j);
                            } else if (laberinto[i][j] == 'S') {
                                salida = new Posicion(i, j);
                            }
                        } else {
                            System.err.println("Error: Elemento vacío en la celda (" + i + ", " + j + ")");
                            return false;
                        }
                    }
                } else {
                    System.err.println("Error: Faltan líneas de datos en el archivo.");
                    return false;
                }
            }

            if (entrada == null || salida == null) {
                System.err.println("Error: 'E' (Entrada) o 'S' (Salida) no encontradas.");
                return false;
            }

            return true;

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo o formato numérico inválido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Imprime la matriz del laberinto en la consola para verificación.
     */
    public void imprimirLaberinto() {
        if (laberinto == null) {
            System.out.println("El laberinto no ha sido cargado.");
            return;
        }
        System.out.println("\n--- Representación del Laberinto Cargado (" + filas + "x" + columnas + ") ---");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(laberinto[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Implementa la estrategia de Backtracking (DFS) usando una Pila.
     */
    public List<Posicion> resolver() {
        if (laberinto == null || entrada == null) {
            return null;
        }

        Stack<Posicion> pilaRuta = new Stack<>();
        boolean[][] visitado = new boolean[filas][columnas];

        // 1. Inicialización
        pilaRuta.push(entrada);
        visitado[entrada.fila][entrada.columna] = true;

        // 2. Búsqueda con Backtracking
        while (!pilaRuta.isEmpty()) {
            Posicion actual = pilaRuta.peek();

            // Objetivo encontrado
            if (actual.fila == salida.fila && actual.columna == salida.columna) {
                return new ArrayList<>(pilaRuta);
            }

            // Intentar moverse a un vecino válido
            boolean seMovio = false;
            for (int i = 0; i < 4; i++) {
                int nFila = actual.fila + DIR_FILA[i];
                int nColumna = actual.columna + DIR_COL[i];

                if (esMovimientoValido(nFila, nColumna, visitado)) {
                    // AVANZAR
                    Posicion siguiente = new Posicion(nFila, nColumna);
                    pilaRuta.push(siguiente);
                    visitado[nFila][nColumna] = true;
                    seMovio = true;
                    break;
                }
            }

            // 3. BACKTRACKING (Si no se pudo mover a ningún lado)
            if (!seMovio) {
                pilaRuta.pop();
            }
        }

        return null;
    }

    private boolean esMovimientoValido(int r, int c, boolean[][] visitado) {
        // 1. Dentro de los límites
        if (r < 0 || r >= filas || c < 0 || c >= columnas) {
            return false;
        }

        // 2. No es una pared ('1')
        if (laberinto[r][c] == '1') {
            return false;
        }

        // 3. No ha sido visitada
        if (visitado[r][c]) {
            return false;
        }

        return true;
    }
}