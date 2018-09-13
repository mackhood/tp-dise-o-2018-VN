package dominio.usuario;

import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;

import javax.persistence.*;


@Embeddable
public class Domicilio {


	protected Long id;

	public String calle;
	public int altura;
	public int piso;
	public char departamento;


	public Domicilio(String calle, int altura, int piso, char departamento) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
	}

}
