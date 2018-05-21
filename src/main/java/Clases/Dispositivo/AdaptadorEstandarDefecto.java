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

    public void ponerModoAhorro() {

    }

    public double getConsumoTotal() {
        return 0;
    }

    public EstadoDispositivo estadoDispositivo() {
        return null;
    }

    public int getIdInteligente() {
        return 0;
    }

    public boolean estaEncendido() {
        return false;
    }

    public boolean estaApagado() {
        return false;
    }

    public boolean sonIguales(DispositivoInteligente dispositivoInteligente) {
        return false;
    }

    public int getIdFabrica() {
        return 0;
    }

    public double consumoUltimasXHoras(double horas) {
        return 0;
    }

    public void cambiarEstado(EstadoDispositivo nuevoEstado) {

    }


}
