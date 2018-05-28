

package Clases.Dispositivo;

import Clases.Fabricante;

public abstract class Dispositivo {

    protected String nombre;
    protected double consumoEstimadoPorHora;
    protected double horasDeUso = 0;
    Fabricante fabricante;


    public void serUsado(int cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }


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



    public void aumentarConsumoPor(int aumento) {
        consumoEstimadoPorHora = consumoEstimadoPorHora + aumento;
    }


    public abstract int getPuntos();
}
