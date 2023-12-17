 package solucionOptimizada;
import solucionIngenua.solucion_Ingenua;

 import java.util.Arrays;
 import java.util.Random;

 public class solucion_Optimizada {
     private int max, min;

     int[][] prueba = new int[4][4];


     public solucion_Optimizada(int max, int min, int equipos) {
         this.max = max;
         this.min = min;
         matrizFechas(prueba);
         //matrizFechas(equipos, 2*(equipos-1));
         System.out.println(Arrays.deepToString(prueba));
     }


     public void matrizFechas(int[][] prueba) {
         if (prueba.length <= 1) {
             //System.out.println(Arrays.deepToString(prueba));
         } else {

             int mitad = prueba.length / 2;


             int[][] prueba1 = Arrays.copyOfRange(prueba, 0, mitad);
             int[][] prueba2 = Arrays.copyOfRange(prueba, mitad, prueba.length);

             for (int i = 0; i < prueba1.length; i++) {
                 for (int j = 0; j < prueba1[i].length; j++) {
                     if (prueba1[i][j] == 0) {
                         //prueba1[i][j] = Random.ne;
                     } else
                         prueba1[i][j] = prueba1[i][j] + 1;
                 }
             }
             for (int i = 0; i < prueba2.length; i++) {
                 for (int j = 0; j < prueba1[i].length; j++) {
                     prueba1[i][j] = prueba1[i][j] + 1;
                 }
             }
             matrizFechas(prueba1);
             matrizFechas(prueba2);

         }
     }

     public void eligeRecorrido(int equipo){
         int [] mejorRecorrido = new int[prueba.length];
         for( int i = 0; i < solucion_Ingenua.Recorr().length;i++){

         }
     }

    public static void main(String[] args) {
        int equipos = 4;
        int[][] fechas = new int[2*(equipos-1)][equipos];

        solucion_Optimizada optima = new solucion_Optimizada(3,1,equipos);
        //optima.matrizFechas(equipos, 2*(equipos-1));
    }
}