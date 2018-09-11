package dominio.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import dominio.categoria.Categoria;
import dominio.dispositivo.Conversor;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.entities.NoTieneDispositivoException;
import dominio.simplexservice.Recomendacion;
import dominio.transformador.Transformador;
import dominio.zonageografica.Ubicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "CLIENTE")

public class Cliente {
	Recomendacion recomendacion;

	@GeneratedValue
	@Id
	private Long id;

	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;
	@Column(length = 50)
	private String apellido;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

	private ID identificacion;
	private long telefono;

	@ManyToOne(fetch = FetchType.EAGER)
	private Domicilio domicilio;

	private LocalDate fechaDeAlta;
	private Categoria categoria;
	@Column(length = 150)
	private String username;
	@Column(length = 150)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<DispositivoEstandar> dispositivosEstandar = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	private Ubicacion ubicacion;
	@ManyToOne(fetch = FetchType.LAZY)
	private Transformador transformador;

	private boolean ahorroAutomatico = false;

	public Cliente(String unNombre, String unApellido, String username, ID id, Domicilio unDomicilio, long unTelefono,
			List<DispositivoEstandar> estandares, List<DispositivoInteligente> inteligentes) {

		this.nombre = unNombre;
		this.apellido = unApellido;
		this.identificacion = id;
		this.username = username;
		this.domicilio = unDomicilio;
		this.telefono = unTelefono;
		this.dispositivosEstandar = estandares;
		this.dispositivosInteligentes = inteligentes;
		//recomendacion = new Recomendacion(this.todosLosDispositivos());
		this.fechaDeAlta = LocalDate.now();
	}

	public Cliente(String unNombre, String unApellido, String username, ID id, Domicilio unDomicilio, long unTelefono,
			Ubicacion ubicacion) {

		this.nombre = unNombre;
		this.apellido = unApellido;
		this.identificacion = id;
		this.username = username;
		this.domicilio = unDomicilio;
		this.telefono = unTelefono;
		// this.dispositivosEstandar = dispEstandar;
		// this.dispositivosInteligentes = dispInteligentes;
		this.fechaDeAlta = LocalDate.now();
		this.ubicacion = ubicacion;
		//recomendacion = new Recomendacion(this.todosLosDispositivos());
	}

	public double puntosAcumulados() {
		return dispositivosInteligentes.stream().mapToDouble(inte -> inte.getPuntos()).sum()
				+ dispositivosEstandar.stream().mapToDouble(estandar -> estandar.getPuntos()).sum();
	}

	public List<Dispositivo> todosLosDispositivos() {

		List<Dispositivo> todos = new ArrayList<>();
		todos.addAll(dispositivosInteligentes);
		todos.addAll(dispositivosEstandar);
		//todos.stream().forEach(dispositivo -> System.out.println(dispositivo.getNombre()));
		return todos;
	}

	public void agregarModuloAdaptador(Conversor conversor, DispositivoEstandar disp)
			throws NoTieneDispositivoException {

		if (this.tieneDispositivo(disp)) {

			conversor.convertirInteligente(this, disp);
		}

		else

			throw new NoTieneDispositivoException("No se encuentra en posesion del dispositivo que intenta adaptar");
	}

	public boolean tieneDispositivo(DispositivoEstandar disp) {

		return dispositivosEstandar.contains(disp);
	}

	public boolean algunDispositivoEncendido() {

		return dispositivosInteligentes.stream().anyMatch(disp -> disp.estaEncendido());
	}

	public long cantidadDeDispositivosEncendidos() {

		return dispositivosInteligentes.stream().filter(disp -> disp.estaEncendido()).count();
	}

	public long cantidadDeDispositivosApagados() {

		return dispositivosInteligentes.stream().filter(disp -> disp.estaApagado()).count();
	}

	public int cantidadDeDispositivos() {

		return todosLosDispositivos().size();
	}

	public void agregarDispositivoInteligente(DispositivoInteligente disp) {

		dispositivosInteligentes.add(disp);
	}

	public void agregarDispositivoEstandar(DispositivoEstandar disp) {

		dispositivosEstandar.add(disp);
	}

	public void usarDispositivo(DispositivoEstandar dispositivo, int cantHorasEstimativa)
			throws NoTieneDispositivoException {

		if (this.tieneDispositivo(dispositivo)) {

			dispositivo.serUsado(cantHorasEstimativa);
		}

		else
			throw new NoTieneDispositivoException("No posee el dispositivo indicado");
	}

	public double consumoEnergeticoTotal() {

		return todosLosDispositivos().stream().mapToDouble(disp -> disp.getConsumoTotal()).sum();
	}

	public double obtenerGastosAproximados() {

		return categoria.calcularCostosPara(this);
	}

	public void setCategoria(Categoria unaCategoria) {

		categoria = unaCategoria;
	}

	public Categoria getCategoria() {

		return this.categoria;
	}

	public String nombreCategoria() {

		return this.categoria.getNombre();
	}

	public String nombre() {

		return this.nombre;
	}

	public List<DispositivoInteligente> getDispositivosInteligentes() {

		return dispositivosInteligentes;
	}

	public void activarAhorroAutomatico() {

		this.ahorroAutomatico = true;

	}
	public void realizarRecomendacionParaLosDispositivos()
	{
		recomendacion = new Recomendacion(this.todosLosDispositivos());
		recomendacion.asignarHorasMaximasRecomendadasACadaDispositivo(this);
	}

	public double resultadoDeLaFuncionEconomica() {

		return recomendacion.getResultadoDeLaFuncionEconomica();
	}

	public double[] horasMaximasDeConsumoPorDispositivo() {
		return recomendacion.getHorasMaximaDeConsumoPorDispositivo();
	}

	public boolean esHogarEficiente() {
		return this.consumoEnergeticoTotal() < recomendacion.getResultadoDeLaFuncionEconomica();
	}

	public Ubicacion getPosicion() {
		return ubicacion;
	}

	public DispositivoEstandar sacarDispositivoEstandarLista(DispositivoEstandar estandar) {

		dispositivosEstandar.remove(estandar);
		return estandar;
	}

	public void setTransformador(Transformador transformador) {
		this.transformador = transformador;
	}

	public Transformador getTransformador() {
		return transformador;
	}
}
