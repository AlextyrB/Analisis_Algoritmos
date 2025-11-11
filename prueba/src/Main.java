import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    private static final String NOMBRE_ARCHIVO = "laberinto1.cvs";

    public static void main(String[] args) {

        System.out.println("Intentando cargar y resolver el laberinto desde: " + NOMBRE_ARCHIVO);
        MazeSolver solver = new MazeSolver();
        if (solver.cargarLaberinto(NOMBRE_ARCHIVO)) {
            solver.imprimirLaberinto();
            List<List<Posicion>> historialRuta = solver.resolverPasoAPaso();
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Solucionador de Laberintos - Proceso de Backtracking");
                if (historialRuta != null && !historialRuta.isEmpty()) {
                    System.out.println("\nIniciando visualización del proceso de Backtracking (" + historialRuta.size() + " pasos totales).");
                    MazeViewer viewer = new MazeViewer(solver.getLaberinto(), historialRuta);
                    frame.add(viewer);
                } else {
                    System.out.println("\n No se pudo resolver el laberinto. Mostrando laberinto estático.");
                    MazeViewer viewer = new MazeViewer(solver.getLaberinto(), new ArrayList<>());
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
    }
}