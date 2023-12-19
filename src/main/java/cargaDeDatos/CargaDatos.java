/**
 * Esta clase permite cargar los datos de un archivo de texto
 * @author Santiago Anibal Carrillo Torres, Edwar Yamir Forero Blanco, Juan Eduardo Calderon Jaramillo
 * @version 1.0
 */

package cargaDeDatos;

import solucionIngenua.solucion_Ingenua;

/**
 * Esta clase permite cargar los datos de un archivo de texto y pasarlos a la clase solucion_Ingenua
 *
 */
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
    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @return numeroEquipos
     * @param numeroEquipos
     */

    public void setNumeroEquipos(int numeroEquipos) {
        this.numeroEquipos = numeroEquipos;
    }
    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return numeroEquipos
     */

    public int getNumeroEquipos() {
        return numeroEquipos;
    }

    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @return tamanoMinimo
     * @param tamanoMinimo
     */
    public void setTamanoMinimo(int tamanoMinimo) {
        this.tamanoMinimo = tamanoMinimo;
    }
    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return tamanoMinimo
     */
    public int getTamanoMinimo() {
        return tamanoMinimo;
    }
    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @return tamanoMaximo
     * @param tamanoMaximo
     */
    public void setTamanoMaximo(int tamanoMaximo) {
        this.tamanoMaximo = tamanoMaximo;
    }
    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return tamanoMaximo
     */
    public int getTamanoMaximo() {
        return tamanoMaximo;
    }
    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @return matrizDistancias
     * @param matrizDistancias
     */
    public void setMatrizDistancias(int[][] matrizDistancias) {
        this.matrizDistancias = matrizDistancias;
    }
    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return matrizDistancias
     */
    public int[][] getMatrizDistancias() {
        return matrizDistancias;
    }
    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @return solucion_Ing
     * @param solucion_Ing
     */
    public void setSolIng(String solucion_Ing) {
        this.solucion_Ing = solucion_Ing;
    }
    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return solucion_Ing
     */
    public String getSolIng() {
        return solucion_Ing;
    }
    /**
     * Método que permite crear una instancia de la clase solucion_Ingenua y pasarle los datos
     */
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
