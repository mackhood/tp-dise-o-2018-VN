package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoInteligente extends Dispositivo {

    String nombre;
    int idInteligente;
    EstadoDispositivo estadoDispositivo = new EstadoApagado();


    public DispositivoInteligente(String nombre, double consumoEstimadoPorHora, Fabricante unFabricante) {

        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
        this.fabricante = unFabricante;
    }

    private int getIdInteligente() {
        return idInteligente;
    }

    public boolean estaEncendido() {
        return estadoDispositivo.estaEncendido();
    }

    public boolean estaApagado() {
        return estadoDispositivo.estaApagado();
    }

    public boolean sonIguales(DispositivoInteligente dispositivoInteligente) {
        return this.idInteligente == dispositivoInteligente.getIdInteligente();
    }

    public boolean estaEnModoAhorro() { return estadoDispositivo.estaEnModoAhorro();}
    public void apagar() {
        estadoDispositivo.apagar(this);
    }

    public void encender() {
        estadoDispositivo.encender(this);
    }

    public void ponerModoAhorro() {
        estadoDispositivo.ponerModoAhorro(this);
    }


    public double consumoUltimasXHoras(double x) {
        if(x < horasDeUso)
        {
            return consumoEstimadoPorHora * x;
        }
        else
            return horasDeUso * consumoEstimadoPorHora;
    }

    public EstadoDispositivo estadoDispositivo(){return estadoDispositivo;}

    public void cambiarEstado(EstadoDispositivo estadoNuevo) {
        estadoDispositivo = estadoNuevo;
    }


}
