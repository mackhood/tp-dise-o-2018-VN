package Clases.Dispositivo;

import Clases.Fabricante;

public abstract class Dispositivo {

    protected String nombre;
    protected EstadoDispositivo estado = null;
    protected double consumoEstimadoPorHora;
    protected double horasDeUso = 0;
    Fabricante fabricante;


    public void serUsado(int cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }

    public abstract void encender();

    public abstract void apagar();

    public double consumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public String getNombre() {

        return nombre;
    }

    public double getConsumoTotal() {

        return consumoEstimadoPorHora * horasDeUso;
    }

    public int getIdFabrica() {
        return fabricante.idFabrica();
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public EstadoDispositivo estadoDispositivo() {
        return estado;
    }

    public boolean esCiertoEstado(EstadoDispositivo estadoCond) {
        return estado.equals(estadoCond);
    }

    public void aumentarConsumoPor(int aumento) {
        consumoEstimadoPorHora = consumoEstimadoPorHora + aumento;
    }

}
