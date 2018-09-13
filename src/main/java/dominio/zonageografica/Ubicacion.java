package dominio.zonageografica;

import dominio.usuario.Cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ubicacion {
	@GeneratedValue
	@Id
	private Long id;
	@Column(name = "POSICIONX", nullable = false, length = 20)
	private double posicionX;
	@Column(name = "POSICION7", nullable = false, length = 20)
	private double posicionY;

	public Ubicacion(double posicionX, double posicionY) {

		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	public Double calcularDistancia(Ubicacion ubicacionCliente) {

		double posicionXcliente = ubicacionCliente.getPosicionX();
		double posicionYcliente = ubicacionCliente.getPosicionX();
		return Math.hypot(posicionXcliente - posicionX, posicionYcliente - posicionY);

	}

	public double getPosicionX() {
		return posicionX;
	}

	public double getPosicionY() {
		return posicionY;
	}

}
