import java.util.List;
public class Main {

    // Confirma que este sea el nombre de tu archivo existente
    private static final String NOMBRE_ARCHIVO = "laberinto1.cvs";

    public static void main(String[] args) {

        // La llamada a crearArchivoDeLaberinto() ha sido eliminada para solo leer el archivo.

        System.out.println("Intentando cargar y resolver el laberinto desde: " + NOMBRE_ARCHIVO);

        // --- EJECUCIÓN DEL SOLUCIONADOR ---
        MazeSolver solver = new MazeSolver();

        if (solver.cargarLaberinto(NOMBRE_ARCHIVO)) {

            // Imprime el laberinto cargado para confirmar la lectura correcta.
            solver.imprimirLaberinto();

            List<Posicion> rutaSolucion = solver.resolver();

            // --- IMPRESIÓN DE LA RUTA ---
            if (rutaSolucion != null) {
                System.out.println("\n¡Ruta de Solución Encontrada! (Backtracking)");
                System.out.println("La ruta se compone de " + rutaSolucion.size() + " pasos:");
                int i = 0;
                for (Posicion p : rutaSolucion) {
                    if (i == 0) System.out.println("  ENTRADA (E): " + p.toString());
                    else if (i == rutaSolucion.size() - 1) System.out.println("  SALIDA (S): " + p.toString());
                    else System.out.println("  Paso " + i + ": " + p.toString());
                    i++;
                }
            } else {
                System.out.println("\n❌ No existe una ruta de solución entre 'E' y 'S'.");
            }
        }
    }
}