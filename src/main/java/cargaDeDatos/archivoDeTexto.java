package cargaDeDatos;
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;


/**
 * Esta clase permite cargar los datos de un archivo de texto
 *  * @author Santiago Anibal Carrillo Torres, Edwar Yamir Forero Blanco, Juan Eduardo Calderon
 */

public class archivoDeTexto{
    /**
     * Este método permite cargar los datos de un archivo de texto
     * @param args argumentos de la línea de comandos
     *
     */
    public static void main(String[] args){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("ELIJA EL ARCHIVO A SOLUCIONAR");

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            leerArchivo(selectedFile);
        }
    }

    /**
     * Este método permite leer los datos de un archivo de texto
     * @param file archivo de texto que se va a leer
     *
     */
    private static void leerArchivo(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int numEquipos = Integer.parseInt(br.readLine());

            // Leer la segunda línea (min)
            int min = Integer.parseInt(br.readLine());

            // Leer la tercera línea (max)
            int max = Integer.parseInt(br.readLine());

            // Leer la matriz de distancias
            int[][] matrizDistancias = new int[numEquipos][numEquipos];
            for (int i = 0; i < numEquipos; i++) {
                String[] distancias = br.readLine().split(" ");
                for (int j = 0; j < numEquipos; j++) {
                    matrizDistancias[i][j] = Integer.parseInt(distancias[j]);
                }
            }
            // Crear una instancia y pasar la información
            CargaDatos cargaDatos = new CargaDatos();
            cargaDatos.setNumeroEquipos(numEquipos);
            cargaDatos.setTamanoMinimo(min);
            cargaDatos.setTamanoMaximo(max);
            cargaDatos.setMatrizDistancias(matrizDistancias);

            cargaDatos.soluciones();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}