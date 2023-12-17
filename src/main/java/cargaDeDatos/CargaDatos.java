package cargaDeDatos;

import solucionIngenua.solucion_Ingenua;

public class CargaDatos {
    private int numeroEquipos;
    private int tamanoMinimo;
    private int tamanoMaximo;
    private int[][] matrizDistancias;
    private String solucion_Ing;

    // Constructor para inicializar la clase
    /*public CargaDatos(int numeroEquipos, int tamanoMinimo, int tamanoMaximo, int[][] matrizDistancias) {
        this.numeroEquipos = numeroEquipos;
        this.tamanoMinimo = tamanoMinimo;
        this.tamanoMaximo = tamanoMaximo;
        this.matrizDistancias = matrizDistancias;
    }*/

    // Métodos getter para acceder a las variables desde fuera de la clase

    public void setNumeroEquipos(int numeroEquipos) {
        this.numeroEquipos = numeroEquipos;
    }
    public int getNumeroEquipos() {
        return numeroEquipos;
    }

    public void setTamanoMinimo(int tamanoMinimo) {
        this.tamanoMinimo = tamanoMinimo;
    }
    public int getTamanoMinimo() {
        return tamanoMinimo;
    }

    public void setTamanoMaximo(int tamanoMaximo) {
        this.tamanoMaximo = tamanoMaximo;
    }
    public int getTamanoMaximo() {
        return tamanoMaximo;
    }

    public void setMatrizDistancias(int[][] matrizDistancias) {
        this.matrizDistancias = matrizDistancias;
    }
    public int[][] getMatrizDistancias() {
        return matrizDistancias;
    }

    public void setSolIng(String solucion_Ing) {
        this.solucion_Ing = solucion_Ing;
    }
    public String getSolIng() {
        return solucion_Ing;
    }

    public void soluciones(){

        solucion_Ingenua sl = new solucion_Ingenua(2*(numeroEquipos-1),getNumeroEquipos(), getTamanoMaximo(), getTamanoMinimo(), getMatrizDistancias());
        setSolIng(sl.toString());

        /*System.out.println("Solución Ingenua");
        System.out.println(solucion_Ing);*/

        guardarDatosEnArchivo guardarDatos = new guardarDatosEnArchivo(getNumeroEquipos(), getTamanoMinimo(), getTamanoMaximo(), getSolIng());
        guardarDatos.creacionFile();

        /*new solucion_Optimizada(numeroEquipos, tamanoMinimo, tamanoMaximo, matrizDistancias);
        setSolIng(sl.toString());

        *//*System.out.println("Solución Ingenua");
        System.out.println(solucion_Ing);*//*

        guardarDatosEnArchivo guardarDatos = new guardarDatosEnArchivo(getNumeroEquipos(), getTamanoMinimo(), getTamanoMaximo(), getSolIng());
        guardarDatos.creacionFile();*/
    }
}
