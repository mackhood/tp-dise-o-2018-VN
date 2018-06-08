package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandar extends Dispositivo {

    public DispositivoEstandar(String nombre, double consumoEstimadoPorHora) {

        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
    }


     public double getHorasDeUso () {
        return horasDeUso;
     }

    public double getConsumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public String getNombre() {

        return nombre;
    }


    public void serUsado(double cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }



    public double getConsumoTotal() {
        return this.consumoEstimadoPorHora * this.horasDeUso;
    }

    public int getPuntos() {
        return 0;
    }

    public void serUsado(int cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }
}
