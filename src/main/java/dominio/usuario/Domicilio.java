package dominio.usuario;

import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;

import javax.persistence.*;


@Entity
public class Domicilio {

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO)
	protected Long id;

	@Column(length=150)
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
