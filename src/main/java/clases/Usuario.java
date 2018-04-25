package clases;

import java.time.LocalDate;

public class Usuario 
{
	protected String nombre;
	protected String apellido;
	protected String nombreUsuario;
	protected String contrasenia;
	protected LocalDate fechaAlta;
	protected String domicilio;
	protected int id;

	public String nombre()
	{
		return nombre;
	}

	public LocalDate fechaAlta()
	{
		return fechaAlta;
	}
}
