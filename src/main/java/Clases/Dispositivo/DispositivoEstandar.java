package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandar extends Dispositivo {
    AdapterEstandar adapter = new AdapterEstandarDefecto();
    int idEstandar;

    public DispositivoEstandar(String nombre, double consumoEstimadoPorHora, Fabricante unFabricante) {

        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
        this.fabricante = unFabricante;
    }

    public void agregarAdaptadorInteligente() {
        adapter = new AdapterEstandarAInteligente(this);
    }

    public void apagar() {
        adapter.apagar();
    }

    public void encender() {
        adapter.encender();
    }

    public EstadoDispositivo estadoDispositivo() {
        return adapter.estadoDispositivo();
    }

    public double getConsumoTotal() {
        return this.consumoEstimadoPorHora * this.horasDeUso;
    }

    public int getIdEstandar() {
        return idEstandar;
    }

}
