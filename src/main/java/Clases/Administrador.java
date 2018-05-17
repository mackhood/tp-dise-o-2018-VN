package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Administrador {
	
	String nombreCompleto;
	String apellido;
	Domicilio domicilio;
	LocalDate fechaAlta;
	long numId;
	String username;
	String password;
	
	public Administrador(String nombreCompleto, String unApellido, LocalDate fecha) {
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
	
	public long cantMesesComoAdmin() {
	
		LocalDate ahora = LocalDate.now();
		
		return fechaAlta.until(ahora, ChronoUnit.MONTHS);
	}
	
}
