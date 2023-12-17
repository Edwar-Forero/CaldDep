package cargaDeDatos;

public class CargaDatos {
    private final int numeroEquipos;
    private final int tamanoMinimo;
    private final int tamanoMaximo;
    private final int[][] matrizDistancias;

    // Constructor para inicializar la clase
    public CargaDatos(int numeroEquipos, int tamanoMinimo, int tamanoMaximo, int[][] matrizDistancias) {
        this.numeroEquipos = numeroEquipos;
        this.tamanoMinimo = tamanoMinimo;
        this.tamanoMaximo = tamanoMaximo;
        this.matrizDistancias = matrizDistancias;
    }

    // Métodos getter para acceder a las variables desde fuera de la clase
    public int getNumeroEquipos() {
        return numeroEquipos;
    }

    public int getTamanoMinimo() {
        return tamanoMinimo;
    }

    public int getTamanoMaximo() {
        return tamanoMaximo;
    }

    public int[][] getMatrizDistancias() {
        return matrizDistancias;
    }
}
