import java.io.*;
import java.nio.file.*;

public class CesarDecoder {
    private static final String ALFABETO = "abcdefghijklmnñopqrstuvwxyz ,.";

    public static void main(String[] args) {
        String nombreArchivo = "cesarr.txt";

        try {
            String mensajeCifrado = leerArchivo(nombreArchivo);
            System.out.println("Mensaje cifrado: " + mensajeCifrado);
            System.out.println("\nDESCIFRANDO POR FUERZA BRUTA\n");

            String mejorResultado = "";
            int mejorDesplazamiento = -1;

            for (int desplazamiento = 0; desplazamiento < ALFABETO.length(); desplazamiento++) {
                String mensajeDescifrado = descifrar(mensajeCifrado, desplazamiento);
                System.out.println("Desplazamiento " + desplazamiento + ": " + mensajeDescifrado);

                if (tieneSentido(mensajeDescifrado)) {
                    mejorResultado = mensajeDescifrado;
                    mejorDesplazamiento = desplazamiento;
                }
            }

            if (mejorDesplazamiento != -1) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("RESULTADO FINAL ENCONTRADO:");
                System.out.println("Desplazamiento: " + mejorDesplazamiento);
                System.out.println("Mensaje descifrado: " + mejorResultado);
                System.out.println("=".repeat(60));
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static String leerArchivo(String nombreArchivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(nombreArchivo)));
    }

    private static String descifrar(String mensaje, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();

        for (char c : mensaje.toCharArray()) {
            int posicion = ALFABETO.indexOf(Character.toLowerCase(c));

            if (posicion != -1) {
                int nuevaPosicion = (posicion - desplazamiento + ALFABETO.length()) % ALFABETO.length();
                resultado.append(ALFABETO.charAt(nuevaPosicion));
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    private static boolean tieneSentido(String mensaje) {
        String mensajeLower = mensaje.toLowerCase();

        String[] palabrasComunes = {
                "el ", "la ", "de ", "que ", "en ", "los ", "un ", "por ",
                "con ", "para ", "una ", "es ", "del ", "las ", " y ",
                "hola", "mundo", "mensaje", "texto", "cesar"
        };

        int coincidencias = 0;
        for (String palabra : palabrasComunes) {
            if (mensajeLower.contains(palabra)) {
                coincidencias++;
                if (coincidencias >= 2) {
                    return true;
                }
            }
        }

        return false;
    }
}