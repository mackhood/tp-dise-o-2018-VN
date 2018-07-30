package Clases.ZonaGeografica;

import Clases.Usuario.Cliente;

public class Ubicacion {

    private double posicionX;
    private double posicionY;

    public Ubicacion(double posicionX,double posicionY){

        this.posicionX=posicionX;
        this.posicionY=posicionY;
    }

    public Double calcularDistancia (Cliente cliente) {

        double a= cliente.getPosicionX();
        double b=cliente.getPosicionY();
        double z = Math.hypot(posicionX-a, posicionY-b);
        return z;
    }

}
