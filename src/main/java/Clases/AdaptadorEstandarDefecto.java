package Clases;

public class AdaptadorEstandarDefecto implements AdaptadorEstandard {
    private static AdaptadorEstandarDefecto ourInstance = new AdaptadorEstandarDefecto();

    public static AdaptadorEstandarDefecto getInstance() {
        return ourInstance;
    }

    public AdaptadorEstandarDefecto() {
    }

    public void apagar() {

    }

    public void encender() {

    }

    public EstadoDispositivoEnum estadoDispositivo() {
        return null;
    }

    public double getConsumoTotal() {
        return 0;
    }

}
