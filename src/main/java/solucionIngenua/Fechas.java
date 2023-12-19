/**
 * Creación de las fechas para el torneo por medio de solución ingenua
 * @version 1.0, 15/12/2023
 * @autor Santiago A. Carrillo Torres, Edwar Y. Forero Blanco, Juan E. Calderon
 */

package solucionIngenua;

/**
 * Clase que se encarga de generar las fechas del torneo
 */
public class Fechas {
    private int[][] torneo;
    private int totalR=0;
    private final int[][] enfrent;
    private final int columna;
    private final int min, max;


    /**
     * Constructor de la clase Fechas
     * @param fila número de fechas de la matriz
     * @param columna número de equipos de la matriz
     * @param max número máximo de partidos seguidos de un equipo
     * @param min número mínimo de partidos seguidos de un equipo
     */
    public Fechas(int fila, int columna, int max, int min){
        this.enfrent = new int[fila][columna];
        this.columna = columna;
        this.min = min;
        this.max = max;

        Vuelta();
        MiniRecor();
    }


    /**
     * Método que genera fechas para la ida del torneo (No tiene en cuenta las distancias)
     */
    public void OrgFechas(){
        if (this.columna % 2 != 0) {
            System.out.println("El número de equipos debe ser par");
            System.exit(0);
        } else{
            int ultPos1 = -1;
            for (int nFecha = 0; nFecha < this.enfrent.length/2; nFecha++) {
                for (int nTeam = 0; nTeam < this.enfrent[nFecha].length; nTeam++) {
                    if (enfrent[nFecha][nTeam] != 0){
                        continue;
                    }
                    else{
                        int equipo = 1;
                        while (equipo <= columna){
                            //Elegir los contrincantes del cada equipo
                            if(equipo-1 != nTeam && equipo > ultPos1){

                                //La posición del equipo es mayor a la del último equipo que jugó para evitar que se repitan los equipos
                                //Se asigna el equipo a la posición
                                enfrent[nFecha][nTeam] = equipo;

                                //Guarda la posición del contrincante del equipo 1
                                ultPos1 = enfrent[nFecha][0];

                                //Configuración para la última Nfecha que se tiene que jugar en la ida
                                if(equipo >= columna){

                                    //Se asigna el valor al equipo contrario en la última Nfecha
                                    enfrent[nFecha][equipo-1] = (nTeam+1)*-1;

                                    //Se recorre de nuevo las posiciones para los equipos que no tienen contrincante en la última Nfecha
                                    for (int i = 0; i < ((this.enfrent[nFecha].length)-1);i++){

                                        //El valor del equipo debe ser mayor que 2 y menor que el final ya que son los valores faltantes
                                        if (enfrent[nFecha][i] == 0 && equipo>=2 && equipo<columna){
                                            enfrent[nFecha][i] = equipo;
                                            enfrent[nFecha][equipo-1] = (i+1)*-1;
                                        }
                                        equipo--;
                                    }
                                    break;
                                }
                                else{
                                    //Si no es la última Nfecha se asigna el contrincante del equipo normalmente
                                    enfrent[nFecha][nTeam] = equipo;
                                    ultPos1 = enfrent[nFecha][0];
                                    if (enfrent[nFecha][equipo-1] == 0){
                                        enfrent[nFecha][equipo-1] = (nTeam+1)*-1;
                                        break;
                                    }
                                }
                            }
                            equipo++;
                        }
                    }
                }
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
        //Retorna la matriz con los valores de la vuelta
    }


    /**
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
     * Método que suma el recorrido total de los equipos en el torneo
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
                    //System.out.println("Team: " + (nTeam + 1) + ", recorrido: " + solucion_Ingenua.Recorr()[posicionEqui][posicionEqui2]);
                    totalR += solucion_Ingenua.Recorr()[posicionEqui][posicionEqui2];
                }

                //Tenga partidos de visitante seguidos
                if (enfrent[nFechas][nTeam] < 0 && enfrent[nFechas + 1][nTeam] < 0) {
                     posicionEqui = (enfrent[nFechas][nTeam] * -1) - 1;
                    posicionEqui2 = (enfrent[nFechas + 1][nTeam] * -1) - 1;
                    totalR += solucion_Ingenua.Recorr()[posicionEqui][posicionEqui2];
                }

                //Esté de local y vaya de visita
                if (enfrent[nFechas][nTeam] > 0 && enfrent[nFechas + 1][nTeam] < 0) {
                    posicionEqui = nTeam;
                    posicionEqui2 = (enfrent[nFechas + 1][nTeam] * -1) - 1;
                    totalR += solucion_Ingenua.Recorr()[posicionEqui][posicionEqui2];
                }

                //Esté de visita y vaya de local
                if (enfrent[nFechas][nTeam] < 0 && enfrent[nFechas + 1][nTeam] > 0) {
                    posicionEqui = (enfrent[nFechas][nTeam] * -1) - 1;
                    posicionEqui2 = nTeam;
                    totalR += solucion_Ingenua.Recorr()[posicionEqui][posicionEqui2];
                }

                //Ultimo partido visitante y se devuelve a casa
                if (enfrent[nFechas][nTeam] < 0 && nFechas+1 == enfrent.length-1) {
                    posicionEqui = (enfrent[nFechas][nTeam] * -1) - 1;
                    posicionEqui2 = nTeam;
                    totalR += solucion_Ingenua.Recorr()[posicionEqui][posicionEqui2];
                }
            }
        }
        return totalR;
    }


    /**
     * Método que valida que los equipos no tengan más partidos seguidos de los permitidos
     * @param enfrent matriz con las fechas del torneo
     * @return true si los equipos cumplen con min y max, false si no
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
        for (int[] ints : torneo) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.print("\n");
        }
        return "";
    }


    public static void main(String[] args) {
        int prueba = 8;
        Fechas fechas = new Fechas(2*(prueba-1),prueba, 7,1);
        System.out.println(fechas.toString());
    }
}
