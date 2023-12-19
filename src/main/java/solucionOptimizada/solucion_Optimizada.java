 package solucionOptimizada;

 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.TreeMap;

 public class solucion_Optimizada {

     private final int max, min, fila, columna;
     private HashMap<Integer, Integer> fechasTeam;

     int[][] recorridos;


     public solucion_Optimizada(int fila, int columna, int max, int min, int [][] matrizDistancias) {
         this.fila = fila;
         this.columna = columna;
         this.max = max;
         this.min = min;
         recorridos = matrizDistancias;

         fechasTeam = new HashMap<>(columna*(columna-1));
     }

     public void matrizFechas(int[][] prueba) {
         if (prueba.length <= 1) {

         } else {
         }
     }

    public static void main(String[] args) {
        int prueba = 4;
        int[][] recorrido = {
                {0, 745, 665, 929},
                {745, 0, 80, 337},
                {665, 80, 0, 380},
                {929, 337, 380, 0}
        };
        solucion_Optimizada optima = new solucion_Optimizada(2*(prueba-1),prueba,3,1,recorrido);
        //System.out.println(optima.toString());



        //optima.matrizFechas(equipos, 2*(equipos-1));

    }
}