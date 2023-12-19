package solucionOptimizada;

public class ObjectOptima {

    private int contrincantes;
    private int nFechas;

    public ObjectOptima(int nFecha, int contrincantes){
        this.nFechas = nFecha;
        this.contrincantes = contrincantes;

    }

    public void setNFechas(int nFechas) {
        this.nFechas = nFechas;
    }

    public int getNFechas() {
        return nFechas;
    }

    public int getContrincantes() {
        return contrincantes;
    }

    public void setContrincantes(int contrincantes) {
        this.contrincantes = contrincantes;
    }

}
