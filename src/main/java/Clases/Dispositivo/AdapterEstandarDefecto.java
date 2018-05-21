package Clases.Dispositivo;

public class AdapterEstandarDefecto implements AdapterEstandar {
    private static AdapterEstandarDefecto ourInstance = new AdapterEstandarDefecto();

    public static AdapterEstandarDefecto getInstance() {
        return ourInstance;
    }

    public AdapterEstandarDefecto() {
    }

    public void apagar() {

    }

    public void encender() {

    }

    public void ponerModoAhorro() {

    }

    public EstadoDispositivo estadoDispositivo() {
        return new EstadoNulo();
    }

    public int getIdInteligente() {
        return 0;
    }

    @Override
    public boolean estaEncendido() {
        return false;
    }

    @Override
    public boolean estaApagado() {
        return false;
    }

    @Override
    public boolean sonIguales(DispositivoInteligente dispositivoInteligente) {
        return false;
    }

    @Override
    public int getIdFabrica() {
        return 0;
    }

    @Override
    public double consumoUltimasXHoras(double horas) {
        return 0;
    }

    @Override
    public void cambiarEstado(EstadoDispositivo nuevoEstado) {

    }

    public double getConsumoTotal() {
        return 0;
    }

}