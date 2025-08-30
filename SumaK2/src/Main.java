import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Boolean> diccionario = new HashMap<>();

        System.out.print("Ingresa el tamaño del arreglo: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Ingresa los elementos:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.print("Ingresa el valor de K: ");
        int K = scanner.nextInt();
        for (int elemento : array) {
            int complemento = K - elemento;

            if (diccionario.containsKey(complemento)) {
                System.out.println("Encontrado");
                return;
            }

            diccionario.put(elemento, true);
        }

        System.out.println("no Encontrado");

        scanner.close();
    }
}