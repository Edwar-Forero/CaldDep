/**
 * Creación de las fechas para el torneo por medio de solución ingenua
 * @version 1.0, 15/12/2023
 * @autor Santiago A. Carrillo Torres, Edwar Y. Forero Blanco, Juan E. Calderon Jaramillo
 */

package solucionIngenua;

import cargaDeDatos.CargaDatos;
import cargaDeDatos.guardarDatosEnArchivo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de generar las fechas del torneo
 */
public class solucion_Ingenua {

    //Matriz que guarda las fechas del torneo
    private int[][] torneo;
    //Variable que guarda el recorrido total de los equipos
    private int totalR=0;
    //Matriz generada para guardar las fechas del torneo
    private final int[][] enfrent;
    //Número de equipos del torneo
    private final int columna;
    //Número máximo y mínimo de partidos seguidos de un equipo
    private final int min, max;
    //Lista que guarda los equipos
    List<Integer> teams;
    //Recorridos para los equipos
    int[][] recorridos;

    /**
     * Constructor de la clase que recibe los datos para generar las fechas del torneo
     * @param fila número de fechas de la matriz
     * @param columna número de teams de la matriz
     * @param max número máximo de partidos seguidos de un equipo
     * @param min número mínimo de partidos seguidos de un equipo
     */
    public solucion_Ingenua(int fila, int columna, int max, int min, int [][] matrizDistancias) {
        this.enfrent = new int[fila][columna];
        this.columna = columna;
        this.min = min;
        this.max = max;
        this.recorridos = matrizDistancias;

        MiniRecor();
    }


    /**
     * Método que guarda los equipos
     */
    public void guardarEquipos() {
        teams = new ArrayList<>();
        for (int i = 0; i < this.columna; i++) {
            teams.add(i);
        }
    }

    /**
     * Método que genera fechas para la ida del torneo (No tiene en cuenta las distancias)
     */
    public void OrgFechas(){
        guardarEquipos();

        //Si el equipo es impar no se puede generar el torneo
        if (this.columna % 2 != 0) {
            System.exit(0);
        }
        else{
            for (int i = 0; i < enfrent.length/2; i++) {
                for (int j = 0; j < columna / 2; j++) {
                    // Los teams se emparejan y juegan entre sí
                    int equipo1 = teams.get(j);
                    int equipo2 = teams.get(columna - j - 1);
                    enfrent[i][equipo1] = equipo2 + 1; // El equipo1 juega en casa contra equipo2
                    enfrent[i][equipo2] = -(equipo1 + 1); // El equipo2 juega fuera contra equipo1
                }

                // Rotar los teams para la siguiente jornada
                int primerEquipo = teams.remove(1);
                teams.add(primerEquipo);
            }
        }
    }


    /**
     * Método que genera fechas para la vuelta del torneo (No tiene en cuenta las distancias)
     */
    public void Vuelta(){
        //Agrego los valores de la ida
        OrgFechas();
        //Se recorre la otra mitad de la matriz
        for (int nFechas = enfrent.length/2; nFechas < enfrent.length; nFechas++){
            for (int nEquipos = 0; nEquipos < this.columna; nEquipos++) {
                //Se asigna el valor de la ida a la vuelta con el valor contrario
                enfrent[nFechas][nEquipos] = enfrent[nFechas-(enfrent.length/2)][nEquipos]*-1;
            }
        }
    }


    /**w
     * Método que se encarga de llamar a la función para permutaciones
     */
    public void MiniRecor(){
        Vuelta();
        permutaciones(enfrent, 0);
    }

    /**
     * Método recursivo que permuta todas las fechas del torneo
     * @param fechasEquipo matriz con las fechas del torneo
     * @param nFecha número de fechas del torneo
     */
    public void permutaciones(int[][] fechasEquipo, int nFecha){
        if (nFecha == fechasEquipo.length - 1) {
            int actual = SumaRecorrido(fechasEquipo);
            if ((totalR > actual || (totalR == 0)) && (minYMax(fechasEquipo))){
                torneo = fechasEquipo.clone();
                totalR = actual;
            }
        }
        else {
            for (int i = nFecha; i < fechasEquipo.length; i++) {

                int[] temp = fechasEquipo[nFecha];
                fechasEquipo[nFecha] = fechasEquipo[i];
                fechasEquipo[i] = temp;

                permutaciones(fechasEquipo, nFecha + 1);

                temp = fechasEquipo[nFecha];
                fechasEquipo[nFecha] = fechasEquipo[i];
                fechasEquipo[i] = temp;
            }
        }
    }

    /**
     * Método que suma el recorrido total de los teams en el torneo
     * @param enfrent matriz con las fechas del torneo
     * @return suma total del recorrido de un torneo
     */
    public int SumaRecorrido(int[][] enfrent){
        int totalR = 0;
        int posicionEqui;
        int posicionEqui2;
        for (int nTeam = 0; nTeam < this.columna; nTeam++) {
            for (int nFechas = 0; nFechas < enfrent.length - 1; nFechas++) {


                //Inicie el torneo de visita
                if (nFechas == 0 && enfrent[nFechas][nTeam] < 0){
                    posicionEqui = nTeam;
                    posicionEqui2 = (enfrent[nFechas][nTeam] * -1) - 1;
                    totalR += recorridos[posicionEqui][posicionEqui2];
                }

                //Tenga partidos de visitante seguidos
                if (enfrent[nFechas][nTeam] < 0 && enfrent[nFechas + 1][nTeam] < 0) {
                    posicionEqui = (enfrent[nFechas][nTeam] * -1) - 1;
                    posicionEqui2 = (enfrent[nFechas + 1][nTeam] * -1) - 1;
                    totalR += recorridos[posicionEqui][posicionEqui2];
                }

                //Esté de local y vaya de visita
                if (enfrent[nFechas][nTeam] > 0 && enfrent[nFechas + 1][nTeam] < 0) {
                    posicionEqui = nTeam;
                    posicionEqui2 = (enfrent[nFechas + 1][nTeam] * -1) - 1;
                    totalR += recorridos[posicionEqui][posicionEqui2];
                }

                //Esté de visita y vaya de local
                if (enfrent[nFechas][nTeam] < 0 && enfrent[nFechas + 1][nTeam] > 0) {
                    posicionEqui = (enfrent[nFechas][nTeam] * -1) - 1;
                    posicionEqui2 = nTeam;
                    totalR += recorridos[posicionEqui][posicionEqui2];
                }

                //Ultimo partido visitante y se devuelve a casa
                if (enfrent[nFechas][nTeam] < 0 && nFechas+1 == enfrent.length-1) {
                    posicionEqui = (enfrent[nFechas][nTeam] * -1) - 1;
                    posicionEqui2 = nTeam;
                    totalR += recorridos[posicionEqui][posicionEqui2];
                }
            }
        }
        return totalR;
    }


    /**
     * Método que valida que los teams no tengan más partidos seguidos de los permitidos
     * @param enfrent matriz con las fechas del torneo
     * @return true si los teams cumplen con min y max, false si no
     */
    public boolean minYMax(int[][] enfrent){
        int contPos, contNeg;
        boolean permitido = true;
        for (int nTeams = 0; nTeams < this.columna; nTeams++){
            contPos=0;
            contNeg=0;

            for (int[] ints : enfrent) {

                if (ints[nTeams] < 0) {
                    contPos = 0;
                    contNeg++;
                    if (contNeg > this.max || contNeg < this.min) {
                        permitido = false;
                        break;
                    }
                } else {
                    contNeg = 0;
                    contPos++;
                    if (contPos > max || contPos < min) {
                        permitido = false;
                        break;
                    }
                }
            }
        }
        return permitido;
    }

    /**
     * Método que retorna la matriz con las fechas del torneo
     * @return matriz con las fechas del torneo
     */
    @Override
    public String toString() {
        StringBuilder acumularFilas = new StringBuilder();
        for (int[] ints : torneo) {
            for (int anInt : ints) {
                acumularFilas.append(anInt).append(" ");
                //System.out.print(anInt + " ");
            }
            acumularFilas.append("\n");
            //System.out.print("\n");
        }
        return acumularFilas.toString();
    }

    /*public static void main(String[] args) {
        int prueba = 6;
        int[][] recorrido = {
                {0, 184, 222, 177, 216, 231, 120,  60},
                {184, 0,  45, 123, 128, 200,  52, 100},
                {222, 45,   0, 129, 121, 203,  15, 300},
                {177, 123, 129,   0,  46,  83, 250, 15},
                {216, 128, 121,  46,   0,  83, 100, 7},
                {231, 200, 203,  83,  83,   0,  20, 10},
                {120, 52,  15, 250, 100,  20,  0,  441},
                {60, 100, 300,  15,   7,  10, 441, 0},
        };
        solucion_Ingenua fechas = new solucion_Ingenua(2*(prueba-1),prueba, 5,1, recorrido);
        //System.out.println(fechas.toString());

        String s = fechas.toString();
        System.out.println(s);
    }*/
}