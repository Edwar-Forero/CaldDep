/**
 * @autor Santiago Anibal Carrillo Torres, Edwar Yamir Forero Blanco, Juan Eduardo Calderon Jaramillo
 */
package solucionOptimizada;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que se encarga de generar las fechas del torneo
 */
public class solucion_Optimizada {

    private int limitePermutaciones;
    //Matriz que guarda las fechas del torneo
    private int[][] torneo;
    //Variable que guarda el recorrido total de los equipos
    private int totalR=0;
    //Matriz generada para guardar las fechas del torneo
    private final int[][] enfrent;

    //Número máximo y mínimo de partidos seguidos de un equipo y equipos del torneo respectivamente
    private final int min, max, columna;
    //Lista que guarda los equipos
    List<Integer> teams;
    //Recorridos para los equipos
    private final int[][] recorridos;

    /**
     * Constructor de la clase que recibe los datos para generar las fechas del torneo
     * @param fila número de fechas de la matriz
     * @param columna número de teams de la matriz
     * @param max número máximo de partidos seguidos de un equipo
     * @param min número mínimo de partidos seguidos de un equipo
     */
    public solucion_Optimizada(int fila, int columna, int max, int min, int [][] matrizDistancias, int limite) {
        this.enfrent = new int[fila][columna];
        this.columna = columna;
        this.min = min;
        this.max = max;
        this.recorridos = matrizDistancias;
        this.limitePermutaciones = limite;

        Vuelta();
    }


    /**
     * Método que guarda los equipos
     *
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
                for (int j = 0; j < columna/2; j++) {
                    // Los equipos se emparejan y juegan entre sí
                    int equipo1 = teams.get(j);
                    int equipo2 = teams.get(columna - j - 1);
                    enfrent[i][equipo1] = equipo2 + 1; // El equipo1 juega en casa contra equipo2
                    enfrent[i][equipo2] = -(equipo1 + 1); // El equipo2 juega fuera contra equipo1
                }

                // Rotar los equipos para la siguiente jornada
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

        //Realizar permutaciones y encontrar el mejor torneo
        permutaciones(enfrent, limitePermutaciones);
    }

    /**
     * Método recursivo que permuta todas las fechas del torneo
     * @param fechasEquipo matriz con las fechas del torneo
     * @param limitePermutaciones número de permutaciones que se realizarán
     */
    public void permutaciones(int[][] fechasEquipo, int limitePermutaciones) {
        Random rand = new Random();

        for (int permutacion = 0; permutacion < limitePermutaciones; permutacion++) {
            int fila1 = rand.nextInt(fechasEquipo.length);
            int fila2 = rand.nextInt(fechasEquipo.length);

            // Intercambiar la fila1 con la fila2
            int[] temp = fechasEquipo[fila1];
            fechasEquipo[fila1] = fechasEquipo[fila2];
            fechasEquipo[fila2] = temp;

            //System.out.println(Arrays.deepToString(fechasEquipo));
            int actual = SumaRecorrido(fechasEquipo);
            if ((totalR > actual || (totalR == 0)) && (minYMax(fechasEquipo))){
                torneo = fechasEquipo.clone();
                //System.out.println(Arrays.deepToString(torneo));
                totalR = actual;
            }
        }
    }

    /**
     * Método que suma el recorrido total de los teams en el torneo
     * @param enfrent matriz con las fechas del torneo
     * @return suma total del recorrido de un torneo
     */
    public int SumaRecorrido(int[][] enfrent){
        int suma = 0;
        int posicionEqui;
        int posicionEqui2;
        for (int nTeam = 0; nTeam < this.columna; nTeam++) {
            for (int nFechas = 0; nFechas < enfrent.length - 1; nFechas++) {
                //Inicie el torneo de visita
                if (nFechas == 0 && enfrent[nFechas][nTeam] < 0){
                    posicionEqui = nTeam;
                    posicionEqui2 = (enfrent[nFechas][nTeam] * -1) - 1;
                    suma += recorridos[posicionEqui][posicionEqui2];
                }

                //Tenga partidos de visitante seguidos
                if (enfrent[nFechas][nTeam] < 0 && enfrent[nFechas + 1][nTeam] < 0) {
                    posicionEqui = (enfrent[nFechas][nTeam] * -1) - 1;
                    posicionEqui2 = (enfrent[nFechas + 1][nTeam] * -1) - 1;
                    suma += recorridos[posicionEqui][posicionEqui2];
                }

                //Esté de local y vaya de visita
                if (enfrent[nFechas][nTeam] > 0 && enfrent[nFechas + 1][nTeam] < 0) {
                    posicionEqui = nTeam;
                    posicionEqui2 = (enfrent[nFechas + 1][nTeam] * -1) - 1;
                    suma += recorridos[posicionEqui][posicionEqui2];
                }

                //Esté de visita y vaya de local
                if (enfrent[nFechas][nTeam] < 0 && enfrent[nFechas + 1][nTeam] > 0) {
                    posicionEqui = (enfrent[nFechas][nTeam] * -1)-1;
                    posicionEqui2 = nTeam;
                    suma += recorridos[posicionEqui][posicionEqui2];
                }

                //Ultimo partido visitante y se devuelve a casa
                if ((enfrent[nFechas+1][nTeam] < 0 && nFechas+1 == enfrent.length-1)) {
                    posicionEqui = (enfrent[nFechas+1][nTeam] * -1)-1;
                    posicionEqui2 = nTeam;
                    suma += recorridos[posicionEqui][posicionEqui2];
                }
            }
        }
        return suma;
    }


    /**
     * Método que valida que los teams no tengan más partidos seguidos de los permitidos
     * @param enfrent matriz con las fechas del torneo
     * @return true si los teams cumplen con min y max, false si no
     */
    public boolean minYMax(int[][] enfrent){
        //Contador de partidos seguidos
        int contPos, contNeg;
        boolean permitido = true;
        for (int nTeams = 0; nTeams < this.columna; nTeams++) {
            //Se resetean los contadores
            contPos=0;
            contNeg=0;
            for (int[] ints : enfrent) {
                //Si el equipo es visitante se suma al contador de visitantes
                if (ints[nTeams] < 0) {
                    contPos = 0;
                    contNeg++;
                    //Si el contador de visitantes es mayor o menor a los permitidos se sale del ciclo
                    if (contNeg > this.max || contNeg < this.min) {
                        permitido = false;
                        break;
                    }
                }
                //Si el equipo es local se suma al contador de locales
                else {
                    contNeg = 0;
                    contPos++;
                    //Si el contador de locales es mayor o menor a los permitidos se sale del ciclo
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
}