package Clases;

import java.time.LocalTime;

public class Dispositivo {

	String nombre;
	double consumo;
	boolean encendido = false;
	double consumoTotal = 0;
	
	int horaEncendido;
	int horaApagado;
	
	double horasDeUso = 0;
	
	
	public Dispositivo(String nombre,double consumo,boolean encendido) {
		
		this.nombre = nombre;
		this.consumo = consumo;
		this.encendido = encendido;
	}
	
/*
	  
	public void usarDispositivo(LocalTime horaEncendido, int horas) {
	
		this.encender(horaEncendido);
		
		LocalTime horaApagado = horaEncendido.plusHours(horas);
		this.apagar(horaApagado);
		
		if (horaEncendido.isAfter(horaApagado)) {
			
			horasDeUso += horaEncendido.getHour()+ horaApagado.getHour() -12;
			this.reiniciar();
		}
		
		else {
		
			horasDeUso += this.horaApagado - this.horaEncendido
			this.reiniciar();
		}
	}
	
	public void reiniciar() {
		
		this.horaApagado = 0;
		this.horaEncendido = 0;
	}
	
	public void encender(LocalTime horaEncendido) {
		
		if (!encendido) {
			
			encendido = true;
		}
		
		this.horaEncendido = horaEncendido.getHour();
	}
	
	public void apagar(LocalTime horaApagado) {
		
		encendido = false;
		
		this.horaApagado = horaApagado.getHour();
		
	}
	
	*/
	
	public void encender() {
		
		encendido = true;
	}
	
	public void apagar() {
		
		encendido = false;
	}
	
	public String getNombre() {
		
		return nombre;
	}

	public double getConsumo() {
		
		return consumo;
	}

	public boolean isEncendido() {
		
		return encendido;
	}
	
}
