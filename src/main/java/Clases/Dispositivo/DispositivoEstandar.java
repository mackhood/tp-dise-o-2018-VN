package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandar extends Dispositivo {

    DispositivoEstandarInteligente dispositivoEstandarInteligente;
    int idEstandar;

    public DispositivoEstandar(String nombre, double consumoEstimadoPorHora, Fabricante unFabricante) {

        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
        this.fabricante = unFabricante;
    }

    public void agregarAdaptadorInteligente() {
        dispositivoEstandarInteligente = new DispositivoEstandarInteligente(this);
    }

    public void apagar() {

    }

    public void encender() {

    }

    public double getConsumoTotal() {
        return this.consumoEstimadoPorHora * this.horasDeUso;
    }

    public int getIdEstandar() {
        return idEstandar;
    }

    public DispositivoEstandarInteligente getDispositivoEstandarInteligente(){return dispositivoEstandarInteligente;}


}
