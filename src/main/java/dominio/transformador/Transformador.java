package dominio.transformador;

import java.util.ArrayList;
import java.util.List;

import dominio.dispositivo.Dispositivo;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;

import javax.persistence.*;

@Table(name = "Transformadores")
@Entity
public class Transformador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Cliente> usuariosConectados = new ArrayList<>();
	@OneToOne(fetch = FetchType.EAGER)
	private Ubicacion ubicacion;

	public Transformador(List<Cliente> usuariosConectados, Ubicacion ubicacion) {

		this.ubicacion = ubicacion;
		this.usuariosConectados = usuariosConectados;

	}

	public double suministroActual() {
		return this.consumoDeDispositivosInteligentesClientes();
	}

	private double consumoDeDispositivosInteligentesClientes() {
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
	
	public double consumoDeIntervalo(Intervalo intervalo) {
		
		return usuariosConectados.stream().mapToDouble(cliente -> cliente.consumoDeIntervalo(intervalo)).sum();
	}
	
	public List<Cliente> getUsuariosConectados() {

		return usuariosConectados;
	}
}
