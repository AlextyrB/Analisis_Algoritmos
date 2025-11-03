public class ProblemaMochila {

    /**
     * Resuelve el problema de la mochila usando Divide y Vencerás (enfoque recursivo)
     * @param pesos Arreglo con los pesos de los objetos
     * @param valores Arreglo con los valores de los objetos
     * @param capacidad Capacidad máxima de la mochila
     * @param n Índice del objeto actual (comenzando desde el final)
     * @return Valor máximo que se puede obtener
     */
    public static int mochila(int[] pesos, int[] valores, int capacidad, int n) {
        // CASO BASE: No hay más objetos o la capacidad es 0
        if (n == 0 || capacidad == 0) {
            return 0;
        }

        // Si el peso del objeto actual es mayor que la capacidad restante,
        // no se puede incluir, así que se ignora
        if (pesos[n - 1] > capacidad) {
            return mochila(pesos, valores, capacidad, n - 1);
        }

        // DIVIDIR: Tenemos dos opciones
        // Opción 1: NO incluir el objeto actual
        int sinIncluir = mochila(pesos, valores, capacidad, n - 1);

        // Opción 2: INCLUIR el objeto actual
        int incluyendo = valores[n - 1] + mochila(pesos, valores, capacidad - pesos[n - 1], n - 1);

        // COMBINAR: Retornar el máximo entre ambas opciones
        return Math.max(sinIncluir, incluyendo);
    }

    /**
     * Versión con impresión de pasos para visualizar el proceso
     */
    public static int mochilaConPasos(int[] pesos, int[] valores, int capacidad, int n, String indent) {
        System.out.println(indent + "Llamada: n=" + n + ", capacidad=" + capacidad);

        // CASO BASE
        if (n == 0 || capacidad == 0) {
            System.out.println(indent + "→ Caso base: retorna 0");
            return 0;
        }

        // Si el objeto no cabe
        if (pesos[n - 1] > capacidad) {
            System.out.println(indent + "→ Objeto " + n + " (peso=" + pesos[n-1] + ") NO cabe. Ignorar.");
            return mochilaConPasos(pesos, valores, capacidad, n - 1, indent + "  ");
        }

        // Explorar ambas opciones
        System.out.println(indent + "→ Objeto " + n + " (peso=" + pesos[n-1] + ", valor=" + valores[n-1] + ")");

        System.out.println(indent + "  Opción 1: NO incluir objeto " + n);
        int sinIncluir = mochilaConPasos(pesos, valores, capacidad, n - 1, indent + "    ");

        System.out.println(indent + "  Opción 2: INCLUIR objeto " + n);
        int incluyendo = valores[n - 1] + mochilaConPasos(pesos, valores, capacidad - pesos[n - 1], n - 1, indent + "    ");

        int resultado = Math.max(sinIncluir, incluyendo);
        System.out.println(indent + "→ Mejor opción: " + resultado);

        return resultado;
    }

    /**
     * Encuentra qué objetos se deben incluir en la mochila
     */
    public static void imprimirSolucion(int[] pesos, int[] valores, int capacidad) {
        int n = pesos.length;
        int[][] dp = new int[n + 1][capacidad + 1];

        // Llenar tabla DP
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacidad; w++) {
                if (pesos[i - 1] <= w) {
                    dp[i][w] = Math.max(valores[i - 1] + dp[i - 1][w - pesos[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Rastrear objetos incluidos
        int w = capacidad;
        System.out.println("\nObjetos incluidos en la mochila:");
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("  - Objeto " + i + ": peso=" + pesos[i - 1] + ", valor=" + valores[i - 1]);
                w -= pesos[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        // Ejemplo 1: Caso básico
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  PROBLEMA DE LA MOCHILA - DIVIDE Y VENCERÁS");
        System.out.println("═══════════════════════════════════════════════\n");

        int[] pesos = {2, 3, 4, 5};
        int[] valores = {3, 4, 5, 6};
        int capacidad = 8;
        int n = pesos.length;

        System.out.println("Objetos disponibles:");
        for (int i = 0; i < n; i++) {
            System.out.println("  Objeto " + (i + 1) + ": peso=" + pesos[i] + ", valor=" + valores[i]);
        }
        System.out.println("\nCapacidad de la mochila: " + capacidad);
        System.out.println("\n───────────────────────────────────────────────");

        int valorMaximo = mochila(pesos, valores, capacidad, n);

        System.out.println("\n✓ Valor máximo que se puede obtener: " + valorMaximo);

        imprimirSolucion(pesos, valores, capacidad);

        // Ejemplo 2: Visualización del proceso
        System.out.println("\n\n═══════════════════════════════════════════════");
        System.out.println("  EJEMPLO 2 - CON VISUALIZACIÓN DE PASOS");
        System.out.println("═══════════════════════════════════════════════\n");

        int[] pesos2 = {1, 3, 4};
        int[] valores2 = {15, 20, 25};
        int capacidad2 = 5;
        int n2 = pesos2.length;

        System.out.println("Objetos disponibles:");
        for (int i = 0; i < n2; i++) {
            System.out.println("  Objeto " + (i + 1) + ": peso=" + pesos2[i] + ", valor=" + valores2[i]);
        }
        System.out.println("\nCapacidad de la mochila: " + capacidad2);
        System.out.println("\n───────────────────────────────────────────────");
        System.out.println("Árbol de recursión:\n");

        int valorMaximo2 = mochilaConPasos(pesos2, valores2, capacidad2, n2, "");

        System.out.println("\n✓ Valor máximo que se puede obtener: " + valorMaximo2);

        imprimirSolucion(pesos2, valores2, capacidad2);

        // Información adicional
        System.out.println("\n\n═══════════════════════════════════════════════");
        System.out.println("  ANÁLISIS DEL ALGORITMO");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("\nESTRATEGIA DIVIDE Y VENCERÁS:");
        System.out.println("1. DIVIDIR: Para cada objeto, se tienen 2 opciones:");
        System.out.println("   - Incluir el objeto en la mochila");
        System.out.println("   - No incluir el objeto en la mochila");
        System.out.println("\n2. CONQUISTAR: Se resuelve recursivamente para n-1 objetos");
        System.out.println("\n3. COMBINAR: Se toma el máximo entre ambas opciones");
        System.out.println("\nCOMPLEJIDAD:");
        System.out.println("- Temporal (versión recursiva): O(2^n) - exponencial");
        System.out.println("- Espacial: O(n) - profundidad de la recursión");
        System.out.println("\nNOTA: Para mejorar la eficiencia, se puede usar");
        System.out.println("programación dinámica (memoización) → O(n*W)");
        System.out.println("═══════════════════════════════════════════════");
    }
}

/*
 * EXPLICACIÓN DEL ALGORITMO
 * =========================
 *
 * El problema de la mochila (Knapsack Problem) consiste en:
 * - Dada una mochila con capacidad máxima W
 * - Dado un conjunto de n objetos, cada uno con peso y valor
 * - Maximizar el valor total sin exceder la capacidad
 *
 * ESTRATEGIA DIVIDE Y VENCERÁS:
 *
 * Para cada objeto, tenemos DOS decisiones:
 *
 * 1. NO incluir el objeto:
 *    mochila(pesos, valores, capacidad, n-1)
 *
 * 2. INCLUIR el objeto (si cabe):
 *    valores[n-1] + mochila(pesos, valores, capacidad - pesos[n-1], n-1)
 *
 * Luego tomamos el MÁXIMO entre ambas opciones.
 *
 * CASOS BASE:
 * - Si n = 0 (no hay objetos): retorna 0
 * - Si capacidad = 0: retorna 0
 * - Si peso del objeto > capacidad: no se puede incluir
 *
 * EJEMPLO:
 * Objetos: {peso=2,valor=3}, {peso=3,valor=4}, {peso=4,valor=5}
 * Capacidad: 5
 *
 * Árbol de decisiones:
 *                    mochila(5, 3)
 *                    /           \
 *              NO incluir      INCLUIR
 *              objeto 3        objeto 3
 *                /                 \
 *         mochila(5,2)         mochila(1,2)
 *           /      \              /      \
 *          ...    ...           ...    ...
 *
 * El algoritmo explora todas las combinaciones y retorna el valor máximo.
 */