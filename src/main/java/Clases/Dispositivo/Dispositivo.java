

package Clases.Dispositivo;

import Clases.Fabricante;

public abstract class Dispositivo {

    protected String nombre;
    protected double consumoEstimadoPorHora;
    protected double horasDeUso = 0;



    public double consumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getConsumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public void setConsumoEstimadoPorHora(double consumoEstimadoPorHora) {
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
    }

    public double getHorasDeUso() {
        return horasDeUso;
    }

    public void setHorasDeUso(double horasDeUso) {
        this.horasDeUso = horasDeUso;
    }

    public double getConsumoTotal() {

        return consumoEstimadoPorHora * horasDeUso;
    }

    public void aumentarConsumoPor(int aumento) {
        consumoEstimadoPorHora = consumoEstimadoPorHora + aumento;
    }


    public abstract int getPuntos();
}
