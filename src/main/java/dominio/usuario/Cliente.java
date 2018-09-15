package dominio.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import dominio.categoria.Categoria;
import dominio.dispositivo.Conversor;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.entities.NoTieneDispositivoException;
import dominio.transformador.Transformador;
import dominio.zonageografica.AsignadorDeZonaService;
import dominio.zonageografica.Ubicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {

	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="idCliente")
	protected Long id;

	@Column(name = "Nombre", nullable = false, length = 100)
	private String nombre;

	@Column(length = 50)
	private String apellido;

	@Embedded
	private ID identificacion;
	
	private long telefono;

	@Embedded
	private Domicilio domicilio;
	private LocalDate fechaDeAlta;
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	@Column(length = 150)
	private String username;
	@Column(length = 150)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "idCliente")
	//@JoinTable(joinColumns={@JoinColumn(name="Cliente_idCliente")}, inverseJoinColumns={@JoinColumn(name="dispositivosEstandar_idDispositivo")})
	@JoinColumn(name="idCliente")
	private List<DispositivoEstandar> dispositivosEstandar = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "idCliente")
	//@JoinTable(joinColumns={@JoinColumn(name="Cliente_idCliente")}, inverseJoinColumns={@JoinColumn(name="dispositivosInteligentes_idDispositivo")})
	@JoinColumn(name="idCliente")

	private List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Ubicacion ubicacion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Transformador transformador;

	private boolean ahorroAutomatico = false;

	public Cliente(){}
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
		// recomendacion = new RecomendacionParaHogarEficiente(this.todosLosDispositivos());
		this.fechaDeAlta = LocalDate.now();
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
	
	public double consumoDeIntervalo(Intervalo intervalo) {
		
		return dispositivosInteligentes.stream().mapToDouble(disp -> disp.consumoParaIntervalo(intervalo)).sum();
	}
	
	/*
	 * public void asignarHorasMaximasRecomendadasALosDispositivos() { recomendacion
	 * = new RecomendacionParaHogarEficiente(this);
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

	public Ubicacion getUbicacion() {
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

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Long getId() {
		return id;
	}
	public Dispositivo getDispositivoDeNombre(String nombreDisp)
	{
		return this.getTodosLosDispositivos().stream().filter(dispositivo -> nombreDisp.equals(dispositivo.getNombre())).collect(Collectors.toList()).get(0);
	}
}
