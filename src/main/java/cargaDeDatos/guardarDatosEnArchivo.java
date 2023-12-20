package cargaDeDatos;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *  * @author Santiago Anibal Carrillo Torres, Edwar Yamir Forero Blanco, Juan Eduardo Calderon Jaramillo
 * Clase que se encarga de guardar los datos de la solucion en un archivo de texto
 */
public class guardarDatosEnArchivo {

    private int numeroEquipos;
    private int tamanoMinimo;
    private int tamanoMaximo;
    private String torneo;
    /**
     * Metodo constructor de la clase, el cual recibe los datos de la clase solucion_ingenua
     * @param numeroEquipos cantidad de equipos
     * @param tamanoMinimo limite minimo de partidos seguidos por equipo
     * @param tamanoMaximo limite maximo de partidos seguidos por equipo
     * @param torneo matriz del torneo establecido
     */
    public guardarDatosEnArchivo(int numeroEquipos, int tamanoMinimo, int tamanoMaximo, String torneo) {
        this.numeroEquipos = numeroEquipos;
        this.tamanoMinimo = tamanoMinimo;
        this.tamanoMaximo = tamanoMaximo;
        this.torneo = torneo;

    }

    /**
     * Metodo que se encarga de crear el archivo de texto
     */
    public void creacionFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("SELECIONE LA RUTA PARA GUARDAR LA SOLUCION");
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            guardarArchivo(selectedFile);
        }
    }

    /**
     * Metodo que se encarga de guardar los datos en el archivo de texto elegido
     * @param file archivo de texto elegido
     */
    private void guardarArchivo(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            // Escribir en el archivo
            bw.write(numeroEquipos + "\n");
            bw.write(tamanoMinimo + "\n");
            bw.write(tamanoMaximo + "\n");
            bw.write(torneo + "\n");

            System.out.println("Datos guardados exitosamente en: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
