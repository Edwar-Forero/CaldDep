/**
 * @autor Santiago Anibal Carrillo Torres, Edwar Yamir Forero Blanco, Juan Eduardo Calderon Jaramillo
 */
package solucionOptimizada;



/**
 * Clase que se encarga de generar las fechas del torneo
 */
public class solucion_Optimizada {
    private int max, min;
    int[][] prueba = new int[4][4];
        /**
         * Constructor de la clase que recibe los datos para generar las fechas del torneo
         * @param max
         * @param min
         * @param equipos
         */
     public solucion_Optimizada( int max, int min, int equipos){
            this.max = max;
            this.min = min;
        }

        /**
         * Método que genera las fechas del torneo
         * @param prueba
         */
        public void matrizFechas ( int[][] prueba){
            if (prueba.length <= 1) {

            } else {
            }
        }
        /**
         * Método que genera los recorridos de los equipos
         * @param args
         */
        public static void main (String[]args){
            int[][] recorrido = {
                    {0, 745, 665, 929},
                    {745, 0, 80, 337},
                    {665, 80, 0, 380},
                    {929, 337, 380, 0}
            };
            solucion_Optimizada optima = new solucion_Optimizada(3, 1, recorrido.length);
            //System.out.println(optima.toString());


            //optima.matrizFechas(equipos, 2*(equipos-1));

        }
    }
}