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
    private Posicion entrada;
    private Posicion salida;
    private final int[] DIR_FILA = {0, 0, 1, -1};
    private final int[] DIR_COL = {1, -1, 0, 0};

    public boolean cargarLaberinto(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            filas = Integer.parseInt(br.readLine().trim());
            columnas = Integer.parseInt(br.readLine().trim());
            laberinto = new char[filas][columnas];

            for (int i = 0; i < filas; i++) {
                String linea = br.readLine();
                if (linea != null) {
                    String[] elementos = linea.split(",");
                    if (elementos.length < columnas) {
                        System.err.println("Error: La fila " + i + " tiene menos de " + columnas + " elementos.");
                        return false;
                    }
                    for (int j = 0; j < columnas; j++) {
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
    public List<List<Posicion>> resolverPasoAPaso() {
        if (laberinto == null || entrada == null) {
            return null;
        }

        Stack<Posicion> pilaRuta = new Stack<>();
        boolean[][] visitado = new boolean[filas][columnas];
        List<List<Posicion>> historialRuta = new ArrayList<>();

        pilaRuta.push(entrada);
        visitado[entrada.fila][entrada.columna] = true;

        historialRuta.add(new ArrayList<>(pilaRuta));

        while (!pilaRuta.isEmpty()) {
            Posicion actual = pilaRuta.peek();

            if (actual.fila == salida.fila && actual.columna == salida.columna) {
                historialRuta.add(new ArrayList<>(pilaRuta));
                return historialRuta;
            }

            boolean seMovio = false;
            for (int i = 0; i < 4; i++) {
                int nFila = actual.fila + DIR_FILA[i];
                int nColumna = actual.columna + DIR_COL[i];

                if (esMovimientoValido(nFila, nColumna, visitado)) {
                    Posicion siguiente = new Posicion(nFila, nColumna);
                    pilaRuta.push(siguiente);
                    visitado[nFila][nColumna] = true;
                    seMovio = true;
                    historialRuta.add(new ArrayList<>(pilaRuta));
                    break;
                }
            }

            if (!seMovio) {
                pilaRuta.pop();
                if (!pilaRuta.isEmpty()) {
                    historialRuta.add(new ArrayList<>(pilaRuta));
                }
            }
        }

        return historialRuta;
    }
    private boolean esMovimientoValido(int r, int c, boolean[][] visitado) {
        if (r < 0 || r >= filas || c < 0 || c >= columnas) return false;
        if (laberinto[r][c] == '1') return false;
        if (visitado[r][c]) return false;
        return true;
    }
    public char[][] getLaberinto() {
        return laberinto;
    }

    public void imprimirLaberinto() {
        if (laberinto == null) return;
        System.out.println("\n--- Representación del Laberinto Cargado (" + filas + "x" + columnas + ") ---");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(laberinto[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------");
    }
}