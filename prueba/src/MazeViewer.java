import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MazeViewer extends JPanel implements ActionListener {
    private final char[][] laberinto;
    private final List<List<Posicion>> historialRuta;
    private final int TAMAÑO_CELDA = 30;
    private Timer timer;
    private int indicePaso = 0;
    private static final int DELAY_MS = 150;

    private List<Posicion> rutaActual = new ArrayList<>();

    public MazeViewer(char[][] laberinto, List<List<Posicion>> historialRuta) {
        this.laberinto = laberinto;
        this.historialRuta = historialRuta;
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        this.setPreferredSize(new java.awt.Dimension(columnas * TAMAÑO_CELDA, filas * TAMAÑO_CELDA));
        if (historialRuta != null && !historialRuta.isEmpty()) {
            timer = new Timer(DELAY_MS, this);
            timer.start();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (indicePaso < historialRuta.size()) {
            rutaActual = historialRuta.get(indicePaso);
            indicePaso++;

            repaint();
        } else {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (laberinto == null) return;

        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                int x = c * TAMAÑO_CELDA;
                int y = r * TAMAÑO_CELDA;

                switch (laberinto[r][c]) {
                    case '1': g.setColor(Color.BLACK); break;
                    case '0': g.setColor(Color.WHITE); break;
                    case 'E': g.setColor(Color.GREEN); break;
                    case 'S': g.setColor(Color.RED); break;
                    default: g.setColor(Color.LIGHT_GRAY);
                }
                g.fillRect(x, y, TAMAÑO_CELDA, TAMAÑO_CELDA);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, TAMAÑO_CELDA, TAMAÑO_CELDA);
            }
        }

        if (rutaActual != null && rutaActual.size() > 1) {
            g.setColor(Color.ORANGE.darker());
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
            g2.setStroke(new java.awt.BasicStroke(4));
            for (int i = 0; i < rutaActual.size() - 1; i++) {
                Posicion p1 = rutaActual.get(i);
                Posicion p2 = rutaActual.get(i + 1);
                int x1 = p1.columna * TAMAÑO_CELDA + TAMAÑO_CELDA / 2;
                int y1 = p1.fila * TAMAÑO_CELDA + TAMAÑO_CELDA / 2;
                int x2 = p2.columna * TAMAÑO_CELDA + TAMAÑO_CELDA / 2;
                int y2 = p2.fila * TAMAÑO_CELDA + TAMAÑO_CELDA / 2;
                g2.drawLine(x1, y1, x2, y2);
            }
            Posicion ultimo = rutaActual.get(rutaActual.size() - 1);
            g.setColor(Color.BLUE);
            int radio = TAMAÑO_CELDA / 4;
            g.fillOval(ultimo.columna * TAMAÑO_CELDA + TAMAÑO_CELDA / 2 - radio,
                    ultimo.fila * TAMAÑO_CELDA + TAMAÑO_CELDA / 2 - radio,
                    radio * 2, radio * 2);
        }
    }
}