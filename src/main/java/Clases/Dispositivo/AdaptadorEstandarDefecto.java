package Clases.Dispositivo;

public class AdaptadorEstandarDefecto implements AdapterEstandar {
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

    public EstadoDispositivo estadoDispositivo() {
        return new EstadoNulo();
    }


}
