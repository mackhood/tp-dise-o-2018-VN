package Clases.Dispositivo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import Clases.Fabricante;

public class DispositivoInteligente extends Dispositivo {

    String nombre;
    private double consumoPorHora;
    private double horasDeUso = 0;
    private Fabricante fabricante;
    private int idInteligente;
    private EstadoDispositivo estadoDispositivo;
    //DispositivoFisico dispositivoFisico;
    private LocalDateTime horaEncendido = null;
    private LocalDateTime horaApagado = null;
    private double consumoTotal = 0;

    public DispositivoInteligente(String nombre, int idInteligente, Fabricante unFabricante) {

        this.nombre = nombre;
        this.idInteligente = idInteligente;
        this.fabricante = unFabricante;
        estadoDispositivo = new EstadoApagado();
    }

    public EstadoDispositivo estadoDispositivo() {
        return estadoDispositivo;
    }

    public boolean esCiertoEstado(EstadoDispositivo estadoCond) {
        return estadoDispositivo.equals(estadoCond);
    }

    public  void  setNombre ( String nombreNuevo ){
        this.nombre=nombreNuevo;
    }

    public void setConsumoPorHora (double consumoHora) {

        this.consumoPorHora= consumoHora;
    }
    
    public void setHorasDeUso (double horas) {
    	
    	this.horasDeUso = horas;
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
        //dispositivoFisico.apagar();
        this.horaApagado = LocalDateTime.now();
    }

    public void encender() {
        
    	estadoDispositivo.encender(this);
        //dispositivoFisico.encender();
        this.horaEncendido = LocalDateTime.now();
    }

    public void ponerModoAhorro() {
        estadoDispositivo.ponerModoAhorro(this);
        //dispositivoFisico.ahorro();
    }
    
    public void serUsado(long horas) {
    	
    	LocalDateTime horaActual = LocalDateTime.now();
    	this.horaEncendido = horaActual;
    	this.horaApagado = horaActual.plusHours(horas);
    	
    	double consumoGenerado = calcularConsumo(horaEncendido,horaApagado);
    	this.consumoTotal += consumoGenerado;
    	this.horasDeUso += horas;
    }
    
    public double calcularConsumo(LocalDateTime unHorario, LocalDateTime otroHorario) {
    	 
    	horasDeUso = unHorario.until(otroHorario, ChronoUnit.HOURS);
    	return horasDeUso*consumoPorHora;
    }
    
    /* Para este metodo de abajo no se me ocurre otra idea que hacer varios if con las distintas situaciones
      	tipo si la hora de encendido y apagado estan antes del intervalo de consulta, etc.
      		Si alguien tiene una mejor idea u otra forma de implementarlo buenisimo.
     */
    
    public double consumoUltimasNHoras(long horas) {
        
    	LocalDateTime hasta = LocalDateTime.now();
    	LocalDateTime desde = hasta.minusHours(horas);
    	return calcularConsumo(desde,hasta);
    }

    public void cambiarEstado(EstadoDispositivo estadoNuevo) {
       
    	estadoDispositivo = estadoNuevo;
    }

    public void ejecutar (DispositivoFisico dispositivoFisico) {

        dispositivoFisico.ejecutar();
    }
    
    public void aumentarConsumoPor(double cantidad) {
    	
    	this.consumoPorHora += cantidad;
    }
    
    public double getConsumoTotal() {

    	return consumoTotal;
    }


	public boolean estaEnModoAhorro() {
		
		return estadoDispositivo.estaEnModoAhorro();
	}
    public int getPuntos() {
        return 10;
    }
}