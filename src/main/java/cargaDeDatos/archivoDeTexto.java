package cargaDeDatos;
import solucionIngenua.solucion_Ingenua;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta clase permite cargar los datos de un archivo de texto
 * @author Santiago Anibal Carrillo Torres, Edwar Yamir Forero Blanco, Juan Eduardo Calderon
 * @version 1.0
 */

public class archivoDeTexto {
    /**
     * Este método permite cargar los datos de un archivo de texto
     * @param args mensaje que se muestra en la ventana de carga de archivos
     *
     */
    public static void main(String[] args) {
        // Crear un objeto JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Configurar el filtro para mostrar solo archivos de texto
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto", "txt", "text", "csv", "dat");
        fileChooser.setFileFilter(filter);

        // Mostrar el diálogo de selección de archivo
        int result = fileChooser.showOpenDialog(null);

        // Procesar la selección del usuario
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Leer el contenido del archivo
                String fileContent = readFile(selectedFile);

                // Parsear el contenido y crear una instancia de solucionIngenua
                solucion_Ingenua solucion = parseFileContent(fileContent);

                // Acceder a las variables de solucionIngenua según sea necesario
                System.out.println("Número de Equipos: " + solucion.getNumeroEquipos());
                System.out.println("Tamaño Mínimo: " + solucion.getTamanoMinimo());
                System.out.println("Tamaño Máximo: " + solucion.getTamanoMaximo());
                System.out.println("Matriz de Distancias:");
                int[][] matrizDistancias = solucion.getMatrizDistancias();
                for (int[] matrizDistancia : matrizDistancias) {
                    for (int i : matrizDistancia) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }

            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada por el usuario");
        }
    }

    private static String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static solucion_Ingenua parseFileContent(String fileContent) {
        String[] lines = fileContent.split("\n");

        // Extraer valores de las primeras tres líneas
        int numeroEquipos = Integer.parseInt(lines[0]);
        int tamanoMinimo = Integer.parseInt(lines[1]);
        int tamanoMaximo = Integer.parseInt(lines[2]);

        // Inicializar la matriz de distancias
        int[][] matrizDistancias = new int[numeroEquipos][numeroEquipos];

        // Llenar la matriz de distancias desde las líneas restantes
        for (int i = 0; i < numeroEquipos; i++) {
            String[] distances = lines[i + 3].split(" ");
            for (int j = 0; j < numeroEquipos; j++) {
                matrizDistancias[i][j] = Integer.parseInt(distances[j]);
            }
        }

        // Crear y devolver una instancia de la clase solucionIngenua
        return new solucion_Ingenua(numeroEquipos, tamanoMinimo, tamanoMaximo, matrizDistancias);
    }
}
