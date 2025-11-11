import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    private static final String NOMBRE_ARCHIVO = "laberinto.cvs";

    public static void main(String[] args) {

        System.out.println("Intentando cargar y resolver el laberinto desde: " + NOMBRE_ARCHIVO);
        SolucionLab solver = new SolucionLab();
        if (solver.cargarLaberinto(NOMBRE_ARCHIVO)) {
            solver.imprimirLaberinto();
            List<List<Posicion>> historialRuta = solver.resolverPasoAPaso();
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Solucionador de Laberintos - Proceso de Backtracking");
                if (historialRuta != null && !historialRuta.isEmpty()) {
                    System.out.println("\nIniciando visualización del proceso de Backtracking (" + historialRuta.size() + " pasos totales).");
                    VistaLab viewer = new VistaLab(solver.getLaberinto(), historialRuta);
                    frame.add(viewer);
                } else {
                    System.out.println("\n No se pudo resolver el laberinto. Mostrando laberinto estático.");
                    VistaLab viewer = new VistaLab(solver.getLaberinto(), new ArrayList<>());
                    frame.add(viewer);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        } else {
            System.err.println("Fallo al cargar el laberinto. Asegúrate de que el archivo exista y esté en formato correcto.");
        }
        solver.imprimirLaberinto();

        List<List<Posicion>> rutaSolucion = solver.resolverPasoAPaso();
        if (rutaSolucion != null) {
            System.out.println("\n¡Ruta de Solución Encontrada! (Backtracking)");
            System.out.println("La ruta se compone de " + rutaSolucion.size() + " pasos:");
            int i = 0;
            for (List<Posicion> p : rutaSolucion) {
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