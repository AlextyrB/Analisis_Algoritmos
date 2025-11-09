import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProblemaMochila {

    public static int[][] construirTablaDP(int[] pesos, int[] valores, int capacidad) {
        int n = pesos.length;
        int[][] dp = new int[n + 1][capacidad + 1];

        for (int i = 1; i <= n; i++) {
            int pesoActual = pesos[i - 1];
            int valorActual = valores[i - 1];

            for (int w = 1; w <= capacidad; w++) {
                if (pesoActual <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], valorActual + dp[i - 1][w - pesoActual]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp;
    }
    public static List<String> rastrearSolucion(int[] pesos, int[] valores, int capacidad, int[][] dp) {
        List<String> solucion = new ArrayList<>();
        int i = pesos.length;
        int w = capacidad;
        while (i > 0 && w > 0) {
            if (dp[i][w] != dp[i - 1][w]) {
                solucion.add("Objeto " + i + " (Peso=" + pesos[i - 1] + ", Valor=" + valores[i - 1] + ")");
                w -= pesos[i - 1];
            }
            i--;
        }


        Collections.reverse(solucion);
        return solucion;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("PROBLEMA DE LA MOCHILA");

        System.out.print("Ingrese la capacidad máxima de la mochila: ");
        int capacidad = scanner.nextInt();

        System.out.print("Ingrese el número total de objetos: ");
        int n = scanner.nextInt();

        int[] pesos = new int[n];
        int[] valores = new int[n];

        System.out.println("\n******** Ingreso de Datos de Objetos ************");
        for (int i = 0; i < n; i++) {
            System.out.println("Objeto " + (i + 1) + ":");
            System.out.print("  Peso: ");
            pesos[i] = scanner.nextInt();
            System.out.print("  Valor: ");
            valores[i] = scanner.nextInt();
        }

        scanner.close();
        System.out.println("\n*************RESULTADOS ***************");

        try {
            int[][] dp = construirTablaDP(pesos, valores, capacidad);
            int valorMaximo = dp[n][capacidad];
            List<String> objetosIncluidos = rastrearSolucion(pesos, valores, capacidad, dp);

            System.out.println("Valor Máximo Obtenido: " + valorMaximo);
            System.out.println("Objetos seleccionados:");

            if (objetosIncluidos.isEmpty()) {
                System.out.println("  - La mochila está vacía (capacidad muy pequeña o no hay objetos).");
            } else {
                for (String objeto : objetosIncluidos) {
                    System.out.println("  - " + objeto);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al procesar los datos. Asegúrese de ingresar números enteros válidos.");
        }

        System.out.println("*****************************************");
    }
}