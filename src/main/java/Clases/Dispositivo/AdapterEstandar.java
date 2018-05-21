package Clases.Dispositivo;


public interface AdapterEstandar {

    void apagar();

    void encender();

    void ponerModoAhorro();

    double getConsumoTotal();

    EstadoDispositivo estadoDispositivo();

    int getIdInteligente();

    boolean estaEncendido();

    boolean estaApagado();

    boolean sonIguales(DispositivoInteligente dispositivoInteligente);

    int getIdFabrica();

    double consumoUltimasXHoras(double horas);

    void cambiarEstado(EstadoDispositivo nuevoEstado);
}