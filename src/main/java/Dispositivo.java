package tpDisenioGrupo6;

public class Dispositivo {
	
	
	
	
	boolean encendido;
	String nombre;
	int consumo;
	
	
	
	
	Dispositivo (boolean encendido,String nombre, int consumo) {
		
		this.encendido= encendido;
		this.nombre=nombre;
		this.consumo=consumo;
		
	}
	
	String nombre () {
		
		return nombre;
	}
	
	int consumo() {
		
		return consumo;
	}
	
	
	

}
