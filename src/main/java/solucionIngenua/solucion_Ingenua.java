package solucionIngenua;

import java.util.Arrays;

public class solucion_Ingenua {
    private int numeroEquipos;
    private int tamanoMinimo;
    private int tamanoMaximo;
    private int[][] matrizDistancias;

    // Constructor para inicializar la clase
    public solucion_Ingenua(int numeroEquipos, int tamanoMinimo, int tamanoMaximo, int[][] matrizDistancias) {
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
    public void prueba (){
        int fechaTotal = getNumeroEquipos() * (getNumeroEquipos() - 1) / 2;
        Fechas fechas = new Fechas(fechaTotal,getNumeroEquipos());
        System.out.println(Arrays.deepToString(fechas.Vuelta()));
    }


    //Solo usar para prueba,nada más
    public static int[][] Recorr(){
        int[][] recorrido = new int[4][4];
        recorrido[0][0] = 0;
        recorrido[0][1] = 745;
        recorrido[0][2] = 665;
        recorrido[0][3] = 929;
        recorrido[1][0] = 745;
        recorrido[1][1] = 0;
        recorrido[1][2] = 80;
        recorrido[1][3] = 337;
        recorrido[2][0] = 665;
        recorrido[2][1] = 80;
        recorrido[2][2] = 0;
        recorrido[2][3] = 380;
        recorrido[3][0] = 929;
        recorrido[3][1] = 337;
        recorrido[3][2] = 380;
        recorrido[3][3] = 0;
        return recorrido;
    }
}
