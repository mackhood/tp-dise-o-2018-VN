package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandar extends Dispositivo {
    private String nombre;
    private double consumoEstimadoPorHora;
    private double horasDeUso = 0;
   private Fabricante fabricante;
    private   int idEstandar;

    public DispositivoEstandar(String nombre, double consumoEstimadoPorHora, Fabricante unFabricante) {

        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
        this.fabricante = unFabricante;
    }

    public Fabricante  getFabricante() {

        return fabricante;

    }

     public double getHorasDeUso () {
        return horasDeUso;
     }

    public double consumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public String getNombre() {

        return nombre;
    }


    public void serUsado(int cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }



    public double getConsumoTotal() {
        return this.consumoEstimadoPorHora * this.horasDeUso;
    }

    public int getIdEstandar() {
        return idEstandar;
    }

    public int getPuntos() {
        return 5;
    }

}
