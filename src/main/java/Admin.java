package domain;

import java.time.LocalDate;

public class Admin {
	
	String nombreCompleto;
	Domicilio domicilio;
	LocalDate fechaAlta;
	long numId;
	String username;
	String pw;
	
	public Admin(String nombreCompleto) {
		
		this.nombreCompleto = nombreCompleto;
		this.fechaAlta = LocalDate.now();
	}
	
	public void setDomicilio(Domicilio domicilio) {
		
		this.domicilio = domicilio;
	}

	public LocalDate fechaActual() {
		
		return LocalDate.now();
	}
	
	public int diferenciaEnAnios() {
		
		if (this.fechaActual().getMonthValue() < fechaAlta.getMonthValue()) {
			
			return this.fechaActual().getYear() - fechaAlta.getYear() -1;
		}
		
		else return this.fechaActual().getMonthValue() - fechaAlta.getMonthValue();
	}
	
	public int mesesComoAdmin() {
		
		return this.diferenciaEnAnios()*12 + Math.abs(this.fechaActual().getMonthValue() - fechaAlta.getMonthValue());
		
	}
}
