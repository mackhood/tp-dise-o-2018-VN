package dominio.zonageografica;

import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ZonaGeografica {

	protected List<Transformador> transformadores = new ArrayList<>();
	private String descripcion;
	private Double radio;
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

		return transformadores.stream().min(Comparator.comparingDouble(t -> t.calcularDistancia(ubicacionCliente)))
				.get();

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
