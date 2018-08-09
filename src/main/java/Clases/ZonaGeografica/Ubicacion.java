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

        Ubicacion ubicacionCliente= cliente.getPosicion();
        double posicionXcliente =ubicacionCliente.getPosicionX();
        double posicionYcliente =ubicacionCliente.getPosicionX();
        return Math.hypot(posicionXcliente-posicionX, posicionYcliente-posicionY);

    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }
}
