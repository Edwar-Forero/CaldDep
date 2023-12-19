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
        Fechas fechas = new Fechas(fechaTotal,getNumeroEquipos(), getTamanoMaximo(), getTamanoMinimo());
    }


    //Solo usar para prueba,nada más
    public static int[][] Recorr(){
        int[][] recorrido = {
                {0, 184, 222, 177, 216, 231, 120,  60},
                {184, 0,  45, 123, 128, 200,  52, 100},
                {222, 45,   0, 129, 121, 203,  15, 300},
                {177, 123, 129,   0,  46,  83, 250,  15},
                {216, 128, 121,  46,   0,  83, 100,   7},
                {231, 200, 203,  83,  83,   0,  20,  10},
                {120, 52,  15, 250, 100,  20,  0,  441},
                {60, 100, 300,  15,   7,  10, 441,   0},

        };
        return recorrido;
    }
}
