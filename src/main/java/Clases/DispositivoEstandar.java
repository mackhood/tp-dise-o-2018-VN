package Clases;

import Clases.entities.DispositivoEstandarException;

public class DispositivoEstandar extends Dispositivo {

    AdaptadorEstandard adapter = new AdaptadorEstandarDefecto();

    public DispositivoEstandar(String nombre, double consumoEstimadoPorHora) {
        super(nombre, consumoEstimadoPorHora);

    }

    public boolean tieneAdapter() {
        return adapter != null;
    }

    public void agregarAdaptadorInteligente() {
        adapter = new AdapterEstandarAInteligente(this);

    }

    public DispositivoEstandarException tirarException() {
        return new DispositivoEstandarException();
    }

    @Override
    public void apagar() {
        adapter.apagar();

    }

    @Override
    public void encender() {
        adapter.encender();

    }

    @Override
    public EstadoDispositivoEnum estadoDispositivo() {
        return adapter.estadoDispositivo();
    }

    @Override
    public double getConsumoTotal() {
        return adapter.getConsumoTotal();
    }
}
