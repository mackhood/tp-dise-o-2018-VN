package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandar {
    String nombre;
    int idEstandar;
    Fabricante fabricante;
    double consumoEstimadoPorHora;
    double horasEstimadasDeUso = 0;
    EstadoDispositivo estadoDispositivo = new EstadoNulo();
    AdapterEstandar adapter = new AdapterEstandarDefecto();

    public DispositivoEstandar(String nombre, int idEstandar, Fabricante unFabricante, double consumoEstimadoPorHora) {
        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
        this.idEstandar = idEstandar;
        this.fabricante = unFabricante;
    }

    public void encender() {
        adapter.encender();
    }

    public void apagar() {
        adapter.apagar();
    }

    public void ponerModoAhorro() {
        adapter.ponerModoAhorro();
    }




    public int getIdEstandar() {
        return idEstandar;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarAdaptadorInteligente() {
        adapter = new AdapterEstandarAInteligente(this);
    }

    public EstadoDispositivo estadoDispositivo() {
        return adapter.estadoDispositivo();
    }

    public double getConsumoEstimadoPorHora() {
        return consumoEstimadoPorHora * horasEstimadasDeUso;
    }
    private int getIdInteligente() {
        return idInteligente;
    }

    public boolean estaEncendido() {
        return estadoDispositivo.estaEncendido();
    }

    public boolean estaApagado() {
        return estadoDispositivo.estaApagado();
    }

    public boolean sonIguales(DispositivoInteligente dispositivoInteligente) {
        return adapter.
    }

    public int identificadorFabrica() {
        return fabricante.idFabrica();
    }

    public void cambiarEstado(EstadoDispositivo estadoNuevo) {
        estadoDispositivo = estadoNuevo;
    }
}
