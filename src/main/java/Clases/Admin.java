package Clases;

import java.time.LocalDate;

public class Admin {
	
	String nombreCompleto;
	String apellido;
	Domicilio domicilio;
	LocalDate fechaAlta;
	long numId;
	String username;
	String pw;
	
	public Admin(String nombreCompleto, String unApellido, LocalDate fecha) {
		
		this.nombreCompleto = nombreCompleto;
		this.apellido = unApellido;
		this.fechaAlta = fecha;
	}
	
	public void setDomicilio(Domicilio domicilio) {
		
		this.domicilio = domicilio;
	}

	public LocalDate fechaActual() {
		
		return LocalDate.now();
	}
	
	public LocalDate fechaAlta() {
		
		return fechaAlta;
	}
	
	public int cantMesesComoAdmin() {
	
		int difAnios;
		int difMeses;
		LocalDate ahora = LocalDate.now();
		
		if (ahora.isEqual(fechaAlta)) {
			
			return 0;
		} 
		
		//Admin admin1 = new Admin("AAAAA","BBBBBB",LocalDate.of(2016, 5, 18));
		//Admin admin2 = new Admin("XXXX","ZZZZ",LocalDate.of(2014, 9, 20));
		// (2012,1,30)
		// (2012,1,1)
		else if(ahora.getMonthValue() > fechaAlta.getMonthValue()){
			
			difAnios = ahora.getYear() - fechaAlta.getYear();
			difMeses = ahora.getMonthValue() - fechaAlta.getMonthValue() ; 
			return 12* difAnios - difMeses; 
		}
		
		else {
			
			difAnios = ahora.getYear() - fechaAlta.getYear();
			difMeses = ahora.getMonthValue() - fechaAlta.getMonthValue();
			return 12* difAnios + difMeses;
		}
		
	}
	
}
