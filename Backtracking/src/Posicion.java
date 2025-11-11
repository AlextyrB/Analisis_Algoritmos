class Posicion {
    int fila;
    int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }
}