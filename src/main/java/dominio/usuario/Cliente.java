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
import dominio.zonageografica.AsignadorDeZonaService;
import dominio.zonageografica.Ubicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO)
	protected Long id;

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
	@OneToOne(fetch = FetchType.EAGER)
	private Categoria categoria;
	@Column(length = 150)
	private String username;
	@Column(length = 150)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<DispositivoEstandar> dispositivosEstandar = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

	@OneToOne(fetch = FetchType.EAGER)
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
		// recomendacion = new Recomendacion(this.todosLosDispositivos());
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
		// recomendacion = new Recomendacion(this.todosLosDispositivos());
	}

	public double puntosAcumulados() {
		return dispositivosInteligentes.stream().mapToDouble(inte -> inte.getPuntos()).sum()
				+ dispositivosEstandar.stream().mapToDouble(estandar -> estandar.getPuntos()).sum();
	}

	public void agregarModuloAdaptador(Conversor conversor, DispositivoEstandar disp)

			throws NoTieneDispositivoException {

		if (this.tieneDE(disp)) {
			conversor.convertirInteligente(this, disp);
		}

		else
			throw new NoTieneDispositivoException("No se encuentra en posesion del dispositivo que intenta adaptar");
	}

	public void conectarseATransformador(AsignadorDeZonaService asignadorDeZonaService) {
		transformador = asignadorDeZonaService.buscarTransformadorCercanoPara(this);
	}

	public boolean tieneDE(DispositivoEstandar disp) {

		return dispositivosEstandar.contains(disp);
	}

	public boolean algunDispositivoEncendido() {

		return dispositivosInteligentes.stream().anyMatch(disp -> disp.estaEncendido());
	}

	public long cantidadDeDIEncendidos() {

		return dispositivosInteligentes.stream().filter(disp -> disp.estaEncendido()).count();
	}

	public long cantidadDeDIApagados() {

		return dispositivosInteligentes.stream().filter(disp -> disp.estaApagado()).count();
	}

	public int cantidadDeDispositivos() {

		return getTodosLosDispositivos().size();
	}

	public void agregarDispositivoInteligente(DispositivoInteligente disp) {

		dispositivosInteligentes.add(disp);
	}

	public void agregarDispositivoEstandar(DispositivoEstandar disp) {

		dispositivosEstandar.add(disp);
	}

	public void usarDispositivo(DispositivoEstandar dispositivo, double cantHorasEstimada)

			throws NoTieneDispositivoException {

		if (this.tieneDE(dispositivo)) {

			dispositivo.serUsado(cantHorasEstimada);
		}

		else
			throw new NoTieneDispositivoException("No posee el dispositivo indicado");
	}

	public double consumoEnergeticoTotal() {

		return getTodosLosDispositivos().stream().mapToDouble(disp -> disp.getConsumoTotal()).sum();
	}

	public void activarAhorroAutomatico() {

		this.ahorroAutomatico = true;
	}

	public boolean estaEnModoAhorroAutomatico() {
		return ahorroAutomatico == true;
	}

	public double consumoDispositivosInteligentes() {
		return dispositivosInteligentes.stream()
				.mapToDouble(dispositivoInteligente -> dispositivoInteligente.getConsumoTotal()).sum();
	}

	/*
	 * public boolean esHogarEficiente() { return this.consumoEnergeticoTotal() <
	 * recomendacion.getResultadoDeLaFuncionEconomica(); }
	 */

	public DispositivoEstandar sacarDispositivoEstandarLista(DispositivoEstandar estandar) {

		dispositivosEstandar.remove(estandar);
		return estandar;
	}

	public double obtenerGastosAproximados() {

		return categoria.calcularCostosPara(this);
	}

	/*
	 * public void asignarHorasMaximasRecomendadasALosDispositivos() { recomendacion
	 * = new Recomendacion(this);
	 * recomendacion.asignarHorasMaximasRecomendadasACadaDispositivo(this); }
	 * 
	 * public double resultadoDeLaFuncionEconomica() {
	 * 
	 * return recomendacion.getResultadoDeLaFuncionEconomica(); }
	 * 
	 * public void realizarRecomendacionParaLosDispositivosInteligentes() {
	 * 
	 * this.getDispositivosInteligentes().stream().forEach(dispositivo -> {
	 * if(dispositivo.consumioMasDeLaRecomendacion()) {dispositivo.apagar();} }) };
	 * 
	 * public double[] horasMaximasDeConsumoPorDispositivo() {
	 * 
	 * return recomendacion.getHorasMaximaDeConsumoPorDispositivo(); }
	 */

	// GETTERS/SETTERS

	public String getNombreCategoria() {

		return categoria.getNombre();
	}

	public String getNombre() {

		return nombre;
	}

	public Ubicacion getPosicion() {
		return ubicacion;
	}

	public List<Dispositivo> getTodosLosDispositivos() {

		List<Dispositivo> todos = new ArrayList<>();
		todos.addAll(dispositivosInteligentes);
		todos.addAll(dispositivosEstandar);
		// todos.stream().forEach(dispositivo ->
		// System.out.println(dispositivo.getNombre()));
		return todos;
	}

	public void setTransformador(Transformador transformador) {
		this.transformador = transformador;
	}

	public Transformador getTransformador() {
		return transformador;
	}

	public void setCategoria(Categoria unaCategoria) {

		categoria = unaCategoria;
	}

	public Categoria getCategoria() {

		return categoria;
	}

	public List<DispositivoInteligente> getDispositivosInteligentes() {

		return dispositivosInteligentes;
	}

}
