package dominio.transformador;

import java.util.ArrayList;
import java.util.List;

import dominio.dispositivo.Dispositivo;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;

import javax.persistence.*;


@Entity
public class Transformador {
	@GeneratedValue
	@Id
	private Long id;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Cliente> usuariosConectados = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Ubicacion ubicacion;

	protected float radioCubierto;

	public Transformador(List<Cliente> usuariosConectados, Ubicacion ubicacion, float radioCubierto) {

		this.ubicacion = ubicacion;
		this.usuariosConectados = usuariosConectados;
		this.radioCubierto = radioCubierto;
	}


	public double suministroActual() {
		return this.consumoDeDispositivosInteligentesClientes();
	}
	private double consumoDeDispositivosInteligentesClientes(){
		return usuariosConectados.stream().mapToDouble(cliente -> cliente.consumoDispositivosInteligentes()).sum();
	}

	public double energiaConsumidaClientes() {
		return usuariosConectados.stream().mapToDouble(usuario -> usuario.consumoEnergeticoTotal()).sum();
	}

	public Double calcularDistancia(Ubicacion ubicacionCliente) {
		return ubicacion.calcularDistancia(ubicacionCliente);
	}

	public void agregarCliente(Cliente cliente) {
		usuariosConectados.add(cliente);
	}
}
