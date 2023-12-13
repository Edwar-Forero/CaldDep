package solucionIngenua;
import java.util.Arrays;

public class Fechas {

    private int contP, contN;
    private int totalR=0;
    private final int[][] enfrent;
    private final int columna;

    public Fechas(int fila, int columna){
        this.enfrent = new int[fila][columna];
        this.columna = columna;
    }

    public void OrgFechas(){
        if (this.columna % 2 != 0) {
            System.out.println("El número de equipos debe ser par");
            System.exit(0);
        } else{
            int ultPos1 = -1;
            for (int nFecha = 0; nFecha < this.enfrent.length/2; nFecha++) {
                for (int nTeam = 0; nTeam < this.enfrent[nFecha].length; nTeam++) {
                    if (enfrent[nFecha][nTeam] != 0){}
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
        //Retorna la matriz con los valores de la ida
    }

    public int[][] Vuelta(){
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
        return enfrent;
    }


    public void PermutaFecha(int[] fechaCambio){
        //Se recorre la matriz
        for (int nFechas = 0; nFechas < enfrent.length; nFechas++){
            for (int nEquipos = 0; nEquipos < this.columna; nEquipos++) {
                //Se permuta el valor de la matriz
                if (enfrent[nFechas][nEquipos] < 0){
                    enfrent[nFechas][nEquipos] = enfrent[nFechas][nEquipos]*-1;
                }
                else{
                    enfrent[nFechas][nEquipos] = enfrent[nFechas][nEquipos]*-1;
                }
            }
        }
    }

    public int[][] AnalizaRecorridos(int max, int min){
        Vuelta();
        for (int nTeams = 0; nTeams < this.columna; nTeams++){
            contP=0;
            contN=0;
            for (int nFechas = 0; nFechas < enfrent.length; nFechas++) {
                if (enfrent[nFechas][nTeams] < 0){
                    contP=0;
                    contN++;
                    int valor = (enfrent[nFechas][nTeams]*-1)-1;
                    int recorrido = solucion_Ingenua.Recorr()[nTeams][valor];
                    if (contN > max || contN < min){
                        PermutaFecha(enfrent[nFechas]);
                        System.out.println(Arrays.toString(enfrent[nFechas]));
                    }
                }
                else{
                    contN=0;
                    contP++;
                    if (contP > max || contP < min){
                        PermutaFecha(enfrent[nFechas]);
                        System.out.println(Arrays.toString(enfrent[nFechas]));
                    }
                }
            }
        }
        return enfrent;
    }

    public int SumaRecorrido(int inicio, int equipo) {
        int posicionEqui;
        int posicionEqui2;
        Vuelta();
        for (int nTeam = inicio; nTeam < equipo+1; nTeam++) {
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


                //Ultimo partido visitante y termina de local
                if (enfrent[nFechas][nTeam] < 0 && nFechas+1 == enfrent.length-1) {
                    posicionEqui = (enfrent[nFechas+1][nTeam] * -1) - 1;
                    posicionEqui2 = nTeam;
                    totalR += solucion_Ingenua.Recorr()[posicionEqui][posicionEqui2];
                }
            }
        }
            return totalR;
        }

    public static void main(String[] args) {
        int prueba = 4;
        Fechas fechas = new Fechas(2*(prueba-1),prueba);
        System.out.println(fechas.SumaRecorrido(0,prueba-1));
        System.out.println(Arrays.deepToString(fechas.AnalizaRecorridos(3,1)));
        System.out.println("Recorrido total: "+fechas.totalR+" km");
    }
}
