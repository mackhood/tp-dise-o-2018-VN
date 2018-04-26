package Clases;

public class Dispositivo {

	String nombre;
	double consumo;
	boolean encendido = false;
	double consumoTotal = 0;
	
	
	public Dispositivo(String nombre,double consumo,boolean encendido) {
		
		this.nombre = nombre;
		this.consumo = consumo;
		this.encendido = encendido;
	}
	
	/*
	public void usarDispositivo(double horas) {
		
		consumoTotal += this.consumo * horas;
	}
	*/
	
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
