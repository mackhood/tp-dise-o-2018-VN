package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoInteligente {

    String nombre;
    private double consumoPorHora;
    private double horasDeUso = 0;
    private Fabricante fabricante;
    private int idInteligente;
    private EstadoDispositivo estadoDispositivo;
    DispositivoFisico dispositivoFisico;

    public DispositivoInteligente(String nombre, int idInteligente, Fabricante unFabricante) {

        this.nombre = nombre;
        this.idInteligente = idInteligente;
        this.fabricante = unFabricante;
    }


    public  void  setNombre ( String nombreNuevo ){
        this.nombre=nombreNuevo;
    }

    public void setConsumoHora (double consumoHora) {

        this.consumoPorHora= consumoHora;
    }

     public void setHorasDeUso (double horasUso) {

        this.horasDeUso=horasUso;
     }

    public void SetFabricante ( Fabricante fabricanteNuevo ) {
        this.fabricante=fabricanteNuevo;

    }


    public void  SetIdInteligente( int idNuevo) {
        this.idInteligente=idNuevo;

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
        dispositivoFisico.apagar();
    }

    public void encender() {
        estadoDispositivo.apagar(this);
        dispositivoFisico.encender();

    }

    public void ponerModoAhorro() {
        estadoDispositivo.ponerModoAhorro(this);
        dispositivoFisico.ahorro();
    }


    public double consumoUltimasXHoras(double X) {
        //Falta hacer la logica
        return 0;
    }

    public void cambiarEstado(EstadoDispositivo estadoNuevo) {
        estadoDispositivo = estadoNuevo;
    }


    public void ejecutar (DispositivoFisico dispositivoFisico) {

        dispositivoFisico.ejecutar();
    }

   /* public double getConsumoTotal() {

    }     Lo hace solo (no entendi como o¡implementarlo)*/
}