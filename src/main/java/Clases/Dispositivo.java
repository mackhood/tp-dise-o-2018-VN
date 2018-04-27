package Clases;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Dispositivo {

	String nombre;
	double consumo;
	boolean encendido = false;
	double consumoTotal = 0;
	
	LocalDateTime horaEncendido;
	LocalDateTime horaApagado;
	
	double horasDeUso = 0;
	
	
	public Dispositivo(String nombre,double consumo,boolean encendido) {
		
		this.nombre = nombre;
		this.consumo = consumo;
		this.encendido = encendido;
	}
	
	public int calcularIntervalo(LocalTime horario, LocalTime otroHorario) {
		
		int sumaHoras = horario.getHour() + otroHorario.getHour();
		
		if (sumaHoras > 24) {
			
			return sumaHoras -24;
		}
		
		else return sumaHoras;
	}
	
	public void registrarUso(LocalDateTime horaEncendido, int horas) {
	
		this.encender(horaEncendido);
	
		LocalDateTime horaApagado = horaEncendido.plusHours(horas);
		
		this.apagar(horaApagado);
			
		horasDeUso += horaEncendido.until(horaApagado,ChronoUnit.HOURS);
		consumoTotal += horasDeUso * this.consumo;
		this.reiniciar();
	}
	
	public void reiniciar() {
		
		this.horaApagado = null;
		this.horaEncendido = null;
	}
	
	public void encender(LocalDateTime horaEncendido) {
		
		if (!encendido) {
			
			encendido = true;
		}
		
		this.horaEncendido = horaEncendido;
	}
	
	
	public void apagar(LocalDateTime horaApagado) {
		
		encendido = false;
		
		this.horaApagado = horaApagado;
		
	}
	
	public String getNombre() {
		
		return nombre;
	}

	public double getConsumoTotal() {
		
		return consumoTotal;
	}

	public boolean isEncendido() {
		
		return encendido;
	}
	
}
