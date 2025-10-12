public class FuerzaBruta {
        public static void main(String[] args) {
            int[] A = {-9, 3, 5, -2, 9, -7, 4, 8, 6};

            int maxProducto = Integer.MIN_VALUE;
            int num1 = 0, num2 = 0;

            for (int i = 0; i < A.length; i++) {
                for (int j = i + 1; j < A.length; j++) {
                    int producto = A[i] * A[j];
                    if (producto > maxProducto) {
                        maxProducto = producto;
                        num1 = A[i];
                        num2 = A[j];
                    }
                }
            }

            System.out.println("Arreglo: " + java.util.Arrays.toString(A));
            System.out.println("Números con producto máximo: " + num1 + " y " + num2);
            System.out.println("Producto máximo: " + maxProducto);
        }
}

