package cargaDeDatos;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class guardarDatosEnArchivo {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            guardarArchivo(selectedFile);
        }
    }

    private static void guardarArchivo(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Solicitar información al usuario (tamaño, min, max, matriz)
            int numEquipos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño del equipo:"));
            int min = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor mínimo:"));
            int max = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor máximo:"));

            // Escribir en el archivo
            bw.write(numEquipos + "\n");
            bw.write(min + "\n");
            bw.write(max + "\n");

            // Generar y escribir la matriz de distancias
            for (int i = 0; i < numEquipos; i++) {
                for (int j = 0; j < numEquipos; j++) {
                    int distancia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la distancia entre el equipo " + (i + 1) + " y el equipo " + (j + 1) + ":"));
                    bw.write(distancia + " ");
                }
                bw.write("\n");
            }

            System.out.println("Datos guardados exitosamente en: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
