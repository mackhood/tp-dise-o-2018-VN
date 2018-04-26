package domain;

public class Dispositivo {

	String nombre;
	int consumo;
	boolean encendido = false;
	int consumoTotal = 0;
	
	
	Dispositivo(String nombre,int consumo,boolean encendido) {
		
		this.nombre = nombre;
		this.consumo = consumo;
		this.encendido = encendido;
	}
	
	public void usarDispositivo(int horas) {
		
		consumoTotal += this.consumo * horas;
	}
	
	public void apagar() {
		
		encendido = false;
	}
	
	public String getNombre() {
		
		return nombre;
	}

	public int getConsumoTotal() {
		
		return consumoTotal;
	}

	public boolean isEncendido() {
		
		return encendido;
	}
	
}
