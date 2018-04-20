package clases;

import java.time.LocalDate;

public class Administrador extends Usuario {

	public Administrador(String unNombre, String unApellido, LocalDate fecha) 
	{
		nombre = unNombre;
		apellido = unApellido;
		fechaAlta = fecha;
	}

	public int cantMesesComoAdmin() 
	{
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
