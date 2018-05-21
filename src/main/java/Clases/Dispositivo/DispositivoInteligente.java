package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoInteligente {

    String nombre;
    Fabricante fabricante;
    int idInteligente;
    EstadoDispositivo estadoDispositivo;

    public DispositivoInteligente(String nombre, int idInteligente, Fabricante unFabricante) {
        this.nombre = nombre;
        this.idInteligente = idInteligente;
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

    public void apagar() {
        estadoDispositivo.apagar(this);
    }

    public void encender() {
        estadoDispositivo.apagar(this);
    }

    public void ponerModoAhorro() {
        estadoDispositivo.ponerModoAhorro(this);
    }

    public int getIdFabrica() {
        return fabricante.idFabrica();
    }


    public double consumoUltimasXHoras(double X) {
        //Falta hacer la logica
        return 0;
    }

    public void cambiarEstado(EstadoDispositivo estadoNuevo) {
        estadoDispositivo = estadoNuevo;
    }


}
