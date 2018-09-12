package dominio.zonageografica;

import dominio.entities.TransformadorNullException;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Entity
public class ZonaGeografica {

	@GeneratedValue
	@Id
	private Long id;


	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	protected List<Transformador> transformadores = new ArrayList<>();
	@Column(length=150)
	private String descripcion;
	private Double radio;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Ubicacion ubicacion;

	public ZonaGeografica(String descripcion, List<Transformador> transformadores,Ubicacion ubicacion,Double radio) {
		this.transformadores = transformadores;
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.radio = radio;
	}

	public double consumoTotal() {
		return transformadores.stream().mapToDouble(transformador -> transformador.energiaConsumidaClientes()).sum();
	}

	public Transformador devolverTransformadorCercano(Ubicacion ubicacionCliente) {

		Optional<Transformador> transformadorCercanano = Optional.of( transformadores.stream().min(Comparator.comparingDouble(t -> t.calcularDistancia(ubicacionCliente))).get());
		if (transformadorCercanano.isPresent())
			throw new TransformadorNullException("La zona no tiene ningun Transformador");

		return transformadorCercanano.get();

	}

	public boolean hayAlgunTransformador() {

		return !this.transformadores.isEmpty();
	}

	public double consumoTotalZona() {

		return transformadores.stream().mapToDouble(transformador -> transformador.suministroActual()).sum();
	}



	public List<Transformador> getTransformadores() {
		return transformadores;

	}

	public Transformador conectarATransformadorCercano(Cliente cliente) {
		Transformador transformadorCercano = this.devolverTransformadorCercano(cliente.getPosicion());
		transformadorCercano.agregarCliente(cliente);
		return  transformadorCercano;

	}

	public boolean perteneceClienteAZona(Cliente cliente) {
		return this.distanciaACliente(cliente.getPosicion()) < radio;
	}
	public Double distanciaACliente(Ubicacion ubicacion){
		return ubicacion.calcularDistancia(ubicacion);
	}

}
