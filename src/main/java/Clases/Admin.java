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
	
	public LocalDate fechaAlta()
	{
		return fechaAlta;
	}
	/*
	public int diferenciaEnAnios() {
		
		if (this.fechaActual().getMonthValue() < fechaAlta.getMonthValue()) {
			
			return this.fechaActual().getYear() - fechaAlta.getYear() -1;
		}
		
		else return this.fechaActual().getMonthValue() - fechaAlta.getMonthValue();
	}
	*/
	public int cantMesesComoAdmin() {
		
		//return this.diferenciaEnAnios()*12 + Math.abs(this.fechaActual().getMonthValue() - fechaAlta.getMonthValue());
		// FECHAALTA 2018-04-18
		// FECHANOW 2020-01-25
		// FECHANOW 2020-08-25
		int difAnios;
		int difMeses;
		LocalDate ahora = LocalDate.now();
		if (ahora.isEqual(fechaAlta)) 
		{
			return 0;
		} 
		else if (fechaAlta.getDayOfMonth() > ahora.getDayOfMonth()) 
		{
			difAnios = ahora.getYear() - fechaAlta.getYear();
			difMeses = ahora.getMonthValue() - fechaAlta.getMonthValue();
			return 12 * difAnios - Math.abs(difMeses);
		}
		else 
		{
			difAnios = ahora.getYear() - fechaAlta.getYear();
			difMeses = ahora.getMonthValue() - fechaAlta.getMonthValue();
			return 12 * difAnios + Math.abs(difMeses);
		}
		
	}
	
}
