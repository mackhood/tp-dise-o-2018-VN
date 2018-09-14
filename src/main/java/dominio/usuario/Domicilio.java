package dominio.usuario;

import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;

import javax.persistence.*;

@Embeddable
public class Domicilio {

	@Column(name = "Calle", length=100)
	public String calle;
	@Column(name = "Altura", length=100)
	public int altura;
	public int piso;
	public char departamento;


	public Domicilio(){}
	public Domicilio(String calle, int altura, int piso, char departamento) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
	}

}

