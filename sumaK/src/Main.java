import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean encontrado = false;

        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = scanner.nextInt();
        int[] arreglo = new int[n];

        System.out.println("Ingrese los elementos:");
        for (int i = 0; i < n; i++) {
            arreglo[i] = scanner.nextInt();
        }

        System.out.print("Ingrese el valor de k: ");
        int k = scanner.nextInt();

        for (int i = 0; i < n - 1 && !encontrado; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arreglo[i] + arreglo[j] == k) {
                    encontrado = true;
                    break;
                }
            }
        }

        if (encontrado == true) {
            System.out.println("Encontrado");
        } else {
            System.out.println("No encontrado");
        }

        scanner.close();
    }
}