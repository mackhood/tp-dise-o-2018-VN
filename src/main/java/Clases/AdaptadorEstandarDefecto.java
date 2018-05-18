package Clases;

public class AdaptadorEstandarDefecto implements AdapterEstandar {
    private static AdaptadorEstandarDefecto ourInstance = new AdaptadorEstandarDefecto();

    public static AdaptadorEstandarDefecto getInstance() {
        return ourInstance;
    }

    public AdaptadorEstandarDefecto() {
    }


    public EstadoDispositivoEnum estadoDispositivo() {
        return null;
    }


}
