package cargaDeDatos;

import solucionIngenua.solucion_Ingenua;
import solucionOptimizada.solucion_Optimizada;

import javax.swing.*;

/**
 * Esta clase permite cargar los datos de un archivo de texto y pasarlos a la clase solucion_Ingenua
 * @author Santiago Anibal Carrillo Torres, Edwar Yamir Forero Blanco, Juan Eduardo Calderon Jaramillo
 *
 */
public class CargaDatos {
    private int numeroEquipos;
    private int tamanoMinimo;
    private int tamanoMaximo;
    private int[][] matrizDistancias;
    private String solucion_Ing;

    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @param numeroEquipos es el número de equipos que se van a crear
     *
     */
    public void setNumeroEquipos(int numeroEquipos) {
        this.numeroEquipos = numeroEquipos;
    }

    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return retorna el número de equipos
     */
    public int getNumeroEquipos() {
        return numeroEquipos;
    }

    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @param tamanoMinimo es el mínimo de partidos seguidos que puede tener un equipo
     */
    public void setTamanoMinimo(int tamanoMinimo) {
        this.tamanoMinimo = tamanoMinimo;
    }

    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return tamanoMinimo retorna el mínimo de partidos seguidos que puede tener un equipo
     */
    public int getTamanoMinimo() {
        return tamanoMinimo;
    }

    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @param tamanoMaximo retorna el máximo de partidos seguidos que puede tener un equipo
     */
    public void setTamanoMaximo(int tamanoMaximo) {
        this.tamanoMaximo = tamanoMaximo;
    }

    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return retorna tamanoMaximo que es el máximo de partidos seguidos que puede tener un equipo
     */
    public int getTamanoMaximo() {
        return tamanoMaximo;
    }

    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @param matrizDistancias es la matriz de distancias entre los equipos
     */
    public void setMatrizDistancias(int[][] matrizDistancias) {
        this.matrizDistancias = matrizDistancias;
    }

    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return matrizDistancias retorna la matriz de distancias entre los equipos
     */
    public int[][] getMatrizDistancias() {
        return matrizDistancias;
    }

    /**
     * Método setter para modificar las variables desde fuera de la clase
     * @param solucion_Ing es la solución ingenua que se obtiene*
     */
    public void setSolIng(String solucion_Ing) {
        this.solucion_Ing = solucion_Ing;
    }

    /**
     * Métodos getter para acceder a las variables desde fuera de la clase
     * @return solucion_Ing retorna la solución ingenua que se obtiene
     */
    public String getSolIng() {
        return solucion_Ing;
    }

    /**
     * Método que permite crear una instancia de la clase solucion_Ingenua y pasarle los datos
     */
    public void soluciones(){

        // Crear cuadro de diálogo con dos botones
        int opcion = JOptionPane.showOptionDialog(null, "Selecciona una opcion:", "Elegir el modo de solucion",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Solucion Ingenua", "Solucion Optimizada"}, null);
        // Realizar acciones según la opción seleccionada
        if (opcion == JOptionPane.YES_OPTION) {
            // Crear instancia de la clase solucion_Ingenua y pasarle los datos
            solucion_Ingenua sl = new solucion_Ingenua(2 * (numeroEquipos - 1), getNumeroEquipos(), getTamanoMaximo(), getTamanoMinimo(), getMatrizDistancias());
            setSolIng(sl.toString());
            System.out.println(sl.toString());

            // Guardar datos en archivo para solucion_Ingenua
            guardarDatosEnArchivo guardarDatos = new guardarDatosEnArchivo(getNumeroEquipos(), getTamanoMinimo(), getTamanoMaximo(), getSolIng());
            guardarDatos.creacionFile();
        } else if (opcion == JOptionPane.NO_OPTION) {
            // Si se selecciona "Solucion Optimizada"
            int a = (int) Math.pow(4, getMatrizDistancias().length);
            solucion_Optimizada slo = new solucion_Optimizada(2 * (numeroEquipos - 1), getNumeroEquipos(), getTamanoMaximo(), getTamanoMinimo(), getMatrizDistancias(), a);
            System.out.println(slo.toString());
            guardarDatosEnArchivo guardarDatos2 = new guardarDatosEnArchivo(getNumeroEquipos(), getTamanoMinimo(), getTamanoMaximo(), slo.toString());
            guardarDatos2.creacionFile();
        }

    }
}
